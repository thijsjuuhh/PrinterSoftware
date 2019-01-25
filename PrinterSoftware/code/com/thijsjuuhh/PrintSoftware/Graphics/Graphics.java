package com.thijsjuuhh.PrintSoftware.Graphics;

import java.util.ArrayList;

public class Graphics {

	private ArrayList<Render> toRender = new ArrayList<>();
	public Render2D render2d;
	private Window window;
	private int backgroundColor = 0xFFFFFF;

	private int width, height;

	public Graphics(Window window) {
		this.window = window;
		width = window.getWidth();
		height = window.getHeight();
		render2d = new Render2D(width, height);
	}

	public void render() {	
		if (width != window.getWidth() || height != window.getHeight())
			render2d.setDimensions(width = window.getWidth(), height = window.getHeight());
		
		
		render2d.clearScreen(backgroundColor);
		
		for (Render render : toRender)
			render.render(render2d);
	}

	
	public int getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
