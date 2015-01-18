package com.ihstsa.tvg15;

public class RootTile extends TreeTile
{
	public RootTile(HexGrid grid, AxialVector point, TreeTile parent) 
	{
		super(grid, point, parent);
		tree = parent.getTree();
	}
}
