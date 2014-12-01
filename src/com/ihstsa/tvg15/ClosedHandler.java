package com.ihstsa.tvg15;

import org.jsfml.window.event.Event;

/**
 * An {@link EventHandler} that closes the window when it runs
 * @author Paul
 * 
 */
public class ClosedHandler implements EventHandler {
	@Override
	public void handle(Game game, Event event) {
		game.window.close();
	}
}
