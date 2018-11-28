package com.thijsjuuhh.PrintSoftware;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.thijsjuuhh.PrintSoftware.Graphics.Graphics;
import com.thijsjuuhh.PrintSoftware.Graphics.Window;

public class Main implements Runnable{

	private Window window;
	private Graphics graphics;
	
	private BufferedImage img;
	private int[] pixels;
	private int width;
	private int height;
	
	public Main() {
		window = new Window(1200, 600, "Printer", false);
		
		graphics = new Graphics(window);
		
		
		Thread thread = new Thread(this);
		thread.start();
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void run() {
		while(true) {
			render();
		}
	}

	private void render() {
		BufferStrategy bs = window.getBufferStrategy();
		if(bs == null) {
			window.createBufferStrategy(3);
			return;
		}
		
		if(window.getWidth() != width || window.getHeight() != height) {
			width = window.getWidth();
			height = window.getHeight();
			
			img = new BufferedImage(window.getWidth(), window.getHeight(), BufferedImage.TYPE_INT_RGB);
			pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
				
		}
		
 		graphics.render();
		
		if(pixels.length == graphics.render2d.pixels.length)
			for(int i = 0; i < pixels.length; i++)
				pixels[i] = graphics.render2d.pixels[i];
		
		java.awt.Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		bs.show();
	}

}
