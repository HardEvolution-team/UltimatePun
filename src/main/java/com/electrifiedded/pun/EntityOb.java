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
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityOb extends EntityIronGolem {
   private final net.minecraft.world.BossInfoServer bossInfo = (net.minecraft.world.BossInfoServer) new net.minecraft.world.BossInfoServer(this.getDisplayName(), net.minecraft.world.BossInfo.Color.PURPLE, net.minecraft.world.BossInfo.Overlay.PROGRESS).setDarkenSky(false);

   @Override
   public void addTrackingPlayer(net.minecraft.entity.player.EntityPlayerMP player) {
      super.addTrackingPlayer(player);
      this.bossInfo.addPlayer(player);
   }

   @Override
   public void removeTrackingPlayer(net.minecraft.entity.player.EntityPlayerMP player) {
      super.removeTrackingPlayer(player);
      this.bossInfo.removePlayer(player);
   }


   public double waypointX;
   public double waypointZ;

   public EntityOb(World par1World) {
      super(par1World);
      this.setHealth(this.getMaxHealth());
      this.setSize(2.4F, 5.8F);
      this.isImmuneToFire = true;
      this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20000, 0));
      this.experienceValue = 50;
      this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0, false));
      this.tasks.addTask(7, new EntityAIWander(this, 1.0));
      this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 0, true, false, null));
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityLiving.class, 0, true, false, null));
      this.canBeCollidedWith();
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0);
   }

   public boolean attackEntityAsMob(Entity par1Entity) {
      boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10 + this.rand.nextInt(8));
      if (flag) {
         par1Entity.motionY = par1Entity.motionY + (0.55 + this.rand.nextInt(10) / 20);
         this.world.setEntityState(this, (byte)4);

         for (int i = 0; i < 150; i++) {
            par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 0.0F);
         }

         ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1));
         ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 1));
      }

      this.playSound(LegacySounds.get("mob.irongolem.throw"), 1.5F, 1.5F);
      return flag;
   }

   public boolean canBePushed() {
      return false;
   }

   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
         this.setDead();
      }
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());

      for (int i = 0; i < this.rand.nextInt(100); i++) {
         LegacyParticles.spawn(this.world, 
               "portal",
               this.posX,
               this.posY,
               this.posZ,
               this.rand.nextInt(20) - 10,
               this.rand.nextInt(8) - 4,
               this.rand.nextInt(20) - 10
            );
      }

      if (this.getHealth() <= 0.0F) {
         for (int i = 0; i < 10; i++) {
            LegacyParticles.spawn(this.world, "largeexplode", this.posX, this.posY + 2.0, this.posZ, 0.0, 0.0, 0.0);
         }

         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 1,
               Math.round((float)Math.round(this.posY)),
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 1,
               Math.round((float)Math.round(this.posY)),
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 1,
               Math.round((float)Math.round(this.posY)) + 1,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 1,
               Math.round((float)Math.round(this.posY)) + 1,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)),
               Math.round((float)Math.round(this.posY)) + 2,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)),
               Math.round((float)Math.round(this.posY)) + 3,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)),
               Math.round((float)Math.round(this.posY)) + 4,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)),
               Math.round((float)Math.round(this.posY)) + 5,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 1,
               Math.round((float)Math.round(this.posY)) + 3,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 1,
               Math.round((float)Math.round(this.posY)) + 4,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 1,
               Math.round((float)Math.round(this.posY)) + 3,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 1,
               Math.round((float)Math.round(this.posY)) + 4,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 2,
               Math.round((float)Math.round(this.posY)) + 4,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 2,
               Math.round((float)Math.round(this.posY)) + 3,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 2,
               Math.round((float)Math.round(this.posY)) + 2,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 2,
               Math.round((float)Math.round(this.posY)) + 2,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 2,
               Math.round((float)Math.round(this.posY)) + 3,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 2,
               Math.round((float)Math.round(this.posY)) + 4,
               Math.round((float)Math.round(this.posZ)),
               Blocks.OBSIDIAN
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 2,
               Math.round((float)Math.round(this.posY)) + 1,
               Math.round((float)Math.round(this.posZ)),
               Blocks.NETHER_BRICK,
               1,
               1
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 2,
               Math.round((float)Math.round(this.posY)) + 1,
               Math.round((float)Math.round(this.posZ)),
               Blocks.NETHER_BRICK,
               1,
               1
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) + 1,
               Math.round((float)Math.round(this.posY)) + 4,
               Math.round((float)Math.round(this.posZ)),
               Blocks.NETHER_BRICK,
               1,
               1
            );
         LegacyWorld.setBlock(this.world, 
               Math.round((float)Math.round(this.posX)) - 1,
               Math.round((float)Math.round(this.posY)) + 4,
               Math.round((float)Math.round(this.posZ)),
               Blocks.NETHER_BRICK,
               1,
               1
            );
         this.setDead();
      }

      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(45.0, 4.0, 45.0));
      if (list != null) {
         for (int k2 = 0; k2 < list.size(); k2++) {
            if (list.get(k2) instanceof EntityLivingBase && ((EntityLivingBase)list.get(k2)).onGround) {
               double d0 = ((EntityLivingBase)list.get(k2)).posX - this.posX;
               double d1 = ((EntityLivingBase)list.get(k2)).posZ - this.posZ;
               ((EntityLivingBase)list.get(k2)).motionX = -d0 / (9.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
               ((EntityLivingBase)list.get(k2)).motionZ = -d1 / (9.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
            }
         }
      }
   }

   protected void dropFewItems(boolean par1, int par2) {
      int j = 5 + this.rand.nextInt(6);

      for (int k = 0; k < j; k++) {
         this.dropItemWithOffset(Item.getItemFromBlock(Blocks.OBSIDIAN), 1, 0.0F);
      }

      j = 5 + this.rand.nextInt(6);

      for (int var7 = 0; var7 < j; var7++) {
         this.dropItemWithOffset(Item.getItemFromBlock(Blocks.NETHERRACK), 1, 0.0F);
      }

      j = 5 + this.rand.nextInt(6);

      for (int var8 = 0; var8 < j; var8++) {
         this.dropItemWithOffset(PunMod.obsidianshards, 1, 0.0F);
      }
   }

   public int getTotalArmorValue() {
      return 14;
   }

   @Override
   protected void setEquipmentBasedOnDifficulty(net.minecraft.world.DifficultyInstance difficulty) {
      super.setEquipmentBasedOnDifficulty(difficulty);
      ItemStack c = new ItemStack(Items.NETHERBRICK);
      c.addEnchantment(net.minecraft.init.Enchantments.THORNS, 3);
      LegacyEquipment.set(this, 2, c);
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return LegacySounds.get("mob.wither.hurt");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("mob.irongolem.death");
   }

   @Override
   protected void playStepSound(BlockPos pos, Block blockIn) {
      this.playSound(LegacySounds.get("mob.irongolem.walk"), 1.0F, 1.0F);
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
