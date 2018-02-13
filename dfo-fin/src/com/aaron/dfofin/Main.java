package com.aaron.dfofin;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Main {

	public static void main(String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.allowSoftwareMode = false;
		config.foregroundFPS = 0;
		config.vSyncEnabled = false;

		new LwjglApplication(new Dofin(), config);
	}

}
