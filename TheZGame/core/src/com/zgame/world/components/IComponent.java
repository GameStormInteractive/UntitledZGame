package com.zgame.world.components;

public abstract class IComponent {

	private ComponentType cmpType;
	
	public IComponent(ComponentType cmpType)
	{
		this.cmpType = cmpType;
	}
	
	public ComponentType getType()
	{
		return cmpType;
	}
	
	public abstract void reset();
}
