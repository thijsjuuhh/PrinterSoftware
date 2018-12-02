package com.thijsjuuhh.PrintSoftware.Graphics;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.thijsjuuhh.PrintSoftware.Interface.PSInterface;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private ArrayList<PSInterface> interfaces = new ArrayList<>();
	
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

	public boolean add(PSInterface instance) {
		return interfaces.add(instance);
	}

	public boolean remove(PSInterface instance) {
		return interfaces.remove(instance);
	}

	public Window(int width, int height, String title, boolean full_screen) {
		this(width, height, title, true, false, true, full_screen);
	}
	
	
	
	public void render(Render2D render) {
		for(PSInterface instance : interfaces)
			instance.render(render);
	}
	
	public void update() {
		for(PSInterface instance : interfaces)
			instance.update();
	}
}
