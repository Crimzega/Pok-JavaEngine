package com.sulvic.pje.lib;

import java.awt.Color;

public class Palette{
	
	private Color[] colors = new Color[16];
	
	public Palette(){ for(int i = 0; i < colors.length; i++) colors[i] = Color.black; }
	
	public Color getColor(int index){ return colors[index]; }
	
	public void setColor(int index, Color clr){ colors[index] = clr; }
	
	public Color[] getColors(){ return colors; }
	
}
