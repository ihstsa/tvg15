package com.ihstsa.tvg15;

/**
 * Directions on a flat-topped hexagon
 * @author Jacob
 *
 */
public enum HexDirection
{
	TOP,
	TOP_RIGHT,
	BOT_RIGHT,
	BOT,
	BOT_LEFT,
	TOP_LEFT;
	public int getAngle(){
		return this.ordinal() * 60;
	} 
}