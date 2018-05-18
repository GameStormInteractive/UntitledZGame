package com.zgame.world.components;

public enum ComponentType {
	POSITION(0),
	VELOCITY(1),
	DESTINATION(2),
	SPRITE(3),
	ANIMATION(4),
	SOLIDBODY(5),
	USERCNTL(6),
	ZOMBIFIER(7);	//EMPTY CMP
	
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
