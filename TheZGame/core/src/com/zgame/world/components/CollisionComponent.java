package com.zgame.world.components;

import com.badlogic.gdx.math.Rectangle;

public class CollisionComponent extends IComponent{
	
	private Rectangle boundingRect;
	
	public CollisionComponent()
	{		
		super(ComponentType.COLLISION);
	}
	
	public void init(Rectangle bounds)
	{
		boundingRect = bounds;
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
