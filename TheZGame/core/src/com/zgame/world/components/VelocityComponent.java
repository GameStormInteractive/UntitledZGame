package com.zgame.world.components;

public class VelocityComponent extends IComponent{

	private float xVelocity;
	private float yVelocity;
	
	public VelocityComponent()
	{
		super(ComponentType.VELOCITY);
		reset();
	}
	
	public void init(float xVelocity, float yVelocity)
	{
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
	public float getXVelocity() {
		return xVelocity;
	}
	public void setXVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}
	public float getYVelocity() {
		return yVelocity;
	}
	public void setYVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}

	@Override
	public void reset() {
		xVelocity = 0.0f;
		yVelocity = 0.0f;
	}
	
}
