package com.zgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.zgame.ui.InputManager;
import com.zgame.ui.pages.GamePage;
import com.zgame.ui.pages.UIPage;
import com.zgame.world.EcsManager;
import com.zgame.world.listeners.EntityContactListener;

public class TheZGame extends ApplicationAdapter {
	// World and camera
	private World world;
	private OrthographicCamera camera;

	// Physics and collision needed objects
	private Map map;
	private EntityContactListener entityContactListener;

	// Manager objects
	private InputManager gameInputManager;
	private InputManager uiInputManager;
	private EcsManager   ecsManager;
		
	//UI Pages
	GamePage gamePage;
	UIPage   activePage;
	
	// Misc, may delete later, used for testing
	private SpriteBatch batch; //bjr
	private BitmapFont font; //bjr
	
	@Override
	public void create () {
		world = new World(null, false);
		camera = new OrthographicCamera(1280, 720);
		entityContactListener = new EntityContactListener();
		
		gameInputManager = new InputManager(camera);
		uiInputManager = new InputManager(camera);
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(uiInputManager);
		multiplexer.addProcessor(gameInputManager);
		Gdx.input.setInputProcessor(multiplexer);
		uiInputManager.activate();
		gameInputManager.activate();
		
		ecsManager = new EcsManager(camera, gameInputManager, world, entityContactListener);
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