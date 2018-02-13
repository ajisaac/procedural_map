package com.aaron.dfofin;

import com.badlogic.gdx.Game;

public class Dofin extends Game{

	public Dofin(){
		super();
	}
	@Override
	public void create() {
		setScreen(new MainMenuScreen(this));		
	}
	@Override
	public void render() {
		super.render();
	}
	public void dispose(){
		super.dispose();
	}

}
