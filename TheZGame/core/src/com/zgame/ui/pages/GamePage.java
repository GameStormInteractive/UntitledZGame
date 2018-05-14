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
		
		// TODO -  Modify this so that the camera moves at a linearly increasing speed the closer the mouse gets to the edge
		int cameraMoveDeltaFar = 40; // Distance from edge before camera starts to move
		int cameraMoveDeltaClose = 30; // Distance from edge before camera starts to move faster

		///////////////////////////////////////
		// Use these to create a linear equation to  move the camera speed faster as the mouse gets closer to the edge
		float minSpeed = 2.0f;
		float maxSpeed = 6.0f;
		//////////////////////////////////////
		
		float moveAmountX = 0.0f;
		float moveAmountY = 0.0f;
		
		if (mousePosWorld.x < cameraMoveDeltaFar)
		{
			if (mousePosWorld.x < cameraMoveDeltaClose)
			{
				moveAmountX = -maxSpeed;
			}
			else
			{
				moveAmountX = -minSpeed;
			}
		}
		else if (mousePosWorld.x > Gdx.graphics.getWidth() - cameraMoveDeltaFar)
		{
			if (mousePosWorld.x > Gdx.graphics.getWidth() - cameraMoveDeltaClose)
			{
				moveAmountX = maxSpeed;
			}
			else
			{
				moveAmountX = minSpeed;
			}
		}
		else if (mousePosWorld.y < cameraMoveDeltaFar)
		{
			if (mousePosWorld.y < cameraMoveDeltaClose)
			{
				moveAmountY = maxSpeed;
			}
			else
			{
				moveAmountY = minSpeed;
			}
		}
		else if (mousePosWorld.y > Gdx.graphics.getHeight() - cameraMoveDeltaFar)
		{
			if (mousePosWorld.y > Gdx.graphics.getHeight() - cameraMoveDeltaClose)
			{
				moveAmountY = -maxSpeed;
			}
			else
			{
				moveAmountY = -minSpeed;
			}
		}
		
		camera.translate(moveAmountX, moveAmountY);

		camera.update();	
	}
}
