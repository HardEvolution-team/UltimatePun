package com.electrifiedded.pun;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/** 1.7 RenderAbombnible: swells with the fuse (integer fuse / 80 like the original). */
public class RenderAbombnible extends RenderLiving<EntityAbombnible> {
   private static final ResourceLocation BOMB = new ResourceLocation("gibby_pun:textures/texturemaps/armor/Abombnible.png");

   public RenderAbombnible(RenderManager rm, Abombnible model, float shadow) {
      super(rm, model, shadow);
   }

   @Override
   protected void applyRotations(EntityAbombnible e, float ageInTicks, float yaw, float partial) {
      GlStateManager.translate(0.0F, -0.5F, 0.0F);
      GlStateManager.rotate(180.0F - yaw, 0.0F, 1.0F, 0.0F);
   }

   @Override
   protected void preRenderCallback(EntityAbombnible e, float partial) {
      float swell = e.fuse / 80;
      GlStateManager.scale(1.0F + swell, 1.0F + swell, 1.0F + swell);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityAbombnible e) {
      return BOMB;
   }
}
