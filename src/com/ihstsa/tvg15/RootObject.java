package com.ihstsa.tvg15;

import org.jsfml.system.Vector2f;
/**
 * A subclass of {@link GameObject} that does nothing and only serves as a container for other objects.
 * @author Paul
 *
 */
public class RootObject extends StandardGameObject {
	private static Vector2f base = new Vector2f(0, 0);
	public RootObject(){
		super();
		this.pos = base;
	}

	@Override
	public Vector2f getPos() {
		return base;
	}
}
