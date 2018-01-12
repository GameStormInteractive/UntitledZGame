package com.zgame.world;

import com.badlogic.gdx.math.Vector2;

public class GameObject {
	protected Vector2 position;
	
	public GameObject()
	{
		position = new Vector2();
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
}
