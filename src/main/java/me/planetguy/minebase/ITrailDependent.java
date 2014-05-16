package me.planetguy.minebase;

import net.minecraft.tileentity.TileEntity;

public interface ITrailDependent {
	
	public void setParent(TileEntity parent);
	
	public void onParentExplode();
	
	public void onChildExplode(int x, int y, int z);
	
	public void onPlaceChild(int x, int y, int z);

}
