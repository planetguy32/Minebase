package me.planetguy.minebase;

import net.minecraft.client.renderer.entity.RenderArrow;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectile.class, new RenderArrow());
	}
	

}
