package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.system.Vector3f;

/*
       /
      /
     /
----X---+q
   /
  /
 /
+r
*/

/**
 * A class representing the grid of tiles. Is a {@link GameObject} that will render all contained tiles.
 * @author Paul
 *
 */
public class HexGrid extends GameObject
{
	private Map<Integer, HashMap<Integer, Tile>> grid;
	public List<Tile> tiles;
	private Vector2f pos;
	private Game game;
	
	/**
	 * Constructs a HexGrid
	 * @argument pos The position of the HexGrid
	 */
	public HexGrid(Game game, Vector2f pos)
	{
		this.game = game;
		grid = new HashMap<Integer, HashMap<Integer, Tile>>();
		tiles = new ArrayList<Tile>();
		this.pos = pos;
	}
	
	public void updateSunlight()
	{
		for(int q : grid.keySet())
		{
			Collection<Tile> tiles = grid.get(q).values();
			List<TranslucentTile> translucentTiles = new ArrayList<>();
			for(Tile t : tiles){
				if(t instanceof TranslucentTile){
					translucentTiles.add((TranslucentTile)t);
				}
			}
			Collections.sort(translucentTiles, Tile.qComparator);
			double sunlight = 1;
			for(TranslucentTile t : translucentTiles){
				t.sunAmount = sunlight;
				sunlight *= (1 - t.getOpacity());
			}
		}
	}
	
	/**
	 * Creates a tile at the given coordinates and stores it in the grid
	 * @param v
	 * @return The newly created tile
	 */
	public Tile createTile(AxialVector v){
		Tile t = new Tile(this, v);
		t.parent = this;
		putTile(t);
		return t;
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
		tiles.add(tile);
	}
	
	/**
	 * Gets a tile at a given AxialVector
	 * @param v The AxialVector to get the Tile from
	 * @return The given tile
	 */
	public Tile getTile(AxialVector v)
	{
		return getTile(v.q, v.r);
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
	 * Gets the tile at a certain pixel location
	 * @param pos The pixel location to look at
	 * @return The given Tile
	 */
	public Tile tileForPixel(Vector2i pos)
	{
		double q = 2./3. * pos.x / Tile.SIZE;
		double r = (-1./3. * pos.x + 1./3.*Math.sqrt(3) * pos.y) / Tile.SIZE;
		AxialVector v = AxialVector.fromCubic(Utilities.hexRound(new Vector3f((float)q, (float)(-q - r), (float)r)));
		return getTile(v);
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

	@Override
	public Vector2f getPos() {
		return pos;
	}
	@Override
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	@SuppressWarnings("unchecked") // I'm quite sure that this cast is type-safe
	@Override
	List<GameObject> getChildren() {
		return (List<GameObject>)((List<? extends GameObject>)tiles);
	}

}
