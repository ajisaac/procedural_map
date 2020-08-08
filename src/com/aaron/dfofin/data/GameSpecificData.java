package com.aaron.dfofin.data;

import java.io.Serializable;
import java.util.ArrayList;

public class GameSpecificData implements Serializable {
  private static final long serialVersionUID = -2223383948818307691L;
  private static String gameName;
  private static String gameFolder;
  private static ArrayList<String> chunkMap;

  public GameSpecificData() {
    chunkMap = new ArrayList<String>();
  }

  public void addChunk(int x, int y) {
    chunkMap.add(x + "." + y + ".DAT");
  }

  public boolean containsChunk(int x, int y) {
    String o = Integer.toString(x) + "." + Integer.toString(y) + ".DAT";
    if (chunkMap.contains(o))
      return true;
    else
      return false;
  }

  public int sizeOfChunkMap() {
    return chunkMap.size();
  }

  public ArrayList<String> getChunkMap() {
    return chunkMap;
  }

}
