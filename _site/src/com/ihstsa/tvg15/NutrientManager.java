package com.ihstsa.tvg15;

public class NutrientManager 
{
	private double maxGlucose;
	private double currentGlucose;
	private double maxWater;
	private double currentWater;
	private Tree tree;
	
	public NutrientManager(Tree tree)
	{
		this.tree = tree;
	}
	
	public void setMaxGlucose()
	{
		maxGlucose = tree.getGlucoseCapacity();
	}
	public void setMaxWater()
	{
		maxWater = tree.getWaterCapacity();
	}
	public void addWater()
	{
		currentWater += tree.getWaterBuffer();
	}
	public void addGlucose()
	{
		double sunlight = tree.getSunlightProduction();
		double production = tree.getGlucoseProduction();
		if(sunlight <= production && sunlight <= currentWater)
		{
			currentGlucose += sunlight;
			currentWater -= sunlight;
		}
		else if(production <= sunlight && production <= currentWater)
		{
			currentGlucose += production;
			currentWater -= production;
		}
		else
		{
			currentGlucose += currentWater;
			currentWater = 0;
		}
	}
}
