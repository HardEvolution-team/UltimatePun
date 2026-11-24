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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCloud extends EntityFlying {
   private int charge;
   private Entity targetedEntity;

   public EntityCloud(World par1World) {
      super(par1World);
      this.experienceValue = 20;
      this.setSize(2.0F, 1.2F);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0);
   }

   public void onUpdate() {
      super.onUpdate();
      this.charge++;
      this.targetedEntity = this.world.getNearestAttackablePlayer(this, 60.0, 60.0);
      if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }

      if (this.targetedEntity != null) {
         if (this.posY < this.targetedEntity.posY) {
            this.motionY++;
         }

         if (this.posY < 70.0) {
            this.motionY++;
         }

         if (this.posY > 120.0) {
            this.motionY--;
         }

         this.faceEntity(this.targetedEntity, 0.0F, 0.0F);
         Vec3d look = this.targetedEntity.getLookVec();
         double X = look.x * 0.1;
         double Y = look.y * 0.1;
         double Z = look.z * 0.1;
         this.motionX -= X / 1.6;
         this.motionZ -= Z / 1.6;
         if (this.posX - this.targetedEntity.posX < 2.0
            && this.posX - this.targetedEntity.posX > -2.0
            && this.posZ - this.targetedEntity.posZ < 2.0
            && this.posZ - this.targetedEntity.posZ > -2.0
            && this.canEntityBeSeen(this.targetedEntity)) {
            this.motionX = 0.0;
            this.motionZ = 0.0;
            if (this.charge > 40) {
               EntityLightningBolt bolt = new EntityLightningBolt(
                  this.world, this.targetedEntity.posX, this.targetedEntity.posY, this.targetedEntity.posZ
               , false);
               this.world.addWeatherEffect(bolt);
               this.charge = 0;
            }
         }
      } else {
         this.motionX = -0.06;
         if (this.posY < 80.0) {
            this.motionY++;
         }

         if (this.posY > 130.0) {
            this.motionY--;
         }
      }
   }
}
