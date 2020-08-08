package com.aaron.dfofin.creation;

import com.aaron.dfofin.S;
import com.aaron.dfofin.data.Chunk;

/**
 * @author aaron
 *	Simply sets up the game chunks and saves them then returns 
 *	an array of chunks to the main thread before finishing
 */
public class CreateNewGame implements Runnable{
	private Chunk[][] ch;
	private boolean isDone = false;

	@Override
	public void run() {
		ch = new Chunk[S.numOfRenderableChunksX][S.numOfRenderableChunksY];
		Creation c = new Creation();
		for(int i = 0; i < S.numOfRenderableChunksX; i++){
			for(int j = 0; j < S.numOfRenderableChunksY; j++){
				ch[i][j] = c.createChunk(i, j);
				ch[i][j].saveChunk(S.gameName);
			}
		}
		isDone = true;
	}
	
	public synchronized Chunk[][] getArray(){
		if(!isDone)
			return null;
		else
			return ch;
	}
}
