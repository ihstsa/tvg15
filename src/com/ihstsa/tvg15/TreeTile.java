package com.ihstsa.tvg15;

import java.util.Map;

public class TreeTile extends TranslucentTile
{
	public Tile parentTreeTile;
	public Map<HexDirection, TreeTile> childTreeTiles;
	protected Tree tree;
	
	public TreeTile(HexGrid grid, AxialVector point, TreeTile parent) 
	{
		super(grid, point);
		parentTreeTile = parent;
		tree = ((TreeTile)parentTreeTile).getTree();
	}
	
	public Tree getTree()
	{
		return tree;
	}
	
}
