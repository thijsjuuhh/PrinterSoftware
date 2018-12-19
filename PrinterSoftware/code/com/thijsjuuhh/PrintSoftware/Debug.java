package com.thijsjuuhh.PrintSoftware;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import com.thijsjuuhh.PrintSoftware.Graphics.Graphics;
import com.thijsjuuhh.PrintSoftware.Graphics.Window;
import com.thijsjuuhh.PrintSoftware.layout.Textures;

public class Debug implements Runnable {

	private Window window;
	private Graphics graphics;

	private BufferedImage img;
	private int[] pixels;
	private int width;
	private int height;

	private ArrayList<String> commands = new ArrayList<>();

	private boolean running = true;

	private int currentMessage = 0;
	private int maxMessages = 15;
	private int typeBarY = 100;

	public Debug() {
		window = new Window(800, 600, "Printer", false, false, true, true);

		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				running = false;
				window.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		window.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Random r = new Random();
				int code = r.nextInt(4);
				int ra = r.nextInt();
				System.out.println(code + ra + "Clicked?");
				Logger.log(code, ra + "Clicked?");
			}
		});

		window.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				currentMessage -= e.getWheelRotation();
				if (currentMessage < 0)
					currentMessage = 0;
				if (currentMessage >= commands.size() - maxMessages + 3)
					currentMessage = commands.size() < maxMessages ? 0 : commands.size() - maxMessages + 3;
			}
		});

		graphics = new Graphics(window);
		graphics.setBackgroundColor(0xcccccc);
		Thread thread = new Thread(this);
		thread.start();

		Logger.debug = this;
	}

	@Override
	public void run() {
		while (running) {
			render();
		}
	}

	long prevTime = 0;
	int frames = 0;

	private synchronized void render() {
		BufferStrategy bs = window.getBufferStrategy();
		if (bs == null) {
			window.createBufferStrategy(3);
			return;
		}

		frames++;

		long curTime = System.currentTimeMillis();
		if (curTime - prevTime >= 1000) {
			prevTime = curTime;
			System.out.println(frames);
			frames = 0;
		}

		if (window.getWidth() != width || window.getHeight() != height) {
			width = window.getWidth();
			height = window.getHeight();

			img = new BufferedImage(window.getWidth(), window.getHeight(), BufferedImage.TYPE_INT_RGB);
			pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

		}

		int x = 20;
		int y = window.getHeight() - typeBarY + ((window.getHeight() - 40) / maxMessages) * (currentMessage - 1);
		int color = 0;

		graphics.render();
		for (int i = commands.size() - 1; i >= 0; i--) {
			if (commands.get(i).charAt(0) == Character.forDigit(Logger.INFO, 10))
				color = 0;
			else if (commands.get(i).charAt(0) == Character.forDigit(Logger.WARNING, 10))
				color = 0xFFFFFF00;
			else if (commands.get(i).charAt(0) == Character.forDigit(Logger.ERROR, 10))
				color = 0xFFFF0000;
			else if (commands.get(i).charAt(0) == Character.forDigit(Logger.SUCCEED, 10))
				color = 0xFF00FF00;
			graphics.render2d.renderText(commands.get(i).substring(1), x, y, Textures.basic,
					(window.getHeight() - typeBarY - 20) / maxMessages, color);
			y -= (window.getHeight() - 40) / maxMessages;
		}

		graphics.render2d.fillRect(0, window.getHeight() - typeBarY, width, window.getHeight(),
				graphics.getBackgroundColor());

		int length = (pixels.length <= graphics.render2d.pixels.length) ? pixels.length
				: graphics.render2d.pixels.length;

		for (int i = 0; i < length; i++)
			pixels[i] = graphics.render2d.pixels[i];
		try {
			java.awt.Graphics g = bs.getDrawGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();
			bs.show();
		} catch (NullPointerException e) {
		}
	}

	public void addMessage(int type, String message) {
		commands.add(type + message);
	}

}
