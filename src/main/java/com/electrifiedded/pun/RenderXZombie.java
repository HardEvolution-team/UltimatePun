package com.electrifiedded.pun;

import com.electrifiedded.archaiclib.legacy.client.LegacyChargeLayer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

/** 1.7 RenderXZombie: zombie with a scrolling energy overlay. */
public class RenderXZombie extends RenderZombie {
   private static final ResourceLocation ARMOR = new ResourceLocation("gibby_pun:textures/texturemaps/armor/zombie_armor.png");

   public RenderXZombie(RenderManager rm) {
      super(rm);
      this.addLayer(new LegacyChargeLayer<EntityZombie>(this, new ModelZombie(), ARMOR, e -> true));
   }
}
