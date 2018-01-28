package com.zgame.world.systems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zgame.world.Signature;
import com.zgame.world.components.ComponentType;


public class RendererSystem implements ISystem {
	
	//List of component requirements for entities this system processes
	List<ComponentType> spriteCmpsReqs;
	List<ComponentType> animationCmpsReqs;
	
	private Set<Integer> spriteEntityList;
	private Set<Integer> animationEntityList;
	
	public RendererSystem()
	{
		//Initialize sets of entities to be processed
		spriteEntityList = new HashSet<Integer>();
		animationEntityList = new HashSet<Integer>();
		
		//Populate lists of components that an entity must have to be processed by this system
		
		//List for static sprite processing
		spriteCmpsReqs = new ArrayList<ComponentType>();
		spriteCmpsReqs.add(ComponentType.SPRITE);
		spriteCmpsReqs.add(ComponentType.POSITION);
		
		//List for animation processing
		animationCmpsReqs = new ArrayList<ComponentType>();
		animationCmpsReqs.add(ComponentType.ANIMATION);
		animationCmpsReqs.add(ComponentType.POSITION);
	}

	@Override
	public void update() {
		for(Integer entityID : spriteEntityList)
		{
			
		}
	}

	@Override
	public boolean evaluateEntity(Integer entityID, Signature entitySig) {
		boolean matches = false;
		
		//Add entity to static sprite processing list if sig matches
		if(entitySig.contains(spriteCmpsReqs))
		{
			matches = true;
			spriteEntityList.add(entityID);
		}
		//Remove otherwise
		else
		{
			spriteEntityList.remove(entityID);
		}
		
		//Add entity to animation processing list if sig matches
		if(entitySig.contains(animationCmpsReqs))
		{
			matches = true;
			animationEntityList.add(entityID);
		}
		//Remove otherwise
		else
		{
			animationEntityList.remove(entityID);
		}
		return matches;
	}

	@Override
	public void removeEntity(Integer entityID) {
		spriteEntityList.remove(entityID);
		animationEntityList.remove(entityID);
	}
}
