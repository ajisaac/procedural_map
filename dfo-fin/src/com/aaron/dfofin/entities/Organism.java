package com.aaron.dfofin.entities;

import com.aaron.dfofin.Renderer;
import com.aaron.dfofin.S.Direction;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Organism implements Entity{
	int globalX, globalY, localX, localY, chunkX, chunkY;
	int tex;


	@Override
	public void setFacing(Direction dir) {
		
	}
	@Override
	public void addToRendering(Renderer renderer) {
		renderer.entitiesCollection.add(this);
	}
	@Override	
	public void removeFromRendering(Renderer renderer) {
		renderer.entitiesCollection.remove(this);
	}	
	
	public void draw(SpriteBatch batch) {
	}	
	
	@Override
	public void setTex(int charPlayer) {
		tex = charPlayer;		
	}
	@Override
	public int getTex() {
		return tex;
	}	
	@Override
	public void setGlobalX(int x) {
		globalX = x;
	}
	@Override
	public void setGlobalY(int y) {
		globalY = y;
	}
	@Override
	public void setLocalX(int x) {
		localX = x;		
	}
	@Override
	public void setLocalY(int y) {
		localY = y;
	}
	@Override
	public void setChunkX(int x) {
		chunkX = x;		
	}
	@Override
	public void setChunkY(int y) {
		chunkY = y;		
	}
	@Override
	public void move(Direction dir, int speed) {
	}
	@Override
	public int getGlobalX() {
		return globalX;
	}
	@Override
	public int getGlobalY() {
		return globalY;
	}
	@Override
	public int getLocalX() {
		return localX;
	}
	@Override
	public int getLocalY() {
		return localY;
	}
	@Override
	public int getChunkX() {
		return chunkX;
	}
	@Override
	public int getChunkY() {
		return chunkY;
	}

}
