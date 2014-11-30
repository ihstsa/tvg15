package com.ihstsa.tvg15;

import java.util.Map;
import java.util.HashMap;

public class HexGrid {
	Map<Integer, HashMap<Integer, Tile>> grid;
	public HexGrid(){
		grid = new HashMap<Integer, HashMap<Integer, Tile>>();
	}
	
	public void putTile(Tile tile){
		HashMap<Integer, Tile> m = grid.get(tile.q);
		if(m == null){
			m = new HashMap<Integer, Tile>();
			grid.put(tile.q, m);
		}
		m.put(tile.r, tile);
	}
	
	public Tile getTile(int q, int r){
		HashMap<Integer, Tile> m = grid.get(q);
		if(m == null) return null;
		return m.get(r);
	}
}
