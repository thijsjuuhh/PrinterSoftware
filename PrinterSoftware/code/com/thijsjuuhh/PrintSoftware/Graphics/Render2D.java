package com.thijsjuuhh.PrintSoftware.Graphics;

public class Render2D {

	private int width, height;
	public int[] pixels;

	public Render2D(int width, int height) {
		setDimensions(width, height);
	}

	public void setDimensions(int width, int height) {
		int temp_width;
		int temp_height;

		if (width < this.width)
			temp_width = width;
		else
			temp_width = this.width;

		if (height < this.height)
			temp_height = height;
		else
			temp_height = this.height;

		int[] temp = new int[width * height];
		for (int y = 0; y < temp_height; y++)
			for (int x = 0; x < temp_width; x++)
				temp[x + y * width] = pixels[x + y * this.width];

		this.width = width;
		this.height = height;

		pixels = temp;
	}

	public void clearScreen(int col) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = col;
		}
	}
	
	public void renderText(String text, int x, int y, Font font, int size) {
		for(int index = 0; index < text.length(); index++) {
			Sprite toRender = font.getChar(text.charAt(index)).resize(size, size);
			renderSprite(x+index*toRender.getWidth()+index*font.spaceLength(size)*index, y, toRender);
		}
	}

	public void renderSprite(int x, int y, Sprite s) {
		for(int y0 = 0; y0 < s.getHeight(); y0++) {
			int yP = y0 + y;
			for(int x0 = 0; x0 < s.getWidth(); x0++) {
				int xP = x0 + x;
				int col = s.pixels[x0 + y0 *s.getWidth()];
				System.out.println(s.getTransparentColor() + " - " + 0xffff00ff);
				if(col != s.getTransparentColor())
					setPixel(xP, yP, col);
			}
		}
	}
	
	public void fillRect(int x, int y, int width, int height, int col) {
		for (int y0 = 0; y0 < height; y0++) {
			int yP = y0 + y;
			for (int x0 = 0; x0 < width; x0++) {
				int xP = x0 + x;
				setPixel(xP, yP, col);
			}
		}
	}

	private void setPixel(int x, int y, int col) {
		if(x >= 0 && x < width && y >= 0 && y < height)
			pixels[x + y*width] = col;
	}
}
