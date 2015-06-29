package com.ihstsa.tvg15;

import java.util.List;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class LabeledBar extends StandardGameObject {
	
	private String label;
	private RectangleShape fgr;
	private RectangleShape bgr;
	private Text t_label;

	public LabeledBar(String label, Vector2f size, Color bg, Color fg){
		fgr = new RectangleShape(size);
		bgr = new RectangleShape(size);
		t_label = new Text("The text", Game.font, 24);
		t_label.setOrigin(0, size.y * 0.4f);
		t_label.setColor(Color.RED);
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
		t_label.setPosition(offset);
		window.draw(bgr);
		window.draw(fgr);
		window.draw(t_label);
	}

	public void setFgcolor(Color fgcolor) {
		fgr.setFillColor(fgcolor);
	}

	public void setBgcolor(Color bgcolor) {
		bgr.setFillColor(bgcolor);
	}
}
