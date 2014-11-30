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
	
	/**
	 * Gets the Tile 1 unit in the given direction from a given Tile
	 * @param t The tile to look from
	 * @param s The direction to go in
	 * @return The tile 1 unit in the given direction
	 */
	public Tile getRelativeTile(Tile t, HexDirection s)
	{
		return getRelativeTile(t, s, 1);
	}
	
	/**
	 * Gets the Tile a given distance in a given direction from a given Tile
	 * @param t The tile to look from
	 * @param s The direction to go in
	 * @param n The distance to go
	 * @return The tile in the given direction a given units away from a given Tile 
	 */
	public Tile getRelativeTile(Tile t, HexDirection s, int n)
	{
		return getRelativeTile(t.pos, s, n);
	}
	
	/**
	 * Gets the Tile 1 unit away from a given AxialVector in a given direction
	 * @param v The AxialVector to go from
	 * @param s The direction to move in
	 * @return The tile specified
	 */
	public Tile getRelativeTile(AxialVector v, HexDirection s)
	{
		return getRelativeTile(v.q, v.r, s);
	}
	
	/**
	 * Gets the Tile a given distance from a given AxialVector in a given direction
	 * @param v The AxialVector to go from
	 * @param s The direction to move in
	 * @param n The distance to move
	 * @return The tile specified
	 */
	public Tile getRelativeTile(AxialVector v, HexDirection s, int n)
	{
		return getRelativeTile(v.q, v.r, s, n);
	}
	
	/**
	 * Gets the Tile 1 unit from a given point in a given direction
	 * @param q The q of the point to go from
	 * @param r The r of the point to go from
	 * @param s The direction to move in
	 * @return The specified tile
	 */
	public Tile getRelativeTile(int q, int r, HexDirection s)
	{
		return getRelativeTile(q, r, s, 1);
	}
	
	/**
	 * Gets the Tile a given distance from a given point in a given direction
	 * @param q The q of the point to go from
	 * @param r The r of the point to go from
	 * @param s The direction to move in
	 * @param n The distance to move in
	 * @return The specified tile
	 */
	public Tile getRelativeTile(int q, int r, HexDirection s, int n)
	{
		switch(s)
		{
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
