package com.zgame.world.components;

import com.badlogic.gdx.physics.box2d.Body;

public class PositionComponent extends IComponent{

	private float x;
	private float y;
	private Body body;
	
	public PositionComponent()
	{
		super(ComponentType.POSITION);
		reset();
	}
	
	public void init(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void init(float x, float y, Body body)
	{
		this.x = x;
		this.y = y;
		this.body = body;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
		
		if (body != null)
		{
			body.setTransform(x, this.y, 0);
		}
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
		
		if (body != null)
		{
			body.setTransform(this.x, y, 0);
		}
	}

	@Override
	public void reset() {
		x = 0.0f;
		y = 0.0f;
		
		if (body != null)
		{
			body.setTransform(x, y, 0);
		}
	}
}
