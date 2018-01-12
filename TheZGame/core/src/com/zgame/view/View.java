package com.zgame.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.zgame.world.World;

public class View {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private ArrayList<IRenderer> renderers;
	
	public View()
	{
		camera = new OrthographicCamera(1280, 720);
		batch = new SpriteBatch();
		atlas = new TextureAtlas(Gdx.files.internal("testtexture.atlas"));
		
		renderers = new ArrayList<IRenderer>();
		renderers.add(new ZombieRenderer(atlas));
	}
	
	public void draw(World world)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(IRenderer renderer : renderers)
		{
			renderer.render(batch, world);
		}
		batch.end();
	}
	
	public void dispose()
	{
		batch.dispose();
	}

}
