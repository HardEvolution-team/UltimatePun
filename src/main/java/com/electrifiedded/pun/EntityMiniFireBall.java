package com.electrifiedded.pun;

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
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMiniFireBall extends EntityThrowable {
   public EntityMiniFireBall(World par1World) {
      super(par1World);
   }

   public EntityMiniFireBall(World par1World, EntityLivingBase par2EntityLivingBase) {
      super(par1World, par2EntityLivingBase);
      com.electrifiedded.archaiclib.legacy.entity.LegacyThrowable.applyLegacyThrowerInit(this, par2EntityLivingBase, 1.5F, 1.0F, 0.0F);
      this.motionX = this.motionX + this.rand.nextGaussian() / 5.0;
      this.motionY = this.motionY + this.rand.nextGaussian() / 5.0;
      this.motionZ = this.motionZ + this.rand.nextGaussian() / 5.0;
   }

   protected float getGravityVelocity() {
      return -0.0F;
   }

   public EntityMiniFireBall(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.ticksExisted > 100) {
         this.setDead();
      }

      LegacyParticles.spawn(this.world, 
            "smoke",
            this.posX,
            this.posY,
            this.posZ,
            this.rand.nextDouble() / 40.0,
            this.rand.nextDouble() / 40.0,
            this.rand.nextDouble() / 40.0
         );
      LegacyParticles.spawn(this.world, 
            "flame",
            this.posX,
            this.posY,
            this.posZ,
            this.rand.nextDouble() / 40.0,
            this.rand.nextDouble() / 40.0,
            this.rand.nextDouble() / 40.0
         );
   }

   protected void onImpact(RayTraceResult var1) {
      if (var1.entityHit != null) {
         if (var1.entityHit instanceof EntityLivingBase) {
            var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, var1.entityHit), 2.0F);
            var1.entityHit.setFire(5);
         }

         if (this.world != null) {
            this.setDead();
         }
      }
   }
}
