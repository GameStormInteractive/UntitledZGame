package com.zgame.world;

import java.util.List;

import com.zgame.world.components.ComponentType;

public class Signature {
	static final int length = ComponentType.values().length;
	boolean [] signature = new boolean[length];
	
	public Signature()
	{
		//Initialize the signature as empty
		reset();
	}
	
	//Add a new component to the signature
	public void addComponent(ComponentType cmpType)
	{
		signature[cmpType.getValue()] = true;
	}
	
	//Remove a component from the signature
	public void removeComponent(ComponentType cmpType)
	{
		signature[cmpType.getValue()] = false;
	}
	
	//Check if signature contains all the requisite components
	public boolean contains(List<ComponentType> requiredCmps)
	{
		boolean contains = true;
		
		for(ComponentType cmpType : requiredCmps)
		{
			/*System.out.print("Sig: " );
			for(int i = 0; i < length; i++)
			{
				System.out.print(signature[i]);
			}
			System.out.print("\n");
			System.out.println("Reqs: " + requiredCmps);*/
			if(signature[cmpType.getValue()] == false)
			{
				contains = false;
				break;
			}
		}
		
		return contains;
	}
	
	//Set signature to empty
	public void reset()
	{
		for(int i = 0; i < length; i++)
		{
			signature[i] = false;
		}
	}
}
