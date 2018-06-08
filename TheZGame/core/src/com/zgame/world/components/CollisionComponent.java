package com.zgame.world.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;

public class CollisionComponent extends IComponent{
	
	private Rectangle boundingRect;
	private Body body;
	
	public CollisionComponent()
	{		
		super(ComponentType.COLLISION);
	}
	
	public void init(Rectangle bounds)
	{
		boundingRect = bounds;
	}
	
	public void init(Body body)
	{
		this.body = body;
	}
	
	public Rectangle getBounds()
	{
		return boundingRect;
	}
	
	@Override
	public void reset() 
	{
		boundingRect = new Rectangle(0, 0, 0, 0);
	}
}
