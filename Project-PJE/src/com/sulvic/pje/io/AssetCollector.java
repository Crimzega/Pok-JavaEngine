package com.sulvic.pje.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sulvic.io.EndianInputStream;
import com.sulvic.io.EndianOutputStream;
import com.sulvic.pje.game.Palette;

public class AssetCollector{
	
	private static BufferedImage changeColorUsingPalettes(BufferedImage image, Palette basePalette, Palette chosenPalette){
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < image.getHeight(); y++) for(int x = 0; x < image.getWidth(); x++);
		return newImage;
	}
	
	private static String formatAssetToPath(AssetLocation assetLoc){ return String.format("res/assets/%s", assetLoc).replace(':', '/'); }
	
	public static File getFilePathFromAsset(AssetLocation assetLoc){ return new File(formatAssetToPath(assetLoc)); }
	
	public static BufferedImage getColoredTileset(AssetLocation assetLoc, Palette palette) throws IOException{
		File filePath = getFilePathFromAsset(assetLoc);
		BufferedImage imageBase = ImageIO.read(filePath);
		Palette basePalette = Palette.getImagePalette(imageBase);
		BufferedImage newImage = new BufferedImage(imageBase.getWidth(), imageBase.getHeight(), BufferedImage.TYPE_INT_RGB);
		return changeColorUsingPalettes(newImage, basePalette, palette);
	}
	
	public static EndianInputStream getInputStreamFromAsset(AssetLocation assetLoc) throws IOException{
		return new EndianInputStream(new FileInputStream(getFilePathFromAsset(assetLoc)));
	}
	
	public static EndianOutputStream getOutputStreamFromAsset(AssetLocation assetLoc) throws IOException{
		return new EndianOutputStream(new FileOutputStream(getFilePathFromAsset(assetLoc)));
	}
	
}
