package me.planetguy.minebase.multiblock;

import net.minecraft.world.World;
import me.planetguy.minebase.BlockMinebase;
import me.planetguy.minebase.Minebase;
import me.planetguy.minebase.TileEntityMinebase;

public class Utils {

	public static void buildPlatform(World w, int x, int y, int z,
			int platformSize, String parent) {
		for(int dx=-platformSize; dx<=platformSize; dx++){
			for(int dz=-platformSize; dz<=platformSize; dz++){
				w.setBlock(x+dx, y, z+dz, Minebase.instance.mainBlock);
				w.setBlockMetadataWithNotify(x+dx, y, z+dz, BlockMinebase.META_PLATFORM, 3);
				((TileEntityMinebase)w.getTileEntity(x, y+2, z)).setOwner(parent);
			}
		}
		
	}

}
