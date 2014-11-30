package com.ihstsa.tvg15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsfml.window.Window;
import org.jsfml.window.event.Event;


public class EventManager
{
	Map<Event.Type, Set<EventHandler>> handlers = new HashMap<Event.Type, Set<EventHandler>>();
	Game game;
	
	public EventManager(Game game_)
	{
		game = game_;
	}
	
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
	
	public void removeHandler(Event.Type eventType, EventHandler eventHandler)
	{
		Set<EventHandler> hs = handlers.get(eventType);
		if(hs == null) return;
		hs.remove(eventHandler);
	}
	
	public void handle(Window window)
	{
		for(Event e : window.pollEvents())
		{
			this.handleEvent(e);
		}
		this.handleEvent(null);
		
	}
	private void handleEvent(Event e){
		Set<EventHandler> hs = handlers.get(e == null ? null : e.type);
		if(hs == null) return;
		for(EventHandler r : hs)
		{
			r.handle(game, e);
		}
	}
}
