package com.ihstsa.tvg15;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Tile extends GameObject<CircleShape>
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
		this.object = circleShape;
		pos = point;
	}
	
	/**
	 * Makes a Tile
	 * @param grid The grid the tile will be on.
	 * @param q The q position of the Tile
	 * @param r The r position of the Tile
	 */
	public Tile(HexGrid grid, int q, int r)
	{
		this(grid, new AxialVector(q, r));
	}
	
	public Vector2f getPos(){
		return new Vector2f((float) (SIZE * 3./2. * pos.q), (float) (SIZE * Math.sqrt(3) * (pos.r+(pos.q/2.))));
	}
}
