package me.planetguy.minebase.multiblock;

import me.planetguy.minebase.BlockMinebase;
import me.planetguy.minebase.Minebase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PatternHub {

	public int platformSize=2;
	
	public void build(World w, int x, int y, int z, TileEntity parent) {
		w.setBlock(x,y+1,z, Minebase.instance.mainBlock);
		w.setBlock(x,y+2,z, Minebase.instance.mainBlock);
		w.setBlockMetadataWithNotify(x, y+1, z, BlockMinebase.META_ASSEMBLER, 3);
		
		for(int dx=-platformSize; dx<=platformSize; dx++){
			for(int dz=-platformSize; dz<=platformSize; dz++){
				w.setBlock(x+dx, y, z+dz, Minebase.instance.mainBlock);
				w.setBlockMetadataWithNotify(x+dx, y, z+dz, BlockMinebase.META_PLATFORM, 3);
			}
		}
	}
	

}
