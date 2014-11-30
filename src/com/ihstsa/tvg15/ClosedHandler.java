package com.ihstsa.tvg15;

import org.jsfml.graphics.Color;
import org.jsfml.window.event.Event;

public class ClosedHandler implements EventHandler {
	@Override
	public void handle(Game game, Event event) {
		game.window.close();
	}
}
