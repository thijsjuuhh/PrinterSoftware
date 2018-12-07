package com.thijsjuuhh.PrintSoftware.Graphics;

import com.thijsjuuhh.PrintSoftware.layout.Textures;

public class Text {

	private Sprite[] textSprites;
	private int size;

	public Text(String text, Font font, int size, int color) {
		this.size = size;
		textSprites = font.getText(text, size, color);
	}

	public void render(Render2D render, int x, int y) {

		for (int i = 0; i < textSprites.length; i++) {
			Sprite sprite = textSprites[i];
			if (sprite == null) {
				x += size / 16 * 15;
			} else {
				render.renderSprite(x, y, sprite);
				x += sprite.getRealWidth() + size / 16 * 3;
			}
		}
	}

	public Text(String text, int size, int color) {
		this(text, Textures.basic, size, color);
	}

}
