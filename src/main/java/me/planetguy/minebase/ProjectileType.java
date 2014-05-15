package me.planetguy.minebase;

public enum ProjectileType {
	
	TOWER(1),
	BOMB(1),
	ANTI_AIR(1),
	BRIDGE(1), 
	CLUSTER(1),
	REPAIR(1),
	
	MISSILE(3),
	EMP(3),
	SPIKE(3),
	RECLAIM(3),
	BALLOON(3),
	MINE(3),
	
	CRAWLER(7),
	VIRUS(7),
	ENERGY(7),
	SHIELD(7),
	OFFENSE(7),
	HUB(7);
	
	public final int cost;
	
	private ProjectileType(int cost){
		this.cost=cost;
	}

}
