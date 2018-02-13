package com.aaron.dfofin.entities;

import com.aaron.dfofin.LT;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Organism {
	public Player(int x, int y){
		this.setGlobalX(x);
		this.setGlobalY(y);
		setTex(LT.charPlayer);
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(LT.charTextures[tex], globalX, globalY);
	}
}
