package com.electrifiedded.pun;
import net.minecraft.util.DamageSource;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEquipment;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityGlaciadorBoss extends EntityColdKiller {
   public EntityGlaciadorBoss(World par1World) {
      super(par1World);
      this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3000, 0));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24000000417232514);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0);
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("random.anvil_land");
   }

   protected float getSoundPitch() {
      return 0.6F;
   }

   @Override
   public void onUpdate() {
      super.onUpdate();
      if (this.getHealth() < 15.0F) {
         this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3000, 2));
      }
   }

   @Override
   protected void setEquipmentBasedOnDifficulty(net.minecraft.world.DifficultyInstance difficulty) {
      super.setEquipmentBasedOnDifficulty(difficulty);
      LegacyEquipment.set(this, 0, new ItemStack(PunMod.arctcut));
      LegacyEquipment.set(this, 1, new ItemStack(Items.DIAMOND_HELMET));
      LegacyEquipment.set(this, 2, new ItemStack(Items.DIAMOND_CHESTPLATE));
      LegacyEquipment.set(this, 3, new ItemStack(Items.DIAMOND_LEGGINGS));
      LegacyEquipment.set(this, 4, new ItemStack(Items.DIAMOND_BOOTS));
   }

   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
      par1EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 0));
   }

   @Override
   public int getTotalArmorValue() {
      int i = 0;
      if (this.getHealth() >= 15.0F) {
         i = super.getTotalArmorValue() + 16;
      } else if (this.getHealth() < 15.0F) {
         i = super.getTotalArmorValue();
      }

      if (i > 23) {
         i = 23;
      }

      return i;
   }
}
