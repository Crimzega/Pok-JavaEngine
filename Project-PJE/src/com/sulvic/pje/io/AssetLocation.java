package com.sulvic.pje.io;

public class AssetLocation{
	
	private String gameAssetCore, gameAssetPath;
	
	private AssetLocation(String[] assetLoc){ this(assetLoc[0], assetLoc[1]); }
	
	public AssetLocation(String gameBase, String assetPath){
		gameAssetCore = gameBase;
		gameAssetPath = assetPath;
	}
	
	public AssetLocation(String assetLoc){ this(formatAssetLoc(assetLoc)); }
	
	private static String[] formatAssetLoc(String assetLoc){ return assetLoc.contains(":")? assetLoc.split(":"): new String[]{"core", assetLoc}; }
	
	public String getAssetCore(){ return gameAssetCore; }
	
	public String getAssetPath(){ return gameAssetPath; }
	
	public String toString(){ return gameAssetCore + ":" + gameAssetPath; }
	
}
