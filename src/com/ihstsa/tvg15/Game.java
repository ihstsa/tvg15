package com.ihstsa.tvg15;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
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
	
	static Random random = new Random();
	
	RenderWindow window;
	public EventManager manager;
	Renderer renderer;
	HexGrid grid;
	Tree tree;
	ActiveIndicator ai;
	
	Tree tree2;
	
	static Texture s1;
	static Texture s2;
	static Texture s3;
	static Texture s4;
	static Texture s5;

	
	static {
		s1 = new Texture();
		s2 = new Texture();
		s3 = new Texture();
		s4 = new Texture();
		s5 = new Texture();
		try {
			s1.loadFromFile(Paths.get("res", "S1.png"));
			s2.loadFromFile(Paths.get("res", "S2.png"));
			s3.loadFromFile(Paths.get("res", "S3.png"));
			s4.loadFromFile(Paths.get("res", "S4.png"));
			s5.loadFromFile(Paths.get("res", "S5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	static Font font;
	static {
		font = new Font();
		try {
			font.loadFromFile(Paths.get("res", "FreeSans.ttf"));
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
		tree2 = new EnemyTree(grid);
		tree.nutrientManager.currentGlucose = 200;
		tree2.nutrientManager.currentGlucose = 200;
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
		ai = new ActiveIndicator(grid, 0, 0);
		grid.tiles.add(ai);
		TreeTile t = new BaseTile(grid, new AxialVector(0, 0), tree);
		TreeTile t2 = new BaseTile(grid, new AxialVector(5, 0), tree2, true);
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
		Sprite x = new Sprite();
		x.setTexture(s1);
		x.setPosition(0, 0);
		x.setScale(.55f, .55f);
		while(true){
			window.clear(Color.WHITE);
			window.draw(x);
			window.display();
			while(true){
				Event q = window.pollEvent();
				if(q != null && q.type == Event.Type.KEY_PRESSED) break;
			}
			window.clear(Color.WHITE);
			x.setTexture(s2);
			window.draw(x);
			window.display();
			while(true){
				Event q = window.pollEvent();
				if(q != null && q.type == Event.Type.KEY_PRESSED) break;
			}
			window.clear(Color.WHITE);
			x.setTexture(s3);window.draw(x);
			window.draw(x);
			window.display();
			while(true){
				Event q = window.pollEvent();
				if(q != null && q.type == Event.Type.KEY_PRESSED) break;
			}
			window.clear(Color.WHITE);
			x.setTexture(s4);window.draw(x);
			window.draw(x);
			window.display();
			while(true){
				Event q = window.pollEvent();
				if(q != null && q.type == Event.Type.KEY_PRESSED) break;
			}
			window.clear(Color.WHITE);
			x.setTexture(s5);window.draw(x);
			window.draw(x);
			window.display();
			while(true){
				Event q = window.pollEvent();
				if(q != null && q.type == Event.Type.KEY_PRESSED) break;
			}
			break;
		}
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
		    manager.handle(window);
			window.display();
		}
	}

}
