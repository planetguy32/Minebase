package me.planetguy.minebase;

public enum ProjectileType {
	
	TOWER(1, true),
	BOMB(1, false),
	ANTI_AIR(1, true),
	BRIDGE(1, false), 
	CLUSTER(1, false),
	REPAIR(1, false),
	
	MISSILE(3, false),
	EMP(3, false),
	SPIKE(3, false),
	RECLAIM(3, false),
	BALLOON(3, false),
	MINE(3, false),
	
	CRAWLER(7, false),
	VIRUS(7, false),
	ENERGY(7, true),
	SHIELD(7, true),
	OFFENSE(7, true),
	HUB(7, true);
	
	public final int cost;
	public final boolean isBuilding;
	
	private ProjectileType(int cost, boolean isBuilding){
		this.cost=cost;
		this.isBuilding=isBuilding;
	}

}
