package me.planetguy.minebase;

import me.planetguy.minebase.util.TreeRecord;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMinebase extends TileEntity{
	
	private TreeRecord record;
	
	private String nodeID;
	
	private String owner;
	
	public TileEntityMinebase(){
		nodeID="__NULL__";
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		setNodeID(tag.getString("network"));
		setOwner(tag.getString("owningPlayer"));
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setString("network", getNodeID());
		tag.setString("owningPlayer", getOwner());
		record=TreeRecord.map.inverse().get(nodeID);
	}

	private String getNodeID() {
		return nodeID;
	}

	private void setNodeID(String containingNetwork) {
		this.nodeID = containingNetwork;
	}

	private String getOwner() {
		return owner;
	}

	private void setOwner(String owner) {
		this.owner = owner;
	}

}
