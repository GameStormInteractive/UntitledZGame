package com.zgame.world.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.zgame.world.EcsManager;
import com.zgame.world.Signature;
import com.zgame.world.components.ComponentType;


public class CollisionSystem implements ISystem {
	//Reference to EcsManager
	EcsManager ecsManager;
	
	//List of component requirements for entities this system processes
	List<ComponentType> collisionCmpsReqs;
	
	private Set<Integer> collisionEntityList;
	
	private OrthographicCamera camera;
	
	public CollisionSystem(EcsManager ecsManager, OrthographicCamera camera)
	{
		this.ecsManager = ecsManager;
		
		//Initialize sets of entities to be processed
		collisionEntityList = new HashSet<Integer>();
		
		//Populate lists of components that an entity must have to be processed by this system
		
		//List for collision processing
		collisionCmpsReqs = new ArrayList<ComponentType>();
		collisionCmpsReqs.add(ComponentType.COLLISION);
		collisionCmpsReqs.add(ComponentType.POSITION);
		
		this.camera = camera;
	}

	@Override
	public void update() 
	{
		for(Integer entityID : collisionEntityList)
		{
			System.out.println("EntityID: " + entityID);
		}
	}

	@Override
	public boolean evaluateEntity(Integer entityID, Signature entitySig) 
	{
		boolean matches = false;
		
		//Add entity to collision processing list if sig matches
		if(entitySig.contains(collisionCmpsReqs))
		{
			matches = true;
			collisionEntityList.add(entityID);
		}
		//Remove otherwise
		else
		{
			removeEntity(entityID);
		}
		
		return matches;
	}

	@Override
	public void removeEntity(Integer entityID) 
	{
		collisionEntityList.remove(entityID);
	}
}
