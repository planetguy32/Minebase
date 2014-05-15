package me.planetguy.minebase;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMinebase extends Block implements ITileEntityProvider{

	static final int META_LAUNCHER=0;
	static final int META_ASSEMBLER=1;
	
	protected BlockMinebase() {
		super(Material.iron);
		this.setCreativeTab(Minebase.creativeTab);
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float i, float d, float k){
		switch(w.getBlockMetadata(x, y, z)){
		case META_LAUNCHER:
			double dx=player.posX-x+0.5;
			double dz=player.posZ-z+0.5;
			System.out.println("("+dx+"\n   "+dz);
			return true;
			
		case META_ASSEMBLER:
			
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityMinebase();
	}
	
}
