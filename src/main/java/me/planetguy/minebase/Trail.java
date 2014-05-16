package me.planetguy.minebase;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Trail {
	
	public static HashSet<Block> bannedBlocks;
	
	static{
		bannedBlocks=new HashSet<Block>();
		bannedBlocks.add(Blocks.water);
		bannedBlocks.add(Blocks.lava);
	}
	
	public static int canPlaceBuilding(World w, int x, int y, int z){
		while(w.getBlock(x, y, z).equals(Blocks.air)){
			y--;
			if(y<=0)//handle void holes
				return -1;
		}
		Block b=w.getBlock(x, y, z);
		return bannedBlocks.contains(b) ? -1 : y;
	}

}
