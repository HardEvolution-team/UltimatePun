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
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSandWitch extends RenderLiving {
   private static final ResourceLocation sandt = new ResourceLocation("gibby_pun:textures/texturemaps/armor/sandwitch.png");

   public RenderSandWitch(RenderManager rm, ModelWitch witch, float par2) {
      super(rm, witch, par2);
   }

   protected ResourceLocation getEntityTexture(Entity var1) {
      return sandt;
   }
}
