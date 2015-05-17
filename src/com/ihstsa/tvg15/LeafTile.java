package com.ihstsa.tvg15;

public class LeafTile extends TreeTile
{
	public double sunlightPerLeaf = .01;
	public int numberOfLeaves;
	
	public LeafTile(HexGrid grid, AxialVector point, TreeTile parent)
	{
		super(grid, point, parent);
		numberOfLeaves = 1;
	}
	
	public double calculateSunlightProduction()
	{
		return sunAmount * sunlightPerLeaf * numberOfLeaves;
	}
	
	public void addLeaf()
	{
		numberOfLeaves++;
	}
}
