package com.zgame.ui.pages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.zgame.ui.IKeyHandler;
import com.zgame.ui.InputManager;
import com.zgame.ui.InputState;

public class GamePage extends UIPage {
	OrthographicCamera camera;
	InputManager uiInputManager;
	EscHandler escHandler;
	
	public GamePage (OrthographicCamera gameCamera, InputManager inputManager)
	{
		camera = gameCamera;
		
		uiInputManager = inputManager;
		
		escHandler = new EscHandler();
		
		uiInputManager.subscribeKey(Keys.ESCAPE, InputState.UP, escHandler);
	}

	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		updateCameraPosition();
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
	
	protected void updateCameraPosition()
	{
		Vector3 mousePosWorld = uiInputManager.getCursorPosition(); // gives display location of mouse
		//camera.unproject(mousePosWorld); // gives world location of mouse
		
		int cameraMoveDelta = 20; // Distance from edge before camera starts to move
		float moveAmount = 1.0f;
		
		if (mousePosWorld.x < cameraMoveDelta)
		{
			System.out.println("Move Left");
			camera.translate(-moveAmount, 0.0f);
		}
		else if (mousePosWorld.x > Gdx.graphics.getWidth() - cameraMoveDelta)
		{
			System.out.println("Move Right");
			camera.translate(moveAmount, 0.0f);
		}
		else if (mousePosWorld.y < cameraMoveDelta)
		{
			System.out.println("Move Up");
			camera.translate(0.0f, moveAmount);
		}
		else if (mousePosWorld.y > Gdx.graphics.getHeight() - cameraMoveDelta)
		{
			System.out.println("Move Down");
			camera.translate(0.0f, -moveAmount);
		}
		else
		{
			//System.out.println("Do nothing");
		}
		camera.update();
		
		//System.out.println("Camera X: " + camera.position.x);
		//System.out.println("Camera Y: " + camera.position.y);
	}

}
