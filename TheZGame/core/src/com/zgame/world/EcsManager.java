package com.zgame.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.zgame.ui.InputManager;
import com.zgame.world.components.CollisionComponent;
import com.zgame.world.components.ComponentType;
import com.zgame.world.components.DestinationComponent;
import com.zgame.world.components.PositionComponent;
import com.zgame.world.components.SpriteComponent;
import com.zgame.world.components.VelocityComponent;
import com.zgame.world.listeners.EntityContactListener;
import com.zgame.world.systems.CollisionSystem;
import com.zgame.world.systems.ISystem;
import com.zgame.world.systems.MoveSystem;
import com.zgame.world.systems.RendererSystem;
import com.zgame.world.systems.UserControlSystem;

public class EcsManager {

	// Reference objects
	private World world;
	private EntityContactListener entityContactListener;
	
	//Entity data
	static final int MAX_ENTITIES = 1000;
	Stack<Integer> availableIDs;
	Entity[] entities;

	//Component data
	PositionComponent[] positionCmps = new PositionComponent[MAX_ENTITIES];
	SpriteComponent[] spriteCmps = new SpriteComponent[MAX_ENTITIES];
	VelocityComponent[] velocityCmps = new VelocityComponent[MAX_ENTITIES];
	DestinationComponent[] destinationCmps = new DestinationComponent[MAX_ENTITIES];
	CollisionComponent[] collisionCmps = new CollisionComponent[MAX_ENTITIES];

	//Systems
	List<ISystem> systems;

	//Rendering data
	private TextureAtlas atlas;
	AtlasRegion zombieRegion;
	public AtlasRegion zombieRegion2;
	AtlasRegion bulletRegion;

	public EcsManager(OrthographicCamera camera, InputManager gameInputManager, World world, EntityContactListener entityContactListener)
	{
		this.world = world;
		this.entityContactListener = entityContactListener;
		
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
			velocityCmps[i] = new VelocityComponent();
			destinationCmps[i] = new DestinationComponent();
			collisionCmps[i] = new CollisionComponent();
		}

		//Initialize Systems
		systems = new ArrayList<ISystem>();
		addSystem(new RendererSystem(this,  camera));
		addSystem(new UserControlSystem(this, gameInputManager));
		addSystem(new MoveSystem(this));
		addSystem(new CollisionSystem(this, camera));

		//Initialize rendering data
		atlas = new TextureAtlas(Gdx.files.internal("testtexture.atlas"));
		zombieRegion = atlas.findRegion("TestZombie");
		zombieRegion2 = atlas.findRegion("TestZombie2");
		bulletRegion = atlas.findRegion("TestBullet");
	}



	/* ################################# *
	 * ####### SYSTEM MANAGEMENT ####### *
	 * ################################# */

	//Main update loop to perform game logic
	public void update()
	{
		for(ISystem system : systems)
		{
			system.update();
		}
	}

	//Add a new system
	private void addSystem(ISystem system)
	{
		systems.add(system);

		//TODO: If we ever have to dynamically add systems after startup, we would need to add logic here to have
		//new system evaluate all existing entities to see if in needed to process any of them
	}

	//Command all systems to evaluate a new or modified entity
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
	public Integer createZombie(float x, float y)
	{
		return createZombie(x, y, zombieRegion);
	}

	//Create a zombie entity
	public Integer createZombie(float x, float y, AtlasRegion region)
	{
		Entity zombie = createEntity();
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		FixtureDef fixtureDef = new FixtureDef();
		
		zombie.setupPhysics(bodyDef, fixtureDef);

		if(zombie != null)
		{
			// Set up physics properties
			
			//Add zombie components
			//Position
			PositionComponent posCmp = positionCmps[zombie.getID()];
			posCmp.init(x, y);
			zombie.addComponent(posCmp.getType());

			//Sprite
			SpriteComponent spriteCmp = spriteCmps[zombie.getID()];
			spriteCmp.init(new Sprite(region));				
			zombie.addComponent(spriteCmp.getType());

			//Velocity
			VelocityComponent velocityCmp = velocityCmps[zombie.getID()];
			velocityCmp.init(0.0f, 0.0f);
			zombie.addComponent(velocityCmp.getType());

			//Destination
			DestinationComponent destinationCmp = destinationCmps[zombie.getID()];
			destinationCmp.init(0.0f, 0.0f);
			zombie.addComponent(destinationCmp.getType());

			//Collision
			CollisionComponent collisionCmp = collisionCmps[zombie.getID()];	
			collisionCmp.init(spriteCmp.getSprite().getBoundingRectangle());
			zombie.addComponent(collisionCmp.getType());

			//User Control
			zombie.addComponent(ComponentType.USERCNTL);

			System.out.println("Zombie created: " + zombie.getID() + " at " + positionCmps[zombie.getID()].getX() + ", " + positionCmps[zombie.getID()].getY());
		}
		else
		{
			System.out.println("WARNING: Unable to create zombie.");
		}

		systemsCheckEntity(zombie.getID());
		return zombie.getID();
	}

	//Create a bullet entity
	public Integer createBullet(float x, float y)
	{
		Entity bullet = createEntity();

		if(bullet != null)
		{
			//Add bullet components
			//Position
			PositionComponent posCmp = positionCmps[bullet.getID()];
			posCmp.init(x, y);
			bullet.addComponent(posCmp.getType());

			//Sprite
			SpriteComponent spriteCmp = spriteCmps[bullet.getID()];
			spriteCmp.init(new Sprite(bulletRegion));
			bullet.addComponent(spriteCmp.getType());
		}
		else
		{
			System.out.println("WARNING: Unable to create zombie.");
		}

		systemsCheckEntity(bullet.getID());
		return bullet.getID();
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

	public VelocityComponent getVelocityComponent(Integer entityID)
	{
		return velocityCmps[entityID];
	}

	public DestinationComponent getDestinationComponent(Integer entityID)
	{
		return destinationCmps[entityID];
	}

}
