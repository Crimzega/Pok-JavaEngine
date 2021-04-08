package com.sulvic.pje.game;

import com.sulvic.pje.world.EnumMovePerm;
import com.sulvic.pje.world.MapData;
import com.sulvic.pje.world.WorldTile;

public class GameMap{
	
	private final MapData theData;
	
	public GameMap(Tilemap main, Tilemap second){ this(1, 1, main, second); }
	
	public GameMap(int width, int height, Tilemap main, Tilemap second){ theData = new MapData(width, height, main, second); }
	
	public static GameMap resize(GameMap map, int width, int height){
		GameMap newMap = new GameMap(width, height, map.getMainTilemap(), map.getSecondTilemap());
		for(int y = 0; y < height; y++) for(int x = 0; x < width; x++){
			boolean flag = x > map.getWidth() - 1 && y > map.getHeight() - 1;
			newMap.setTile(x, y, flag? map.getTile(x, y): map.getMainTilemap().getDefaultTile());
			newMap.setPermission(x, y, flag? map.getPermission(x, y): EnumMovePerm.PERM_0C);
		}
		return newMap;
	}
	
	public EnumMovePerm[][] getAllPermissions(){ return theData.getAllPermissions(); }
	
	public EnumMovePerm[][] getPermissions(int x, int y, int width, int height){ return theData.getPermissions(x, y, width, height); }
	
	public WorldTile[][] getAllTiles(){ return theData.getAllTiles(); }
	
	public WorldTile[][] getTiles(int x, int y, int width, int height){ return theData.getTiles(x, y, width, height); }
	
	public EnumMovePerm getPermission(int x, int y){ return theData.getPermission(x, y); }
	
	public int getHeight(){ return theData.getHeight(); }
	
	public int getWidth(){ return theData.getWidth(); }
	
	public Tilemap getMainTilemap(){ return theData.getMainTilemap(); }
	
	public Tilemap getSecondTilemap(){ return theData.getSecondTilemap(); }
	
	public void setPermission(int x, int y, EnumMovePerm permission){ theData.setPermission(x, y, permission); }
	
	public void setPermissions(int x, int y, int width, int height, EnumMovePerm[][] permissions){ theData.setPermissions(x, y, width, height, permissions); }
	
	public void setTile(int x, int y, WorldTile tile){ theData.setTile(x, y, tile); }
	
	public void setTiles(int x, int y, int width, int height, WorldTile[][] tiles){ theData.setTiles(x, y, width, height, tiles); }
	
	public WorldTile getTile(int x, int y){ return theData.getTile(x, y); }
	
}
