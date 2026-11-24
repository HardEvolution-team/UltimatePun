package com.electrifiedded.pun;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.ResourceLocation;

/** 1.7 RenderOb: double-size iron golem with the obsidian texture. */
public class RenderOb extends RenderIronGolem {
   private static final ResourceLocation OB = new ResourceLocation("gibby_pun:textures/texturemaps/armor/obsidian.png");

   public RenderOb(RenderManager rm) {
      super(rm);
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityIronGolem e) {
      return OB;
   }

   @Override
   protected void preRenderCallback(EntityIronGolem e, float partial) {
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
   }
}
