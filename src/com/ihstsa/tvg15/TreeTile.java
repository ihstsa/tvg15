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
	public static final int TAPER = 12;
	public List<Vector2f> vertices = new ArrayList<>();
	public Pair<Vector2f, Vector2f> entranceCoords;
	protected boolean enemy;
	
	public TreeTile(HexGrid grid, AxialVector point, TreeTile parent, boolean enemy) 
	{
		super(grid, point);
		this.enemy = enemy;
		parentTreeTile = parent;
		childTreeTiles = new HashMap<>();
		poly = new ConvexShape();
		poly.setFillColor(Utilities.colorFromHex(enemy ? 0x946fdc : 0x964B00));
		if(parent != null){
			width = parent.width - TAPER;
			tree = parent.tree;
			while((tree.treewidth + width) < TAPER){
				tree.treewidth += TAPER;
			}
			parentDirection = getDirectionTo(parentTreeTile);
			parentTreeTile.addChild(this);
		}
	}
	
	public TreeTile(HexGrid grid, AxialVector point, TreeTile parent){ 
		this(grid, point, parent, false);
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
	
	public void updateVertices(){
		vertices.clear();
		entranceCoords = getEntranceCoords();
		if(parentTreeTile == null){
			vertices.add(entranceCoords.a);
			vertices.add(entranceCoords.b);
		}
		for(HexDirection d : HexDirection.values()){
			Tile t = getRelative(d);
			if(t == parentTreeTile){
				vertices.add(entranceCoords.a);
				vertices.add(entranceCoords.b);
			}
			if(childTreeTiles.containsValue(t)){
				TreeTile t2 = (TreeTile)t;
				Pair<Vector2f, Vector2f> x = t2.getEntranceCoords(true);
				vertices.add(x.a);
				vertices.add(x.b);
			}
		}
		if(vertices.size() == 2){
			vertices.add(Vector2f.ZERO);
		}
	}
	
	@Override
	public void draw(RenderWindow window, Vector2f pos){
		circleShape.setPosition(pos);
		window.draw(circleShape);//, states);
		updateVertices();
		poly.setPointCount(vertices.size());
		for(int j = 0; j < vertices.size(); j++){
			poly.setPoint(j, vertices.get(j));
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
		Vector2f a = new Vector2f(-(width+tree.treewidth)/2, -y);
		Vector2f b = new Vector2f((width+tree.treewidth)/2, -y);
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
	
	public void onmousedown(){
		for(HexDirection x : HexDirection.values()){
			if(x == HexDirection.BOT) continue;
			Tile t = getRelative(x);
			if(!(t instanceof TreeTile)){
				getRelative(x).activate(this);
			}
		}
	}

	public void activated_click(Tile tile) {
		for(HexDirection x : HexDirection.values()){
			if(x == HexDirection.BOT) continue;
			getRelative(x).deactivate();
		}
		if(tree.nutrientManager.currentGlucose >= 100){
			tree.nutrientManager.currentGlucose -=100;
			new LeafTile(this.grid, tile.pos, this, enemy);
		}
	}

}
