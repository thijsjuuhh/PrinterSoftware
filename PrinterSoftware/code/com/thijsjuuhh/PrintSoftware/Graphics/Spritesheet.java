package com.thijsjuuhh.PrintSoftware.Graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Spritesheet {

	private String path;
	private int width, height;
	public int[] pixels;

	public Spritesheet(String path) {
		this.path = path;
		load();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		try {
			System.out.print("Trying to retrieve " + path + " ");
			BufferedImage img = ImageIO.read(Spritesheet.class.getResource(path));
			width = img.getWidth();
			height = img.getHeight();
			pixels = new int[width * height];
			img.getRGB(0, 0, width, height, pixels, 0, width);
			System.out.println("Succeeded!");
		} catch (Exception e) {
			System.err.println("FAILED!");
			e.printStackTrace();
		}
	}

}
