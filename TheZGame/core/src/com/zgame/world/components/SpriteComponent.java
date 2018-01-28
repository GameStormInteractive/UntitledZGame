package com.zgame.world.components;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends IComponent{
	
	private Sprite sprite;
	
	public SpriteComponent()
	{
		super(ComponentType.SPRITE);
		reset();
	}
	
	public void init(Sprite sprite)
	{
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void reset() {
		sprite = null;
	}
}
