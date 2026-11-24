package com.electrifiedded.pun;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEquipment;

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
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityColdKiller extends EntitySkeleton {
   private EntityAIAttackMelee aiAttackOnCollide = new EntityAIAttackMelee(this, 1.2, false);

   public EntityColdKiller(World par1World) {
      super(par1World);
      this.isImmuneToFire = true;
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 0, true, false, null));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, 0, true, false, null));
      this.tasks.addTask(5, new EntityAIWander(this, 0.8));
      this.tasks.addTask(6, new EntityAILookIdle(this));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23F);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0);
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.getHealth() < 15.0F) {
         this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3000, 1));
      }
   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      if (this.getIsInvulnerable()) {
         return false;
      } else {
         if (this.getHealth() >= 15.0F) {
            for (int i = 0; i < 100; i++) {
               LegacyParticles.spawn(this.world, 
                     "blockcrack_" + Block.getIdFromBlock(Blocks.ICE) + "_0",
                     this.posX,
                     this.posY + this.rand.nextGaussian(),
                     this.posZ,
                     this.rand.nextGaussian(),
                     this.rand.nextGaussian() * 2.0,
                     this.rand.nextGaussian()
                  );
            }
         }

         return super.attackEntityFrom(par1DamageSource, par2);
      }
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get(this.getHealth() <= 15.0F ? "mob.skeleton.hurt" : "dig.glass");
   }

   @Override
   protected void setEquipmentBasedOnDifficulty(net.minecraft.world.DifficultyInstance difficulty) {
      super.setEquipmentBasedOnDifficulty(difficulty);
      LegacyEquipment.set(this, 0, new ItemStack(PunMod.icesickle));
   }

   public int getTotalArmorValue() {
      int i = 0;
      if (this.getHealth() >= 15.0F) {
         i = super.getTotalArmorValue() + 16;
      } else if (this.getHealth() < 15.0F) {
         i = super.getTotalArmorValue();
      }

      if (i > 20) {
         i = 20;
      }

      return i;
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
