package me.planetguy.minebase.multiblock;

import me.planetguy.minebase.Minebase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class PatternTower {
	
	public static void build(World w, int x, int y, int z, String parent) {
		Utils.buildPlatform(w, x, y, z, 2, parent);
		w.setBlock(x, y+1, z, Minebase.instance.mainBlock, 3, 3);
		for(int i=0; i<8; i++){
			w.setBlock(x, y+i, z, Blocks.cobblestone);
		}
	}

}
