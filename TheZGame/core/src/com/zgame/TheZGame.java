package com.zgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zgame.ui.InputManager;
import com.zgame.world.EcsManager;

public class TheZGame extends ApplicationAdapter {
	
	private OrthographicCamera camera;
	InputManager gameInputManager;
	InputManager uiInputManager;
	EcsManager ecsManager;
	
	@Override
	public void create () {
		camera = new OrthographicCamera(1280, 720);
		
		gameInputManager = new InputManager();
		uiInputManager = new InputManager();
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(uiInputManager);
		multiplexer.addProcessor(gameInputManager);
		Gdx.input.setInputProcessor(multiplexer);
		
		ecsManager = new EcsManager(camera, gameInputManager);
		ecsManager.createZombie(120, 220);
		ecsManager.createZombie(300, 100);
		
		//Create Systems
	}

	@Override
	public void render () {
		ecsManager.update();
	}
	
	@Override
	public void dispose () {
	}
}