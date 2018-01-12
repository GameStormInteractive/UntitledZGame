package com.zgame.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zgame.world.World;

public interface IRenderer {

	public void render(SpriteBatch batch, World world);
	
	public void dispose();
}
