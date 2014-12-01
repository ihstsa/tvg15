package com.ihstsa.tvg15;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.Transformable;
import org.jsfml.system.Vector2f;

public class RootObject<T extends Transformable & Drawable> extends GameObject<T> {
	public RootObject(){
		super(new Vector2f(0, 0));
	}
}
