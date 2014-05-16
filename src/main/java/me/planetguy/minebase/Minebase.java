package me.planetguy.minebase;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "planetguy_Minebase")
public class Minebase {
	
	@Instance("planetguy_Minebase")
	public static Minebase instance;
	
	public Block mainBlock;
	
	@SidedProxy(clientSide="me.planetguy.minebase.ClientProxy", serverSide="me.planetguy.minebase.CommonProxy")
	public static CommonProxy proxy;
	
	public static final CreativeTabs creativeTab=new CreativeTabs("Minebase Commander"){
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Items.arrow;
		}
		
	};
	
	@EventHandler
	public void doPreInit(FMLPreInitializationEvent ev){
		mainBlock=new BlockMinebase();
		GameRegistry.registerBlock(mainBlock, MBBlockWithMeta.class, "minebaseBlock");
		GameRegistry.registerTileEntity(TileEntityMinebase.class, "MinebaseNetMarker");
		EntityRegistry.registerModEntity(EntityProjectile.class, "minebaseProjectile", 0, this, 80, 1, true);
	}
	
	@EventHandler
	public void doInit(FMLInitializationEvent ev){
		proxy.registerRenderers();
		//TODO recipes and creative tab
	}

}
