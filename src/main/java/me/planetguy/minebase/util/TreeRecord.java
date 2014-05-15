package me.planetguy.minebase.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TreeRecord {
	
	public static final BiMap<TreeRecord, String> map=HashBiMap.create();
	
	public final String id=UUID.randomUUID().toString();
	
	public final int x, y, z;
	
	public final World w;
	
	private TreeRecord parent;
	
	private ArrayList<TreeRecord> children=new ArrayList<TreeRecord>();
	
	public TreeRecord(TileEntity record){
		w=record.getWorldObj();
		x=record.xCoord;
		y=record.yCoord;
		z=record.zCoord;
		map.put(this, id);
	}
	
	public TreeRecord getParent(){
		return parent;
	}
	
	public TreeRecord setParent(TreeRecord parent){
		this.parent=parent;
		return this;
	}
	
	public List<TreeRecord> getDirectChildren(){
		return children;
	}
	
	public TreeRecord addChild(TreeRecord child){
		children.add(child);
		return this;
	}
	
	public List<TreeRecord> getAllChildren(){
		return getAllChildren(new ArrayList<TreeRecord>());
	}
	
	private List<TreeRecord> getAllChildren(List<TreeRecord> list){
		list.addAll(children);
		for(TreeRecord r:children){
			r.getAllChildren(list);
		}
		return list;
	}
	
}
