package com.ihstsa.tvg15;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Leaf extends StandardGameObject {
	static Texture leafTexture;
	static Texture enemyLeafTexture;
	Sprite leaf;
	
	static {
		leafTexture = new Texture();
		enemyLeafTexture = new Texture();
		try {
			leafTexture.loadFromFile(Paths.get("res", "Leaf2_green.png"));
			enemyLeafTexture.loadFromFile(Paths.get("res", "Leaf2_purple.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Leaf(boolean enemy)
	{
		super(null);
		leaf = new Sprite(enemy ? enemyLeafTexture : leafTexture);
		leaf.setOrigin(leafTexture.getSize().x / 2f, leafTexture.getSize().y);
		leaf.setScale(.15f, .15f);
		setObject(leaf);
	}
	public void setRotation(float rot){
		leaf.setRotation(rot);
	}
	

}
