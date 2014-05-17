package me.planetguy.minebase.multiblock;

import net.minecraft.block.Block;
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
	
	/**
	 * Fills the walls of a cuboid. Slower than necessary; deal with it.
	 */
	public static void fillEdges(World w, int x1, int y1, int z1, int x2, int y2, int z2, Block block){
		for(int x=x1; x<=x2; x++){
			for(int y=y1; y<=y2; y++){
				for(int z=z1; z<=z2; z++){
					if(x==x1||x==x2||y==y1||y==y2||z==z1||z==z2){
						w.setBlock(x, y, z, block);
					}
				}
			}
		}
	}

}
