package me.planetguy.minebase;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTrail extends TileEntity implements ITrailDependent {
	
	public int sourceX, sourceY, sourceZ;
	
	public int destX, destY, destZ;
	
	public int parentX, parentY, parentZ;
	
	public int childX, childY, childZ;
	
	public boolean shouldExplode=false;
	
	public TileEntityTrail(){ 
		//construct trail from the structure that launched it
	}
	
	public void setParent(TileEntity parent){
		parentX=parent.xCoord;
		parentY=parent.yCoord;
		parentZ=parent.zCoord;
		if(parent instanceof ITrailDependent){
			((ITrailDependent)parent).onPlaceChild(xCoord, yCoord, zCoord);
		}

	}
	
	public void setAbsoluteParent(TileEntity parent){
		sourceX=parent.xCoord;
		sourceY=parent.yCoord;
		sourceZ=parent.zCoord;
		if(parent instanceof ITrailDependent){
			((ITrailDependent)parent).onPlaceChild(xCoord, yCoord, zCoord);
		}
	}
	
	public void setAbsoluteChild(TileEntity child){
		destX=child.xCoord;
		destY=child.yCoord;
		destZ=child.zCoord;
	}
	
	@Override
	public void onPlaceChild(int x, int y, int z){
		childX=x;
		childY=y;
		childZ=z;
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
	
	public void onTrailExplode(){
		if(worldObj.getTileEntity(xCoord, yCoord, zCoord)instanceof TileEntityTrail){
			((TileEntityTrail) worldObj.getTileEntity(xCoord, yCoord, zCoord)).onTrailExplode();
			explode();
		}
	}
	
	public void explode(){
		System.out.println(this + "\nExploding...\n");
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		worldObj.spawnParticle("largeexplode", xCoord, yCoord, zCoord, 0,0,0);
	}

}
