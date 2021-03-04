package com.sulvic.pje.game.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sulvic.io.EndianInputStream;
import com.sulvic.pje.game.Palette;

@SuppressWarnings("unused")
public class GameMap{
	
	protected final int theHeight, theWidth;
	private List<Event> mapEvents, mapHeaderEvents;
	private int[] mapTilesets = {0, 1};
	private short[] tileBorders = {0, 0, 0, 0};
	private Tile[] mapTiles;
	private Palette thePalette;
	private String theName;
	
	public GameMap(){ this(1, 1); }
	
	public GameMap(int width, int height){
		theWidth = width;
		theHeight = height;
		mapTiles = Tile.fillDefaults(this);
		mapHeaderEvents = new ArrayList<Event>();
		mapEvents = new ArrayList<Event>();
	}
	
	public static GameMap parseAdvanceMap(EndianInputStream stream) throws IOException{
		GameMap map = new GameMap(stream.readInt(), stream.readInt()).setTileset(stream.readInt(), stream.readInt());
		byte[] unknown = new byte[]{stream.readByte(), stream.readByte(), stream.readByte(), stream.readByte()};
		short[] borders = new short[4];
		for(int i = 0; i < 4; i++) borders[i] = stream.readShort();
		map.setTileBorders(borders);
		Tile[] tiles = new Tile[map.theWidth * map.theHeight];
		for(int i = 0; i < tiles.length; i++){
			short temp = stream.readShort();
			short tileID = (short)(temp & 0x3FF);
			byte perm = (byte)((temp >> 8) - (tileID >> 8));
			int y = i / map.theWidth;
			int x = i % map.theWidth;
			map.setTile(x, y, new Tile(tileID).setPermission(EnumMovePerm.getPermission(perm)));
		}
		return map;
	}
	
	public static GameMap parseAdvanceMap(EndianInputStream stream, String mapName) throws IOException{
		GameMap map = parseAdvanceMap(stream);
		if(map != null){
			map.setName(mapName);
			return map;
		}
		else return (GameMap)null;
	}
	
	public EnumMovePerm[][] getPermissions(int x, int y, int w, int h){
		EnumMovePerm[][] perms = new EnumMovePerm[h][w];
		for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) perms[y_][x_] = getTile(x + x_, y + y_).getPermission();
		return perms;
	}
	
	public Tile[][] getTiles(int x, int y, int w, int h){
		Tile[][] tiles = new Tile[h][w];
		for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) tiles[y_][x_] = getTile(x + x_, y + y_);
		return tiles;
	}
	
	public EnumMovePerm[] getPermissions(){
		EnumMovePerm[] perms = new EnumMovePerm[mapTiles.length];
		for(int i = 0; i < mapTiles.length; i++) perms[i] = mapTiles[i].getPermission();
		return perms;
	}
	
	public int[] getTilesets(){ return mapTilesets; }
	
	public short[] getTileBorders(){ return tileBorders; }
	
	public Tile[] getTiles(){ return mapTiles; }
	
	public EnumMovePerm getPermission(int x, int y){ return getTile(x, y).getPermission(); }
	
	public GameMap setTileset(int tileset, int tileset1){
		mapTilesets = new int[]{tileset, tileset1};
		return this;
	}
	
	public GameMap setTileBorders(short[] borders){
		tileBorders = borders;
		return this;
	}
	
	public GameMap setTileBorders(short topLeft, short topRight, short bottomLeft, short bottomRight){ return setTileBorders(new short[]{topLeft, topRight, bottomLeft, bottomRight}); }
	
	public int getHeight(){ return theHeight; }
	
	public int getWidth(){ return theWidth; }
	
	public List<Event> getEvents(){ return mapEvents; }
	
	public List<Event> getHeaderEvents(){ return mapHeaderEvents; }
	
	public String getName(){ return theName; }
	
	public String getTilesetPath(){ return String.format("tileset/index_%02x.png", mapTilesets[0]); }
	
	public String getTileset2Path(){ return String.format("tileset/index_%02x.png", mapTilesets[1]); }
	
	public Tile getTile(int x, int y){ return mapTiles[x + y * theWidth]; }
	
	public void addEvent(Event evt){ mapEvents.add(evt); }
	
	public void addEvents(Collection<Event> evts){ mapEvents.addAll(evts); }
	
	public void addHeaderEvent(Event evt){ mapHeaderEvents.add(evt); }
	
	public void addHeaderEvents(Collection<Event> evts){ mapHeaderEvents.addAll(evts); }
	
	public void changeTile(int x, int y, short id){ setTile(x, y, new Tile(id).setPermission(getPermission(x, y))); }
	
	public void changeTiles(int x, int y, int w, int h, short id){ for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) changeTile(x + x_, y + y_, id); }
	
	public void removeEvent(Event evt){ mapEvents.remove(evt); }
	
	public void removeEvents(Collection<Event> evts){ mapEvents.removeAll(evts); }
	
	public void removeHeaderEvent(Event evt){ mapHeaderEvents.remove(evt); }
	
	public void removeHeaderEvents(Collection<Event> evts){ mapHeaderEvents.removeAll(evts); }
	
	public void setName(String name){ theName = name; }
	
	public void setPermission(int x, int y, EnumMovePerm perm){ getTile(x, y).setPermission(perm); }
	
	public void setPermissions(int x, int y, int w, int h, EnumMovePerm perm){ for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) setPermission(x + x_, y + y_, perm); }
	
	public void setPermissions(int x, int y, int w, int h, EnumMovePerm[][] perms){ for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) setPermission(x + x_, y + y_, perms[y_][x_]); }
	
	public void setTile(int x, int y, Tile tile){ mapTiles[x + y * theWidth] = tile; }
	
	public void setTiles(int x, int y, int w, int h, Tile[][] tiles){ for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) setTile(x + x_, y + y_, tiles[y_][x_]); }
	
}
