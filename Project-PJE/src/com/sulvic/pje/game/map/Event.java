package com.sulvic.pje.game.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sulvic.io.EndianInputStream;

public class Event{
	
	private final EventType theType;
	private byte[] evtBytes;
	
	public Event(EventType type){
		theType = type;
		evtBytes = new byte[type.byteCount];
	}
	
	public EventType getType(){ return theType; }
	
	public static List<Event> parseEvents(EndianInputStream stream) throws IOException{
		List<Event> events = new ArrayList<Event>();
		byte[] buffer = new byte[stream.available()], counts = new byte[4];
		stream.read(buffer);
		for(int i = 0; i < 4; i++) counts[i] = buffer[buffer.length - 4 + i];
		int byteIndex = 0;
		for(int i = 0; i < counts.length; i++) for(int j = 0; j < counts[i]; j++){
			Event evt = new Event(EventType.getTypeByIndex(i));
			evt.setBytes(Arrays.copyOfRange(buffer, byteIndex, byteIndex + evt.theType.byteCount));
			byteIndex += evt.theType.byteCount;
			events.add(evt);
		}
		return events;
	}
	
	public byte[] getBytes(){ return evtBytes; }
	
	public void setBytes(byte... bytes){ for(int i = 0; i < evtBytes.length && evtBytes.length <= bytes.length; i++) evtBytes[i] = bytes[i]; }
	
	public static enum EventType{
		
		NPC(24),
		SIGN(12),
		WARP(8),
		SCRIPT(16),
		FLYING_POS(6),
		UNKNOWN(0);
		
		int byteCount;
		
		EventType(int count){ byteCount = count; }
		
		public int getByteCount(){ return byteCount; }
		
		public static EventType getTypeByIndex(int index){
			switch(index){
				case 0:
					return NPC;
				case 1:
					return WARP;
				case 2:
					return SCRIPT;
				case 3:
					return SIGN;
				case 4:
					return FLYING_POS;
				default:
					return UNKNOWN;
			}
		}
		
	}
	
}
