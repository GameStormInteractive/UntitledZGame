package com.zgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.zgame.view.View;
import com.zgame.world.World;

public class TheZGame extends ApplicationAdapter {
	/*private OrthographicCamera camera;
	SpriteBatch batch;
	TextureAtlas atlas;
	Sprite testSprite;
	Texture img;*/
	View gameView;
	World gameWorld;
	
	@Override
	public void create () {
		/*camera = new OrthographicCamera(1280, 720);
		batch = new SpriteBatch();
		
		atlas = new TextureAtlas(Gdx.files.internal("testtexture.atlas"));
		AtlasRegion region = atlas.findRegion("TestZombie");
		testSprite = new Sprite(region);
		testSprite.setPosition(120, 100);
		//testSprite.scale(2.5f);
		
		img = new Texture("badlogic.jpg");*/
		gameView = new View();
		gameWorld = new World();
	}

	@Override
	public void render () {
		gameView.draw(gameWorld);
		/*Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
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
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		testSprite.draw(batch);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		gameView.dispose();
		gameWorld.dispose();
		//batch.dispose();
		//img.dispose();
	}
}