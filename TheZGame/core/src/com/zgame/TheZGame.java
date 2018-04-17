package com.zgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zgame.ui.InputManager;
import com.zgame.world.EcsManager;
import com.badlogic.gdx.graphics.Color; //bjr
import com.badlogic.gdx.graphics.g2d.SpriteBatch; //bjr
import com.badlogic.gdx.graphics.g2d.BitmapFont; //bjr


public class TheZGame extends ApplicationAdapter {
	
	private OrthographicCamera camera;
	InputManager gameInputManager;
	InputManager uiInputManager;
	EcsManager ecsManager;
	
	private SpriteBatch batch; //bjr
	private BitmapFont font; //bjr
	
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
		
		batch = new SpriteBatch(); //bjr
		font = new BitmapFont(); //bjr
		font.setColor(Color.RED); //bjr
		font.getData().setScale(2); //bjr
		
		//Create Systems
	}

	@Override
	public void render () {
		ecsManager.update();
		
		batch.begin(); //bjr
		font.draw(batch, "Hey Ryan!", 200, 250); //bjr
		batch.end(); //bjr
	}
	
	@Override
	public void dispose () {
		batch.dispose(); //bjr
		font.dispose(); //bjr
	}
}