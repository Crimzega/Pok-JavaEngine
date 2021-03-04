package com.sulvic.pje.game;

import java.util.List;

import com.sulvic.lib.Version;
import com.sulvic.pje.game.map.GameMap;
import com.sulvic.util.ContentBuilder;
import com.sulvic.util.SulvicMath;

@SuppressWarnings({"unused"})
public class GameCartridge{
	
	private final String theName;
	private final Version theVersion;
	private List<GameMap> mapList = ContentBuilder.newArrayList();
	private int startingMap;
	
	public GameCartridge(String name, Version version){
		theName = name;
		theVersion = version;
	}
	
	public void setStartingMap(int index){ startingMap = SulvicMath.clampInt(index, 0, mapList.size() - 1); }
	
}
