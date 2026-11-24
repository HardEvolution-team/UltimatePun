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
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityIcyYou extends EntityMob implements IRangedAttackMob {
   private EntityAIAttackRanged aiArrowAttack = new EntityAIAttackRanged(this, 1.0, 20, 60, 16.0F);
   double X = 0.0;
   double Y = 0.0;
   double Z = 0.0;

   public EntityIcyYou(World par1World) {
      super(par1World);
      this.tasks.addTask(3, this.aiArrowAttack);
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 0, true, false, null));
      this.setSize(1.6F, 1.6F);
      this.experienceValue = 75;
   }

   protected void despawnEntity() {
   }

   protected boolean canDespawn() {
      return false;
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("minecart.inside");
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("mob.wither.spawn");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("mob.wither.spawn");
   }

   protected float getSoundPitch() {
      return 0.7F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0);
   }

   public int getTotalArmorValue() {
      int i = super.getTotalArmorValue() + 5;
      if (i > 20) {
         i = 20;
      }

      return i;
   }

   protected void dropFewItems(boolean par1, int par2) {
      super.dropFewItems(par1, par2);
      if (this.rand.nextInt(3) == 0) {
         this.dropItem(PunMod.ublock, 1);
      }
   }

   public void onUpdate() {
      super.onUpdate();
      this.motionX = 0.0;
      this.motionZ = 0.0;
      this.motionY = 0.0;
      if (this.X == 0.0) {
         this.X = this.posX;
         this.Y = this.posY + 0.2;
         this.Z = this.posZ;
      }

      this.posX = this.X;
      this.posY = this.Y;
      this.posZ = this.Z;
      if (this.findPlayerToAttack() != null && this.findPlayerToAttack() instanceof EntityPlayer) {
         Vec3d vec3 = ((EntityPlayer)this.findPlayerToAttack()).getLook(1.0F).normalize();
         Vec3d vec31 = new Vec3d(
            this.posX - ((EntityPlayer)this.findPlayerToAttack()).posX,
            this.getEntityBoundingBox().minY
               + this.height / 2.0F
               - (((EntityPlayer)this.findPlayerToAttack()).posY + ((EntityPlayer)this.findPlayerToAttack()).getEyeHeight()),
            this.posZ - ((EntityPlayer)this.findPlayerToAttack()).posZ
         );
         double d0 = vec31.length();
         vec31 = vec31.normalize();
         double d1 = vec3.dotProduct(vec31);
         if (d1 > 1.0 - 0.025 / d0 && ((EntityPlayer)this.findPlayerToAttack()).canEntityBeSeen(this)) {
            ((EntityPlayer)this.findPlayerToAttack()).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 100));
            ((EntityPlayer)this.findPlayerToAttack()).addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, -1));
            ((EntityPlayer)this.findPlayerToAttack()).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 100));
            ((EntityPlayer)this.findPlayerToAttack()).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 100, 100));
            ((EntityPlayer)this.findPlayerToAttack()).attackEntityFrom(DamageSource.MAGIC, 2.0F);
            LegacySounds.playSoundAtEntity(this.world, this, "dig.glass", 1.0F, 0.9F);
         }
      }
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      if (this.getIsInvulnerable()) {
         return false;
      } else {
         return par1DamageSource == DamageSource.CACTUS ? false : super.attackEntityFrom(par1DamageSource, par2);
      }
   }

   public void setCombatTask() {
      this.tasks.addTask(4, this.aiArrowAttack);
   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      for (int i = 0; i < this.rand.nextInt(6); i++) {
         EntityIceBall ball = new EntityIceBall(this.world, this);
         double d0 = var1.posX - this.posX;
         double d1 = var1.posY - 1.1F - ball.posY;
         double d2 = var1.posZ - this.posZ;
         float f1 = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
         ball.shoot(d0, d1 + f1 + this.rand.nextInt(50) / 100, d2, 1.0F, 12.0F);
         this.world.spawnEntity(ball);
      }
   }

   @Override
   public void setSwingingArms(boolean swingingArms) {
   }

   /** 1.7 EntityMob.findPlayerToAttack: nearest vulnerable player within 16 blocks. */
   protected net.minecraft.entity.Entity findPlayerToAttack() {
      return this.world.getNearestAttackablePlayer(this, 16.0, 16.0);
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
