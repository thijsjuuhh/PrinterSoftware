package com.thijsjuuhh.PrintSoftware.Graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, boolean resizable, boolean undecorated, boolean visible,
			boolean full_screen) {
		setTitle(title);
		setMinimumSize(new Dimension(width, height));
		if (full_screen)
			setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setResizable(resizable);
		setLocationRelativeTo(null);
		setUndecorated(undecorated);
		setVisible(visible);
	}

	public Window(int width, int height, String title, boolean full_screen) {
		this(width, height, title, true, false, true, full_screen);
	}
}
