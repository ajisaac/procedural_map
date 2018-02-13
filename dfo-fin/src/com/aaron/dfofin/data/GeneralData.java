package com.aaron.dfofin.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class GeneralData implements Serializable{
	private static final long serialVersionUID = -4714772594114703326L;	
	ArrayList<String> games;
	public GeneralData(){
		games = new ArrayList<String>();
	}
	public ArrayList<String> getGames(){
		return games;
	}
	public void saveGenData(){
		String s = System.getProperty("user.home") + "\\dfofin\\genDat.DAT";
		File file = new File(s);
		
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
	public static GeneralData loadGenData(){
		GeneralData g = null;
		String s = System.getProperty("user.home") + "\\dfofin\\genDat.DAT";
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(s));
			g = (GeneralData)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return g;
	}

}
