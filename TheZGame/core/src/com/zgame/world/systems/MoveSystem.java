package com.zgame.world.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zgame.world.EcsManager;
import com.zgame.world.Signature;
import com.zgame.world.components.ComponentType;
import com.zgame.world.components.DestinationComponent;
import com.zgame.world.components.PositionComponent;
import com.zgame.world.components.VelocityComponent;


public class MoveSystem implements ISystem 
{
	//Reference to EcsManager
	EcsManager ecsManager;

	//List of component requirements for entities this system processes
	List<ComponentType> moveCmpsReqs;

	private Set<Integer> mobileEntityList;


	public MoveSystem(EcsManager ecsManager)
	{
		this.ecsManager = ecsManager;

		//Initialize sets of entities to be processed
		mobileEntityList = new HashSet<Integer>();

		//Populate lists of components that an entity must have to be processed by this system

		//List for movement processing
		moveCmpsReqs = new ArrayList<ComponentType>();
		moveCmpsReqs.add(ComponentType.POSITION);
		moveCmpsReqs.add(ComponentType.VELOCITY);
		moveCmpsReqs.add(ComponentType.DESTINATION);

	}

	@Override
	public void update() 
	{		
		updateMovement();
		checkCollisions();
	}

	@Override
	public boolean evaluateEntity(Integer entityID, Signature entitySig) 
	{
		boolean matches = false;

		//Add entity to movements processing list if sig matches
		if(entitySig.contains(moveCmpsReqs))
		{
			matches = true;
			mobileEntityList.add(entityID);
		}
		//Remove otherwise
		else
		{
			mobileEntityList.remove(entityID);
		}

		return matches;
	}

	@Override
	public void removeEntity(Integer entityID) 
	{
		mobileEntityList.remove(entityID);
	}

	private void updateMovement()
	{
		for(Integer entityID : mobileEntityList)
		{
			PositionComponent posComp = ecsManager.getPositionCmp(entityID);
			VelocityComponent velComp = ecsManager.getVelocityComponent(entityID);
			DestinationComponent desComp = ecsManager.getDestinationComponent(entityID);
			posComp.setX(posComp.getX() + velComp.getXVelocity());
			posComp.setY(posComp.getY() + velComp.getYVelocity());

			float stopDistance = 10.0f;
			if ( (Math.abs(posComp.getX() - desComp.getX()) < stopDistance) &&
					(Math.abs(posComp.getY() - desComp.getY()) < stopDistance) )
			{
				velComp.setXVelocity(0.0f);
				velComp.setYVelocity(0.0f);
			}
		}
	}

	private void checkCollisions()
	{
		for (Integer entityID : mobileEntityList)
		{
			
		}
	}
}
