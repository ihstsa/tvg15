package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.system.Vector2f;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transformable;

public class GameObject {
	Vector2f pos;
	private Object object;
	List<GameObject> children;
	public GameObject(){
		this(new Vector2f(0, 0), null);
	}
	public GameObject(Object object){
		this(new Vector2f(0, 0), object);
	}
	public GameObject(Vector2f pos){
		this(pos, null);
	}
	public GameObject(Vector2f pos, Object object){
		this.pos = pos;
		this.setObject(object);
		children = new ArrayList<GameObject>();
	}
	
	public void render(RenderWindow window){
		render(window, new Vector2f(0, 0));
	}
	
	Vector2f getPos(){
		return pos;
	}
	public void render(RenderWindow window, Vector2f offset){
		Vector2f base = Vector2f.add(offset, getPos());
		if(getObject() != null){
			((Transformable)getObject()).setPosition(base);
			window.draw((Drawable)getObject());
		}
		for(GameObject o : children){
			o.render(window, base);
		}
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		if(object != null && !(object instanceof Drawable && object instanceof Transformable)){
			throw new IllegalArgumentException("object must implement both Drawable and Transformable");
		}
		this.object = object;
	}
}
