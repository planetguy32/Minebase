package me.planetguy.minebase;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTrail extends TileEntity implements ITrailDependent {
	
	public int parentX, parentY, parentZ;
	
	public int childX, childY, childZ;
	
	public TileEntityTrail(TileEntity parent){ //construct trail from the structure that launched it
		
	}
	
	public void onPlaceChild(int x, int y, int z){
		
	}

	@Override
	public void onParentExplode() {
		((ITrailDependent)worldObj.getTileEntity(childX, childY, childZ)).onChildExplode(xCoord, yCoord, zCoord);
		explode();
	}

	@Override
	public void onChildExplode(int x, int y, int z) {
		((ITrailDependent)worldObj.getTileEntity(x,y,z)).onParentExplode();
		explode();
	}
	
	public void explode(){
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		worldObj.spawnParticle("largeexplode", xCoord, yCoord, zCoord, 0,0,0);
	}

}
