package com.aaron.dfofin;

import java.util.ArrayList;

import com.aaron.dfofin.entities.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {

	GameScreen g;
	SpriteBatch batch;
	int x = 0;
	int y = 0;
	Entity cE;	
	public ArrayList<Entity> entitiesCollection = new ArrayList<Entity>();
	
	public Renderer(GameScreen gameScreen) {
		this.g = gameScreen;
		batch = g.batch;
	}

	public void draw() {
		for(int k = 0; k < S.numOfRenderableChunksX; k++){
			for(int l = 0; l < S.numOfRenderableChunksY; l++){
				for(int i = 0; i < S.chunkSize; i++){
					for(int j = 0; j < S.chunkSize; j++){						
						batch.draw(LT.tileTextures[g.mainChunks[k][l].getTileType(i, j)], (k*S.chunkSize*32)+i*32, (l*S.chunkSize*32)+j*32);
					}			
				}
			}
		}
		for(int i = 0; i < entitiesCollection.size(); i++){
			entitiesCollection.get(i).draw(batch);
		}
	}	
}
