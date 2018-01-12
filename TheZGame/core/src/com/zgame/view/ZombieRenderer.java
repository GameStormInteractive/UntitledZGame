package com.zgame.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.zgame.world.World;
import com.zgame.world.Zombie;

public class ZombieRenderer implements IRenderer{
	Sprite testSprite;
	
	public ZombieRenderer(TextureAtlas atlas)
	{
		AtlasRegion region = atlas.findRegion("TestZombie");
		testSprite = new Sprite(region);
	}

	public void render(SpriteBatch batch, World world)
	{
		for(Zombie zombie : world.getZombies())
		{
			testSprite.setPosition(zombie.getPosition().x, zombie.getPosition().y);
			testSprite.draw(batch);
		}
	}
	
	public void dispose()
	{
		
	}
}
