package com.ihstsa.tvg15;

import java.util.HashSet;
import java.util.Set;

public class Tree 
{
	private Set<TreeTile> tiles;
	private Tile baseTile;
	private double glucoseProduction;
	private double waterCapacity;
	private double glucoseCapacity;
	private double waterBuffer;
	private double sunlightProduction;
	HexGrid grid;
	public Tree(HexGrid grid){
		tiles = new HashSet<>();
		this.grid = grid;
	}
	
	public void addTile(TreeTile newTile)
	{
		tiles.add(newTile);
		grid.putTile(newTile);
		
	}
	
	public void setValues()
	{
		double glucoseProductionSum = 0;
		double waterCapacitySum = 0;
		double glucoseCapacitySum = 0;
		double sunlightProductionSum = 0;
		for(TreeTile tile : tiles)
		{
			glucoseProductionSum += tile.glucoseProduction;
			waterCapacitySum += tile.waterStorage;
			glucoseCapacitySum += tile.glucoseStorage;
			sunlightProductionSum += tile.sunlightProduction;
			waterBuffer += tile.waterBuffer;
			tile.waterBuffer = 0;
		}
		glucoseProduction = glucoseProductionSum;
		waterCapacity = waterCapacitySum;
		glucoseCapacity = glucoseCapacitySum;
		sunlightProduction = sunlightProductionSum;
	}
	
	public void removeTile(TreeTile tile){
		tiles.remove(tile);
	}
	
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
		double temp = waterBuffer;
		waterBuffer = 0;
		return waterBuffer;
	}
	public double getSunlightProduction()
	{
		return sunlightProduction;
	}
}
