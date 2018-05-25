package com.zgame.world.components;

public enum ComponentType {
	POSITION(0),
	VELOCITY(1),
	DESTINATION(2),
	COLLISION(3),
	SPRITE(4),
	ANIMATION(5),
	SOLIDBODY(6),
	USERCNTL(7),
	ZOMBIFIER(8);	//EMPTY CMP
	
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
