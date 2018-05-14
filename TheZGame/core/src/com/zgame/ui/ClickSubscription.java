package com.zgame.ui;

public class ClickSubscription 
{
	private InputState state;
	private IClickHandler handler;
	private boolean projectToWorld;
	
	public ClickSubscription(InputState state, boolean projectToWorld, IClickHandler handler)
	{
		this.state = state;
		this.projectToWorld = projectToWorld;
		this.handler = handler;
	}
	

	public InputState getState() {
		return state;
	}
	
	public boolean getProjecToWorld()
	{
		return projectToWorld;
	}

	public IClickHandler getHandler() {
		return handler;
	}
}
