package me.planetguy.minebase;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMinebase extends Block implements ITileEntityProvider{

	static final int META_LAUNCHER=0;
	static final int META_ASSEMBLER=1; //assumes stacking order
	
	protected BlockMinebase() {
		super(Material.iron);
		this.setCreativeTab(Minebase.creativeTab);
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float i, float d, float k){
		int myMeta=w.getBlockMetadata(x, y, z);
		TileEntityMinebase te=(TileEntityMinebase) w.getTileEntity(x, y+myMeta, z);
		
		switch(myMeta){
		case META_LAUNCHER:
			double dx=player.posX-x+0.5;
			double dz=player.posZ-z+0.5;
			System.out.println("("+dx+"\n   "+dz);
			launchProjectile(te, dx, dz);
			return true;
			
		case META_ASSEMBLER:
			ItemStack item=player.getHeldItem();
			te.setStoredProjectileType(getProjectile(item));
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityMinebase();
	}
	
	public void launchProjectile(TileEntityMinebase te, double xPower, double zPower){
		EntityProjectile projectile=new EntityProjectile(te.getWorldObj(), te.getOwner(), te.getStoredProjectileType());
		projectile.motionX=xPower* getPowerFactor();
		projectile.motionY=Math.sqrt(xPower*xPower+zPower*xPower) * getPowerFactor();
		projectile.motionZ=zPower* getPowerFactor();
		te.setStoredProjectileType(null);
		te.getWorldObj().spawnEntityInWorld(projectile);
	}
	
	public double getPowerFactor(){
		return 2.0;
	}
	
	public ProjectileType getProjectile(ItemStack is){
		if(is.getItem().equals(Items.arrow)){
			return ProjectileType.BOMB;
		}
		return null;
	}
	
}
