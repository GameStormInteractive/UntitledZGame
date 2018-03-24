package com.zgame.ui;

public enum SubscriptionType {
	CLICK_UP(0),
	CLICK_DOWN(1);	//EMPTY CMP
	
	private int value;
	
	private SubscriptionType(int value)
	{
		this.value = value;
	}
	
	//Allow access to the numerical value of the enum
	public int getValue()
	{
		return value;
	}
}
