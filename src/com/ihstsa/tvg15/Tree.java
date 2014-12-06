package com.ihstsa.tvg15;

import java.util.Set;

public class Tree 
{
	private Set<Tile> tiles;
	private Tile baseTile;
	private double glucoseProduction;
	private double waterCapacity;
	private double glucoseCapacity;
	private double waterBuffer;
	private double sunlightProduction;
	
	public double getGlucoseProduction()
	{
		return glucoseProduction;
	}
	public double getWaterCapacity()
	{
		return waterCapacity;
	}
	public double getGlucoseCapacity()
	{
		return glucoseCapacity;
	}
	public double getWaterBuffer()
	{
		return waterBuffer;
	}
	public double getSunlightProduction()
	{
		return sunlightProduction;
	}
}
