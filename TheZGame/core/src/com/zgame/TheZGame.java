package com.zgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zgame.world.EcsManager;

public class TheZGame extends ApplicationAdapter {
	/*private OrthographicCamera camera;
	SpriteBatch batch;
	TextureAtlas atlas;
	Sprite testSprite;
	Texture img;*/
	//View gameView;
	//World gameWorld;
	
	private OrthographicCamera camera;
	EcsManager ecsManager;
	
	@Override
	public void create () {
		//gameView = new View();
		//gameWorld = new World();
		camera = new OrthographicCamera(1280, 720);
		
		ecsManager = new EcsManager(camera);
		ecsManager.createZombie(120, 220);
		ecsManager.createZombie(300, 100);
		
		//Create Systems
	}

	@Override
	public void render () {
		ecsManager.update();
		//gameView.draw(gameWorld);
		/*
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			testSprite.translateX(-10.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			testSprite.translateX(10.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			testSprite.translateY(10.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			testSprite.translateY(-10.0f);
		}
		*/
	}
	
	@Override
	public void dispose () {
		//gameView.dispose();
		//gameWorld.dispose();
	}
}