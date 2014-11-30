package com.ihstsa.tvg15;

import org.jsfml.graphics.Drawable;

public abstract class Tile implements Drawable {
	int q, r;
	HexGrid grid;
	HexDirections directions;
	public Tile(HexGrid grid, int q, int r){
		directions = new HexDirections(this);
		this.grid = grid;
		this.q = q;
		this.r = r;
	}
	
}
