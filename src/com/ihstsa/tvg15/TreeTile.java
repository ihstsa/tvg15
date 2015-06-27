package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	public HexDirection parentDirection;
	public int width;
	protected Tree tree;
	ConvexShape poly;
	
	public TreeTile(HexGrid grid, AxialVector point, TreeTile parent) 
	{
		super(grid, point);
		parentTreeTile = parent;
		childTreeTiles = new HashMap<>();
		poly = new ConvexShape();
		poly.setFillColor(Color.RED);
		if(parent != null){
			width = parent.width - 20;
			tree = parent.tree;
			parentTreeTile.addChild(this);
			parentDirection = getDirectionTo(parentTreeTile);
		}
	}
	
	public double getWaterProduction(){
		return 10;
	}
	public double getLightProduction(){
		return 0;
	}
	public double getUpkeep(){
		return 1;
	}
	public double getWaterStorage(){
		return 100;
	}
	public double getGlucoseStorage(){
		return 100;
	}
	
	public Tree getTree()
	{
		return tree;
	}
	
	public void addChild(TreeTile t){
		childTreeTiles.put(getDirectionTo(t), t);
		tree.addTile(t);
	}
	
	@Override
	public void draw(RenderWindow window, Vector2f pos){
		circleShape.setPosition(pos);
		window.draw(circleShape);//, states);
		List<Vector2f> l = new ArrayList<Vector2f>();
		Pair<Vector2f, Vector2f> z = getEntranceCoords();
		if(parentTreeTile == null){
			l.add(z.a);
			l.add(z.b);
		}
		for(HexDirection d : HexDirection.values()){
			Tile t = getRelative(d);
			if(t == parentTreeTile){
				l.add(z.a);
				l.add(z.b);
			}
			if(childTreeTiles.containsValue(t)){
				TreeTile t2 = (TreeTile)t;
				Pair<Vector2f, Vector2f> x = t2.getEntranceCoords(true);
				l.add(x.a);
				l.add(x.b);
			}
		}
		if(l.size() == 2){
			l.add(Vector2f.ZERO);
		}
		poly.setPointCount(l.size());
		for(int j = 0; j < l.size(); j++){
			poly.setPoint(j, l.get(j));
		}
		//System.out.println("end");
		poly.setPosition(pos);
		window.draw(poly);
		//System.out.println(v.b);
	}
	public Pair<Vector2f, Vector2f> getEntranceCoords(){
		return getEntranceCoords(false);
	}
	public Pair<Vector2f, Vector2f> getEntranceCoords(boolean reversed){
		float y = (float) (SIZE * Math.sqrt(3) / 2);
		Vector2f a = new Vector2f(-width/2, -y);
		Vector2f b = new Vector2f(width/2, -y);
		double rads = (parentDirection.getAngle() + (reversed ? 180 : 0))/180.0*Math.PI;
		Vector2f an = Utilities.rotate(a, Vector2f.ZERO, rads);
		Vector2f bn = Utilities.rotate(b, Vector2f.ZERO, rads);
		//System.out.println(an);
		//System.out.println(a);
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
