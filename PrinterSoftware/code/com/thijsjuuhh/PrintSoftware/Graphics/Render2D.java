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
		
		if(width < this.width)
			temp_width = width;
		else 
			temp_width = this.width;
		
		if(height < this.height)
			temp_height = height;
		else
			temp_height = this.height;

		int[] temp = new int[width*height];
		for(int y = 0; y < temp_height; y++)
			for(int x = 0; x < temp_width; x++)
				temp[x+y*width] = pixels[x+y*this.width];
		
		
		this.width = width;
		this.height = height;
		
		pixels = temp;
	}

	public void fillRect(int x, int y, int width, int height, int col) {
		for (int y0 = y; y0 < y + height; y0++)
			for (int x0 = x; x0 < x + width; x0++)
				pixels[x0 + y0 * width] = col;
	}
}
