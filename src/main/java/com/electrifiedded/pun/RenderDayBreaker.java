package com.electrifiedded.pun;

import com.electrifiedded.archaiclib.legacy.client.LegacyChargeLayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/** 1.7 RenderDayBreaker: texture follows the boss phase (0-5); phase 5 adds the energy overlay. */
public class RenderDayBreaker extends RenderLiving<EntityDayBreaker> {
   private static final ResourceLocation[] PHASES = new ResourceLocation[6];
   private static final ResourceLocation ARMOR = new ResourceLocation("gibby_pun:textures/texturemaps/armor/daybreaker_armor.png");

   static {
      for (int i = 0; i < 6; i++) {
         PHASES[i] = new ResourceLocation("gibby_pun:textures/texturemaps/armor/Daybreak-er_" + i + ".png");
      }
   }

   public RenderDayBreaker(RenderManager rm, Modeldaybreak model, float shadow) {
      super(rm, model, shadow);
      this.addLayer(new LegacyChargeLayer<EntityDayBreaker>(this, new Modeldaybreak(), ARMOR, e -> EntityDayBreaker.entitystate == 5));
   }

   @Override
   protected ResourceLocation getEntityTexture(EntityDayBreaker e) {
      int s = EntityDayBreaker.entitystate;
      return PHASES[s < 0 || s > 5 ? 0 : s];
   }
}
