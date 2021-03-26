package com.sulvic.pje.world;

public class WorldTile{
	
	private final short tileID;
	
	public WorldTile(){ this((short)0); }
	
	public WorldTile(short id){ tileID = id; }
	
	public short getID(){ return tileID; }
	
}
