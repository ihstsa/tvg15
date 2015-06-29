package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tree 
{
	Set<TreeTile> tiles;
	private Tile baseTile;
	public NutrientManager nutrientManager;
	HexGrid grid;
	public int treewidth = TreeTile.TAPER;
	public List<Upgrade> possibleUpgrades;
	public Tree(HexGrid grid){
		tiles = new HashSet<>();
		nutrientManager = new NutrientManager(this);
		possibleUpgrades = new ArrayList<>();
		this.grid = grid;
	}
	
	public void addTile(TreeTile newTile)
	{
		tiles.add(newTile);
		grid.putTile(newTile);
		nutrientManager.updateStaticFactors();
		updateLeaves();
		grid.updateSunlight();
	}
	
	public List<Upgrade> updatePossibleUpgrades(){
		possibleUpgrades.clear();
		for(Upgrade u : Upgrade.values()){
			if(u.cost < nutrientManager.currentGlucose) possibleUpgrades.add(u);
		}
		return possibleUpgrades;
	}
	
	/*public void setValues()
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
	}*/
	
	public void updateLeaves(){
		for(TreeTile t : tiles){
			if(t instanceof LeafTile){
				((LeafTile)t).updateLeaves();
			}
		}
	}
	
	public void removeTile(TreeTile tile){
		tiles.remove(tile);
		nutrientManager.updateStaticFactors();
		grid.updateSunlight();
	}
	
	/*public double getGlucoseProduction()
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
	}*/
}
