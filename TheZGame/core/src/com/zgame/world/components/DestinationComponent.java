package com.zgame.world.components;

public class DestinationComponent extends IComponent{

	private float x;
	private float y;
	
	public DestinationComponent()
	{
		super(ComponentType.DESTINATION);
		reset();
	}
	
	public void init(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void reset() {
		x = 0.0f;
		y = 0.0f;
	}
	
}
