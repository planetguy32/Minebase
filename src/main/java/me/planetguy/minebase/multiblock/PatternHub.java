package me.planetguy.minebase.multiblock;

import me.planetguy.minebase.BlockMinebase;
import me.planetguy.minebase.Minebase;
import me.planetguy.minebase.TileEntityMinebase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PatternHub {

	public static int platformSize=2;
	
	public static void build(World w, int x, int y, int z, String parent) {
		w.setBlock(x,y+1,z, Minebase.instance.mainBlock);
		w.setBlock(x,y+2,z, Minebase.instance.mainBlock);
		w.setBlockMetadataWithNotify(x, y+1, z, BlockMinebase.META_ASSEMBLER, 3);
		
		Utils.buildPlatform(w, x, y, z, platformSize, parent);

	}
	

}
