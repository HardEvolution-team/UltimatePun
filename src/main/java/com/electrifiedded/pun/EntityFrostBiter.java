package com.electrifiedded.pun;
import net.minecraft.util.DamageSource;

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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityFrostBiter extends EntitySpider {
   public EntityFrostBiter(World par1World) {
      super(par1World);
      this.setSize(1.0F, 0.7F);
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("mob.silverfish.say");
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("dig.glass");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("mob.silverfish.kill");
   }

   protected float getSoundPitch() {
      return 1.2F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0);
   }

   protected void dropFewItems(boolean par1, int par2) {
      super.dropFewItems(par1, par2);
      this.dropItem(PunMod.frenchfrice, 1);
   }

   protected Item getDropItem() {
      return Item.getItemFromBlock(Blocks.ICE);
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
