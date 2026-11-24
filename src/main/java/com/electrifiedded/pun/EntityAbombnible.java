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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityAbombnible extends EntityMob {
   private int fuseTime = 30;
   EntityLivingBase targetedEntity;
   public int fuse = 0;

   public EntityAbombnible(World par1World) {
      super(par1World);
      this.experienceValue = 20;
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 0, true, false, null));
      this.tasks.addTask(5, new EntityAIWander(this, 0.8));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
      this.setSize(1.3F, 2.5F);
   }

   public int getTotalArmorValue() {
      int i = 0;
      i = super.getTotalArmorValue() + 6;
      if (i > 20) {
         i = 20;
      }

      return i;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(75.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6);
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("mob.wither.idle");
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("mob.wither.hurt");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("mob.wither.hurt");
   }

   protected float getSoundPitch() {
      return 0.1F;
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      if (this.getIsInvulnerable()) {
         return false;
      } else {
         return par1DamageSource.isExplosion() ? false : super.attackEntityFrom(par1DamageSource, par2);
      }
   }

   public void onUpdate() {
      super.onUpdate();
      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(4.0, 4.0, 4.0));
      if (list != null) {
         for (int k2 = 0; k2 < list.size(); k2++) {
            if (list.get(k2) instanceof EntityLivingBase
               && !(list.get(k2) instanceof EntityAbombnible)
               && !(list.get(k2) instanceof EntityColdKiller)
               && !(list.get(k2) instanceof EntityFrostBiter)
               && this.targetedEntity == null) {
               this.targetedEntity = (EntityLivingBase)list.get(k2);
            }
         }
      }

      if (this.targetedEntity != null) {
         if (this.getDistance(this.targetedEntity) < 6.0) {
            this.fuse++;
            if (this.fuse % 5 == 0) {
               LegacySounds.playSoundAtEntity(this.world, this, "random.fizz", 2.0F, 1.0F);
            }

            if (this.fuse > 35) {
               this.world.newExplosion(this, this.posX, this.posY, this.posZ, 4.5F, false, false);
               this.motionY = 1.0;
               this.fallDistance = -5.0F;
               this.fuse = 0;
            }
         } else {
            this.fuse = 0;
            this.targetedEntity = null;
         }
      }
   }

   protected void dropFewItems(boolean par1, int par2) {
      super.dropFewItems(par1, par2);

      for (int i = 0; i < this.rand.nextInt(3); i++) {
         this.dropItem(Item.getItemFromBlock(PunMod.frozendiamond), 1);
      }

      for (int i = 0; i < this.rand.nextInt(4); i++) {
         this.dropItem(Items.GUNPOWDER, 1);
      }
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
