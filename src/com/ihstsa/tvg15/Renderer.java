package com.ihstsa.tvg15;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;
import org.jsfml.window.event.MouseEvent;
import org.jsfml.window.event.MouseWheelEvent;
import org.jsfml.window.Mouse;

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
	public Vector2i mousePosition = new Vector2i(500, 500);
	public static Vector2f offset = new Vector2f(10000, 10000);
	Tile currentTile;
	double zoom = 1;
	
	public Renderer(Game game){
		this(game, new GUI(game), new RootObject());
	}
	public Renderer(Game game, final GUI gui, GameObject root){
		this.game = game;
		this.gui = gui;
		this.root = root;
		 
		ConstView defaultView = game.window.getDefaultView();
		//mainView = new View(Vector2f.add(defaultView.getCenter(), lots), defaultView.getSize());
		mainView = new View(offset, Vector2f.ZERO);//new Vector2f(0, 0), new Vector2f(1, 1));//defaultView.getCenter(), defaultView.getSize());
		guiView = new View(offset, Vector2f.ZERO);//defaultView.getCenter(), defaultView.getSize());
		
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
				if(mbe.button == Mouse.Button.LEFT){
					Vector2f viewCoords = game.window.mapPixelToCoords(mbe.position, mainView);
					Vector2f gridOffset = game.grid.getAbsoluteOffset();
					Vector2f gridRelative = Vector2f.sub(viewCoords, gridOffset);
					Tile tile = game.grid.tileForPixel(new Vector2i((int)gridRelative.x, (int)gridRelative.y));
					if(tile != null) tile.onmousedown();
				}
			}
		});
		game.manager.addHandler(Event.Type.MOUSE_WHEEL_MOVED, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				MouseWheelEvent mwe = event.asMouseWheelEvent();
				double zf = Math.pow(1.2, -mwe.delta);
				zoom *= zf;
				Vector2f mp1 = game.window.mapPixelToCoords(mousePosition, mainView);
				mainView.zoom((float) zf);
				Vector2f mp2 = game.window.mapPixelToCoords(mousePosition, mainView);
				System.out.println(mp1);
				System.out.println(mp2);
				mainView.move(Vector2f.sub(mp1, mp2));
				
			}
		});
		
		game.manager.addHandler(Event.Type.MOUSE_MOVED, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				MouseEvent mbe = event.asMouseEvent();
				mousePosition = mbe.position;
				Vector2f viewCoords = game.window.mapPixelToCoords(mbe.position, mainView);
				Vector2f gridOffset = game.grid.getAbsoluteOffset();
				Vector2f gridRelative = Vector2f.sub(viewCoords, gridOffset);
				Tile tile = game.grid.tileForPixel(new Vector2i(gridRelative));
				gui.cursor.spin = tile != null;
				if(tile != currentTile){
					if(currentTile != null) currentTile.onmouseout(); 
					currentTile = tile;
					if(currentTile != null) currentTile.onmouseover();
				}
				/*if(tile == null && currentTile != null){
					currentTile.circleShape.setOutlineColor(new Color(0x10, 0x10, 0xaf, 0x22));
					currentTile = null;
				}*/
			}
		});
		
		game.manager.addHandler(null, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				Vector2i size = game.window.getSize();
				if(mousePosition.x < size.x * 0.15){
					mainView.move((float) ((mousePosition.x - size.x * 0.15) * zoom/4), 0);
				}
				if(mousePosition.x > size.x * 0.85){
					mainView.move((float) ((mousePosition.x - size.x * 0.85) * zoom/4), 0);
				}
				if(mousePosition.y < size.y * 0.1){
					mainView.move(0, (float) ((mousePosition.y - size.y * 0.1) * zoom/4));
				}
				if(mousePosition.y > size.y * 0.9){
					mainView.move(0, (float) ((mousePosition.y - size.y * 0.9) * zoom/4));
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
