package com.sulvic.jpe.edit.ui;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sulvic.pje.game.map.GameMap;

public class MapPane extends JPanel{
	
	private GameMap currMap;
	private BufferedImage mapTileset, mapTileset1;
	private JLabel tempLabel;
	
	public MapPane(GameMap map){
		currMap = map;
		tempLabel = new JLabel(31 % 16 + "");
		add(tempLabel);
	}
	
	public GameMap getMap(){ return currMap; }
	
}
