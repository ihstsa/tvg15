package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.system.Vector2f;

public class StandardGameObject extends GameObject {
	
	public static Vector2f base = new Vector2f(0, 0);
	protected Vector2f pos;
	protected List<GameObject> children;
	
	public StandardGameObject(){
		this(null);
	}
	
	public StandardGameObject(Object jsfmlObject){
		super(jsfmlObject);
		children = new ArrayList<GameObject>();
		pos = base;
	}
	
	@Override
	public Vector2f getPos() {
		return pos;
	}
	
	@Override
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	@Override
	List<GameObject> getChildren() {
		return children;
	}


}
