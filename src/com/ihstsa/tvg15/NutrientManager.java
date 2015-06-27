package com.ihstsa.tvg15;

import org.jsfml.window.event.Event;

public class NutrientManager 
{
	public double maxGlucose;
	public double currentGlucose;
	public double maxWater;
	public double currentWater;
	public double upkeep;
	public static final double LIGHT_PER_GLUCOSE = 5;
	public static final double WATER_PER_GLUCOSE = 6;
	private Tree tree;
	
	public NutrientManager(Tree tree)
	{
		this.tree = tree;
		Game.instance.manager.addHandler(null, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				tickDynamicFactors();
			}
		});
	}
	
	public double getTimeLightFactor(){
		return 0.9; // FIX!!!
	}
	
	public void updateStaticFactors(){
		maxGlucose = 0;
		maxWater = 0;
		upkeep = 0;
		for(TreeTile t : tree.tiles){
			maxGlucose += t.getGlucoseStorage();
			maxWater += t.getWaterStorage();
			upkeep += t.getUpkeep();
		}
	}
	
	public void tickDynamicFactors(){
		double tickLight = 0;
		for(TreeTile t : tree.tiles){
			tickLight += t.getLightProduction();
			currentWater += t.getWaterProduction();
		}
		double glucoseAdvance = Math.min(currentWater / WATER_PER_GLUCOSE, tickLight / LIGHT_PER_GLUCOSE);
		currentGlucose += glucoseAdvance;
		currentWater -= WATER_PER_GLUCOSE * glucoseAdvance;
		//currentGlucose -= upkeep;
		currentWater = Math.min(currentWater, maxWater);
		currentGlucose = Math.min(currentGlucose, maxGlucose);
		//System.out.println(currentGlucose);
		Game.instance.renderer.gui.update();
	}
}
