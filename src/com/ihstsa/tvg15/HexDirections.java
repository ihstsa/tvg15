package com.ihstsa.tvg15;
//    |  /
//    | /
//    |/
//----X---+q
//   /|
//  / |
// /  |
//+r  |
public class HexDirections {
	private Tile t;
	public HexDirections(Tile t_){
		t = t_;
	}
	public Tile up(int n){
		return t.grid.getTile(t.q, t.r-n);
	}
	public Tile down(int n){
		return t.grid.getTile(t.q, t.r+n);
	}
	public Tile nw(int n){
		return t.grid.getTile(t.q-n, t.r);
	}
	public Tile ne(int n){
		return t.grid.getTile(t.q+n, t.r-n);
	}
	public Tile sw(int n){
		return t.grid.getTile(t.q-n, t.r+n);
	}
	public Tile se(int n){
		return t.grid.getTile(t.q+n, t.r);
	}
	public Tile up(){
		return up(1);
	}
	public Tile down(){
		return down(1);
	}
	public Tile nw(){
		return nw(1);
	}
	public Tile ne(){
		return ne(1);
	}
	public Tile sw(){
		return sw(1);
	}
	public Tile se(){
		return se(1);
	}

}
