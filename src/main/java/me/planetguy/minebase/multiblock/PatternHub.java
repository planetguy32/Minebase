package me.planetguy.minebase.multiblock;

import me.planetguy.minebase.Minebase;
import net.minecraft.world.World;

public class PatternHub implements IPattern{

	@Override
	public void build(World w, int x, int y, int z) {
		w.setBlock(x,y,z, Minebase.instance.mainBlock);
		w.setBlock(x,y+1,z, Minebase.instance.mainBlock);
		w.setBlock(x,y+2,z, Minebase.instance.mainBlock);
	}
	

}
