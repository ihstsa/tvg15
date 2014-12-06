package com.ihstsa.tvg15;

/**
 * A tile that obstructs light
 * @author Paul
 */
public class TranslucentTile extends Tile 
{
	public double sunAmount;

	public TranslucentTile(HexGrid grid, AxialVector point) {
		super(grid, point);
	}
	public TranslucentTile(HexGrid grid, int q, int r) {
		super(grid, q, r);
	}
	
	public double getOpacity(){
		return 1./3.;
	}
}
