package com.electrifiedded.pun;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;

/** 1.7 RenderFry: small skeleton with the fry texture. */
public class RenderFry extends RenderSkeleton {
   private static final ResourceLocation FRY = new ResourceLocation("gibby_pun:textures/texturemaps/armor/fry.png");

   public RenderFry(RenderManager rm) {
      super(rm);
   }

   @Override
   protected void preRenderCallback(AbstractSkeleton e, float partial) {
      GlStateManager.scale(0.6F, 0.6F, 0.6F);
   }

   @Override
   protected ResourceLocation getEntityTexture(AbstractSkeleton e) {
      return FRY;
   }
}
