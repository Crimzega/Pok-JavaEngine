package com.sulvic.pje.game.map;

public class Tile{
	
	private final short tileID;
	private EnumMovePerm movePerm;
	
	private Tile(){ this((short)0); }
	
	public Tile(short id){ tileID = id; }
	
	protected static Tile[] fillDefaults(GameMap map){
		Tile[] defaults = new Tile[map.theWidth * map.theHeight];
		for(int i = 0; i < defaults.length; i++) defaults[i] = new Tile().setPermission(EnumMovePerm.MP_0C);
		return defaults;
	}
	
	public EnumMovePerm getPermission(){ return movePerm; }
	
	public short getID(){ return tileID; }
	
	public Tile setPermission(EnumMovePerm perm){
		movePerm = perm;
		return this;
	}
	
}
