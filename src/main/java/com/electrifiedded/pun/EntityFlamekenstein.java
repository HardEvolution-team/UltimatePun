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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFlamekenstein extends EntityZombie {
   boolean tornado = false;
   boolean pound = false;
   int Y;
   int counter = 0;

   public EntityFlamekenstein(World par1World) {
      super(par1World);
      this.isImmuneToFire = true;
      this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5000, 1));
      this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, 0, false, false, null));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityAnimal.class, 0, false, false, null));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.250000417232513);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
   }

   public int getTotalArmorValue() {
      int i = super.getTotalArmorValue() + 16;
      if (i > 20) {
         i = 20;
      }

      return i;
   }

   public void onUpdate() {
      LegacyParticles.spawn(this.world, 
            "flame",
            this.posX,
            this.posY,
            this.posZ,
            this.rand.nextGaussian() / 10.0,
            this.rand.nextGaussian() / 10.0,
            this.rand.nextGaussian() / 10.0
         );
      LegacyParticles.spawn(this.world, 
            "smoke",
            this.posX,
            this.posY,
            this.posZ,
            this.rand.nextGaussian() / 10.0,
            this.rand.nextGaussian() / 10.0,
            this.rand.nextGaussian() / 10.0
         );
      if (this.pound) {
         if (this.onGround) {
            for (int x1 = 0; x1 < 6; x1++) {
               for (int z1 = 0; z1 < 6; z1++) {
                  LegacyWorld.setBlock(this.world, 
                        MathHelper.floor(this.posX) + x1 - 3,
                        MathHelper.floor(this.posY),
                        MathHelper.floor(this.posZ) + z1 - 3,
                        Blocks.FIRE
                     );
               }
            }

            List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(7.0, 7.0, 7.0));
            if (list != null) {
               for (int k2 = 0; k2 < list.size(); k2++) {
                  if (list.get(k2) instanceof EntityLivingBase && !(list.get(k2) instanceof EntityFlamekenstein)) {
                     ((EntityLivingBase)list.get(k2)).setFire(8);
                     ((EntityLivingBase)list.get(k2)).motionY = 0.9 + this.rand.nextInt(10) / 10;
                  }
               }
            }

            this.pound = false;
         }
      } else if (this.rand.nextInt(100) == 0 && this.getAttackTarget() != null) {
         double d0 = this.getAttackTarget().posX - this.posX;
         double d1 = this.getAttackTarget().getEntityBoundingBox().minY
            + this.getAttackTarget().height / 2.0F
            - (this.posY + 2.0 + this.height / 2.0F);
         double d2 = this.getAttackTarget().posZ - this.posZ;
         EntityFireball fireball = new EntityBigFireball(this.world, this.posX, this.posY + 3.0, this.posZ, d0, d1, d2);
         this.world.spawnEntity(fireball);
      }

      if (this.tornado) {
         EntityFire fire = new EntityFire(this.world, this);

         for (int i = 0; i < 3; i++) {
            this.world.spawnEntity(fire);
         }

         this.counter++;
         LegacySounds.playSoundAtEntity(this.world, this, "mob.blaze.breathe", 1.0F, 1.0F);
         if (this.counter > 200) {
            this.counter = 0;
            this.tornado = false;
         }
      }

      int x = MathHelper.floor(this.posX);
      int y = MathHelper.floor(this.getEntityBoundingBox().minY);
      int z = MathHelper.floor(this.posZ);
      if (LegacyWorld.getBlock(this.world, x, y, z) != Blocks.FIRE) {
         LegacyWorld.setBlock(this.world, x, y, z, Blocks.FIRE);
      }

      if (this.rand.nextInt(200) == 0 && this.getAttackTarget() != null) {
         this.tornado = true;
      } else if (this.rand.nextInt(200) == 1 && this.getAttackTarget() != null) {
         this.motionY = 1.6;
         LegacySounds.playSoundAtEntity(this.world, this, "mob.wither.shoot", 1.0F, 1.3F);
         this.fallDistance = -400.0F;
         this.pound = true;
      }

      super.onUpdate();
   }

   public boolean attackEntityAsMob(Entity par1Entity) {
      boolean flag = super.attackEntityAsMob(par1Entity);
      if (flag) {
         par1Entity.setFire(16);
      }

      return flag;
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
