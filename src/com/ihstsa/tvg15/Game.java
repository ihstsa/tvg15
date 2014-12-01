package com.ihstsa.tvg15;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

/**
 * The "Main class" of this game. Currently mostly for testing.
 * @author Paul
 *
 */
public class Game 
{
	
	RenderWindow window;
	EventManager manager;
	Renderer renderer;
	HexGrid grid;
	public Game()
	{
		manager = new EventManager(this);
		manager.addHandler(Event.Type.CLOSED, new ClosedHandler());
		CircleShape t = new CircleShape(20);
		t.setOutlineThickness(5);
		t.setOutlineColor(Color.BLACK);
		grid = new HexGrid(new Vector2f(0, 0));
		renderer = new Renderer();
		renderer.root.getChildren().add(grid);
		grid.createTile(new AxialVector(0, 0));
		grid.createTile(new AxialVector(0, 1));
		grid.createTile(new AxialVector(1, 0));
		grid.createTile(new AxialVector(1, 1));
		manager.addHandler(null, renderer);
		window = new RenderWindow();
		window.create(new VideoMode(640, 480), "tvg15");
		window.setFramerateLimit(60);
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
		    manager.handle(window);
			window.display();
			
		}
	}

}
