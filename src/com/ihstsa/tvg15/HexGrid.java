package com.ihstsa.tvg15;

import java.util.Map;
import java.util.HashMap;

//       /
//      /
//     /
//----X---+q
//   /
//  /
// /
//+r

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
		HashMap<Integer, Tile> m = grid.get(tile.pos.q);
		if(m == null)
		{
			m = new HashMap<Integer, Tile>();
			grid.put(tile.pos.q, m);
		}
		m.put(tile.pos.r, tile);
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
	
	Tile getRelative(Tile t, HexDirection s){
		return getRelative(t, s, 1);
	}
	Tile getRelative(Tile t, HexDirection s, int n){
		return getRelative(t.pos.q, t.pos.r, s, n);
	}
	Tile getRelative(int q, int r, HexDirection s){
		return getRelative(q, r, s, 1);
	}
	Tile getRelative(int q, int r, HexDirection s, int n){
		switch(s){
			case TOP:
				return getTile(q, r-n);
			case TOP_RIGHT:
				return getTile(q+n, r-n);
			case BOT_RIGHT:
				return getTile(q+n, r);
			case BOT:
				return getTile(q, r+n);
			case BOT_LEFT:
				return getTile(q-n, r+n);
			case TOP_LEFT:
				return getTile(q-n, r);
		}
		return null;
	}

}
