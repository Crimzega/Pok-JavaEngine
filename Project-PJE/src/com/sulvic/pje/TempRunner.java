package com.sulvic.pje;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.sulvic.io.Endian;
import com.sulvic.io.EndianInputStream;
import com.sulvic.io.SulvicIO;

public class TempRunner{
	
	public static void main(String[] args) throws Exception{
		EndianInputStream stream = new EndianInputStream(Endian.LITTLE, new FileInputStream("Pokemon - Alcuris.gba"));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("results.txt")));
		stream.skipBytes(0x15FD60);
		int nonZero, count = 0;
		while((nonZero = stream.readInt()) != 0){
			writer.append(String.format("%03X: #8%06X", count, nonZero & 0xFFFFFF)).append('\n');
			count++;
		}
		SulvicIO.closeQuietly(stream, writer);
	}
	
}
