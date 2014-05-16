package me.planetguy.minebase.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Tree {
	
	public static final BiMap<Tree, String> map=HashBiMap.create();
	
	public final String id=UUID.randomUUID().toString();
	
	public final int x, y, z;
	
	public final World w;
	
	private Tree parent;
	
	private ArrayList<Tree> children=new ArrayList<Tree>();
	
	public Tree(TileEntity record){
		w=record.getWorldObj();
		x=record.xCoord;
		y=record.yCoord;
		z=record.zCoord;
		map.put(this, id);
	}
	
	public Tree getParent(){
		return parent;
	}
	
	public Tree setParent(Tree parent){
		this.parent=parent;
		return this;
	}
	
	public List<Tree> getDirectChildren(){
		return children;
	}
	
	public Tree addChild(Tree child){
		children.add(child);
		return this;
	}
	
	public List<Tree> getAllChildren(){
		return getAllChildren(new ArrayList<Tree>());
	}
	
	private List<Tree> getAllChildren(List<Tree> list){
		list.addAll(children);
		for(Tree r:children){
			r.getAllChildren(list);
		}
		return list;
	}
	
}
