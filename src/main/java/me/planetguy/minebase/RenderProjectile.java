package me.planetguy.minebase;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderProjectile extends Render{

	ResourceLocation texture=new ResourceLocation("planetguy_Minebase:textures/projectile.png");
	
	//IModelCustom model=AdvancedModelLoader.loadModel(new ResourceLocation("planetguy_Minebase/projectile.obj"));
	
	@Override
	public void doRender(Entity var1, double x, double y, double z,	float whatAre, float theseFor) {
		EntityProjectile entity=(EntityProjectile)var1;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		//model.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return null;
	}

}
