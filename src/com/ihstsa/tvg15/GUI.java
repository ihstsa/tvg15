package com.ihstsa.tvg15;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;

public class GUI {
	
	private CircleShape cs;
	public GUI(){
		cs = new CircleShape(50, 10);
		cs.setPosition(100, 100);
		cs.setFillColor(Color.TRANSPARENT);
		cs.setOutlineColor(Color.BLACK);
		cs.setOutlineThickness(3);
		cs.setOrigin(50, 50);
	}

	Game game;
	public void render(RenderWindow window) {
		// TODO Auto-generated method stub
		cs.setRotation(cs.getRotation()+1);
		window.draw(cs);
	}

}
