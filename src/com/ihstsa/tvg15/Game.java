package com.ihstsa.tvg15;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.MouseButtonEvent;

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
		manager.addHandler(Event.Type.MOUSE_BUTTON_PRESSED, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				MouseButtonEvent mbe = event.asMouseButtonEvent();
				Vector2f gridOffset = grid.getAbsoluteOffset();
				Vector2f gridRelative = Vector2f.sub(new Vector2f(mbe.position.x, mbe.position.y), gridOffset);
				Tile tile = grid.tileForPixel(new Vector2i((int)gridRelative.x, (int)gridRelative.y));
				tile.circleShape.setOutlineColor(Color.BLACK);
			}
		});
		grid = new HexGrid(new Vector2f(100, 100));
		renderer = new Renderer();
		StandardGameObject sgo = new StandardGameObject();
		sgo.setPos(new Vector2f(0, 0));
		renderer.root.getChildren().add(sgo);
		sgo.getChildren().add(grid);
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 8; j++){
				grid.createTile(new AxialVector(i, j-(i/2)));
			}
		}
		manager.addHandler(null, renderer);
		window = new RenderWindow();
		window.create(new VideoMode(1366, 768), "tvg15", WindowStyle.DEFAULT, new ContextSettings(8));
		window.setFramerateLimit(60);
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
		    manager.handle(window);
			window.display();
			
		}
	}

}
