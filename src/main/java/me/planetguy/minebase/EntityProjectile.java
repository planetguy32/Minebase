package me.planetguy.minebase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityProjectile extends EntityThrowable {

	private String network;

	private final ProjectileType projectileType;

	public EntityProjectile(World w){
		super(w);
		projectileType=null;
	}

	public EntityProjectile(TileEntityMinebase te){
		super(te.getWorldObj());
		String network=te.getOwner();
		ProjectileType type=te.getStoredProjectileType();
		this.network=network;
		this.projectileType=type;
		this.setSize(0.5F, 0.5F);
		this.posX=te.xCoord+0.5;
		this.posY=te.yCoord+1.5;
		this.posZ=te.zCoord+0.5;
	}

	public void onUpdate(){
		super.onUpdate();
		System.out.println(this);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		network=tag.getString("network");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setString("network", network);
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public void onImpact(MovingObjectPosition var1) {
		System.out.println("Projectile of type "+this.projectileType+" landed");
		if(projectileType!=null){
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
		this.setDead();
	}

}
