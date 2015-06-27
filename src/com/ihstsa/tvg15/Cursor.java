package com.ihstsa.tvg15;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

/**
 * Gui, mostly for testing, as of now
 * @author Paul
 *
 */
public class Cursor
{
	
	private CircleShape cs;
	private float circleSize = 8;
	private float delta = 0.1f;
	boolean spin;
	private Game game;
	
	public Cursor(Game game)
	{
		this.game = game;
		cs = new CircleShape(8, 6);
		cs.setPosition(-100, -100);
		cs.setFillColor(Color.TRANSPARENT);
		cs.setOutlineColor(Color.BLACK);
		cs.setOutlineThickness(2);
		cs.setOrigin(8, 8);
		game.manager.addHandler(Event.Type.MOUSE_MOVED, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				cs.setPosition(new Vector2f(event.asMouseEvent().position));
			}
		});
	}
	
	public void render(RenderWindow window)
	{
		// TODO Auto-generated method stub
		cs.setRotation(cs.getRotation()+1.5f);
		if(spin){
			circleSize += delta;
			if(circleSize > 12 || circleSize < 8) delta = -delta;
		}else{
			if(circleSize > 8) circleSize -= 0.25f;
			if(circleSize < 8) circleSize = 8;
		}
		cs.setRadius(circleSize);
		cs.setOrigin(circleSize, circleSize);
		window.draw(cs);
	}

}
