package com.zgame.world.components;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends IComponent{
	
	private Sprite sprite;
	
	public SpriteComponent(Integer id, Sprite sprite)
	{
		super(id, ComponentType.SPRITE);
		
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
