package com.zgame.world.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.badlogic.gdx.math.Vector2;
import com.zgame.ui.IClickHandler;
import com.zgame.ui.InputManager;
import com.zgame.ui.InputState;
import com.zgame.world.EcsManager;
import com.zgame.world.Signature;
import com.zgame.world.components.ComponentType;
import com.zgame.world.components.DestinationComponent;
import com.zgame.world.components.PositionComponent;
import com.zgame.world.components.VelocityComponent;


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
		this.ecsManager = ecsManager;
		this.inputManager = inputManager;
		
		//Initialize sets of entities to be processed
		controlledEntityList = new HashSet<Integer>();
		
		//Populate lists of components that an entity must have to be processed by this system
		
		//List for user control processing
		userCntlCmpReqs = new ArrayList<ComponentType>();
		userCntlCmpReqs.add(ComponentType.USERCNTL);
		userCntlCmpReqs.add(ComponentType.POSITION);
		userCntlCmpReqs.add(ComponentType.VELOCITY);
		
		//Register for input callbacks
		clickHandler = new ClickHandler();
		clickCallbackId = inputManager.subscribeClick(InputState.DOWN, true, clickHandler);
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean evaluateEntity(Integer entityID, Signature entitySig) {
		boolean matches = false;
		
		//Add entity to user control processing list if sig matches
		if(entitySig.contains(userCntlCmpReqs))
		{
			matches = true;
			controlledEntityList.add(entityID);
			System.out.println("User Controlled Entity Found: " + entityID);
		}
		//Remove otherwise
		else
		{
			controlledEntityList.remove(entityID);
		}
		
		return matches;
	}

	@Override
	public void removeEntity(Integer entityID) {
		controlledEntityList.remove(entityID);
	}
	
	protected class ClickHandler implements IClickHandler 
	{
		@Override
		public boolean processInput(float x, float y) {
			
			//Calculate velocity to move entity toward click point and apply to entity
			for(Integer entityID : controlledEntityList)
			{
				System.out.println("Entity: " + entityID);
				
				//Position
				PositionComponent posComp = ecsManager.getPositionCmp(entityID);
				System.out.println("X Veloc = " + x + " - " + posComp.getX());
				System.out.println("Y Veloc = " + y + " - " + posComp.getY());
				Vector2 dir = new Vector2(x - posComp.getX(), y - posComp.getY());
				dir.nor();
				float moveSpeed = 2.5f;
				float xVeloc = (moveSpeed * dir.x);
				float yVeloc = (moveSpeed * dir.y);
				
				//Velocity
				VelocityComponent velComp = ecsManager.getVelocityComponent(entityID);
				velComp.setXVelocity(xVeloc);
				velComp.setYVelocity(yVeloc);
				System.out.println("Setting xVeloc: " + xVeloc);
				System.out.println("Setting yVeloc: " + yVeloc);
				
				//Destination
				DestinationComponent desComp = ecsManager.getDestinationComponent(entityID);
				desComp.setX(x);
				desComp.setY(y);
				System.out.println("Setting destination X: " + x);
				System.out.println("Setting destination Y: " + y);				
			}

			System.out.println("Clicked at: " + x + ", " + y);
			return true;
		}
	}
}
