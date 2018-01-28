package com.zgame.world.systems;

import com.zgame.world.Signature;

public interface ISystem {

	//Have system check if entity has component set that it needs to process
	public boolean evaluateEntity(Integer entityID, Signature entitySig);
	
	//Execute system logic for frame
	public void update();
	
	//Inform system of entity deletion so it can remove references if necessary
	public void removeEntity(Integer entityID);
}
