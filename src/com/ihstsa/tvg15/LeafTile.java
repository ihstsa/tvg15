package com.ihstsa.tvg15;

public class LeafTile extends TreeTile
{
	public double sunlightPerLeaf = .01;
	public int numberOfLeaves;
	
	public LeafTile(HexGrid grid, AxialVector point, TreeTile parent)
	{
		super(grid, point, parent);
		numberOfLeaves = 1;
		tree = parent.getTree();
	}
	
	public double calculateMaxGlucose()
	{
		return sunAmount * sunlightPerLeaf;
	}
	
	public void addLeaf()
	{
		numberOfLeaves ++;
	}
}
