package com.zgame.world.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.zgame.ui.IClickHandler;
import com.zgame.ui.InputManager;
import com.zgame.ui.InputState;
import com.zgame.world.EcsManager;
import com.zgame.world.Signature;
import com.zgame.world.components.ComponentType;


public class UserControlSystem implements ISystem {
	//Reference to EcsManager
	protected EcsManager ecsManager;
	
	protected InputManager inputManager;
	
	//List of component requirements for entities this system processes
	protected List<ComponentType> userCntlCmpReqs;
	
	protected Set<Integer> controlledEntityList;
	
	//Input Handling
	ClickHandler clickHandler;
	UUID clickCallbackId;
	
	public UserControlSystem(EcsManager ecsManager, InputManager inputManager)
	{
		this.inputManager = inputManager;
		
		//Initialize sets of entities to be processed
		controlledEntityList = new HashSet<Integer>();
		
		//Populate lists of components that an entity must have to be processed by this system
		
		//List for user control processing
		userCntlCmpReqs = new ArrayList<ComponentType>();
		userCntlCmpReqs.add(ComponentType.USERCNTL);
		userCntlCmpReqs.add(ComponentType.POSITION);
		
		//Register for input callbacks
		clickHandler = new ClickHandler();
		clickCallbackId = inputManager.subscribeClick(InputState.DOWN, clickHandler);
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean evaluateEntity(Integer entityID, Signature entitySig) {
		boolean matches = false;
		System.out.println("Checking a new entity");
		
		//Add entity to user control processing list if sig matches
		if(entitySig.contains(userCntlCmpReqs))
		{
			matches = true;
			controlledEntityList.add(entityID);
		}
		//Remove otherwise
		else
		{
			controlledEntityList.remove(entityID);
		}
		
		System.out.println("Match: " + matches);
		return matches;
	}

	@Override
	public void removeEntity(Integer entityID) {
		controlledEntityList.remove(entityID);
	}
	
	protected class ClickHandler implements IClickHandler 
	{
		@Override
		public boolean processInput(int x, int y) {
			System.out.println("Clicked at: " + x + ", " + y);
			return true;
		}
	}
}
