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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityMinion extends EntityFlying {
   private Entity targetedEntity;

   public EntityMinion(World par1World) {
      super(par1World);
   }

   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }

      if (this.getHealth() <= 0.0F) {
         for (int i = 0; i < 15; i++) {
            Entity entity = spawnCreature(this.world, 65, this.posX, this.posY, this.posZ);
         }

         this.setDead();
      }

      this.targetedEntity = this.world.getNearestAttackablePlayer(this, 100.0, 100.0);
      if (this.targetedEntity != null) {
         this.faceEntity(this.targetedEntity, 0.0F, 0.0F);
         Vec3d look = this.targetedEntity.getLookVec();
         double X = look.x * 0.1;
         double Y = look.y * 0.1;
         double Z = look.z * 0.1;
         this.motionX -= X / 1.9;
         this.motionZ -= Z / 1.9;
         this.motionY -= Y / 1.9;
         if (this.getDistance(this.targetedEntity) < 1.1) {
            this.targetedEntity.attackEntityFrom(DamageSource.MAGIC, 1.0F);
            ((EntityLivingBase)this.targetedEntity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 250, 0));
            ((EntityLivingBase)this.targetedEntity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 250, 0));
            ((EntityLivingBase)this.targetedEntity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0));
            LegacySounds.playSoundAtEntity(this.world, (EntityLivingBase)this.targetedEntity, "portal.portal", 0.8F, 1.0F);
         }
      }
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("fire.ignite");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("fire.ignite");
   }

   public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
      {
         Entity entity = new net.minecraft.entity.passive.EntityBat(par0World);
         if (entity != null && entity instanceof EntityLivingBase) {
            entity.setLocationAndAngles(par2, par4, par6, MathHelper.wrapDegrees(par0World.rand.nextFloat() * 360.0F), 0.0F);
            par0World.spawnEntity(entity);
         }

         return entity;
      }
   }

   protected boolean isValidLightLevel() {
      int i = MathHelper.floor(this.posX);
      int j = MathHelper.floor(this.getEntityBoundingBox().minY);
      int k = MathHelper.floor(this.posZ);
      if (this.world.getLightFor(EnumSkyBlock.SKY, new BlockPos(i, j, k)) > this.rand.nextInt(32)) {
         return false;
      } else {
         int l = this.world.getLightFromNeighbors(new BlockPos(i, j, k));
         if (this.world.isThundering()) {
            int i1 = this.world.getSkylightSubtracted();
            this.world.setSkylightSubtracted(10);
            l = this.world.getLightFromNeighbors(new BlockPos(i, j, k));
            this.world.setSkylightSubtracted(i1);
         }

         return l <= this.rand.nextInt(8);
      }
   }

   public boolean getCanSpawnHere() {
      return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
   }
}
