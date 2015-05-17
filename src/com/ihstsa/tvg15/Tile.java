package com.ihstsa.tvg15;

import java.util.Comparator;
import java.util.List;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class Tile extends GameObject
{
	public static final int SIZE = 150;
	private static final Color outlineColor = new Color(0x10, 0x10, 0xaf, 0x22);
	public AxialVector pos;
	private HexGrid grid;
	CircleShape circleShape;
	//Text text;
	public static Comparator<Tile> rComparator = new Comparator<Tile>(){
		@Override
		public int compare(Tile o1, Tile o2) {
			if(o2.pos.r > o1.pos.r) return -1;
			else if(o2.pos.r < o1.pos.r) return 1;
			
			return 0;
		}
	};
	
	/**
	 * Makes a Tile. This constructor should not be used outside of HexGrid.
	 * Use {@link HexGrid#createTile} to create new tiles.
	 * @param grid The grid the tile will be on.
	 * @param point The point on which the hexagon lies.
	 */
	public Tile(HexGrid grid, AxialVector point)
	{
		this.grid = grid;
		circleShape = new CircleShape(SIZE-3, 6);
		circleShape.setOutlineColor(outlineColor);
		circleShape.setOutlineThickness(3);
		circleShape.rotate(30);
		/*text = new Text("" + point.q + " " + point.r, Game.arial, 24);
		text.setOrigin(0.5f, 0.5f);
		text.setColor(Color.BLACK);*/
		//circleShape.setOutlineColor()
		circleShape.setOrigin(SIZE, SIZE);
		this.setObject(circleShape);
		pos = point;
	}
	/*protected void draw(RenderWindow window, Vector2f offset){
		text.setPosition(offset);
		circleShape.setPosition(offset);
		window.draw(text);
		window.draw(circleShape);
	}*/
	/**
	 * Makes a Tile. This constructor should not be used outside of HexGrid.
	 * Use {@link HexGrid#createTile} to create new tiles.
	 * @param grid The grid the tile will be on.
	 * @param q The q position of the Tile.
	 * @param r The r position of the Tile.
	 */
	public Tile(HexGrid grid, int q, int r)
	{
		this(grid, new AxialVector(q, r));
	}
	
	/**
	 * Gets the pixel position of the Tile's center, as a Vector2f.
	 * @return the center pixel position
	 */
	public Vector2f getPos(){
		return new Vector2f((float) (SIZE * 3./2. * pos.q), (float) (SIZE * Math.sqrt(3) * (pos.r+(pos.q/2.))));
	}
	
	@Override
	public void setPos(Vector2f pos) {
		throw new UnsupportedOperationException("Tile cannot be moved by pixels");
	}

	@Override
	List<GameObject> getChildren() {
		return null;
	}
	
	/**
	 * Gets a tile to the side of this tile.
	 * @param direction direction to go on the grid
	 * @return The neighbor tile
	 */
	public Tile getRelative(HexDirection direction){
		return getRelative(direction, 1);
	}
	
	/**
	 * Gets a tile relative to this tile
	 * @param direction direction to go on the grid
	 * @param n number of tiles to go
	 * @return The relative
	 */
	public Tile getRelative(HexDirection direction, int n){
		return grid.getRelativeTile(this, direction, n);
	}
	
	public HexDirection getDirectionTo(Tile tile){
		int dq = tile.pos.q - this.pos.q;
		int dr = tile.pos.r - this.pos.r;
		if(dq == 0 && dr < 0) return HexDirection.TOP;
		if(dq > 0 && dr < 0) return HexDirection.TOP_RIGHT;
		if(dq > 0 && dr == 0) return HexDirection.BOT_RIGHT;
		if(dq == 0 && dr > 0) return HexDirection.BOT;
		if(dq < 0 && dr > 0) return HexDirection.BOT_LEFT;
		if(dq < 0 && dr == 0) return HexDirection.TOP_LEFT;
		return null;
	}
	
	public void destroy(){

	}

}
