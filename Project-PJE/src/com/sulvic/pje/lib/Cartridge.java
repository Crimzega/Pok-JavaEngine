package com.sulvic.pje.lib;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sulvic.io.Endian;
import com.sulvic.pje.exceptions.GameEngineException;
import com.sulvic.pje.game.GameMap;
import com.sulvic.util.ContentBuilder;

public class Cartridge{
	
	private static final String TYPICAL_FORMAT = "SPJE";
	
	private final GameFormat theFormat;
	private Map<Integer, List<GameMap>> loadableMaps;
	
	public Cartridge(GameFormat format){
		if(format == GameFormat.UNKNOWN) throw new GameEngineException("The game format is unknown.");
		theFormat = format;
		loadableMaps = ContentBuilder.newHashMap();
	}
	
	public Cartridge(GameFormat format, int maxBank){
		this(format);
		populateBanks(maxBank);
	}
	
	public static String getFormat(Cartridge cartridge){
		String result = TYPICAL_FORMAT;
		switch(cartridge.theFormat){
			case DEFAULT:
			break;
			case INVERSE:
				String temp = "";
				for(char ch: result.toCharArray()) temp = ch + temp;
			break;
			default:
			break;
		}
		return result;
	}
	
	private void detectBank(int bank){ if(!loadableMaps.containsKey(bank)) loadableMaps.put(bank, ContentBuilder.newArrayList()); }
	
	private void populateBanks(int max){ for(int i = 0; i < max; i++) loadableMaps.put(i, ContentBuilder.newArrayList()); }
	
	private List<GameMap> getBank(int bank){
		detectBank(bank);
		return loadableMaps.get(bank);
	}
	
	public Map<Integer, List<GameMap>> getMaps(){ return loadableMaps; }
	
	public void addMapToBank(int bank, GameMap map){ getBank(bank).add(map); }
	
	public void addMapsToBank(int bank, GameMap... maps){ addMapsToBank(bank, Arrays.asList(maps)); }
	
	public void addMapsToBank(int bank, Collection<GameMap> maps){ getBank(bank).addAll(maps); }
	
	public GameFormat getFormat(){ return theFormat; }
	
	public static enum GameFormat{
		
		DEFAULT("SulvicVariant_DEFAULT"),
		INVERSE("SulvicVariant_INVERSE"),
		UNKNOWN;
		
		String formatDictionary;
		
		GameFormat(){}
		
		GameFormat(String dict){ formatDictionary = dict; }
		
		public static GameFormat getFormat(String fmt){
			switch(fmt){
				case "SPJE":
					return DEFAULT;
				case "EJPS":
					return INVERSE;
				default:
					return UNKNOWN;
			}
		}
		
		public Endian getEndian(){
			switch(this){
				case DEFAULT:
					return Endian.LITTLE;
				case INVERSE:
					return Endian.BIG;
				default:
					return null;
			}
		}
		
		public byte[] getDictionary() throws UnsupportedEncodingException{ return formatDictionary.getBytes("UTF-8"); }
		
	}
	
}
