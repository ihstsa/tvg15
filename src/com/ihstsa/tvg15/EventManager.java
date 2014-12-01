package com.ihstsa.tvg15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

/**
 * Handles JSFML events in the game. Allows registry and removal of event handlers,
 * which are called in the main loop.
 * @author Paul
 *
 */
public class EventManager
{
	Map<Event.Type, Set<EventHandler>> handlers = new HashMap<Event.Type, Set<EventHandler>>();
	Game game;
	
	public EventManager(Game game)
	{
		this.game = game;
	}
	
	/**
	 * Register an {@link EventHandler}
	 * @param eventType The type of event to register the handler for. A value of null means to call it every frame.
	 * @param eventHandler The {@link EventHandler} to register.
	 * @return
	 */
	public EventHandler addHandler(Event.Type eventType, EventHandler eventHandler)
	{
		Set<EventHandler> hs = handlers.get(eventType);
		if(hs == null)
		{
			hs = new HashSet<EventHandler>();
			handlers.put(eventType, hs);
		}
		hs.add(eventHandler);
		return eventHandler;
	}
	
	/**
	 * Unregisters a handler.
	 * @param eventType The type of event to unregister the handler from.
	 * @param eventHandler The {@link EventHandler} to unregister.
	 */
	public void removeHandler(Event.Type eventType, EventHandler eventHandler)
	{
		Set<EventHandler> hs = handlers.get(eventType);
		if(hs == null) return;
		hs.remove(eventHandler);
	}
	
	/**
	 * Called from the game loop, handles all events from that frame
	 * @param window The {@link Window} to poll events from
	 */
	public void handle(Window window)
	{
		for(Event e : window.pollEvents())
		{
			this.handleEvent(e);
		}
		this.handleEvent(null);
		
	}
	
	/**
	 * Handle a specific event
	 * @param e The {@link Event} to handle
	 */
	private void handleEvent(Event e){
		Set<EventHandler> hs = handlers.get(e == null ? null : e.type);
		if(hs == null) return;
		for(EventHandler r : hs)
		{
			r.handle(game, e);
		}
	}
}
