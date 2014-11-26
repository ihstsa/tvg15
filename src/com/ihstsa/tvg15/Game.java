package com.ihstsa.tvg15;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class Game 
{
	
	RenderWindow window;
	EventManager manager;
	Color c = Color.RED;
	public Game()
	{
		manager = new EventManager(this);
		manager.addHandler(Event.Type.CLOSED, new EventHandler(){
			public void handle(Game game, Event event)
			{
				window.close();
			}
		});
		manager.addHandler(Event.Type.KEY_PRESSED, new EventHandler(){
			public void handle(Game game, Event event){
				game.c = Color.YELLOW;
			}
		});
		manager.addHandler(Event.Type.KEY_RELEASED, new EventHandler(){
			public void handle(Game game, Event event){
				game.c = Color.RED;
			}
		});
		window = new RenderWindow();
		window.create(new VideoMode(640, 480), "tvg15");
		window.setFramerateLimit(60);
		while(window.isOpen())
		{
			window.clear(c);
			window.display();
			
		    manager.handle(window);
		}
	}

}
