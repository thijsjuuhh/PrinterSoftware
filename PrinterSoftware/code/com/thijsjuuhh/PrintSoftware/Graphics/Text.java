package com.thijsjuuhh.PrintSoftware.Graphics;

import com.thijsjuuhh.PrintSoftware.layout.Textures;

public class Text {
	
	private String text;
	private Sprite[] textSprites;
	private int size, color;
	private int x, y;
	
	public Text(String text, Font font, int size, int color) {
		this.text = text;
		this.size = size;
		this.color = color;
		textSprites = font.getText(text, size, color);
	}
	
	public void render(Render2D render) {
		for(int i = 0; i < text.length(); i++) {
			
		}
	}
	
	public Text(String text, int size, int color) {
		this(text, Textures.basic, size, color);
	}
	

}
