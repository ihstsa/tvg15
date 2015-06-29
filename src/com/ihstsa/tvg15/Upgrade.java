package com.ihstsa.tvg15;

public enum Upgrade {
	NEW_NORMAL_TILE (100),
	NEW_LEAF_TILE (150),
	
	ADD_LEAF (20);
	
	public final int cost;
	private Upgrade(int x){
		cost = x;
	}
}
