package com.zgame.world.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class EntityContactListener implements ContactListener {
	
	private Fixture fixtureA;
	private Fixture fixtureB;
	
	public EntityContactListener()
	{
		fixtureA = null;
		fixtureB = null;
	}
	
	/** Called when two fixtures begin to touch. */
	@Override
	public void beginContact (Contact contact)
	{
		// We will want to keep the zombies from trampling over each other here
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		System.out.println("beginContact between " + fixtureA.toString() + " and " + fixtureB.toString());
	}

	/** Called when two fixtures cease to touch. */
	@Override
	public void endContact (Contact contact)
	{
		// We will want to reset the route for the zombies here
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		System.out.println("endContact between " + fixtureA.toString() + " and " + fixtureB.toString());
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
