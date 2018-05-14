package com.zgame.world.components;

public class VelocityComponent extends IComponent{

	private int xVelocity;
	private int yVelocity;
	
	public VelocityComponent()
	{
		super(ComponentType.VELOCITY);
		reset();
	}
	
	public void init(int xVelocity, int yVelocity)
	{
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
	public int getXVelocity() {
		return xVelocity;
	}
	public void setXVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	public int getYVelocity() {
		return yVelocity;
	}
	public void setYVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	@Override
	public void reset() {
		xVelocity = 0;
		yVelocity = 0;
	}
	
}
