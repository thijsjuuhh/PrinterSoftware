package com.thijsjuuhh.PrintSoftware.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {

	private Spritesheet sheet;

	private int width, height, x, y;
	private int real_width, real_height;
	public int[] pixels;

	private int transparent_color;

	public Sprite(Spritesheet sheet, int x, int y, int width, int height, int transparent_color) {
		this.sheet = sheet;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.transparent_color = transparent_color;

		pixels = new int[width * height];
		load();

		calculateRealSize();
	}

	public int getTransparentColor() {
		return transparent_color;
	}

	public Sprite(String path) {
		sheet = new Spritesheet(path);
		x = 0;
		y = 0;
		width = sheet.getWidth();
		height = sheet.getHeight();
		pixels = new int[width * height];
		load();

		calculateRealSize();
	}

	private Sprite(Sprite sprite, int width, int height) {
		this.width = width;
		this.height = height;
		this.transparent_color = sprite.transparent_color;
		BufferedImage img = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_RGB);
		img.setRGB(0, 0, sprite.getWidth(), sprite.getHeight(), sprite.pixels, 0, sprite.getWidth());
		Image temp = img.getScaledInstance(width, height, BufferedImage.SCALE_DEFAULT);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.drawImage(temp, 0, 0, null);
		g.dispose();

		pixels = new int[width * height];
		load(img);

		calculateRealSize();
	}

	public Sprite resize(int new_width, int new_height) {
		return new Sprite(this, new_width, new_height);
	}

	private void calculateRealSize() {
		int top = -1;
		int bottom = -1;
		int left = -1;
		int right = -1;

		for (int y = 0; y < height && top == -1; y++) {
			for (int x = 0; x < width; x++) {
				if (pixels[x + y * width] != transparent_color)
					top = y;
			}
		}

		for (int y = height - 1; y >= 0 && bottom == -1; y--) {
			for (int x = 0; x < width; x++) {
				if (pixels[x + y * width] != transparent_color)
					bottom = y;
			}
		}

		
		for (int x = 0; x < width && left == -1; x++) {
			for (int y = 0; y < height; y++) {
				if (pixels[x + y * width] != transparent_color)
					left = x;
			}
		}

		for (int x = width - 1; x >= 0 && right == -1; x--) {
			for (int y = 0; y < height; y++) {
				if (pixels[x + y * width] != transparent_color)
					right = y;
			}
		}
		
		real_width = right - left + 1;
		real_height = bottom - top + 1;

	}

	private void load(BufferedImage img) {
		img.getRGB(0, 0, width, height, pixels, 0, width);
	}

	private void load() {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getWidth()];

	}

	public Sprite replaceColor(int colToChange, int colToChangeTo) {
		if (colToChange != colToChangeTo)
			for (int pix = 0; pix < pixels.length; pix++)
				if (pixels[pix] == colToChange)
					pixels[pix] = colToChangeTo;
		return this;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRealWidth() {
		return real_width;
	}

	public int getRealHeight() {
		return real_height;
	}
}
