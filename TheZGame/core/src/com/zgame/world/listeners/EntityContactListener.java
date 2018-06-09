package com.zgame.world.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.zgame.world.EcsManager;
import com.zgame.world.Entity;
import com.zgame.world.components.VelocityComponent;

public class EntityContactListener implements ContactListener {
	
	private Fixture fixtureA;
	private Fixture fixtureB;
	private EcsManager ecsManager;
	
	public EntityContactListener(EcsManager ecsManager)
	{
		fixtureA = null;
		fixtureB = null;
		this.ecsManager = ecsManager;
	}
	
	/** Called when two fixtures begin to touch. */
	@Override
	public void beginContact (Contact contact)
	{
		// We will want to keep the zombies from trampling over each other here
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		System.out.println("beginContact between " + fixtureA.toString() + " and " + fixtureB.toString());

		int fixtureAIdent = ((Entity)fixtureA.getBody().getUserData()).getID();
		int fixtureBIdent = ((Entity)fixtureB.getBody().getUserData()).getID();
		
		VelocityComponent velA = ecsManager.getVelocityComponent(fixtureAIdent);
		VelocityComponent velB = ecsManager.getVelocityComponent(fixtureBIdent);

		// Prevent jam ups
		if (velA.getSpeed() > velB.getSpeed())
		{
			velA.setXVelocity(-(velA.getXVelocity()));
			velA.setYVelocity(-(velA.getYVelocity()));
		}
		else
		{
			velB.setXVelocity(-(velB.getXVelocity()));
			velB.setYVelocity(-(velB.getYVelocity()));
		}
	}

	/** Called when two fixtures cease to touch. */
	@Override
	public void endContact (Contact contact)
	{
		// We will want to reset the route for the zombies here
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		System.out.println("endContact between " + fixtureA.toString() + " and " + fixtureB.toString());
		
		int fixtureAIdent = ((Entity)fixtureA.getBody().getUserData()).getID();
		int fixtureBIdent = ((Entity)fixtureB.getBody().getUserData()).getID();
		
		VelocityComponent velA = ecsManager.getVelocityComponent(fixtureAIdent);
		VelocityComponent velB = ecsManager.getVelocityComponent(fixtureBIdent);

		//velA.setXVelocity(0);
		//velA.setYVelocity(0);
		
		//velB.setXVelocity(0);
		//velB.setYVelocity(0);
		
		// Reroute from here
	}

	/*
	 * This is called after a contact is updated. This allows you to inspect a contact before it goes to the solver. If you are
	 * careful, you can modify the contact manifold (e.g. disable contact). A copy of the old manifold is provided so that you can
	 * detect changes. Note: this is called only for awake bodies. Note: this is called even when the number of contact points is
	 * zero. Note: this is not called for sensors. Note: if you set the number of contact points to zero, you will not get an
	 * EndContact callback. However, you may get a BeginContact callback the next step.
	 */
	@Override
	public void preSolve (Contact contact, Manifold oldManifold)
	{
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		System.out.println("preSolve between " + fixtureA.toString() + " and " + fixtureB.toString());
		
		
	}

	/*
	 * This lets you inspect a contact after the solver is finished. This is useful for inspecting impulses. Note: the contact
	 * manifold does not include time of impact impulses, which can be arbitrarily large if the sub-step is small. Hence the
	 * impulse is provided explicitly in a separate data structure. Note: this is only called for contacts that are touching,
	 * solid, and awake.
	 */
	@Override
	public void postSolve (Contact contact, ContactImpulse impulse)
	{
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		System.out.println("postSolve between " + fixtureA.toString() + " and " + fixtureB.toString());
		
	}
}
