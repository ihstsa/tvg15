package com.ihstsa.tvg15;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

public class Tile extends GameObject
{
	public static final int SIZE = 50;
	public AxialVector pos;
	private HexGrid grid;
	private CircleShape circleShape;
	
	/**
	 * Makes a Tile
	 * @param grid The grid the tile will be on.
	 * @param point The point on which the hexagon lies.
	 */
	public Tile(HexGrid grid, AxialVector point)
	{
		this.grid = grid;
		circleShape = new CircleShape(SIZE, 6);
		circleShape.setOutlineColor(Color.BLACK);
		circleShape.setOutlineThickness(2);
		circleShape.rotate(30);
		this.setObject(circleShape);
		pos = point;
	}
	
	/**
	 * Makes a Tile
	 * @param grid The grid the tile will be on.
	 * @param q The q position of the Tile.
	 * @param r The r position of the Tile.
	 */
	public Tile(HexGrid grid, int q, int r)
	{
		this(grid, new AxialVector(q, r));
	}
	
	
	/**
	 * Gets the pixel position of the Tile's center, as a Vector2f.
	 * @return the center pixel position
	 */
	public Vector2f getPos(){
		return new Vector2f((float) (SIZE * 3./2. * pos.q), (float) (SIZE * Math.sqrt(3) * (pos.r+(pos.q/2.))));
	}
}
