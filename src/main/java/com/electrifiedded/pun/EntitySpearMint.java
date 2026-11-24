package com.electrifiedded.pun;
import net.minecraft.item.ItemStack;

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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntitySpearMint extends EntityArrow {
   public EntitySpearMint(World par1World) {
      super(par1World);
      this.setDead();
   }

   public EntitySpearMint(World par1World, double par2, double par4, double par6) {
      super(par1World);
      this.setDead();
   }

   public EntitySpearMint(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5) {
      super(par1World);
      this.setDead();
   }

   public EntitySpearMint(World par1World, EntityLivingBase par2EntityLivingBase, float par3) {
      super(par1World, par2EntityLivingBase);
      this.shoot(par2EntityLivingBase, par2EntityLivingBase.rotationPitch, par2EntityLivingBase.rotationYaw, 0.0F, par3 * 1.5F, 1.0F);
      if (!par2EntityLivingBase.onGround) {
         this.setIsCritical(true);
      }

      this.setDamage(6.0);
   }

   @Override
   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
   }

   @Override
   protected ItemStack getArrowStack() {
      return new ItemStack(PunMod.spearmint);
   }
}
