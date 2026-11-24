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

public class EntityPunisher extends EntityThrowable {
   public EntityPunisher(World par1World) {
      super(par1World);
      this.setDead();
   }

   public EntityPunisher(World par1World, EntityLivingBase par2EntityLivingBase) {
      super(par1World, par2EntityLivingBase);
      com.electrifiedded.archaiclib.legacy.entity.LegacyThrowable.applyLegacyThrowerInit(this, par2EntityLivingBase, 1.5F, 1.0F, 0.0F);
   }

   public EntityPunisher(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
      this.setDead();
   }

   protected void onImpact(RayTraceResult var1) {
      if (var1.entityHit != null) {
         var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5.0F);
         this.setDead();
      }

      if (this.world != null) {
         this.setDead();
      }
   }

   public void onEntityUpdate() {
      super.onEntityUpdate();
      LegacyParticles.spawn(this.world, "reddust", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
   }
}
