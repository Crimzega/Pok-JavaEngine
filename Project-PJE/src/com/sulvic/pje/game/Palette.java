package com.sulvic.pje.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;

import com.sulvic.io.EndianInputStream;

@SuppressWarnings("unused")
public class Palette{
	
	private static final List<Palette> PALETTES = new ArrayList<Palette>();
	private static final int TRANSPARENT_INDEX = 0;
	private int[] theColors;
	
	private Palette(){
		theColors = new int[16];
		PALETTES.add(this);
	}
	
	public static Palette getPaletteFromStream(EndianInputStream stream) throws IOException{
		Palette palette = new Palette();
		palette.fillColors(stream);
		return palette;
	}
	
	public static Palette getImagePalette(BufferedImage image){
		Palette result = new Palette();
		Map<Color, Integer> commonColors = new HashMap<Color, Integer>();
		for(int x = 0; x < image.getWidth(); x++) for(int y = 0; y < image.getHeight(); y++){
			Color color = new Color(image.getRGB(x, y));
			commonColors.put(color, commonColors.containsKey(color)?commonColors.get(color) + 1: 1);
		}
		List<Integer> colorCount = new ArrayList<Integer>();
		for(int value: commonColors.values()) colorCount.add(value);
		Collections.sort(colorCount, new Comparator<Integer>(){
			
			public int compare(Integer arg, Integer arg1){ return -arg.compareTo(arg1); }
			
		});
		int[] truePalette = new int[0];
		for(int value: colorCount) for(Map.Entry<Color,Integer> entry: commonColors.entrySet()) if(entry.getValue() == value){
			System.out.println(entry.getKey().getRGB() & 0xFFFFFF);
			int[] temp = new int[truePalette.length + 1];
			for(int i = 0; i < truePalette.length; i++) temp[i] = truePalette[i];
			temp[truePalette.length] = entry.getKey().getRGB() & 0xFFFFFF;
			truePalette = temp;
		}
		result.fillColors(truePalette);
		return result;
	}
	
	public static List<Palette> getPalettes(){ return PALETTES; }
	
	public static Palette getPalette(int index){ return PALETTES.get(index); }
	
	public int getColor(int index){ return theColors[index]; }
	
	public int getIndexOf(int rgb){
		for(int i = 0; i < theColors.length; i++) if(theColors[i] == rgb) return i;
		return -1;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder("Palette: [\n\t");
		for(int i = 0; i < theColors.length; i++){
			builder.append(i + 1).append(": ");
			builder.append(String.format("%06x", theColors[i] & 0xFFFFFF).toUpperCase());
			if(i != theColors.length - 1) builder.append(",\n\t");
		}
		builder.append("\n]");
		return builder.toString();
	}
	
	private void fillColors(EndianInputStream stream) throws IOException{
		for(int i = 0; i < theColors.length; i++) theColors[i] = ((stream.readByte() & 0xFF) << 16) + ((stream.readByte() & 0xFF) << 8) + (stream.readByte() & 0xFF);
	}
	
	public void fillColors(int... colors){
		if(colors.length < 16) throw new MissingFormatArgumentException("There are not enough colors to fill this palette. Current coount: " + colors.length);
		if(colors.length > 16) System.out.printf("There are too many colors added for this pallete.\n Color amount left: %d", colors.length % 16);
		for(int i = 0; i < theColors.length; i++) theColors[i] = colors[i];
	}
	
}
