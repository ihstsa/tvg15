package com.ihstsa.tvg15;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
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
	static Font arial;
	static {
		arial = new Font();
		try {
			arial.loadFromFile(FileSystems.getDefault().getPath("C:\\Users\\paul", "arial.ttf"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Game()
	{
		
		window = new RenderWindow();
		window.create(new VideoMode(1366, 768), "tvg15", WindowStyle.DEFAULT, new ContextSettings(8));
		
		manager = new EventManager(this);
		manager.addHandler(Event.Type.CLOSED, new ClosedHandler());
		grid = new HexGrid(this, new Vector2f(160, 160));
		renderer = new Renderer(this);
		StandardGameObject sgo = new StandardGameObject();
		renderer.root.getChildren().add(sgo);
		sgo.getChildren().add(grid);
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				grid.createTile(new AxialVector(i, j));
			}
		}
		Tree tree = new Tree(grid);
		TreeTile t = new BaseTile(grid, new AxialVector(0, 4), tree);
		TreeTile t2 = new TreeTile(grid, new AxialVector(1, 3), t);
		TreeTile t3 = new TreeTile(grid, new AxialVector(2, 2), t2);
		TreeTile t4 = new TreeTile(grid, new AxialVector(3, 1), t3);
		TreeTile t5 = new TreeTile(grid, new AxialVector(4, 0), t4);
		TreeTile t6 = new TreeTile(grid, new AxialVector(5, 0), t5);
		manager.addHandler(null, renderer);
		try
		{
			Image img = new Image();
			img.loadFromFile(FileSystems.getDefault().getPath("res", "icon.png"));
			window.setIcon(img);
		}catch(IOException e)
		{
			System.err.println("Could not load icon");
		}

		window.setFramerateLimit(60);
		window.setVerticalSyncEnabled(true);
		window.setMouseCursorVisible(false);
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
		    manager.handle(window);
			window.display();
		}
	}

}
