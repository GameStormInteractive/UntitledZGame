package com.zgame.world;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zgame.world.components.IComponent;
import com.zgame.world.components.PositionComponent;
import com.zgame.world.components.SpriteComponent;

public class ComponentManager {
	static final int MAX_RENDER_CMPS = 2000;
	static final int MAX_COMPONENTS = 5000;
	
	
	Stack<Integer> availableIDs = new Stack<Integer>();
	IComponent[] components = new IComponent[MAX_COMPONENTS];
	
	public ComponentManager()
	{
		//Populate Queue with list of component IDs
		for(int i = 0; i < MAX_COMPONENTS; i++)
		{
			availableIDs.add(i);
		}
	}
	
	private Integer selectComponentID()
	{
		//Get ID from available pool
		Integer id = availableIDs.pop();
		
		//If no IDs are available, log error and return null
		if(id == null)
		{
			System.out.println("WARNING: Exceeded max component count.");
		}
		return id;
	}
	
	public IComponent createSpriteComponent(Sprite sprite)
	{
		Integer id = selectComponentID();
		
		SpriteComponent spriteCmp = null;
		
		if(id != null)
		{
			spriteCmp = new SpriteComponent(id, sprite);
		}
		else
		{
			System.out.println("WARNING: Unable to create Sprite Component.");
		}
		
		return spriteCmp;
	}
	
	public IComponent createPositionComponent(int x, int y)
	{
		Integer id = selectComponentID();
		
		PositionComponent positionCmp = null;
		
		if(id != null)
		{
			positionCmp = new PositionComponent(id, x, y);
		}
		else
		{
			System.out.println("WARNING: Unable to create Position Component.");
		}
		
		return positionCmp;
	}
}
