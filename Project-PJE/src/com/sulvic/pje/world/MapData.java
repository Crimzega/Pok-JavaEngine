package com.sulvic.pje.world;

import com.sulvic.lib.DoubleValueBasic;
import com.sulvic.lib.DoubleValueSet;
import com.sulvic.pje.game.Tilemap;

@SuppressWarnings({"unchecked"})
public class MapData{
	
	private final int theHeight, theWidth;
	private DoubleValueSet<WorldTile, EnumMovePerm>[] theData;
	private DoubleValueSet<Tilemap, Tilemap> theTilemaps;
	
	public MapData(Tilemap main, Tilemap second){ this(1, 1, main, second); }
	
	public MapData(int width, int height, Tilemap main, Tilemap second){
		theWidth = width;
		theHeight = height;
		theData = new DoubleValueSet[width * height];
		theTilemaps = new DoubleValueBasic<Tilemap, Tilemap>(main, second);
		for(int y = 0; y < height; y++) for(int x = 0; x < width; x++) theData[x + y * width] = new DoubleValueBasic<WorldTile, EnumMovePerm>(main.getDefaultTile(), EnumMovePerm.PERM_0C);
	}
	
	private DoubleValueSet<WorldTile, EnumMovePerm> getData(int x, int y){ return theData[x + y * theWidth]; }
	
	public EnumMovePerm[][] getAllPermissions(){
		EnumMovePerm[][] result = new EnumMovePerm[theHeight][theWidth];
		for(int y = 0; y < theWidth; y++) for(int x = 0; x < theWidth; x++) result[y][x] = getData(x, y).getSecondValue();
		return result;
	}
	
	public EnumMovePerm[][] getPermissions(int x, int y, int width, int height){
		EnumMovePerm[][] result = new EnumMovePerm[height][width];
		for(int y1 = 0; y1 < height; y1++) for(int x1 = 0; x1 < width; x++) result[y][x] = getData(x + x1, y + y1).getSecondValue();
		return null;
	}
	
	public WorldTile[][] getAllTiles(){
		WorldTile[][] result = new WorldTile[theHeight][theWidth];
		for(int y = 0; y < theHeight; y++) for(int x = 0; x < theWidth; x++) result[y][x] = getData(x, y).getMainValue();
		return result;
	}
	
	public WorldTile[][] getTiles(int x, int y, int width, int height){
		WorldTile[][] result = new WorldTile[height][width];
		for(int y1 = 0; y1 < height; y1++) for(int x1 = 0; x1 < width; x1++) result[y][x] = getData(x + x1, y + y1).getMainValue();
		return result;
	}
	
	public EnumMovePerm getPermission(int x, int y){ return getData(x, y).getSecondValue(); }
	
	public int getWidth(){ return theWidth; }
	
	public int getHeight(){ return theHeight; }
	
	public Tilemap getMainTilemap(){ return theTilemaps.getMainValue(); }
	
	public Tilemap getSecondTilemap(){ return theTilemaps.getSecondValue(); }
	
	public void setPermission(int x, int y, EnumMovePerm permission){ getData(x, y).setSecondValue(permission); }
	
	public void setPermissions(int x, int y, int width, int height, EnumMovePerm[][] permissions){
		for(int y1 = 0; y1 < height; y1++) for(int x1 = 0; x1 < width; x1++) setPermission(x + x1, y + y1, permissions[y][x]);
	}
	
	public void setTile(int x, int y, WorldTile tile){ getData(x, y).setMainValue(tile); }
	
	public void setTiles(int x, int y, int width, int height, WorldTile[][] tiles){
		for(int y1 = 0; y1 < height; y1++) for(int x1 = 0; x1 < width; x1++) setTile(x + x1, y + y1, tiles[y][x]);
	}
	
	public WorldTile getTile(int x, int y){ return getData(x, y).getMainValue(); }
	
}
