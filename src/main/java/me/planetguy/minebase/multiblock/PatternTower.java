package me.planetguy.minebase.multiblock;

import me.planetguy.minebase.Minebase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class PatternTower {
	
	public static void build(World w, int x, int y, int z, String parent) {
		Utils.fillEdges(w, x-2,y,z-2, x+2,y+18,z+2, Blocks.cobblestone);
		Utils.buildPlatform(w, x, y, z, 2, parent);
		w.setBlock(x, y+1, z, Minebase.instance.mainBlock, 3, 3);
	}

}
