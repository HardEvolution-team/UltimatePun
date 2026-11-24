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
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EnitiyLiqsquid extends net.minecraft.entity.passive.EntityWaterMob {
   public float squidPitch;
   public float prevSquidPitch;
   public float squidYaw;
   public float prevSquidYaw;
   public float squidRotation;
   public float prevSquidRotation;
   public float tentacleAngle;
   public float lastTentacleAngle;
   private float randomMotionSpeed;
   private float rotationVelocity;
   private float field_70871_bB;
   private float randomMotionVecX;
   private float randomMotionVecY;
   private float randomMotionVecZ;

   public EnitiyLiqsquid(World par1World) {
      super(par1World);
      this.setSize(3.0F, 3.0F);
      this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0);
   }

   public boolean canBreatheUnderwater() {
      return true;
   }

   public void onEntityUpdate() {
      int i = this.getAir();
      super.onEntityUpdate();
      if (this.isEntityAlive() && !this.isInWater()) {
         this.setAir(--i);
         if (this.getAir() == -20) {
            this.setAir(0);
            this.attackEntityFrom(DamageSource.DROWN, 2.0F);
         }
      } else {
         this.setAir(300);
      }
   }

   public boolean isInWater() {
      return this.world.handleMaterialAcceleration(this.getEntityBoundingBox().expand(0.0, -0.6F, 0.0), Material.WATER, this);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.prevSquidPitch = this.squidPitch;
      this.prevSquidYaw = this.squidYaw;
      this.prevSquidRotation = this.squidRotation;
      this.lastTentacleAngle = this.tentacleAngle;
      this.squidRotation = this.squidRotation + this.rotationVelocity;
      if (this.squidRotation > (float) Math.PI) {
         this.squidRotation -= (float) Math.PI;
         if (this.rand.nextInt(10) == 0) {
            this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
         }
      }

      if (this.isInWater()) {
         List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(1.5, 1.5, 1.5));
         if (list != null) {
            for (int k2 = 0; k2 < list.size(); k2++) {
               if (list.get(k2) instanceof EntityPlayer) {
                  ((EntityLivingBase)list.get(k2)).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0));
                  if (this.rand.nextInt(100) == 1) {
                     ((EntityLivingBase)list.get(k2)).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0));
                  }

                  for (int i = 0; i < 500; i++) {
                     LegacyParticles.spawn(this.world, 
                           "smoke",
                           this.posX + this.rand.nextGaussian(),
                           this.posY + this.rand.nextGaussian(),
                           this.posZ + this.rand.nextGaussian(),
                           this.rand.nextGaussian(),
                           this.rand.nextGaussian(),
                           this.rand.nextGaussian()
                        );
                  }
               }
            }
         }

         if (this.squidRotation < (float) Math.PI) {
            float f = this.squidRotation / (float) Math.PI;
            this.tentacleAngle = MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;
            if (f > 0.75) {
               this.randomMotionSpeed = 1.0F;
               this.field_70871_bB = 1.0F;
            } else {
               this.field_70871_bB *= 0.8F;
            }
         } else {
            this.tentacleAngle = 0.0F;
            this.randomMotionSpeed *= 0.9F;
            this.field_70871_bB *= 0.99F;
         }

         if (!this.world.isRemote) {
            this.motionX = this.randomMotionVecX * this.randomMotionSpeed;
            this.motionY = this.randomMotionVecY * this.randomMotionSpeed;
            this.motionZ = this.randomMotionVecZ * this.randomMotionSpeed;
         }

         float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         this.renderYawOffset = this.renderYawOffset
            + (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI - this.renderYawOffset) * 0.1F;
         this.rotationYaw = this.renderYawOffset;
         this.squidYaw = this.squidYaw + (float) Math.PI * this.field_70871_bB * 1.5F;
         this.squidPitch = this.squidPitch + (-((float)Math.atan2(f, this.motionY)) * 180.0F / (float) Math.PI - this.squidPitch) * 0.1F;
      } else {
         this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float) Math.PI * 0.25F;
         if (!this.world.isRemote) {
            this.motionX = 0.0;
            this.motionY -= 0.081;
            this.motionY *= 0.98F;
            this.motionZ = 0.0;
         }

         this.squidPitch = (float)(this.squidPitch + (-90.0F - this.squidPitch) * 0.02);
      }
   }

   @Override
   public void travel(float strafe, float vertical, float forward) {
      this.move(net.minecraft.entity.MoverType.SELF, this.motionX, this.motionY, this.motionZ);
   }

   @Override
   protected void updateAITasks() {
      this.idleTime++;
      if (this.idleTime > 100) {
         this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
      } else if (this.rand.nextInt(50) == 0
         || !this.inWater
         || this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F) {
         float f = this.rand.nextFloat() * (float) Math.PI * 2.0F;
         this.randomMotionVecX = MathHelper.cos(f) * 0.2F;
         this.randomMotionVecY = -0.1F + this.rand.nextFloat() * 0.2F;
         this.randomMotionVecZ = MathHelper.sin(f) * 0.2F;
      }

   }

   public boolean getCanSpawnHere() {
      return this.posY > 45.0 && this.posY < 63.0 && super.getCanSpawnHere();
   }

   protected void dropFewItems(boolean par1, int par2) {
      int j = this.rand.nextInt(10 + par2) + 1;

      for (int k = 0; k < j; k++) {
         this.entityDropItem(new ItemStack(Items.DYE, 1, 0), 0.0F);
      }

   }

   @Override
   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("liquid.water");
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("liquid.splash");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("liquid.splash");
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
