package com.zgame.world.components;

import java.util.Random;

public class VelocityComponent extends IComponent{

	private float xVelocity;
	private float yVelocity;
	private float currentSpeed;
	private float speedMax;
	private float speedMin;
	
	public VelocityComponent()
	{		
		super(ComponentType.VELOCITY);
		reset();
		
		Random random = new Random();
		speedMin = 1.5f;
		speedMax = 2.5f;

		currentSpeed = (speedMax * random.nextFloat()) + speedMin;
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
	
	public float getSpeed()
	{
		return currentSpeed;
	}
}
