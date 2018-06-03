package com.zgame.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.zgame.world.components.ComponentType;

public class Entity {

	private final Integer id;
	private Signature signature;
	private BodyDef bodyDef;
	private Body body;
	private FixtureDef fixtureDef;
	private Fixture fixture;
	
	
	public Entity (Integer id)
	{
		this.id = id;
		signature = new Signature();
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
	
	public void setupPhysics(BodyDef bodyDef, FixtureDef fixtureDef)
	{
		this.bodyDef = bodyDef;
		this.fixtureDef = fixtureDef;
		
		
	}
}
