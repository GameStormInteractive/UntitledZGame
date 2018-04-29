package com.zgame.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class KeySubscription 
{
	private Keys key;
	private InputState state;
	private IKeyHandler handler;

	
	public KeySubscription(Keys key, InputState state, IKeyHandler handler)
	{
		this.key = key;
		this.state = state;
		this.handler = handler;
	}
	
	public Input.Keys getKey() {
		return key;
	}

	public InputState getState() {
		return state;
	}

	public IKeyHandler getHandler() {
		return handler;
	}
}
