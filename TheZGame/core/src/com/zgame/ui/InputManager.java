package com.zgame.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;


public class InputManager extends InputAdapter implements InputProcessor{
	
	private boolean active;
	
	Map<UUID, KeySubscription> keySubscriptions = new HashMap<UUID, KeySubscription>();
	Map<UUID, ClickSubscription> clickSubscriptions = new HashMap<UUID, ClickSubscription>();
	
	public InputManager()
	{
		active = false;
	}
	
	//Turn this input handler on
	public void activate()
	{
		active = true;
	}
	
	//Turn input handler off
	public void deactivate()
	{
		active = false;
	}
	
	public UUID subscribeKey(int key, InputState state, IKeyHandler keyHandler)
	{
		UUID uuid = UUID.randomUUID();
		keySubscriptions.put(uuid, new KeySubscription(key, state, keyHandler));
		return uuid;
	}
	
	public UUID subscribeClick(InputState state, IClickHandler clickHandler)
	{
		UUID uuid = UUID.randomUUID();
		clickSubscriptions.put(uuid, new ClickSubscription(state, clickHandler));
		return uuid;
	}
	
	//Unsubscribe from key event, return true if successful, false if not subscribed
	public boolean unsubscribeKey(UUID uuid)
	{
		if(keySubscriptions.remove(uuid) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Unsubscribe from click event, return true if successful, false if not subscribed
	public boolean unsubscribeClick(UUID uuid)
	{
		if(clickSubscriptions.remove(uuid) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Subscribe to simple key presses/clicks up and down
	//Subscribe to get click positions up and down
	
	@Override
    public boolean keyDown (int keycode) 
	{
	   if(active == true)
	   {
		   boolean processed = false;
		   for(KeySubscription sub : keySubscriptions.values())
		   {
			   if((sub.getState() == InputState.DOWN) && (sub.getKey() == keycode))
			   {
				   boolean handled = sub.getHandler().processInput();
				   if(processed == false)
				   {
					   processed = handled;
				   }
			   }
		   }
		   return processed;
	   }
	   else
	   {
		   return false;
	   }
	}

	@Override
    public boolean keyUp (int keycode) 
	{
	   if(active == true)
	   {
		   boolean processed = false;
		   for(KeySubscription sub : keySubscriptions.values())
		   {
			   if((sub.getState() == InputState.UP) && (sub.getKey() == keycode))
			   {
				   boolean handled = sub.getHandler().processInput();
				   if(processed == false)
				   {
					   processed = handled;
				   }
			   }
		   }
		   return processed;
	   }
	   else
	   {
		   return false;
	   }
    }
	
   @Override
   public boolean touchDown (int x, int y, int pointer, int button) {
	   if(active == true)
	   {
		   boolean processed = false;
		   for(ClickSubscription sub : clickSubscriptions.values())
		   {
			   if(sub.getState() == InputState.DOWN)
			   {
				   boolean handled = sub.getHandler().processInput(x, y);
				   if(processed == false)
				   {
					   processed = handled;
				   }
			   }
		   }
		   return processed;
	   }
	   else
	   {
		   return false;
	   }
   }
	
	@Override
	public boolean touchUp (int x, int y, int pointer, int button) 
	{
		   if(active == true)
		   {
			   boolean processed = false;
			   for(ClickSubscription sub : clickSubscriptions.values())
			   {
				   if(sub.getState() == InputState.UP)
				   {
					   boolean handled = sub.getHandler().processInput(x, y);
					   if(processed == false)
					   {
						   processed = handled;
					   }
				   }
			   }
			   return processed;
		   }
		   else
		   {
			   return false;
		   }
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) 
	{
	   if(active == true)
	   {
		   return true;
	   }
	   else
	   {
		   return false;
	   }
	}
	
	
	//Utility function to get the current cursor position regardless of clicks
	public Vector2 getCursorPosition()
	{
		return new Vector2(Gdx.input.getX(), Gdx.input.getY());
	}

}
