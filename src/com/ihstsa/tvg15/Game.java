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
	
	public static Game instance;
	
	RenderWindow window;
	public EventManager manager;
	Renderer renderer;
	HexGrid grid;
	Tree tree;
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
		
		instance = this;
		window = new RenderWindow();
		window.create(new VideoMode(1366, 768), "tvg15", WindowStyle.DEFAULT, new ContextSettings(8));
		
		manager = new EventManager(this);
		manager.addHandler(Event.Type.CLOSED, new ClosedHandler());
		grid = new HexGrid(this, new Vector2f(160, 160));
		tree = new Tree(grid);
		renderer = new Renderer(this);
		StandardGameObject sgo = new StandardGameObject();
		sgo.setPos(Renderer.offset);
		renderer.root.addChild(sgo);
		sgo.addChild(grid);
		for(int i = -30; i < 30; i++)
		{
			for(int j = -15; j < 15; j++)
			{
				grid.createTile(new AxialVector(i, j-i/2));
			}
		}
		grid.removeTile(grid.getTile(0, 0));
		TreeTile t = new BaseTile(grid, new AxialVector(0, 0), tree);
		tree.nutrientManager.updateStaticFactors();
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
		
		manager.runInitEvents();
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
