package com.ihstsa.tvg15;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class LeafTile extends TreeTile
{
	public double sunlightPerLeaf = 0.01;
	Set<Leaf> leaves = new HashSet<Leaf>();
	private List<GameObject> children = new ArrayList<>();
	
	public LeafTile(HexGrid grid, AxialVector point, TreeTile parent, boolean enemy)
	{
		super(grid, point, parent, enemy);
		addLeaves(10);
	}
	
	public LeafTile(HexGrid grid, AxialVector point, TreeTile parent)
	{
		this(grid, point, parent, false);
	}
	
	public boolean addLeaves(int n){
		if(leaves.size() + n > 25) return false;
		for(int i = 0; i < n; i++){
			Leaf l = new Leaf(enemy);
			leaves.add(l);
			addChild(l);
		}
		updateLeaves();
		return true;
	}
	
	public void updateLeaves(){
		if(leaves == null) return;
		updateVertices();
		for(Leaf l : leaves){
			Vector2f a, b;
			do {
				if(vertices.size() == 0) return;
				int j = Game.random.nextInt(vertices.size());
				a = vertices.get(j);
				b = vertices.get((j + 1) % vertices.size());
			} while(Math.sqrt(Math.pow((a.x-b.x), 2) + Math.pow((a.y-b.y), 2)) < SIZE * Math.sqrt(3)/2.);
			float d = Game.random.nextFloat();
			//System.out.println((b.y-a.y) + " " + (b.x-a.x) + " " + Math.atan2(b.y-a.y, b.x-a.x));
			l.setRotation((float)(Math.atan2(b.y-a.y, b.x-a.x)*180./Math.PI));
			l.setPos(new Vector2f(a.x+(b.x-a.x)*d, a.y+(b.y-a.y)*d));
		}
	}
	
	public List<GameObject> getChildren(){
		return children;
	}
	
	public double getLightProduction()
	{
		//System.out.println(sunFactor * sunlightPerLeaf * numberOfLeaves);
		return sunFactor * sunlightPerLeaf * leaves.size();
	}
}
