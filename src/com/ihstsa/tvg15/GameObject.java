package com.ihstsa.tvg15;

import java.util.List;

import org.jsfml.system.Vector2f;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transformable;

/**
 * This class represents an in-game object that is drawn, or a container for such objects.
 * Each GameObject has one rendered JSFML object and a list of child GameObjects to render.
 * @author Paul
 *
 */
public abstract class GameObject {
	/**
	 * The underling JSFML object that this GameObject draws.
	 * Its type should be {@link Transformable}
	 * and {@link Drawable} but there is no good way
	 * to implement this. Therefore, it is an Object and the method to set it does
	 * type checking. 
	 */
	private Object jsfmlObject;
	public GameObject parent;
	public GameObject(){
		this(null);
	}
	
	public GameObject(Object jsfmlObject){
		this.setObject(jsfmlObject);
	}
	
	/**
	 * Returns the location of this GameObject. This is a method instead of a property so it can be
	 * more conveniently overridden by subclasses.
	 * @return the location
	 */
	public abstract Vector2f getPos();
	public abstract void setPos(Vector2f pos);
	
	protected void draw(RenderWindow window, Vector2f offset){
		Object o = getObject();
		if(o == null) return;
		((Transformable)o).setPosition(offset);
		window.draw((Drawable)o);
	}
	
	public void addChild(GameObject child){
		getChildren().add(child);
		child.parent = this;
	}
	
	public Vector2f getAbsoluteOffset(){
		GameObject o = this;
		float x = 0, y = 0;
		while(true){
			x += o.getPos().x;
			y += o.getPos().y;
			if(o.parent == null) break;
			o = o.parent;
		}
		return new Vector2f(x, y);
	}
	
	public void render(RenderWindow window){
		render(window, new Vector2f(0, 0));
	}
	
	/**
	 * Renders this GameObject and all child objects to the {@link RenderWindow} given. It renders itself first,
	 * so that objects farther down the tree appear to be on top.
	 * @param window the {@link RenderWindow} to render to
	 * @param offset the coordinates to start rendering at
	 */
	public void render(RenderWindow window, Vector2f offset){
		Vector2f base = Vector2f.add(offset, getPos());
		draw(window, base);
		List<GameObject> children = getChildren();
		if(children == null) return;
		for(GameObject o : children){
			o.render(window, base);
		}
	}
	public Object getObject() {
		return jsfmlObject;
	}
	/**
	 * Sets the JSFML object, checking that it implements {@link Drawable} and {@link Transformable}
	 * @param jsfmlObject
	 */
	public void setObject(Object jsfmlObject) {
		if(jsfmlObject != null && !(jsfmlObject instanceof Drawable && jsfmlObject instanceof Transformable)){
			throw new IllegalArgumentException("jsfmlObject must implement both Drawable and Transformable");
		}
		this.jsfmlObject = jsfmlObject;
	}

	abstract List<GameObject> getChildren();
}
