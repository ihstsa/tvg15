package com.ihstsa.tvg15;

import org.jsfml.system.Vector3i;

/**
 * Stores an axial vector
 * @author Freya, Paul
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
	public AxialVector(int q, int r)
	{
		this.q = q;
		this.r = r;
	}
	
	/**
	 * Creates an AxialVector from cubic coordinates
	 * @param cubic the cubic coordinates
	 * @return the new AxialVector
	 */
	public static AxialVector fromCubic(Vector3i cubic){
		return new AxialVector(cubic.x, cubic.z);
	}
	
	/**
	 * Converts this vector to cubic coordinates
	 * @return the cubic version of the vector
	 */
	public Vector3i toCubic()
	{
		return new Vector3i(q, -r - q, r);
	}

}