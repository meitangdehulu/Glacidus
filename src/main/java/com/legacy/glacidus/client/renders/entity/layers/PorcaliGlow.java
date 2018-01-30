package com.legacy.glacidus.client.renders.entity.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

import com.legacy.glacidus.client.models.entity.ModelPorcali;
import com.legacy.glacidus.entities.passive.EntityPorcali;

public class PorcaliGlow implements LayerRenderer<EntityPorcali>
{

	private static final ResourceLocation GLOW = new ResourceLocation("glacidus", "textures/entity/porcali/porcali_glow.png");

	private final ModelPorcali model;

	public PorcaliGlow(ModelPorcali porcali)
	{
		super();
		model = porcali;
	}

	@Override
	public void doRenderLayer(EntityPorcali entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();	
		renderManager.renderEngine.bindTexture(GLOW);

	    GlStateManager.enableBlend();
	    GlStateManager.disableAlpha();
	    GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
	    GlStateManager.depthMask(true);
	    int i = 61680;
	    int j = i % 65536;
	    int k = i / 65536;
	    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
	    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	    Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
	    GlStateManager.translate(0.0001D, -0.0001D, 0.0001D);
	    GlStateManager.scale(1.00018F, 1.0001F, 1.00018F);
	    this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	    Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
	    i = entity.getBrightnessForRender();
	    j = i % 65536;
	    k = i / 65536;
	    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
	    GlStateManager.disableBlend();
	    GlStateManager.enableAlpha();
	}

	@Override
	public boolean shouldCombineTextures() 
	{
		return false;
	}

}