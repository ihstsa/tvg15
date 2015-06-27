package com.ihstsa.tvg15;

import java.util.List;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class LabeledBar extends StandardGameObject {
	
	private String label;
	private RectangleShape fgr;
	private RectangleShape bgr;

	public LabeledBar(String label, Vector2f size, Color bg, Color fg){
		fgr = new RectangleShape(size);
		bgr = new RectangleShape(size);
		setFgcolor(fg);
		setBgcolor(bg);
		setFrac(0.5f);
	}
	
	public void setSize(Vector2f size){
		fgr.setSize(size);
		bgr.setSize(size);
	}
	
	public void setFrac(float frac){
		fgr.setScale(frac, 1);
	}
	
	@Override
	public void draw(RenderWindow window, Vector2f offset){
		bgr.setPosition(offset);
		fgr.setPosition(offset);
		window.draw(bgr);
		window.draw(fgr);
	}

	public void setFgcolor(Color fgcolor) {
		fgr.setFillColor(fgcolor);
	}

	public void setBgcolor(Color bgcolor) {
		bgr.setFillColor(bgcolor);
	}
}
