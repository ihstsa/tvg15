package com.ihstsa.tvg15;

import org.jsfml.window.event.Event;

/**
 * An {@link EventHandler} that renders a {@link GameObject} when it is called.
 * Meant to be used with the null event type so it fires every frame.
 * @author Paul
 *
 */
public class Renderer implements EventHandler {
	
	/**
	 * The root {@link GameObject} to render
	 */
	public GameObject root;
	
	public Renderer(){
		this(new RootObject());
	}
	public Renderer(GameObject root){
		this.root = root;
	}

	/**
	 * Renders the root object
	 */
	@Override
	public void handle(Game game, Event event) {
		root.render(game.window);
	}

}
