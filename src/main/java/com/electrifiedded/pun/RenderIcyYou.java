package com.electrifiedded.pun;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/** 1.7 RenderIcyYou: translucent, rotated a quarter turn. */
public class RenderIcyYou extends RenderLiving<EntityIcyYou> {
   private static final ResourceLocation ICY = new ResourceLocation("gibby_pun:textures/texturemaps/armor/IcyYou.png");

   public RenderIcyYou(RenderManager rm, ModelBase model, float shadow) {
      super(rm, model, shadow);
   }

   @Override
   protected void applyRotations(EntityIcyYou e, float ageInTicks, float yaw, float partial) {
      GlStateManager.rotate(-90.0F - yaw, 0.0F, 1.0F, 0.0F);
      GlStateManager.enableNormalize();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void doRender(EntityIcyYou e, double x, double y, double z, float yaw, float partial) {
      super.doRender(e, x, y, z, yaw, partial);
      GlStateManager.disableBlend();
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityIcyYou e) {
      return ICY;
   }
}
