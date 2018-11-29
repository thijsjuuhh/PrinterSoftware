package com.thijsjuuhh.PrintSoftware.Graphics;

import java.util.ArrayList;

public class Graphics {

	private ArrayList<Render> toRender = new ArrayList<>();
	public Render2D render2d;
	private Window window;

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
		
		render2d.clearScreen(0x00ff00);
		render2d.fillRect(10, 10, 100, 50, 0xff00000);
		
		for (Render render : toRender)
			render.render(render2d);
	}

}
