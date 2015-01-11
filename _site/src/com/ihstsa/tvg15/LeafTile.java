package com.ihstsa.tvg15;

public class LeafTile extends Tile
{
	public static final double PRODUCTION_RATE_PER_LEAF = .01;
	public double sunlight = 0;
	public int numberOfLeaves;
	
	public LeafTile(HexGrid grid, AxialVector point)
	{
		super(grid, point);
		numberOfLeaves = 0;
	}
}
