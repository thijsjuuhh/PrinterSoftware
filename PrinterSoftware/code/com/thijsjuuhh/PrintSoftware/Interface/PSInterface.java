package com.thijsjuuhh.PrintSoftware.Interface;

import com.thijsjuuhh.PrintSoftware.Graphics.Render2D;
import com.thijsjuuhh.PrintSoftware.Graphics.Sprite;

public abstract class PSInterface {

	protected int x, y, width, height;
	protected Sprite[] sprites;

	public PSInterface(int x, int y, int width, int height, Sprite...sprites) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprites = sprites;
	}
	
	public abstract void update();
	
	public abstract void render(Render2D render);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Sprite[] getSprites() {
		return sprites;
	}
	
}
