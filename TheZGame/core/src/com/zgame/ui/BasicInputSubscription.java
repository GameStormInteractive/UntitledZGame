package com.zgame.ui;

public class BasicInputSubscription implements IInputSubscription
{
	private IInputHandler handler;
	private SubscriptionType subType;
	
	public BasicInputSubscription(SubscriptionType subType, IInputHandler handler)
	{
		this.subType = subType;
		this.handler = handler;
	}

	@Override
	public IInputHandler getHandler() {
		return handler;
	}

	@Override
	public SubscriptionType getSubType() {
		return subType;
	}

}
