package com.sulvic.pje.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.sulvic.io.Endian;
import com.sulvic.io.EndianInputStream;
import com.sulvic.io.EndianOutputStream;
import com.sulvic.io.SulvicIO;
import com.sulvic.pje.game.map.Event;
import com.sulvic.pje.game.map.GameMap;
import com.sulvic.pje.game.map.Tile;

public class MapFormatter{
	
	private static void writeEventsToFile(List<Event> evts, ZipOutputStream zipStream, EndianOutputStream coreStream) throws IOException{
		List<Event> npcs = new ArrayList<Event>(), warps = new ArrayList<Event>(), scripts = new ArrayList<Event>(), signs = new ArrayList<Event>();
		Event flyingPos = null;
		for(Event evt: evts){
			switch(evt.getType()){
				case NPC:
					npcs.add(evt);
				break;
				case WARP:
					warps.add(evt);
				break;
				case SCRIPT:
					scripts.add(evt);
				break;
				case SIGN:
					signs.add(evt);
				break;
				case FLYING_POS:
					flyingPos = evt;
				break;
				default:
				break;
			}
		}
		zipStream.putNextEntry(new ZipEntry("scripts/"));
		for(int i = 0; i < npcs.size(); i++){
			Event npc = npcs.get(i);
			zipStream.putNextEntry(new ZipEntry("scripts/npc_" + String.format("%02x", i)));
			coreStream.write(npc.getBytes());
			zipStream.closeEntry();
		}
		for(int i = 0; i < warps.size(); i++){
			Event warp = warps.get(i);
			zipStream.putNextEntry(new ZipEntry("scripts/warp_" + String.format("%02x", i)));
			coreStream.write(warp.getBytes());
			zipStream.closeEntry();
		}
		for(int i = 0; i < scripts.size(); i++){
			Event script = scripts.get(i);
			zipStream.putNextEntry(new ZipEntry("scripts/script_" + String.format("%02x", i)));
			coreStream.write(script.getBytes());
			zipStream.closeEntry();
		}
		for(int i = 0; i < signs.size(); i++){
			Event sign = signs.get(i);
			zipStream.putNextEntry(new ZipEntry("scripts/sign_" + String.format("%02x", i)));
			coreStream.write(sign.getBytes());
			zipStream.closeEntry();
		}
		zipStream.closeEntry();
		if(flyingPos != null){
			zipStream.putNextEntry(new ZipEntry("flyingPos"));
			coreStream.write(flyingPos.getBytes());
			zipStream.closeEntry();
		}
	}
	
	private static void writeHeaderEventsToFile(List<Event> evts, ZipOutputStream zipStream, EndianOutputStream coreStream){
		
	}
	
	public static void writeToFile(GameMap map, String pjmFile) throws IOException{
		ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(pjmFile + ".pjm"));
		zipStream.setMethod(ZipEntry.DEFLATED);
		zipStream.setLevel(6);
		EndianOutputStream coreStream = new EndianOutputStream(Endian.LITTLE, zipStream);
		zipStream.putNextEntry(new ZipEntry("header"));
		coreStream.writeUTF(map.getName());
		coreStream.writeInt(map.getWidth());
		coreStream.writeInt(map.getHeight());
		for(int tileset: map.getTilesets()) coreStream.writeInt(tileset);
		for(short border: map.getTileBorders()) coreStream.writeShort(border);
		zipStream.closeEntry();
		Tile[] tiles = map.getTiles();
		zipStream.putNextEntry(new ZipEntry("tiles"));
		for(Tile tile: tiles) coreStream.writeShort(tile.getID());
		zipStream.closeEntry();
		zipStream.putNextEntry(new ZipEntry("movement"));
		for(Tile tile: tiles) coreStream.writeByte(tile.getPermission().getValue());
		writeHeaderEventsToFile(map.getHeaderEvents(), zipStream, coreStream);
		writeEventsToFile(map.getEvents(), zipStream, coreStream);
		zipStream.closeEntry();
		SulvicIO.closeQuietly(coreStream, zipStream);
	}
	
	public static void writeToFile(Endian endianness, String mapPath, String mapName, String pjmName) throws IOException{
		writeToFile(GameMap.parseAdvanceMap(new EndianInputStream(endianness, new FileInputStream(mapPath)), mapName), pjmName);
	}
	
	public static void parseAdvanceMapFiles(Endian endianness, String advMapPath, String mapName, String pjmName) throws IOException{
		GameMap map = GameMap.parseAdvanceMap(new EndianInputStream(Endian.LITTLE, new FileInputStream(advMapPath + ".map")));
		if(map != null){
			map.setName(mapName);
			List<Event> events = Event.parseEvents(new EndianInputStream(Endian.LITTLE, new FileInputStream(advMapPath + ".evt")));
			for(Event evt: events) System.out.println(evt.getType());
			if(events != null) map.addEvents(events);
		}
		writeToFile(map, pjmName);
	}
	
}
