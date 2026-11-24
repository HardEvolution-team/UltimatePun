package com.electrifiedded.pun;
import net.minecraft.client.renderer.entity.RenderManager;

import net.minecraft.init.MobEffects;
import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;
import com.electrifiedded.archaiclib.legacy.registry.LegacyRecipes;
import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;
import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.SoundEvent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderColdKiller extends RenderBiped {
   private static final ResourceLocation skeletont = new ResourceLocation("gibby_pun:textures/texturemaps/armor/coldkiller.png");
   private static final ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");

   public RenderColdKiller(RenderManager rm, ModelBiped par1, float par2) {
      super(rm, par1, par2);
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityColdKiller)entity);
   }

   private ResourceLocation getEntityTexture(EntityColdKiller entity) {
      if (entity.getHealth() >= 15.0F) {
         return skeletont;
      } else {
         return entity.getHealth() < 15.0F ? skeletonTextures : null;
      }
   }
}
