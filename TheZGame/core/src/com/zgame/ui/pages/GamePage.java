package com.zgame.ui.pages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.zgame.ui.IKeyHandler;
import com.zgame.ui.InputManager;
import com.zgame.ui.InputState;

public class GamePage extends UIPage {
	InputManager uiInputManager;
	EscHandler escHandler;
	
	public GamePage (InputManager inputManager)
	{
		uiInputManager = inputManager;
		
		escHandler = new EscHandler();
		
		uiInputManager.subscribeKey(Keys.ESCAPE, InputState.UP, escHandler);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	protected class EscHandler implements IKeyHandler
	{
		@Override
		public boolean processInput() {
			System.out.println("Exit hit");
			Gdx.app.exit();
			return true;
		}
	}

}
