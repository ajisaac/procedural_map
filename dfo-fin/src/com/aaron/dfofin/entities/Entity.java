package com.aaron.dfofin.entities;

import com.aaron.dfofin.Renderer;
import com.aaron.dfofin.S.Direction;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
	void setFacing(Direction dir);
	void move(Direction dir, int speed);
	void removeFromRendering(Renderer renderer);
	void addToRendering(Renderer renderer);
	void setTex(int charPlayer);
	int getTex();
	void draw(SpriteBatch batch);
	void setGlobalX(int x);
	void setGlobalY(int y);
	void setLocalX(int x);
	void setLocalY(int y);
	void setChunkX(int x);
	void setChunkY(int y);
	int getGlobalX();
	int getGlobalY();
	int getLocalX();
	int getLocalY();
	int getChunkX();
	int getChunkY();
}
