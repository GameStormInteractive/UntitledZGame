package com.zgame.world;

import com.zgame.world.components.ComponentType;

public class Entity {

	private final Integer id;
	private Signature signature;
	
	public Entity (Integer id)
	{
		this.id = id;
	}
	
	public Integer getID()
	{
		return id;
	}
	
	//Add a component to the entity
	public void addComponent(ComponentType cmpType)
	{
		signature.addComponent(cmpType);
	}
	
	//Remove a component from the entity
	public void removeComponent(ComponentType cmpType)
	{
		signature.removeComponent(cmpType);
	}
	
	public Signature getSignature()
	{
		return signature;
	}
	
	public void reset()
	{
		signature.reset();
	}
}
