package com.ihstsa.tvg15;

import org.jsfml.graphics.Color;

public class LeafTile extends TreeTile
{
	public double sunlightPerLeaf = 1;
	public int numberOfLeaves;
	
	public LeafTile(HexGrid grid, AxialVector point, TreeTile parent)
	{
		super(grid, point, parent);
		numberOfLeaves = 1;
		poly.setFillColor(Color.GREEN);
	}
	
	public double getLightProduction()
	{
		//System.out.println(sunFactor * sunlightPerLeaf * numberOfLeaves);
		return sunFactor * sunlightPerLeaf * numberOfLeaves;
	}
}
