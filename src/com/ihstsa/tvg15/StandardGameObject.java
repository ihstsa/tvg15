package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.system.Vector2f;

/**
 * A GameObject that has a position and its own children list, and will render itself and its children
 * @author Paul
 *
 */
public class StandardGameObject extends GameObject {
	
	protected Vector2f pos;
	protected List<GameObject> children;
	
	public StandardGameObject(){
		this(null);
	}
	
	public StandardGameObject(Object jsfmlObject){
		super(jsfmlObject);
		children = new ArrayList<GameObject>();
		pos = Vector2f.ZERO;
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
