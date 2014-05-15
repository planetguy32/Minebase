package me.planetguy.minebase;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "planetguy_Minebase")
public class Minebase {
	
	@EventHandler
	public void doPreInit(FMLPreInitializationEvent ev){
		GameRegistry.registerBlock(new BlockMinebase(), "minebaseBlock");
		GameRegistry.registerTileEntity(TileEntityMinebase.class, "MinebaseNetMarker");
	}
	
	@EventHandler
	public void doInit(FMLInitializationEvent ev){
		//TODO recipes and creative tab
	}

}
