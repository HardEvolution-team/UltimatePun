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
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBlackHole extends EntityThrowable {
   public EntityBlackHole(World par1World) {
      super(par1World);
      this.setDead();
   }

   public EntityBlackHole(World par1World, EntityLivingBase par2EntityLivingBase) {
      super(par1World, par2EntityLivingBase);
      com.electrifiedded.archaiclib.legacy.entity.LegacyThrowable.applyLegacyThrowerInit(this, par2EntityLivingBase, 0.7F, 1.0F, 0.0F);
   }

   public EntityBlackHole(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
      this.setDead();
   }

   protected float getGravityVelocity() {
      return 0.0F;
   }


   public void onEntityUpdate() {
      super.onEntityUpdate();
      if (this.rand.nextInt(10) == 0) {
         LegacySounds.playSoundAtEntity(this.world, this, "mob.wither.spawn", 2.0F, 0.6F);
      }

      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(15.0, 4.0, 15.0));
      if (list != null) {
         for (int k2 = 0; k2 < list.size(); k2++) {
            if (list.get(k2) instanceof EntityLivingBase && ((EntityLivingBase)list.get(k2)).onGround) {
               double d0 = ((EntityLivingBase)list.get(k2)).posX - this.posX;
               double d1 = ((EntityLivingBase)list.get(k2)).posZ - this.posZ;
               ((EntityLivingBase)list.get(k2)).motionX = -d0 / (10.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
               ((EntityLivingBase)list.get(k2)).motionZ = -d1 / (10.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
            }
         }
      }

      if (this.ticksExisted > 200) {
         this.world.newExplosion(this, this.posX, this.posY, this.posZ, 30.0F, false, true);
         this.setDead();
      }
   }

   protected void onImpact(RayTraceResult var1) {
      if (!this.world.isRemote) {
         this.world.newExplosion(this, this.posX, this.posY, this.posZ, 20.0F, false, true);
         this.setDead();
      }
   }
}
