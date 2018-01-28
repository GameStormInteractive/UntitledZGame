package com.zgame.world;

import java.util.ArrayList;

public class World {
	
	ArrayList<Zombie> zombies;

	public World()
	{
		zombies = new ArrayList<Zombie>();
		zombies.add(new Zombie(120.0f, 220.0f));
		zombies.add(new Zombie(300.0f, 100.0f));
	}
	
	public ArrayList<Zombie> getZombies()
	{
		return zombies;
	}
	
	public void dispose()
	{
		
	}
	
}
