package com.electrifiedded.pun;
import net.minecraft.util.DamageSource;

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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntitySandWitch extends EntityWitch implements IRangedAttackMob {
   Entity player;
   int block;

   public EntitySandWitch(World par1World) {
      super(par1World);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0, 60, 15.0F));
      this.tasks.addTask(2, new EntityAIWander(this, 1.0));
      this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(3, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 0, true, false, null));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.245);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      this.player = var1;
      int X = Math.round((float)Math.round(this.player.posX - 1.0));
      int Y = Math.round((float)Math.round(this.player.posY + 3.0));
      int Z = Math.round((float)Math.round(this.player.posZ - 1.0));

      for (int x = 0; x < 3; x++) {
         for (int z = 0; z < 3; z++) {
            for (int y = 0; y < 3; y++) {
               if (this.rand.nextInt(2) == 0) {
                  this.block = 0;
               } else {
                  this.block = 1;
               }

               LegacySounds.playSoundAtEntity(this.world, this, "dig.sand", 1.2F, 0.9F);
               LegacyWorld.setBlock(this.world, X + x, Y + y, Z + z, Blocks.SAND, this.block, 0);
            }
         }
      }
   }

   protected void dropRareDrop(int par1) {
      this.dropItem(PunMod.sandorcery, 1);
   }

   @Override
   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("mob.witch.idle");
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("dig.sand");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("dig.sand");
   }

   protected void dropFewItems(boolean par1, int par2) {
      int j = this.rand.nextInt(3) + 1;

      for (int k = 0; k < j; k++) {
         this.dropItem(Item.getItemFromBlock(Blocks.SAND), 1);
      }
   }

   @Override
   public void setSwingingArms(boolean swingingArms) {
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }

   @Override
   protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
      super.dropLoot(wasRecentlyHit, lootingModifier, source);
      if (wasRecentlyHit) {
         int k = this.rand.nextInt(200) - lootingModifier;
         if (k < 5) {
            this.dropRareDrop(k <= 0 ? 1 : 0);
         }
      }
   }
}
