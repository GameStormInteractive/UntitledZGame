package com.thez.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zgame.TheZGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Z Game";
		config.fullscreen = false;
		config.width = 640;
		config.height = 360;
		config.resizable = false;
		config.fullscreen = true;
		new LwjglApplication(new TheZGame(), config);
	}
}
