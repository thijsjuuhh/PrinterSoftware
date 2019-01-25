package com.thijsjuuhh.PrintSoftware.Graphics;

import java.util.HashMap;

public class Font {

	private Spritesheet sheet;
	private int rowlength, columnlength;
	private Sprite[] characters;
	private int spacelength = 8;
	private String order = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{*}~ ";

	private HashMap<String, Sprite[]> memory = new HashMap<>();
	
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
		if (character == ' ')
			return null;
		for (int index = 0; index < order.length(); index++)
			if (order.charAt(index) == character)
				return characters[index];
		return characters[characters.length - 1];
	}

	
	public Sprite[] getText(String text, int size, int color) {
		if(memory.containsKey(text))
			return memory.get(text);
		Sprite[] result = new Sprite[text.length()];
		for (int i = 0; i < text.length(); i++) {
			Sprite sprite = getChar(text.charAt(i));
			if (sprite != null)
				sprite = sprite.resize(size, size).replaceColor(0xffffffff, color);
			result[i] = sprite;
		}
		memory.put(text, result);
		return result;
	}

	public int spaceLength(int size) {
		return size / 16 * spacelength;
	}

}
