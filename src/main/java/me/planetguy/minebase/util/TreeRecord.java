package me.planetguy.minebase.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TreeRecord {
	
	public final int x, y, z;
	
	public final World w;
	
	private TreeRecord parent;
	
	private ArrayList<TreeRecord> children=new ArrayList<TreeRecord>();
	
	public TreeRecord(TileEntity record){
		w=record.getWorldObj();
		x=record.xCoord;
		y=record.yCoord;
		z=record.zCoord;
	}
	
	public TreeRecord getParent(){
		return parent;
	}
	
	public TreeRecord setParent(TreeRecord parent){
		this.parent=parent;
		return this;
	}
	
	public List<TreeRecord> getChildren(){
		return children;
	}
	
	public void addChild(TreeRecord child){
		children.add(child);
	}

}
