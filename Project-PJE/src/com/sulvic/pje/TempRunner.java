package com.sulvic.pje;

import java.io.FileOutputStream;
import java.util.zip.Deflater;

import com.sulvic.io.EndianOutputStream;
import com.sulvic.io.SulvicIO;
import com.sulvic.pje.util.StringHelper;

public class TempRunner{
	
	private static final String DICT1 = "SulvicVariant_DEFAULT";
	
//	private static void deflateWithDict() throws Exception{
//		String inputString = "blahblahblahblahblah??";
//        byte[] input = inputString.getBytes("UTF-8");
//        byte[] dict = "blah".getBytes("UTF-8");
//
//        // Compress the bytes
//        byte[] output = new byte[100];
//        Deflater compresser = new Deflater();
//        compresser.setInput(input);
//        compresser.setDictionary(dict);
//        compresser.finish();
//        int compressedDataLength = compresser.deflate(output);
//
//        // Decompress the bytes
//        Inflater decompresser = new Inflater();
//        decompresser.setInput(output, 0, compressedDataLength);
//        byte[] result = new byte[100];
//        decompresser.inflate(result);
//        decompresser.setDictionary(dict);
//        int resultLength = decompresser.inflate(result);
//        decompresser.end();
//
//        // Decode the bytes into a String
//        String outputString = new String(result, 0, resultLength, "UTF-8");
//        System.out.println("Decompressed String: " + outputString);
//	}
	
	public static void main(String[] args) throws Exception{
		String input = StringHelper.reverse("An immense amount of extreme bullshit just waiting to happen.");
		byte[] inputData = input.getBytes("UTF-8"), dict = DICT1.getBytes("UTF-8");
		byte[] result = new byte[400];
		Deflater deflater = new Deflater();
		deflater.setInput(inputData);
		deflater.setDictionary(dict);
		deflater.finish();
		int newLength = deflater.deflate(result);
		EndianOutputStream stream = new EndianOutputStream(new FileOutputStream("custom_deflate.pcj"));
		stream.write(StringHelper.reverse("SPJE").getBytes("UTF-8"));
		stream.writeByte((byte)1);
		stream.write(result);
		SulvicIO.closeQuietly(stream);
		System.out.println(new String(result));
	}
	
}
