package com.zgame.ui;

public class ClickSubscription 
{
	private InputState state;
	private IClickHandler handler;

	
	public ClickSubscription(InputState state, IClickHandler handler)
	{
		this.state = state;
		this.handler = handler;
	}
	

	public InputState getState() {
		return state;
	}

	public IClickHandler getHandler() {
		return handler;
	}
}
