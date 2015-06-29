package com.ihstsa.tvg15;

public class BaseTile extends TreeTile
{
	public BaseTile(HexGrid grid, AxialVector vector, Tree parentTree, boolean enemy)
	{
		super(grid, vector, null, enemy);
		tree = parentTree;
		tree.addTile(this);
		width = 0;
		parentDirection = HexDirection.BOT;
	}
	
	public BaseTile(HexGrid grid, AxialVector vector, Tree parentTree){
		this(grid, vector, parentTree, false);
	}
}
