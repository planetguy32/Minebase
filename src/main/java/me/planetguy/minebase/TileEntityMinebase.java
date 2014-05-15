package me.planetguy.minebase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMinebase extends TileEntity{
	
	private String containingNetwork;
	
	public TileEntityMinebase(){
		containingNetwork="__NULL__";
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		setContainingNetwork(tag.getString("network"));
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setString("network", getContainingNetwork());
	}

	private String getContainingNetwork() {
		return containingNetwork;
	}

	private void setContainingNetwork(String containingNetwork) {
		this.containingNetwork = containingNetwork;
	}

}
