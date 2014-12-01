package com.ihstsa.tvg15;

import org.jsfml.window.event.Event;

public class Renderer implements EventHandler {
	
	public GameObject root;
	
	public Renderer(){
		this(new RootObject());
	}
	public Renderer(GameObject root){
		this.root = root;
	}

	@Override
	public void handle(Game game, Event event) {
		root.render(game.window);
	}

}
