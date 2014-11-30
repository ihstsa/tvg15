package com.ihstsa.tvg15;

public abstract class Tile
{
	public static final int SIZE = 50;
	public AxialVector pos;
	private HexGrid grid;
	
	/**
	 * Makes a Tile
	 * @param grid The grid the tile will be on.
	 * @param point The point on which the hexagon lies.
	 */
	public Tile(HexGrid grid, AxialVector point)
	{
		this.grid = grid;
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
}
