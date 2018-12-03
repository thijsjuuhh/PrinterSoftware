package com.thijsjuuhh.PrintSoftware.Graphics;

public class Font {

	private Spritesheet sheet;
	private int rowlength, columnlength;
	private Sprite[] characters;
	private int spacelength = 8;
	private String order = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{*}~ ";

	public Font(String path, int spritesize) {
		sheet = new Spritesheet(path);
		rowlength = sheet.getWidth() / spritesize;
		columnlength = sheet.getHeight() / spritesize;

		characters = new Sprite[rowlength * columnlength];

		for (int y = 0; y < columnlength; y++)
			for (int x = 0; x < rowlength; x++)
				characters[x + y * rowlength] = new Sprite(sheet, x * spritesize, y * spritesize, spritesize,
						spritesize, 0xffff00ff);
	}

	public Sprite getChar(char character) {
		for (int index = 0; index < order.length(); index++)
			if (order.charAt(index) == character)
				return characters[index];
		return characters[characters.length - 1];
	}

	public int spaceLength(int size) {
		return size / 16 * spacelength;
	}

}
