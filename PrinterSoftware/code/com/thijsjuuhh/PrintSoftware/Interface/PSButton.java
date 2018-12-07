package com.thijsjuuhh.PrintSoftware.Interface;

import com.thijsjuuhh.PrintSoftware.Graphics.Render2D;
import com.thijsjuuhh.PrintSoftware.Graphics.Sprite;

public class PSButton extends PSInterface{

	public PSButton(int x, int y, int width, int height, Sprite...sprites) {
		super(x, y, width, height, sprites);
	
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Render2D render) {
		render.renderSprite(x, y, sprites[0]);
	}

}
