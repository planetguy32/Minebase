package me.planetguy.minebase;

import java.util.HashSet;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Trail {
	
	public static HashSet<Block> bannedBlocks;
	
	public static HashSet<Block> skippedBlocks;
	
	static{
		bannedBlocks=new HashSet<Block>();//Break if water
		bannedBlocks.add(Blocks.water);
		bannedBlocks.add(Blocks.lava);
		skippedBlocks=new HashSet<Block>();//Continue if 
		skippedBlocks.add(Blocks.leaves);
		skippedBlocks.add(Blocks.leaves2);
		skippedBlocks.add(Blocks.log);
		skippedBlocks.add(Blocks.log2);
		skippedBlocks.add(Blocks.air);
	}
	
	public static int canPlaceBuilding(World w, int x, int y, int z){
		while(skippedBlocks.contains(w.getBlock(x, y, z))){
			y--;
			if(y<=0)//handle void holes
				return -1;
		}
		Block b=w.getBlock(x, y, z);
		return bannedBlocks.contains(b) ? -1 : y;
	}

}
