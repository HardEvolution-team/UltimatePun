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
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySquidBomb extends EntityThrowable {
   public EntitySquidBomb(World world) {
      super(world);
      this.setDead();
   }

   public EntitySquidBomb(World par1World, EntityLivingBase par2EntityLivingBase) {
      super(par1World, par2EntityLivingBase);
      com.electrifiedded.archaiclib.legacy.entity.LegacyThrowable.applyLegacyThrowerInit(this, par2EntityLivingBase, 1.5F, 1.0F, 0.0F);
      this.setSize(1.0F, 1.4F);
   }

   public EntitySquidBomb(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
      this.setDead();
   }

   protected void onImpact(RayTraceResult var1) {
      if (!this.world.isRemote) {
         List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(6.0, 6.0, 6.0));
         if (list != null) {
            for (int k2 = 0; k2 < list.size(); k2++) {
               if (list.get(k2) instanceof EntityLivingBase) {
                  ((EntityLivingBase)list.get(k2)).attackEntityFrom(DamageSource.causeThrownDamage(this, (EntityLivingBase)list.get(k2)), 8.0F);
                  ((EntityLivingBase)list.get(k2)).motionY = 1.0 + this.rand.nextInt(10) / 15;
                  double d0 = ((EntityLivingBase)list.get(k2)).posX - this.posX;
                  double d1 = ((EntityLivingBase)list.get(k2)).posZ - this.posZ;
                  ((EntityLivingBase)list.get(k2)).motionX = d0 / 1.8;
                  ((EntityLivingBase)list.get(k2)).motionZ = d1 / 1.8;
               }
            }
         }
      }

      for (int i = 0; i < 300; i++) {
         EntityRain drop = new EntityRain(
            this.world,
            this.posX + this.rand.nextInt(60) / 10 - 3.0,
            this.posY + this.rand.nextInt(60) / 10 - 3.0,
            this.posZ + this.rand.nextInt(60) / 10 - 3.0
         );
         this.world.spawnEntity(drop);
      }

      for (int i = 0; i < 100; i++) {
         EntityInk ink = new EntityInk(
            this.world,
            this.posX + this.rand.nextInt(60) / 10 - 3.0,
            this.posY + this.rand.nextInt(60) / 10 - 3.0,
            this.posZ + this.rand.nextInt(60) / 10 - 3.0
         );
         this.world.spawnEntity(ink);
      }

      LegacySounds.playSoundAtEntity(this.world, this, "random.splash", 2.0F, 1.0F);
      this.setDead();
   }
}
