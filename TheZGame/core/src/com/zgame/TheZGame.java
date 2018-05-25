package com.zgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.zgame.ui.InputManager;
import com.zgame.ui.pages.GamePage;
import com.zgame.ui.pages.UIPage;
import com.zgame.world.EcsManager;
import com.badlogic.gdx.graphics.Color; //bjr
import com.badlogic.gdx.graphics.g2d.SpriteBatch; //bjr
import com.badlogic.gdx.graphics.g2d.BitmapFont; //bjr


public class TheZGame extends ApplicationAdapter {
	
	private OrthographicCamera camera;
	InputManager gameInputManager;
	InputManager uiInputManager;
	EcsManager ecsManager;
	
	//UI Pages
	GamePage gamePage;
	UIPage activePage;
	
	private SpriteBatch batch; //bjr
	private BitmapFont font; //bjr
	
	@Override
	public void create () {
		camera = new OrthographicCamera(1280, 720);
		
		gameInputManager = new InputManager(camera);
		uiInputManager = new InputManager(camera);
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(uiInputManager);
		multiplexer.addProcessor(gameInputManager);
		Gdx.input.setInputProcessor(multiplexer);
		uiInputManager.activate();
		gameInputManager.activate();
		
		ecsManager = new EcsManager(camera, gameInputManager);
		float maxRows = 10.0f;
		float maxCols = 10.0f;
		float width = 128.0f;
		float height = 128.0f;
		float startX = 0.0f - (maxCols/2)*width;
		float startY = 0.0f - (maxRows/2)*height;
		
		for (int i = 0; i < maxRows; i++)
		{
			for (int j = 0; j < maxCols; j++)
			{
				ecsManager.createZombie(startX + (j*width), startY + (i * height), ecsManager.zombieRegion2);
			}
		}
		//ecsManager.createZombie(0.0f, 0.0f, ecsManager.zombieRegion2);
		//ecsManager.createZombie(120.0f, 220.0f, ecsManager.zombieRegion2);
		//ecsManager.createZombie(-200.0f, 100.0f, ecsManager.zombieRegion2);
		//ecsManager.createBullet(300.0f, 100.0f);
		
		batch = new SpriteBatch(); //bjr
		font = new BitmapFont(); //bjr
		font.setColor(Color.RED); //bjr
		font.getData().setScale(5); //bjr
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear); //bjr
		
		//Create UI Pages
		gamePage = new GamePage(camera, uiInputManager);
		activePage = gamePage;
	}

	@Override
	public void render () {
		activePage.update();
		ecsManager.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose(); //bjr
		font.dispose(); //bjr
	}
}