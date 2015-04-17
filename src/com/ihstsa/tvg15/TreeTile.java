package com.ihstsa.tvg15;

import java.util.HashMap;
import java.util.Map;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConvexShape;
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
	ConvexShape poly;
	
	public TreeTile(HexGrid grid, AxialVector point, TreeTile parent) 
	{
		super(grid, point);
		parentTreeTile = parent;
		childTiles = new HashMap<>();
		width = SIZE;
		poly = new ConvexShape();
		poly.setFillColor(Color.RED);
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
		poly.setPointCount(12);
		int i = 0;
		for(HexDirection d : HexDirection.values()){
			Tile t = getRelative(d);
			if(t instanceof TreeTile){
				TreeTile t2 = (TreeTile)t;
				Pair<Vector2f, Vector2f> x = t2.getEntranceCoords();
				poly.setPoint(i, x.a);
				poly.setPoint(i+1, x.b);
				i+=2;
			}
		}
		poly.setPointCount(i);
		window.draw(poly);
		//System.out.println(v.b);
	}
	
	public Pair<Vector2f, Vector2f> getEntranceCoords(){
		float y = (float) (SIZE * Math.sqrt(3) / 2);
		Vector2f a = new Vector2f(-width/2, -y);
		Vector2f b = new Vector2f(width/2, -y);
		double rads = (parentDirection.getAngle())/180.0*Math.PI;
		Vector2f an = Utilities.rotate(a, getPos(), rads);
		Vector2f bn = Utilities.rotate(b, getPos(), rads);
		/*double s = Math.sin(rads);
		double c = Math.cos(rads);
		double axn = a.x * c - a.y * s;
		double ayn = a.x * s - a.y * c;
		double bxn = b.x * c - b.y * s;
		double byn = b.x * s - b.y * c;*/
		return new Pair<>(an, bn);
	}
	
	public void destroy(){
		tree.removeTile(this);
	}

}
