package com.electrifiedded.pun;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/** 1.7 RenderLiqSquid: giant squid (liqsquid model) hanging 4.2 blocks lower than a squid. */
public class RenderLiqSquid extends RenderLiving<EnitiyLiqsquid> {
   private static final ResourceLocation SQUID = new ResourceLocation("gibby_pun:textures/texturemaps/armor/liqsquid.png");

   public RenderLiqSquid(RenderManager rm, ModelBase model, float shadow) {
      super(rm, model, shadow);
   }

   @Override
   protected void applyRotations(EnitiyLiqsquid e, float ageInTicks, float yaw, float partial) {
      float f3 = e.prevSquidPitch + (e.squidPitch - e.prevSquidPitch) * partial;
      float f4 = e.prevSquidYaw + (e.squidYaw - e.prevSquidYaw) * partial;
      GlStateManager.translate(0.0F, -0.5F, 0.0F);
      GlStateManager.rotate(180.0F - yaw, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f3, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f4, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, -4.2F, 0.0F);
   }

   @Override
   protected float handleRotationFloat(EnitiyLiqsquid e, float partial) {
      return e.lastTentacleAngle + (e.tentacleAngle - e.lastTentacleAngle) * partial;
   }

   @Override
   protected ResourceLocation getEntityTexture(EnitiyLiqsquid e) {
      return SQUID;
   }
}
