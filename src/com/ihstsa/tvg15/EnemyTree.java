package com.ihstsa.tvg15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsfml.window.event.Event;

public class EnemyTree extends Tree {
	static HexDirection ds[] = {HexDirection.TOP_LEFT, HexDirection.TOP, HexDirection.TOP_RIGHT};

	public EnemyTree(HexGrid grid) {
		super(grid);
		// TODO Auto-generated constructor stub
		Game.instance.manager.addHandler(null, new EventHandler(){
			@Override
			public void handle(Game game, Event event) {
				updatePossibleUpgrades();
				System.out.println(possibleUpgrades.size());
				if(possibleUpgrades.size() < 3) return;
				Upgrade u = Utilities.randomFromList(possibleUpgrades);
				System.out.println(u);
				if(u == Upgrade.ADD_LEAF){
					List<LeafTile> x = new ArrayList<>();
					for(Tile t : tiles){
						if(t instanceof LeafTile) x.add((LeafTile)t);
					}
					if(x.size() == 0) return;
					LeafTile t = Utilities.randomFromList(x);
					if(t.addLeaves(5)){
						nutrientManager.currentGlucose -= Upgrade.ADD_LEAF.cost;
					}
					return;
				}
				List<Pair<TreeTile, Tile>> x = new ArrayList<>();
				for(TreeTile t : tiles){
					Tile t2;
					int i = 0;
					do {
						t2 = t.getRelative(Utilities.randomFromList(ds));
						i++;
					} while(t2 instanceof TreeTile && i < 30);
					if(t2 instanceof TreeTile) continue;
					x.add(new Pair<>(t, t2));
				}
				Pair<TreeTile, Tile> t = Utilities.randomFromList(x);
				if(t.b == null) return;
				if(u == Upgrade.NEW_NORMAL_TILE){
					new TreeTile(Game.instance.grid, t.b.pos, t.a, true);
				}else{
					new LeafTile(Game.instance.grid, t.b.pos, t.a, true);
				}
			}
		});
	}

}
