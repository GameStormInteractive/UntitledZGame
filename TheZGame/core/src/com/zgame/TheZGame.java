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
		
		gameInputManager = new InputManager();
		uiInputManager = new InputManager();
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(uiInputManager);
		multiplexer.addProcessor(gameInputManager);
		Gdx.input.setInputProcessor(multiplexer);
		uiInputManager.activate();
		gameInputManager.activate();
		
		ecsManager = new EcsManager(camera, gameInputManager);
		ecsManager.createZombie(0, 0);
		ecsManager.createZombie(120, 220);
		ecsManager.createBullet(300, 100);
		
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
		gamePage.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin(); //bjr
		font.draw(batch, "Hey Ryan!", 0, 0); //bjr
		batch.end(); //bjr
	}
	
	@Override
	public void dispose () {
		batch.dispose(); //bjr
		font.dispose(); //bjr
	}
}