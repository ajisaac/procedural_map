package com.aaron.dfofin;

import com.aaron.dfofin.creation.Creation;
import com.aaron.dfofin.data.Chunk;
import com.aaron.dfofin.data.GameSpecificData;
import com.aaron.dfofin.entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen, InputProcessor{

	Dofin game;
	OrthographicCamera camera;
	SpriteBatch batch;
	BitmapFont font = new BitmapFont();
	Creation c;
	Chunk[][] mainChunks;
	Renderer renderer;
	Player player;
	static GameSpecificData gameSpec;
	
	int screenWidth;
	int screenHeight;
	float deltaTime;
	final float UPDATES_PER_SECOND = 25;
	final float TIME_BETWEEN_UPDATES = 1/UPDATES_PER_SECOND;
	float updateCounter = 0;

	public GameScreen(final Dofin game, Chunk[][] ch){
		this.game = game;
		mainChunks = ch;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
		player = new Player(510,510);
		Gdx.input.setInputProcessor(this);
		renderer = new Renderer(this);
		player.addToRendering(renderer);
		gameSpec = new GameSpecificData();
		for(int i = 0; i < S.numOfRenderableChunksX; i++){
			for(int j = 0; j < S.numOfRenderableChunksY; j++){
				if(!gameSpec.containsChunk(ch[i][j].getX(), ch[i][j].getY())){
					gameSpec.addChunk(ch[i][j].getX(), ch[i][j].getY());
				}
			}
		}	
	}

	//the most perfect game loop of all time
	@Override
	public void render(float delta) {
		deltaTime = Gdx.graphics.getDeltaTime(); //time since last frame
		updateCounter += deltaTime;
		if(updateCounter > TIME_BETWEEN_UPDATES){ //if last update was more than 1/25th of a second
			updateCounter =- TIME_BETWEEN_UPDATES; //bring the counter back down to less than 0.04;
		}
		
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		renderer.draw();
		font.draw(batch, Integer.toString(Gdx.graphics.getFramesPerSecond()), camera.position.x -300, camera.position.y -300);
		batch.end();
		
	}


	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.A){
			player.setGlobalX(player.getGlobalX() - 500);
			camera.position.x = player.getGlobalX();
		}
		if(keycode == Input.Keys.D){
			player.setGlobalX(player.getGlobalX() + 500);
			camera.position.x = player.getGlobalX();
		}
		if(keycode == Input.Keys.S){
			player.setGlobalY(player.getGlobalY() - 500);
			camera.position.y = player.getGlobalY();
		}
		if(keycode == Input.Keys.W){
			player.setGlobalY(player.getGlobalY() + 500);
			camera.position.y = player.getGlobalY();
		}
		return false;
	}
	@Override
	public void resize(int width, int height) {		
		/*camera.setToOrtho(false, width, height);
		camera.update();*/
	}
	@Override
	public void pause() {

	}
	@Override
	public void resume() {

	}
	@Override
	public void hide() {

	}
	@Override
	public void dispose() {
		
	}
	@Override
	public void show() {

	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return true;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
