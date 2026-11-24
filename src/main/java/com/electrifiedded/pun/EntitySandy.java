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
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySandy extends EntityThrowable {
   public EntitySandy(World world) {
      super(world);
      this.setDead();
   }

   public EntitySandy(World par1World, EntityLivingBase par2EntityLivingBase) {
      super(par1World, par2EntityLivingBase);
      com.electrifiedded.archaiclib.legacy.entity.LegacyThrowable.applyLegacyThrowerInit(this, par2EntityLivingBase, 1.5F, 1.0F, 0.0F);
      this.setSize(2.0F, 2.0F);
   }

   public EntitySandy(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
      this.setDead();
   }

   protected void onImpact(RayTraceResult var1) {
      if (var1.entityHit != null) {
         var1.entityHit.motionX = 0.0;
         var1.entityHit.motionZ = 0.0;
         var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, var1.entityHit), 5.0F);
         var1.entityHit.motionY = 1.0;
         this.setDead();
      }

      if (this.world != null) {
         int X = Math.round((float)Math.round(this.posX - 1.0));
         int Y = Math.round((float)Math.round(this.posY));
         int Z = Math.round((float)Math.round(this.posZ - 1.0));
         LegacyWorld.setBlock(this.world, X, Y, Z, Blocks.SAND);
         LegacyWorld.setBlock(this.world, X, Y + 1, Z, Blocks.SAND);
         LegacyWorld.setBlock(this.world, X, Y, Z + 1, Blocks.SAND);
         LegacyWorld.setBlock(this.world, X, Y + 1, Z + 1, Blocks.SAND);
         LegacyWorld.setBlock(this.world, X + 1, Y, Z, Blocks.SAND);
         LegacyWorld.setBlock(this.world, X + 1, Y + 1, Z, Blocks.SAND);
         LegacyWorld.setBlock(this.world, X + 1, Y, Z + 1, Blocks.SAND);
         LegacyWorld.setBlock(this.world, X + 1, Y + 1, Z + 1, Blocks.SAND);
         this.setDead();
      }
   }
}
