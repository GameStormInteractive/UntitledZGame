package com.zgame.world.components;

public abstract class IComponent {

	private Integer id;
	private ComponentType cmpType;
	
	public IComponent(Integer id, ComponentType cmpType)
	{
		this.id = id;
		this.cmpType = cmpType;
	}
	
	public int getID()
	{
		return id;
	}
	
	public ComponentType getType()
	{
		return cmpType;
	}
}
