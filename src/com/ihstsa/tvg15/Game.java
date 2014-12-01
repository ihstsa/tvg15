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
		grid = new HexGrid(new Vector2f(0, 0));
		renderer = new Renderer();
		StandardGameObject sgo = new StandardGameObject();
		sgo.setPos(new Vector2f(50, 50));
		renderer.root.getChildren().add(sgo);
		sgo.getChildren().add(grid);
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 8; j++){
				grid.createTile(new AxialVector(i, j-(i/2)));
			}
		}
		manager.addHandler(null, renderer);
		window = new RenderWindow();
		window.create(new VideoMode(1366, 768), "tvg15");
		window.setFramerateLimit(60);
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
		    manager.handle(window);
			window.display();
			
		}
	}

}
