package com.aaron.dfofin.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.aaron.dfofin.S;

public class Chunk implements Serializable{
	private static final long serialVersionUID = -7729812117069599505L;
	private int size = S.chunkSize;
	private double[][] elevationData;
	private byte[][] heatData, percipData, riverData, biomeData, isLandData, isMountainData;
	private int[][] tileType;
	private int worldCoordOfChunkX, worldCoordOfChunkY;
	public Chunk(int x, int y){
		this.worldCoordOfChunkX = x;
		this.worldCoordOfChunkY = y;
		elevationData = new double[S.chunkSize][S.chunkSize];
		heatData = new byte[S.chunkSize][S.chunkSize];
		percipData = new byte[S.chunkSize][S.chunkSize];
		riverData = new byte[S.chunkSize][S.chunkSize];
		biomeData = new byte[S.chunkSize][S.chunkSize];
		isLandData = new byte[S.chunkSize][S.chunkSize];
		isMountainData = new byte[S.chunkSize][S.chunkSize];
		tileType = new int[S.chunkSize][S.chunkSize];
		
	}
	public void saveChunk(String folder){
		String fileName = folder + "/" + worldCoordOfChunkX + "." + worldCoordOfChunkY + ".dat";
		File file = new File(fileName);
		
		ObjectOutputStream oos;

		try {			
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(this);
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Chunk loadChunk(int x, int y){
		Chunk chunk = null;
		String fileName = Integer.toString(x) + "." + Integer.toString(y) + ".dat";
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			chunk = (Chunk)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return chunk;
	}
	
	public int getX() {
		return worldCoordOfChunkX;
	}
	public int getY() {
		return worldCoordOfChunkY;
	}
	public void setIsLand(int i, int j, byte isLand) {
		isLandData[i][j] = isLand;
		
	}
	public void setIsMountain(int i, int j, byte isMountain) {
		isMountainData[i][j] = isMountain;
		
	}
	public void setElevationHeight(int i, int j, double getmountaindata) {
		elevationData[i][j] = getmountaindata;
	}
	public void setHeat(int i, int j, byte getheatdata) {
		heatData[i][j] = getheatdata;
		
	}
	public void setPercip(int i, int j, byte getpercipdata) {
		percipData[i][j] = getpercipdata;
		
	}
	public void setIsRiver(int i, int j, byte createriverdata) {
		riverData[i][j] = createriverdata;
		
	}
	public void setBiomeType(int i, int j, byte createbiomedata) {
		biomeData[i][j] = createbiomedata;
		
	}
	public void setTileType(int i, int j, int tiletype) {
		tileType[i][j] = tiletype;
		
	}
	public int getTileType(int i, int j){
		return tileType[i][j];
	}
}
