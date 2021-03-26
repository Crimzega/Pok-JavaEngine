package com.sulvic.pje.game;

import com.sulvic.pje.world.WorldTile;

public class Tilemap{
	
	private final int tilemapID;;
	private final short mainTile;
	
	public Tilemap(int id){ this(id, (short)0); }
	
	public Tilemap(int id, short tileId){
		tilemapID = id;
		mainTile = tileId;
	}
	
	public WorldTile getDefaultTile(){ return new WorldTile(mainTile); }
	
	public int getID(){ return tilemapID; }
	
}
