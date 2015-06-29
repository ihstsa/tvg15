package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Transformable;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.system.Vector3f;
import org.jsfml.system.Vector3i;

/**
 * Utility class
 * @author Paul
 *
 */
public final class Utilities {
	public static final List<GameObject> NO_CHILDREN = new ArrayList<>();
	
	
	// Prevent construction
	private Utilities(){
		
	}
	
	/**
	 * Rounds cubic hexagon coordinates to the nearest hex
	 * @param in Floating-point coordinates
	 * @return Rounded coordinates
	 */
	public static Vector3i hexRound(Vector3f in){
		int rx = Math.round(in.x);
		int ry = Math.round(in.y);
		int rz = Math.round(in.z);
		
		float xDiff = Math.abs(rx - in.x);
		float yDiff = Math.abs(ry - in.y);
		float zDiff = Math.abs(rz - in.z);
		
		if(xDiff > yDiff && xDiff > zDiff){
			rx = -ry-rz;
		}else if(yDiff > zDiff){
			ry = -rx-rz;
		}else{
			rz = -rx-ry;
		}
		return new Vector3i(rx, ry, rz);
	}
	public static Vector2f vectorToFloat(Vector2i in){
		return new Vector2f(in.x, in.y);
	}
	public static Vector2f rotate(Vector2f point, Vector2f center, double angle){
		double dx, dy;
		dx = point.x - center.x;
		dy = point.y - center.y;
		double initial = Math.atan2(dx, dy);
		double radius = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		double new_ = initial - angle;
		Vector2f newp = new Vector2f((float)(Math.sin(new_) * radius), (float)(Math.cos(new_) * radius));
		return newp;
	}
	public static void setX(Transformable obj, float x){
		obj.setPosition(x, obj.getPosition().y);
	}
	public static void setY(Transformable obj, float y){
		obj.setPosition(obj.getPosition().x, y);
	}
	public static Vector2f mapPixelToCoords(Vector2i point, ConstView view){
		FloatRect viewport = view.getViewport();
		float x = -1.f + 2.f * (point.x - viewport.left) / viewport.width;
		float y = 1.f - 2.f * (point.y - viewport.top) / viewport.height;
		System.out.println(x + " " + y);
		return view.getInverseTransform().transformPoint(x, y);
	}
	public static Color colorFromHex(int i){
		return new Color(i >> 16, (i >> 8) & 0xFF, i & 0xFF);
	}
	
	public static <T> T randomFromList(List<T> list){
		return list.get(Game.random.nextInt(list.size()));
	}
	public static <T> T randomFromList(T[] list){
		return list[Game.random.nextInt(list.length)];
	}

}
