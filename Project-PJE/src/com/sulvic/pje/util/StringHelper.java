package com.sulvic.pje.util;

public class StringHelper{
	
	public static boolean isEmpty(String str){ return str.isEmpty() || str == ""; }
	
	public static String reverse(String str){
		String result = "";
		for(char ch: str.toCharArray()) result = ch + result;
		return result;
	}
	
}
