package com.ihstsa.tvg15;

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
}
