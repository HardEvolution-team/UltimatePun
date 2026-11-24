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
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityFlicing extends EntityFlying implements IRangedAttackMob {
   private EntityAIAttackRanged aiArrowAttack = new EntityAIAttackRanged(this, 1.0, 20, 60, 10.0F);
   protected EntityPlayer targetedEntity;
   int charge;

   public EntityFlicing(World par1World) {
      super(par1World);
      this.experienceValue = 10;
      this.tasks.addTask(3, this.aiArrowAttack);
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5);
   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      EntityIceBall ball = new EntityIceBall(this.world, this);
      double d0 = var1.posX - this.posX;
      double d1 = var1.posY - 1.1F - ball.posY;
      double d2 = var1.posZ - this.posZ;
      float f1 = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
      LegacySounds.playSoundAtEntity(this.world, this, "random.click", 1.0F, 1.0F);
      ball.shoot(d0, d1 + f1 + this.rand.nextInt(50) / 100, d2, 1.0F, 12.0F);
      this.world.spawnEntity(ball);
   }

   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }

      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(14.0, 14.0, 14.0));
      if (list != null) {
         for (int k2 = 0; k2 < list.size(); k2++) {
            if (list.get(k2) instanceof EntityPlayer && this.targetedEntity == null) {
               this.targetedEntity = (EntityPlayer)list.get(k2);
            }
         }
      }

      if (this.targetedEntity != null) {
         this.charge++;
         if (this.charge > 50 && this.canEntityBeSeen(this.targetedEntity)) {
            this.attackEntityWithRangedAttack(this.targetedEntity, 0.0F);
            this.charge = 0;
         }

         this.targetedEntity = this.world.getNearestAttackablePlayer(this, 30.0, 30.0);
         if (this.targetedEntity != null && this.targetedEntity.getDistance(this) > 10.0F) {
            double d0 = this.targetedEntity.posX - this.posX;
            double d1 = this.targetedEntity.getEntityBoundingBox().minY
               + this.targetedEntity.height / 2.0F
               - (this.posY + this.height / 2.0F);
            double d2 = this.targetedEntity.posZ - this.posZ;
            this.motionX = d0 / 30.0;
            this.motionY = d1 / 30.0 + 0.1;
            this.motionZ = d2 / 30.0;
         }
      }
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("random.fuse");
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("dig.glass");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("dig.glass");
   }

   protected float getSoundPitch() {
      return 0.7F;
   }

   protected void dropFewItems(boolean par1, int par2) {
      super.dropFewItems(par1, par2);
      if (this.rand.nextInt(2) == 0) {
         this.dropItem(Item.getItemFromBlock(PunMod.frozenemerald), 1);
      }

      this.dropItem(PunMod.frenchfrice, 1);
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

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      if (this.getIsInvulnerable()) {
         return false;
      } else {
         return par1DamageSource == DamageSource.CACTUS ? false : super.attackEntityFrom(par1DamageSource, par2);
      }
   }

   public boolean getCanSpawnHere() {
      return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
   }

   @Override
   public void setSwingingArms(boolean swingingArms) {
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
