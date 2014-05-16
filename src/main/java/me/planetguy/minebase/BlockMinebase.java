package me.planetguy.minebase;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockMinebase extends Block implements ITileEntityProvider{

	public static final int META_LAUNCHER=0;
	public static final int META_ASSEMBLER=1; //assumes stacking order
	public static final int META_PLATFORM=2;

	protected BlockMinebase() {
		super(Material.iron);
		this.setCreativeTab(Minebase.creativeTab);
		LanguageRegistry.instance().addNameForObject(this, "en_US", "Minebase Block");
	}

	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float i, float d, float k){
		int myMeta=w.getBlockMetadata(x, y, z);
		TileEntityMinebase te=(TileEntityMinebase) w.getTileEntity(x, y+myMeta, z);
		switch(myMeta){
		case META_LAUNCHER:
			double dx=player.posX-x+0.5;
			double dz=player.posZ-z+0.5;
			launchProjectile(te, dx, dz);
			return true;

		case META_ASSEMBLER:
			if(te==null)return false;
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
		
		if(te.getStoredProjectileType()!=null){
			EntityProjectile projectile=new EntityProjectile(te);
			te.setStoredProjectileType(null);
			te.getWorldObj().spawnEntityInWorld(projectile);
			projectile.motionX=-xPower* getPowerFactor();
			projectile.motionY=MathHelper.sqrt_double(xPower*xPower+zPower*xPower) * getPowerFactor();
			projectile.motionZ=-zPower* getPowerFactor();
		}
	}

	public double getPowerFactor(){
		return 0.2;
	}

	public ProjectileType getProjectile(ItemStack is){
		if(is==null)return null;
		if(is.getItem().equals(Items.arrow)){
			return ProjectileType.BOMB;
		}else if(is.getItem().equals(Item.getItemFromBlock(Blocks.chest))){
			return ProjectileType.HUB;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List items){
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 1));
	}

}
