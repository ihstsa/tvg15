package com.ihstsa.tvg15;

/**
 * Stores an axial vector
 * @author Freya
 * @version 11-30-14
 */
public class AxialVector
{
	public int q, r;
	
	/**
	 * Creates an {@link AxialVector}
	 *  @param q The q coordinate.
	 *  @param r The r coordinate.
	 */
	public AxialVector (int q, int r)
	{
		this.q = q;
		this.r = r;
	}
	
	/**
	 * Finds the cubic coordinate Z
	 * @return the Z value
	 */
	public int getZ()
	{
		return -r - q;
	}
}