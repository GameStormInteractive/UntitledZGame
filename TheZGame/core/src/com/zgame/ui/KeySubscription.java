package com.zgame.ui;

public class KeySubscription 
{
	private int key;
	private InputState state;
	private IKeyHandler handler;

	
	public KeySubscription(int key, InputState state, IKeyHandler handler)
	{
		this.key = key;
		this.state = state;
		this.handler = handler;
	}
	
	public int getKey() {
		return key;
	}

	public InputState getState() {
		return state;
	}

	public IKeyHandler getHandler() {
		return handler;
	}
}
