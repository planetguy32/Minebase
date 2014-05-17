package me.planetguy.minebase;

import java.lang.reflect.Field;

import me.planetguy.minebase.multiblock.PatternHub;
import me.planetguy.minebase.multiblock.PatternTower;
import me.planetguy.minebase.multiblock.Trail;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityProjectile extends EntityArrow {

	private int parentX,
	parentY,
	parentZ;

	private String network;

	private ProjectileType projectileType;

	final Field inGround;

	public EntityProjectile(World w) {
		super(w);
		projectileType=null;
		try{
			inGround=EntityArrow.class.getDeclaredField("inGround");
			inGround.setAccessible(true);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public EntityProjectile(TileEntityMinebase te){
		this(te.getWorldObj());
		String network=te.getOwner();
		ProjectileType type=te.getStoredProjectileType();
		this.network=network;
		this.projectileType=type;
		this.setSize(0.5F, 0.5F);
		this.posX=te.xCoord+0.5;
		this.posY=te.yCoord+1.5;
		this.posZ=te.zCoord+0.5;
		parentX=te.xCoord;
		parentY=te.yCoord;
		parentZ=te.zCoord;
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		//projectileType=ProjectileType.valueOf(tag.getString("type"));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		//if(projectileType!=null)
		//	tag.setString("type", projectileType.toString());
	}

	public void onUpdate(){
		super.onUpdate();
		if(worldObj.isRemote)return;
		leaveTrace();
		try {
			if(inGround.getBoolean(this))
				onImpact();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public void onImpact() {
		if(worldObj.isRemote)return;
		System.out.println("Projectile of type "+this.projectileType+" landed");
		if(projectileType!=null){
			switch(this.projectileType){
			case ANTI_AIR:
				break;
			case BALLOON:
				break;
			case BOMB:
				this.worldObj.createExplosion(this, posX, posY, posZ, 4.0f, true);
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
				PatternHub.build(worldObj, (int)posX, (int)posY, (int)posZ, network);
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
				PatternTower.build(worldObj,(int)posX, (int)posY, (int)posZ, network);
				break;
			case VIRUS:
				break;
			default:
				break;
			}
		}
		this.setDead();
	}

	public void leaveTrace(){
		if(projectileType!=null&&projectileType.isBuilding){
			int traceResult=Trail.canPlaceBuilding(worldObj, (int)posX, (int)posY, (int)posZ);
			if(traceResult==-1){
				setDead();
				worldObj.spawnParticle("largeexplode", posX, posY, posZ, motionX, motionY, motionZ);
				try{
					((ITrailDependent)worldObj.getTileEntity(parentX, parentY, parentZ)).onChildExplode((int)posX, (int)posY, (int)posZ);
				}catch(ClassCastException e){
					e.printStackTrace();
				}
			}else if(!(worldObj.getBlock((int)posX,traceResult+1,(int)posZ) instanceof BlockMinebase)&&!(worldObj.getBlock((int)posX,traceResult,(int)posZ) instanceof BlockMinebase)){
				worldObj.setBlock((int)posX,traceResult+1,(int)posZ, Minebase.instance.mainBlock);
				worldObj.setBlockMetadataWithNotify((int)posX,traceResult+1,(int)posZ, BlockMinebase.META_TRAIL, 3);
				try{
					((ITrailDependent)worldObj.getTileEntity((int)posX,traceResult+1,(int)posZ))
					.setParent(worldObj.getTileEntity(parentX, parentY, parentZ));
				}catch(ClassCastException e){
					e.printStackTrace();
				}
				parentX=(int)posX;
				parentY=traceResult+1;
				parentZ=(int)posZ;
			}
		}

	}

}
