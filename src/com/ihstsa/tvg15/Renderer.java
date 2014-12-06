package com.ihstsa.tvg15;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

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
	public GUI gui;
	public Game game;
	public View mainView;
	public View guiView;
	
	public Renderer(Game game){
		this(game, new GUI(), new RootObject());
	}
	public Renderer(Game game, GUI gui, GameObject root){
		this.game = game;
		this.gui = gui;
		this.root = root;
		 
		ConstView defaultView = game.window.getDefaultView();
		mainView = new View(defaultView.getCenter(), defaultView.getSize());
		guiView = new View(defaultView.getCenter(), defaultView.getSize());
		
		game.manager.addHandler(Event.Type.RESIZED, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				Vector2f floatSize = new Vector2f(game.window.getSize());
				mainView.setSize(floatSize);
				guiView.setSize(floatSize);
				guiView.setCenter(Vector2f.div(floatSize, 2));
			}
		});
		
		game.manager.addHandler(Event.Type.MOUSE_BUTTON_PRESSED, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				MouseButtonEvent mbe = event.asMouseButtonEvent();
				Vector2f viewCoords = game.window.mapPixelToCoords(mbe.position, mainView);
				Vector2f gridOffset = game.grid.getAbsoluteOffset();
				Vector2f gridRelative = Vector2f.sub(viewCoords, gridOffset);
				Tile tile = game.grid.tileForPixel(new Vector2i((int)gridRelative.x, (int)gridRelative.y));
				if(tile != null){
					tile.circleShape.setOutlineColor(Color.BLACK);
				}
			}
		});
	}

	/**
	 * Renders the root object
	 */
	@Override
	public void handle(Game game, Event event) {
		game.window.setView(mainView);
		root.render(game.window);
		game.window.setView(guiView);
		gui.render(game.window);
	}

}
