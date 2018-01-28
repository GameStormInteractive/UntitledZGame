package com.zgame.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.zgame.world.components.ComponentType;
import com.zgame.world.components.PositionComponent;
import com.zgame.world.components.SpriteComponent;
import com.zgame.world.systems.ISystem;
import com.zgame.world.systems.RendererSystem;

public class EcsManager {

	//Entity data
	static final int MAX_ENTITIES = 1000;
	Stack<Integer> availableIDs;
	Entity[] entities;
	
	//Component data
	PositionComponent[] positionCmps = new PositionComponent[MAX_ENTITIES];
	SpriteComponent[] spriteCmps = new SpriteComponent[MAX_ENTITIES];
	
	//Systems
	List<ISystem> systems;
	
	//Rendering data
	private TextureAtlas atlas;
	AtlasRegion zombieRegion;
	
	public EcsManager()
	{
		//Initialize Entity array and ID tracker
		availableIDs = new Stack<Integer>();
		entities = new Entity[MAX_ENTITIES];
		
		//Populate Stack with list of entity IDs
		for(int i = 0; i < MAX_ENTITIES; i++)
		{
			availableIDs.add(i);
		}
		
		//Initialize component arrays
		for(int i = 0; i < MAX_ENTITIES; i++)
		{
			positionCmps[i] = new PositionComponent();
			spriteCmps[i] = new SpriteComponent();
		}
		
		//Initialize Systems
		systems = new ArrayList<ISystem>();
		systems.add(new RendererSystem());
		
		//Initialize rendering data
		zombieRegion = atlas.findRegion("TestZombie");
	}
	
	
	
	/* ######################### *
	 * ####### UTILITIES ####### *
	 * ######################### */
	
	private void systemsCheckEntity(Integer entityID)
	{
		//Have systems evaluate new or modified entity to see if they want to process it
		for(ISystem system : systems)
		{
			system.evaluateEntity(entityID, entities[entityID].getSignature());
		}
	}
	
	
	
	/* ################################### *
	 * ####### ENTITY MODIFICATION ####### *
	 * ################################### */
	
	//Add component to an entity
	public void addComponent(Integer entityID, ComponentType cmpType)
	{
		entities[entityID].addComponent(cmpType);
		systemsCheckEntity(entityID);
	}
	
	//Remove component from an entity
	public void removeComponent(Integer entityID, ComponentType cmpType)
	{
		entities[entityID].removeComponent(cmpType);
		systemsCheckEntity(entityID);
	}
	
	
	
	/* ############################## *
	 * ####### ENTITY FACTORY ####### *
	 * ############################## */
	
	//Create a new generic entity with no components
	private Entity createEntity()
	{
		//Get ID from available pool
		Integer id = availableIDs.pop();
		Entity entity = null;
		
		if(id != null)
		{
			entity = entities[id];
			
			//If entity with this id has not been created before, create now
			if(entity == null)
			{
				entity = new Entity(id);
				entities[id] = entity;
			}
		}
		//If no IDs are available, log error and return null
		else
		{
			System.out.println("WARNING: Exceeded max entity count.");
		}
		return entity;
	}
	
	//Delete an entity
	public void deleteEntity(Integer entityID)
	{
		entities[entityID].reset();
		
		//Inform all systems to stop processing the entity
		for(ISystem system : systems)
		{
			system.removeEntity(entityID);
		}
		
		//Put entity back into unused pool
		availableIDs.add(entityID);
	}
	
	//Create a zombie entity
	public Integer createZombie(int x, int y)
	{
		Entity zombie = createEntity();
		
		if(zombie != null)
		{
			//Add zombie components
			//Position
			PositionComponent posCmp = positionCmps[zombie.getID()];
			posCmp.init(x, y);
			zombie.addComponent(posCmp.getType());
			
			//Sprite
			SpriteComponent spriteCmp = spriteCmps[zombie.getID()];
			spriteCmp.init(new Sprite(zombieRegion));
			zombie.addComponent(spriteCmp.getType());
		}
		else
		{
			System.out.println("WARNING: Unable to create zombie.");
		}
		
		systemsCheckEntity(zombie.getID());
		return zombie.getID();
	}
	
	
	
	/* ############################### *
	 * ##### COMPONENT ACCESSORS ##### *
	 * ############################### */
	public PositionComponent getPositionCmp(Integer entityID)
	{
		return positionCmps[entityID];
	}
	
	public SpriteComponent getSpriteCmp(Integer entityID)
	{
		return spriteCmps[entityID];
	}

}
