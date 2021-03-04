package com.sulvic.jpe.edit;

import javax.swing.JFrame;

import com.sulvic.pje.game.GameCartridge;

@SuppressWarnings({"serial", "unused"})
public class EditWindow extends JFrame{
	
	private GameCartridge editableCartridge;
	
	public EditWindow(GameCartridge cartridge){
		 editableCartridge = cartridge;
	}
	
}
