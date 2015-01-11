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
	
	public double getOpacity(){
		return 1./3.;
	}
}
