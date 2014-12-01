package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.system.Vector2f;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transformable;

public class GameObject<T extends Transformable & Drawable> {
	Vector2f pos;
	T object;
	List<GameObject<?>> children;
	public GameObject(){
		this(new Vector2f(0, 0), null);
	}
	public GameObject(T object){
		this(new Vector2f(0, 0), object);
	}
	public GameObject(Vector2f pos){
		this(pos, null);
	}
	public GameObject(Vector2f pos, T object){
		 this.pos = pos;
		 this.object = object;
		 children = new ArrayList<GameObject<?>>();
	}
	
	public void render(RenderWindow window){
		render(window, new Vector2f(0, 0));
	}
	
	Vector2f getPos(){
		return pos;
	}
	public void render(RenderWindow window, Vector2f offset){
		Vector2f base = Vector2f.add(offset, getPos());
		if(object != null){
			object.setPosition(base);
			window.draw(object);
		}
		for(GameObject<?> o : children){
			o.render(window, base);
		}
	}
}
