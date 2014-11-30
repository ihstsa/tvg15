package com.ihstsa.tvg15;

/**
 * Stores an axial vector
 * @author Frøjya Ryd
 * @version 11-30-14
 */
public class AxialVector
{
	public int q,r;
	
	/**
	 * Creates an Axial Vector
	 *  @param q The q coordinate.
	 *  @param r The r coordinate.
	 */
	public AxialVector (int q, int r)
	{
		this.q = q;
		this.r = r;
	}
	
	/**
	 * Finds z if using a cubic coordinate system
	 * @return Z if using a cubic coordinate system.
	 */
	public int getZ()
	{
		return -r - q;
	}
}