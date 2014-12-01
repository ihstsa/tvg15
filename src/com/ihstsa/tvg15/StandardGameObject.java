package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.system.Vector2f;

public class StandardGameObject extends GameObject {

	protected Vector2f pos;
	protected List<GameObject> children;
	
	public StandardGameObject(){
		this(null);
	}
	
	public StandardGameObject(Object jsfmlObject){
		super(jsfmlObject);
		children = new ArrayList<GameObject>();
	}
	
	@Override
	public Vector2f getPos() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	List<GameObject> getChildren() {
		return children;
	}

}
