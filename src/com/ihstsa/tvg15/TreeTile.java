package com.ihstsa.tvg15;

import java.util.HashMap;
import java.util.Map;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

public class TreeTile extends TranslucentTile
{
	public TreeTile parentTreeTile;
	public Map<HexDirection, TreeTile> childTreeTiles;
	public double glucoseProduction = 0;
	public double glucoseStorage = 0;
	public double waterStorage = 0;
	public double sunlightProduction = 0;
	public double waterBuffer = 0;
	
	public Map<HexDirection, Tile> childTiles;
	public HexDirection parentDirection;
	public int width;
	protected Tree tree;
	
	public TreeTile(HexGrid grid, AxialVector point, TreeTile parent) 
	{
		super(grid, point);
		parentTreeTile = parent;
		childTiles = new HashMap<>();
		if(parent != null){
			tree = parentTreeTile.getTree();
			tree.addTile(this);
			parentDirection = getDirectionTo(parentTreeTile);
		}
	}
	
	public Tree getTree()
	{
		return tree;
	}
	
	@Override
	public void draw(RenderWindow window, Vector2f pos){
		circleShape.setPosition(pos);
		window.draw(circleShape);//, states);
		Pair<Vector2f, Vector2f> v = getEntranceCoords();
		CircleShape a = new CircleShape(5, 2);
		a.setPosition(Vector2f.add(v.a, pos));
		window.draw(a);
	}
	
	public Pair<Vector2f, Vector2f> getEntranceCoords(){
		Vector2f a = new Vector2f(-width/2, SIZE);
		Vector2f b = new Vector2f(width/2, SIZE);
		double s = Math.sin(parentDirection.getAngle());
		double c = Math.cos(parentDirection.getAngle());
		double axn = a.x * c - a.y * s;
		double ayn = a.x * s - a.y * c;
		double bxn = b.x * c - b.y * s;
		double byn = b.x * s - b.y * c;
		return new Pair<>(new Vector2f((float)axn, (float)ayn), new Vector2f((float)bxn, (float)byn));
	}

}
