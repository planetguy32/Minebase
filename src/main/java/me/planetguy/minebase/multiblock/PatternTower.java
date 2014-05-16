package me.planetguy.minebase.multiblock;

import me.planetguy.minebase.Minebase;
import net.minecraft.world.World;

public class PatternTower {
	
	public static void build(World w, int x, int y, int z, String parent) {
		w.setBlock(x, y, z, Minebase.instance.mainBlock, 3, 3);
		
	}

}
