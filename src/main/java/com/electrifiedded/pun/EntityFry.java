package com.electrifiedded.pun;
import net.minecraft.util.DamageSource;
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
import java.util.Calendar;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFry extends EntitySkeleton implements IRangedAttackMob {
   private EntityAIAttackRanged aiArrowAttack = new EntityAIAttackRanged(this, 1.0, 20, 60, 20.0F);
   public static int attackTimer = 0;

   public EntityFry(World par1World) {
      super(par1World);
      this.setSize(0.3F, 1.0F);
      this.isImmuneToFire = true;
      this.tasks.addTask(1, new EntityAIAttackRanged(this, 0.5, 10, 20.0F));
      this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, 1, true, false, null));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24006);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0);
   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
      EntityMiniFireBall fire = new EntityMiniFireBall(this.world, this);
      this.isImmuneToFire = true;
      double d0 = var1.posX - this.posX;
      double d1 = var1.posY + var1.getEyeHeight() - 1.300000023841858 - fire.posY + this.rand.nextInt(10) / 50;
      double d2 = var1.posZ - this.posZ;
      float f1 = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
      fire.shoot(d0, d1 + f1, d2, 0.6F, 2.0F);
      fire.posY--;
      this.world.spawnEntity(fire);
      LegacySounds.playSoundAtEntity(this.world, this, "fire.fire", 1.4F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.5F) * 1.8F);
      attackTimer = 0;
   }

   @Override
   protected void setEquipmentBasedOnDifficulty(net.minecraft.world.DifficultyInstance difficulty) {
      super.setEquipmentBasedOnDifficulty(difficulty);
      LegacyEquipment.set(this, 0, new ItemStack(Item.getItemFromBlock(Blocks.FIRE)));
   }

   protected void dropFewItems(boolean par1, int par2) {
      int j = this.rand.nextInt(3);

      for (int i = 0; i < j; i++) {
         this.entityDropItem(new ItemStack(Items.BONE), 1.0F);
      }

      j = this.rand.nextInt(2);
      if (j == 1) {
         this.entityDropItem(new ItemStack(Items.FIRE_CHARGE), 1.0F);
      }
   }

   protected void dropRareDrop(int par1) {
      this.entityDropItem(new ItemStack(Items.BLAZE_ROD, 1, 1), 0.0F);
   }

   public IEntityLivingData onInitialSpawn(net.minecraft.world.DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
      par1EntityLivingData = super.onInitialSpawn(difficulty, par1EntityLivingData);
      this.tasks.addTask(4, this.aiArrowAttack);
      this.setEquipmentBasedOnDifficulty(this.world.getDifficultyForLocation(new BlockPos(this)));
      this.setEnchantmentBasedOnDifficulty(this.world.getDifficultyForLocation(new BlockPos(this)));
      this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());
      if (LegacyEquipment.get(this, 4) == null || LegacyEquipment.get(this, 4).isEmpty()) {
         Calendar calendar = this.world.getCurrentDate();
         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
            LegacyEquipment.set(this, 4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
            LegacyEquipment.setDropChance(this, 4, 0.0F);
         }
      }

      return par1EntityLivingData;
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
