package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

/**
 * A GUI for a tree
 * @author Jacob Silcoff
 */
public class GUI extends GameObject{
	private GUITop topBar;
	private GUIBottom bottomBar;
	private List<GameObject> children;
	public Cursor cursor;
	private Tree tree;
	private Game game;
	public static final int BAR_WIDTH = 1000;
	
	public GUI(Game game) 
	{
		tree = game.tree;
		this.game = game;
		cursor = new Cursor(game);
		bottomBar = new GUIBottom();
		topBar = new GUITop();
		children = new ArrayList<GameObject>();
		children.add(bottomBar);
		children.add(topBar);
	}
	
	public void update(){
		topBar.updateBars();
	}
	
	@Override
	public void render(RenderWindow window, Vector2f offset){
		super.render(window, offset);
		cursor.render(window);
	}

	@Override
	public Vector2f getPos() {
		return Vector2f.ZERO;
	}

	@Override
	/**
	 * Does nothing-- you would never want to move the GUI
	 */
	public void setPos(Vector2f pos){
	}

	@Override
	List<GameObject> getChildren() {
		return children;
	}
	
	private class GUITop extends StandardGameObject
	{
		private LabeledBar glucose;
		private LabeledBar water;
		private LabeledBar light;
		public GUITop()
		{
			super();
			glucose = new LabeledBar("Glucose", Vector2f.ZERO, Color.BLACK, Color.GREEN);
			water = new LabeledBar("Water", Vector2f.ZERO, Color.BLACK, Color.BLUE);
			light = new LabeledBar("Light", Vector2f.ZERO, Color.BLACK, Color.YELLOW);
			
			children.add(glucose);
			children.add(water);
			children.add(light);
			
			game.manager.addHandler(Event.Type.RESIZED, new EventHandler(){
				@Override
				public void handle(Game game, Event event) {
					int gw = game.window.getSize().x-10;
					int g2 = gw - 10;

					glucose.setPos(new Vector2f(5, 5));
					light.setPos(new Vector2f(5 + (gw / 3f), 5));
					water.setPos(new Vector2f(5 + (gw * 2f / 3f), 5));
					
					glucose.setSize(new Vector2f(g2/3f, 30));
					light.setSize(new Vector2f(g2/3f, 30));
					water.setSize(new Vector2f(g2/3f, 30));
				}
			});
		}
		/*@Override
		public Vector2f getPos() {
			return Vector2f.ZERO;
		}

		@Override
		List<GameObject> getChildren() {
			return Utilities.NO_CHILDREN;
		}
		@Override
		public void draw(RenderWindow window, Vector2f offset){
			updateBars();
			for(RectangleShape r : rectangleShapes){
				window.draw(r);
			}
		}*/
		public void updateBars(){
			NutrientManager nm = tree.nutrientManager;
			glucose.setFrac((float) (nm.currentGlucose / nm.maxGlucose));
			water.setFrac((float) (nm.currentWater / nm.maxWater));
			light.setFrac((float) nm.getTimeLightFactor());
		}
	}
	
	private class GUIBottom extends GameObject
	{
		public GUIBottom()
		{
			super();
		}
		@Override
		public Vector2f getPos() {
			//fix this
			return Vector2f.ZERO;
		}

		@Override
		List<GameObject> getChildren() {
			return Utilities.NO_CHILDREN;
		}
	}
}
