package com.electrifiedded.pun;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFrostBiter extends RenderLiving<EntityFrostBiter> {
   private static final ResourceLocation BITE = new ResourceLocation("gibby_pun:textures/texturemaps/armor/Frostbiter.png");

   public RenderFrostBiter(RenderManager rm, ModelBase model, float shadow) {
      super(rm, model, shadow);
   }

   @Override
   protected void applyRotations(EntityFrostBiter e, float ageInTicks, float yaw, float partial) {
      GlStateManager.rotate(360.0F - yaw, 0.0F, 1.0F, 0.0F);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityFrostBiter e) {
      return BITE;
   }
}
