package com.thijsjuuhh.PrintSoftware;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.thijsjuuhh.PrintSoftware.Graphics.Graphics;
import com.thijsjuuhh.PrintSoftware.Graphics.Window;

public class Main implements Runnable {

	private Window window;
	private Graphics graphics;

	private BufferedImage img;
	private int[] pixels;
	private int width;
	private int height;
	private long prevTime;
	private int frames;

	public Main() {
		window = new Window(800, 600, "Printer", true);

		graphics = new Graphics(window);

		Thread thread = new Thread(this);
		thread.start();
	}

	public static void main(String[] args) {
		new Debug();
		new Main();
		
		Logger.log(0, "Initializing");
		Logger.log(1, "not found!");
		Logger.log(2, "ERROR");
		Logger.log(3, "Succeeded!");
		
	}

	@Override
	public void run() {
		while (true) {
			render();
		}
	}

	
	
	private void render() {
		BufferStrategy bs = window.getBufferStrategy();
		if (bs == null) {
			window.createBufferStrategy(3);
			return;
		}
		frames++;
		long curTime = System.currentTimeMillis();
		if(curTime - prevTime >= 1000) {
			prevTime = curTime;
		//	System.out.println(frames + ": FPS");
			frames = 0;
		}
		if (window.getWidth() != width || window.getHeight() != height) {
			width = window.getWidth();
			height = window.getHeight();

			img = new BufferedImage(window.getWidth(), window.getHeight(), BufferedImage.TYPE_INT_RGB);
			pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

		}
		
		graphics.render();
		
		int length = (pixels.length <= graphics.render2d.pixels.length) ? pixels.length
				: graphics.render2d.pixels.length;

		for (int i = 0; i < length; i++)
			pixels[i] = graphics.render2d.pixels[i];

		java.awt.Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		bs.show();
	}

}
