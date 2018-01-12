package com.zgame.world;

import java.util.Map;

import com.zgame.world.components.IComponent;

public class Entity {

	private Integer id;
	private Map<Integer, IComponent> components;
	private Signature signature;
	
	public Integer getID()
	{
		return id;
	}
	
	public void setID(Integer id)
	{
		this.id = id;
	}
	
	//Add a component to the entity
	public void addComponent(IComponent component)
	{
		components.put(component.getID(), component);
		signature.addComponent(component.getType());
	}
	
	public boolean removeComponent(int componentID)
	{
		if(components.remove(componentID) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public Signature getSignature()
	{
		return signature;
	}
	
	public void reset()
	{
		id = -1;
		
		components.clear();
		signature.reset();
	}
}
