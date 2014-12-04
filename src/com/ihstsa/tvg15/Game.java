package com.ihstsa.tvg15;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
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
		window = new RenderWindow();
		window.create(new VideoMode(1366, 768), "tvg15", WindowStyle.DEFAULT, new ContextSettings(8));
		
		manager = new EventManager(this);
		manager.addHandler(Event.Type.CLOSED, new ClosedHandler());
		grid = new HexGrid(new Vector2f(160, 160));
		renderer = new Renderer(this);
		StandardGameObject sgo = new StandardGameObject();
		renderer.root.getChildren().add(sgo);
		sgo.getChildren().add(grid);
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 8; j++){
				grid.createTile(new AxialVector(i, j-(i/2)));
			}
		}
		manager.addHandler(null, renderer);
		try{
			Image img = new Image();
			img.loadFromFile(FileSystems.getDefault().getPath("res", "icon.png"));
			window.setIcon(img);
		}catch(IOException e){
			System.err.println("Could not load icon");
		}

		window.setFramerateLimit(60);
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
		    manager.handle(window);
			window.display();
		}
	}

}
