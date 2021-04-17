package com.sulvic.jpe.edit;

import javax.swing.JFrame;

import com.sulvic.pje.lib.Cartridge;

@SuppressWarnings({"serial", "unused"})
public class EditWindow extends JFrame{
	
	private Cartridge editableCartridge;
	
	public EditWindow(Cartridge cartridge){ editableCartridge = cartridge; }
	
}
