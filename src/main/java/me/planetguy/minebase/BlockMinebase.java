package me.planetguy.minebase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockMinebase extends Block{

	int META_LAUNCHER=0;
	int META_ASSEMBLER=1;
	
	protected BlockMinebase() {
		super(Material.iron);
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float i, float d, float k){
		switch(w.getBlockMetadata(x, y, z)){
		case 0:
			return true;
		case 1:
			return true;
		}
		return false;
	}

}
