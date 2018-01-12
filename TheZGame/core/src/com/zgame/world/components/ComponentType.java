package com.zgame.world.components;

public enum ComponentType {
	POSITION(0),
	VELOCITY(1),
	SPRITE(2),
	ANIMATION(3),
	SOLIDBODY(4),
	USERCNTL(5);
	
	private int value;
	
	private ComponentType(int value)
	{
		this.value = value;
	}
	
	//Allow access to the numerical value of the enum
	public int getValue()
	{
		return value;
	}
}
