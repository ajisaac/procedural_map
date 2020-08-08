package com.aaron.dfofin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LT {

  public static Texture tileset = new Texture(Gdx.files.internal("assets/tileset.png"));
  public static TextureRegion grass = new TextureRegion(tileset, 0 * S.tileSize, 5 * S.tileSize, 32, 32);
  public static TextureRegion desert = new TextureRegion(tileset, 4 * S.tileSize, 5 * S.tileSize, 32, 32);
  public static TextureRegion mountains = new TextureRegion(tileset, 6 * S.tileSize, 1 * S.tileSize, 32, 32);
  public static TextureRegion water = new TextureRegion(tileset, 0 * S.tileSize, 7 * S.tileSize, 32, 32);
  public static TextureRegion tree = new TextureRegion(tileset, 7 * S.tileSize, 5 * S.tileSize, 32, 32);
  public static TextureRegion tileTextures[] = {grass, desert, mountains, water, tree};
  public static int tileGrass = 0;
  public static int tileDesert = 1;
  public static int tileMountains = 2;
  public static int tileWater = 3;
  public static int tileTree = 4;


  public static Texture chars = new Texture(Gdx.files.internal("assets/chars.png"));
  public static TextureRegion player = new TextureRegion(chars, 0, 0, 32, 32);
  public static TextureRegion charTextures[] = {player};
  public static int charPlayer = 0;

}