package me.planetguy.minebase;

import java.lang.reflect.Field;
import java.util.ArrayList;

import me.planetguy.minebase.multiblock.PatternBridge;
import me.planetguy.minebase.multiblock.PatternDefensive;
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

	private int lastX, lastY, lastZ;
	
	private int parentX,
	parentY,
	parentZ;

	private String network;

	private ProjectileType projectileType;

	final Field inGround;
	
	ArrayList<TileEntityTrail> trails=new ArrayList<TileEntityTrail>();

	public EntityProjectile(World w) {
		super(w);
		setProjectileType(null);
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
		this.setProjectileType(type);
		this.setSize(0.5F, 0.5F);
		this.posX=te.xCoord+0.5;
		this.posY=te.yCoord+1.5;
		this.posZ=te.zCoord+0.5;
		parentX=te.xCoord;
		parentY=te.yCoord;
		parentZ=te.zCoord;
		lastX=parentX;
		lastY=parentY;
		lastZ=parentZ;
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
		
		if(ShieldingRegistry.tryShootDown(worldObj, (int)posX, (int)posY, (int)posZ, network)){
			setDead();
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
		System.out.println("Projectile of type "+this.getProjectileType()+" landed");
		if(getProjectileType()!=null){
			switch(this.getProjectileType()){
			case ANTI_AIR:
				PatternDefensive.buildAntiAir(worldObj, (int)posX, (int)posY, (int)posZ, network);
				break;
			case BALLOON:
				break;
			case BOMB:
				this.worldObj.createExplosion(this, posX, posY, posZ, 4.0f, true);
				break;
			case BRIDGE:
				PatternBridge.build(worldObj, (int)posX, (int)posY, (int)posZ, network);
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
				PatternDefensive.buildShield(worldObj, (int)posX, (int)posY, (int)posZ, network);
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
		for(TileEntityTrail te:trails){
			te.setAbsoluteChild(worldObj.getTileEntity((int)posX, (int)posY, (int)posZ));
		}
		this.setDead();
	}

	public ProjectileType getProjectileType() {
		return projectileType;
	}

	public void setProjectileType(ProjectileType projectileType) {
		this.projectileType = projectileType;
	}

	public void leaveTrace(){
		if(getProjectileType()!=null&&getProjectileType().isBuilding){
			int traceResult=Trail.canPlaceBuilding(worldObj, (int)posX, (int)posY, (int)posZ);
			if(traceResult==-1){
				setDead();
				worldObj.spawnParticle("largeexplode", posX, posY, posZ, motionX, motionY, motionZ);
				try{
					((ITrailDependent)worldObj.getTileEntity(lastX, lastY, lastZ)).onChildExplode((int)posX, (int)posY, (int)posZ);
				}catch(ClassCastException e){
					e.printStackTrace();
				}
			}else if(!(worldObj.getBlock((int)posX,traceResult+1,(int)posZ) instanceof BlockMinebase)&&!(worldObj.getBlock((int)posX,traceResult,(int)posZ) instanceof BlockMinebase)){
				worldObj.setBlock((int)posX,traceResult+1,(int)posZ, Minebase.instance.mainBlock);
				worldObj.setBlockMetadataWithNotify((int)posX,traceResult+1,(int)posZ, BlockMinebase.META_TRAIL, 3);
				try{
					ITrailDependent te=((ITrailDependent)worldObj.getTileEntity((int)posX,traceResult+1,(int)posZ));
					te.setParent(worldObj.getTileEntity(lastX, lastY, lastZ));
					
					((TileEntityTrail)te).setAbsoluteParent(
							worldObj.getTileEntity( parentX,
													parentY,
													parentZ));
					trails.add((TileEntityTrail) te);
				}catch(ClassCastException e){
					e.printStackTrace();
				}
				lastX=(int)posX;
				lastY=traceResult+1;
				lastZ=(int)posZ;
			}
		}
	}


}
