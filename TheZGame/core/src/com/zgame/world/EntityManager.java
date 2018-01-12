package com.zgame.world;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.zgame.world.components.IComponent;

public class EntityManager {

	static final int MAX_ENTITIES = 1000;
	
	ComponentManager componentManager;
	
	Queue<Integer> availableIDs;
	Entity[] entities;
	
	private TextureAtlas atlas;
	
	public EntityManager()
	{
		componentManager = new ComponentManager();
		
		availableIDs = new ArrayBlockingQueue<Integer>(MAX_ENTITIES);
		entities = new Entity[MAX_ENTITIES];
		
		//Populate Queue with list of entity IDs
		for(int i = 0; i < MAX_ENTITIES; i++)
		{
			availableIDs.add(i);
		}
	}
	
	private Entity createEntity()
	{
		//Get ID from available pool
		Integer id = availableIDs.poll();
		Entity entity = null;
		
		if(id != null)
		{
			entity = entities[id];
			
			//If entity with this id has not been created before, create now
			if(entity == null)
			{
				entity = new Entity();
				entities[id] = entity;
			}
			//If entity exists in pool already, reset entity for reuse
			else
			{
				entity.reset();
			}
			
			entity.setID(id);
		}
		//If no IDs are available, log error and return null
		else
		{
			System.out.println("WARNING: Exceeded max entity count.");
		}
		return entity;
	}
	
	public Entity createZombie(int x, int y)
	{
		Entity zombie = createEntity();
		
		if(zombie != null)
		{
			//Add zombie components
			IComponent posCmp = componentManager.createPositionComponent(x, y);
			zombie.addComponent(posCmp);
			
			AtlasRegion region = atlas.findRegion("TestZombie");
			IComponent spriteCmp = componentManager.createSpriteComponent(new Sprite(region));
			zombie.addComponent(spriteCmp);
			
		}
		else
		{
			System.out.println("WARNING: Unable to create zombie.");
		}
		
		return zombie;
	}
}
