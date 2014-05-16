package me.planetguy.minebase;

public interface ITrailDependent {
	
	public void onParentExplode();
	
	public void onChildExplode(int x, int y, int z);
	
	public void onPlaceChild(int x, int y, int z);

}
