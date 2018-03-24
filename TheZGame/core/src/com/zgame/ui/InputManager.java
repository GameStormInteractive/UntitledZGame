package com.zgame.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;


public class InputManager extends InputAdapter implements InputProcessor{
	
	public static final int MaxSubTypes = SubscriptionType.values().length;
	
	@SuppressWarnings("unchecked")
	Map<UUID, IInputSubscription> [] basicSubscriberMap = new HashMap[MaxSubTypes];
	
	public InputManager()
	{
	}
	
	public UUID subscribe(SubscriptionType subType, IInputSubscription sub)
	{
		UUID uuid = UUID.randomUUID();
		basicSubscriberMap[subType.getValue()].put(uuid, sub);
		return uuid;
	}
	
   @Override
   public boolean touchDown (int x, int y, int pointer, int button) {
      // your touch down code here
      return false; 
   }
	
	@Override
	public boolean touchUp (int x, int y, int pointer, int button) 
	{
		return false;
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) 
	{
		return false;
	}

}
