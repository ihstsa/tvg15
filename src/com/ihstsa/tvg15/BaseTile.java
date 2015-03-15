package com.ihstsa.tvg15;

public class BaseTile extends TreeTile
{
	public BaseTile(HexGrid grid, AxialVector vector, Tree parentTree)
	{
		super(grid, vector, null);
		tree = parentTree;
		tree.addTile(this);
		parentDirection = HexDirection.BOT;
	}
	
}
