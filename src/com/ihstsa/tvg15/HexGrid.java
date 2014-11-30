package com.ihstsa.tvg15;

import java.util.Map;
import java.util.HashMap;

public class HexGrid 
{
	private Map<Integer, HashMap<Integer, Tile>> grid;
	
	/**
	 * Constructs a HexGrid
	 */
	public HexGrid()
	{
		grid = new HashMap<Integer, HashMap<Integer, Tile>>();
	}
	
	/**
	 * Adds a tile to the grid
	 * @param tile The tile to be added to the grid
	 */
	public void putTile(Tile tile)
	{
		HashMap<Integer, Tile> m = grid.get(tile.q);
		if(m == null)
		{
			m = new HashMap<Integer, Tile>();
			grid.put(tile.q, m);
		}
		m.put(tile.r, tile);
	}
	
	/**
	 * Gets the hex tile at a given q and r position
	 * (using axial coordinates)
	 * @param q The q of the desired tile
	 * @param r The r of the desired tile
	 * @return The tile at the given q,r axial position
	 */
	public Tile getTile(int q, int r)
	{
		HashMap<Integer, Tile> m = grid.get(q);
		if(m == null) return null;
		return m.get(r);
	}
}
