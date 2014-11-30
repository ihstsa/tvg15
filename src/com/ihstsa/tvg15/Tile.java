package com.ihstsa.tvg15;

public abstract class Tile
{
	public AxialVector pos;
	private HexGrid grid;
	
	public Tile(HexGrid grid, AxialVector point)
	{
		this.grid = grid;
		pos = point;
	}
	
	public Tile(HexGrid grid, int q, int r)
	{
		this(grid, new AxialVector(q, r));
	}
}
