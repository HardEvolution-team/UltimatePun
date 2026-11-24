package com.electrifiedded.pun;

import com.electrifiedded.archaiclib.legacy.client.LegacyChargeLayer;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;

/** 1.7 RenderHSkeleton: big skeleton with a scrolling energy overlay. */
public class RenderHSkeleton extends RenderSkeleton {
   private static final ResourceLocation ARMOR = new ResourceLocation("gibby_pun:textures/texturemaps/armor/skeleton_armor.png");

   public RenderHSkeleton(RenderManager rm) {
      super(rm);
      this.addLayer(new LegacyChargeLayer<AbstractSkeleton>(this, new ModelSkeleton(1.3F, true), ARMOR, e -> true));
   }

   @Override
   protected void preRenderCallback(AbstractSkeleton e, float partial) {
      GlStateManager.scale(1.5F, 1.3F, 1.5F);
   }
}
