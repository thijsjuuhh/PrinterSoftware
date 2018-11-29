package com.thijsjuuhh.PrintSoftware.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {

	private Spritesheet sheet;

	private int width, height, x, y;
	public int[] pixels;

	public Sprite(Spritesheet sheet, int x, int y, int width, int height) {
		this.sheet = sheet;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		if (x < 0 || x + width >= sheet.getWidth() || y < 0 || y + height >= sheet.getHeight())
			throw new ArrayIndexOutOfBoundsException("Width or height exceeded!");

		pixels = new int[width * height];
		load();
	}

	public Sprite(String path) {
		sheet = new Spritesheet(path);
		x = 0;
		y = 0;
		width = sheet.getWidth();
		height = sheet.getHeight();
		pixels = new int[width * height];
		load();
	}
	
	private Sprite(Sprite sprite, int width, int height) {
		this.width = width;
		this.height = height;
		BufferedImage img = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_RGB);
		img.setRGB(0, 0, sprite.getWidth(), sprite.getHeight(), sprite.pixels, 0, sprite.getWidth());
		Image temp = img.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.drawImage(temp, 0, 0, null);
		g.dispose();
		
		pixels = new int[width*height];
		load(img);
	}
	
	public Sprite resize(int new_width, int new_height) {
		return new Sprite(this, new_width, new_height);
	}
	
	private void load(BufferedImage img) {
		img.getRGB(0, 0, width, height, pixels, 0, width);
	}
	
	private void load() {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getWidth()];

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
