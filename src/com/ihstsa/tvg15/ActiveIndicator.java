package com.ihstsa.tvg15;

import org.jsfml.graphics.Color;

public class ActiveIndicator extends Tile {

	public ActiveIndicator(HexGrid grid, int q, int r) {
		super(grid, q, r);
		circleShape.setOutlineColor(new Color(0x10, 0x10, 0xaf, 0x66));
		// TODO Auto-generated constructor stub
	}
	
	public void onmouseover(){
		
	}
	public void onmouseout(){
		
	}

}
