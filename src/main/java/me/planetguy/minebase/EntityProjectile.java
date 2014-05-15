package me.planetguy.minebase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityProjectile extends Entity{

	private String network;

	private final ProjectileType projectileType;

	public EntityProjectile(World par1World, String network, ProjectileType type) {
		super(par1World);
		this.network=network;
		this.projectileType=type;
	}

	@Override
	protected void entityInit() {
		//Don't see anything critical to do here...
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		network=tag.getString("network");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setString("network", network);
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public void onUpdate(){
		System.out.println("asdfoijafdjk");
		Block block = this.worldObj.getBlock((int)posX, (int)posY,(int)posZ);

		if (block.getMaterial() != Material.air)
		{
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, (int)posX, (int)posY,(int)posZ);

			if (axisalignedbb != null && axisalignedbb.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ)))
			{
				onLand();
			}
		}
	}

	public void onLand(){
		switch(this.projectileType){
		case ANTI_AIR:
			
			break;
		case BALLOON:
			break;
		case BOMB:
			break;
		case BRIDGE:
			break;
		case CLUSTER:
			break;
		case CRAWLER:
			break;
		case EMP:
			break;
		case ENERGY:
			break;
		case HUB:
			break;
		case MINE:
			break;
		case MISSILE:
			break;
		case OFFENSE:
			break;
		case RECLAIM:
			break;
		case REPAIR:
			break;
		case SHIELD:
			break;
		case SPIKE:
			break;
		case TOWER:
			break;
		case VIRUS:
			break;
		default:
			break;
		
		}
	}

}
