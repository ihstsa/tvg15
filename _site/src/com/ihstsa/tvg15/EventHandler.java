package com.ihstsa.tvg15;

import org.jsfml.window.event.Event;

/**
 * An event handler, to be used with {@link EventManager}
 * @author Paul
 *
 */
public interface EventHandler
{
	/**
	 * Called when the event fires
	 * @param game The active {@link Game}
	 * @param event The {@link Event} that fired
	 */
	public void handle(Game game, Event event);
}