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
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDayBreaker extends EntityMob implements IRangedAttackMob {
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


   int charge = 0;
   double Xs = 0.0;
   double Ys;
   double Zs;
   static int entitystate = 0;
   boolean quake = false;
   int counterq = 0;
   int X = MathHelper.floor(this.posX);
   int Y = MathHelper.floor(this.getEntityBoundingBox().minY);
   int Z = MathHelper.floor(this.posZ);
   int dX = this.rand.nextInt(2) - 1;
   int dZ = this.rand.nextInt(2) - 1;
   int rZ = this.Z;
   int rX = this.X;

   public EntityDayBreaker(World par1World) {
      super(par1World);
      this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25, 20, 10.0F));
      this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.setHealth(this.getMaxHealth());
      this.isImmuneToFire = true;
      this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20000, 0));
      this.experienceValue = 10000;
      this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 1, true, false, null));
      this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityLiving.class, 1, true, false, null));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10000.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10000.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0);
   }

   public boolean canBePushed() {
      return false;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      int qcounter = 0;
      this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
      this.world.setWorldTime(14000L);
      this.motionX = 0.0;
      this.motionZ = 0.0;
      this.motionY = 0.0;
      if (this.Xs == 0.0) {
         this.Xs = this.posX;
         this.Ys = this.posY;
         this.Zs = this.posZ;
      }

      this.posX = this.Xs;
      this.posY = this.Ys;
      this.posZ = this.Zs;
      if (entitystate == 0 && this.getHealth() < 9999.0F) {
         entitystate = 1;
         this.getTotalArmorValue();
      }

      if (entitystate > 0) {
         if (this.getHealth() < 8500.0F) {
            entitystate = 2;
            this.getTotalArmorValue();
         }

         if (this.getHealth() < 7000.0F) {
            entitystate = 3;
         }

         if (this.getHealth() < 6000.0F) {
            entitystate = 4;
         }

         if (this.getHealth() < 5000.0F) {
            entitystate = 5;
         }

         for (int i = 0; i < this.rand.nextInt(500); i++) {
            LegacyParticles.spawn(this.world, 
                  "smoke",
                  this.posX,
                  this.posY + this.rand.nextGaussian(),
                  this.posZ,
                  this.rand.nextGaussian() * this.rand.nextGaussian(),
                  0.0,
                  this.rand.nextGaussian() * this.rand.nextGaussian()
               );
         }

         List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(1000.0, 400.0, 1000.0));
         if (list != null) {
            for (int k2 = 0; k2 < list.size(); k2++) {
               if (!(list.get(k2) instanceof EntityPlayer)) {
                  if (list.get(k2) instanceof EntityLivingBase
                     && ((EntityLivingBase)list.get(k2)).getDistance(this) < 100.0F
                     && !(list.get(k2) instanceof EntityMinion)) {
                     ((EntityLivingBase)list.get(k2)).attackEntityFrom(DamageSource.MAGIC, 1.0F);
                     ((EntityLivingBase)list.get(k2)).attackEntityFrom(DamageSource.DROWN, 1.0F);
                  }
               } else {
                  double d0 = ((EntityLivingBase)list.get(k2)).posX - this.posX;
                  double d1 = ((EntityLivingBase)list.get(k2)).posZ - this.posZ;
                  if (this.quake && ((EntityLivingBase)list.get(k2)).getDistance(this) < 50.0F) {
                     ((EntityLivingBase)list.get(k2)).attackEntityFrom(DamageSource.MAGIC, 1.0E-4F);
                     ((EntityLivingBase)list.get(k2)).hurtResistantTime = 0;
                  }

                  if (((EntityLivingBase)list.get(k2)).onGround
                     && ((EntityLivingBase)list.get(k2)).getDistance(this) > 50.0F
                     && ((EntityLivingBase)list.get(k2)).getDistance(this) < 150.0F) {
                     ((EntityLivingBase)list.get(k2)).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 0));
                     ((EntityLivingBase)list.get(k2)).motionX = -d0 / (12.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
                     ((EntityLivingBase)list.get(k2)).motionZ = -d1 / (12.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
                  }

                  if (!(((EntityLivingBase)list.get(k2)).getDistance(this) < 30.0F)) {
                     if (((EntityLivingBase)list.get(k2)).getDistance(this) < 5.0F) {
                        ((EntityLivingBase)list.get(k2)).motionX = d0;
                        ((EntityLivingBase)list.get(k2)).motionZ = d1;
                        ((EntityLivingBase)list.get(k2)).motionY = 1.0;
                     }

                     if (((EntityLivingBase)list.get(k2)).getDistance(this) < 30.0F) {
                        ((EntityLivingBase)list.get(k2)).motionX = d0 / (12.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
                        ((EntityLivingBase)list.get(k2)).motionZ = d1 / (12.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
                     }

                     if (((EntityLivingBase)list.get(k2)).getDistance(this) > 50.0F && ((EntityLivingBase)list.get(k2)).getDistance(this) < 150.0F) {
                        ((EntityLivingBase)list.get(k2)).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60, 0));
                     }
                  } else {
                     ((EntityLivingBase)list.get(k2)).motionX = d0 / (12.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
                     ((EntityLivingBase)list.get(k2)).motionZ = d1 / (12.0F * ((EntityLivingBase)list.get(k2)).getDistance(this));
                     int x = MathHelper.floor(this.posX);
                     int y = MathHelper.floor(this.getEntityBoundingBox().minY);
                     int z = MathHelper.floor(this.posZ);

                     for (int i = 0; i < 3; i++) {
                        for (int t = 0; t < 3; t++) {
                           Block l = LegacyWorld.getBlock(this.world, x + t - 1, y, z + i - 1);
                           if (l == Blocks.GLOWSTONE || l == Blocks.TORCH || l == Blocks.FIRE) {
                              this.charge++;
                              if (this.charge == 20) {
                                 this.damageEntity(DamageSource.MAGIC, 335.0F);
                                 ((EntityLivingBase)list.get(k2)).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0));
                                 ((EntityLivingBase)list.get(k2)).addPotionEffect(new PotionEffect(MobEffects.WITHER, 1000000, 0));
                                 ((EntityLivingBase)list.get(k2)).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 300, 3));
                                 ((EntityLivingBase)list.get(k2)).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 300, 3));
                                 LegacySounds.playSoundAtEntity(this.world, this, "portal.portal", 2.0F, 0.8F);
                                 ((EntityLivingBase)list.get(k2)).motionX = d0 * 2.0;
                                 ((EntityLivingBase)list.get(k2)).motionZ = d1 * 2.0;

                                 for (int it = 0; it < 40; it++) {
                                    for (int en = 0; en < 20; en++) {
                                       for (int e = 0; e < 20; e++) {
                                          if (LegacyWorld.getBlock(this.world, x + e - 10, y + it - 3, z + en - 10) == Blocks.TORCH
                                             || LegacyWorld.getBlock(this.world, x + e - 10, y + it - 3, z + en - 10) == Blocks.FIRE
                                             || LegacyWorld.getBlock(this.world, x + e - 10, y + it - 3, z + en - 10) == Blocks.GLOWSTONE) {
                                             LegacyWorld.setBlockToAir(this.world, x + e - 10, y + it - 3, z + en - 10);
                                          }
                                       }
                                    }
                                 }

                                 for (int it = 0; it < 40; it++) {
                                    for (int en = 0; en < 120; en++) {
                                       for (int ex = 0; ex < 120; ex++) {
                                          if (LegacyWorld.getBlock(this.world, x + ex - 60, y - 5 + it, z + en - 60) != Blocks.AIR) {
                                             LegacyWorld.setBlock(this.world, x + ex - 60, y - 5 + it, z + en - 60, Blocks.COAL_BLOCK);
                                          }
                                       }
                                    }
                                 }

                                 LegacyWorld.setBlock(this.world, x, y - 2, z, Blocks.MOB_SPAWNER, 0, 2);
                                 TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(this.world, x, y - 2, z);
                                 tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityMinion"));
                                 this.charge = 0;
                              }
                           }
                        }
                     }

                     if (((EntityLivingBase)list.get(k2)).getDistance(this) < 3.0F) {
                        ((EntityLivingBase)list.get(k2)).motionX = d0;
                        ((EntityLivingBase)list.get(k2)).motionZ = d1;
                        ((EntityLivingBase)list.get(k2)).motionY = 1.0;
                     }
                  }
               }
            }

            if (entitystate == 5) {
               int randattack = this.rand.nextInt(10000);
               if (randattack == 0) {
                  EntityBlackHole hole = new EntityBlackHole(this.world, this);
                  this.world.spawnEntity(hole);
               }
            }

            if (entitystate == 3) {
               int randattack = this.rand.nextInt(10000);
               if (randattack == 0 && this.counterq == 0) {
                  this.quake = true;
               } else if (this.counterq == 0) {
                  this.quake = false;
               }

               if (this.quake = true) {
                  for (int i = 0; i < 3; i++) {
                     if (this.counterq == 0) {
                        this.X = MathHelper.floor(this.posX);
                        this.Y = MathHelper.floor(this.getEntityBoundingBox().minY);
                        this.Z = MathHelper.floor(this.posZ);
                        this.dX = this.rand.nextInt(3) - 1;
                        this.dZ = this.rand.nextInt(3) - 1;
                        this.rZ = this.Z;
                        this.rX = this.X;
                     }

                     if (this.dZ == 0) {
                        this.rZ = this.rZ + this.rand.nextInt(2);
                     }

                     if (this.dZ == -1) {
                        this.rZ = this.rZ + this.rand.nextInt(2) * -1;
                     }

                     if (this.dX == 0) {
                        this.rX = this.rX + this.rand.nextInt(2);
                     }

                     if (this.dX == -1) {
                        this.rX = this.rX + this.rand.nextInt(2) * -1;
                     }

                     if (this.dZ == 1) {
                        int c = this.rand.nextInt(10);
                        if (c == 3) {
                           this.rZ++;
                        }

                        if (c == 4) {
                           this.rZ--;
                        }
                     }

                     if (this.dX == 1) {
                        int cx = this.rand.nextInt(10);
                        if (cx == 3) {
                           this.rX++;
                        }

                        if (cx == 4) {
                           this.rX--;
                        }
                     }

                     for (int y = 0; y < this.rand.nextInt(200); y++) {
                        if (this.rX != this.X
                           && this.rZ != this.Z
                           && LegacyWorld.getBlock(this.world, this.rX, this.Y - y + 5, this.rZ) != Blocks.AIR) {
                           LegacyWorld.setBlockToAir(this.world, this.rX, this.Y - y + 5, this.rZ);
                        }
                     }

                     if (this.counterq >= 50) {
                        this.quake = false;
                        this.counterq = -1;
                     }
                  }

                  this.counterq++;
               }
            }
         }
      }
   }

   public int getTotalArmorValue() {
      return entitystate * 20;
   }

   protected void despawnEntity() {
   }

   public void attackEntityWithRangedAttack(EntityLivingBase var1, float var2) {
   }

   @Override
   public void setSwingingArms(boolean swingingArms) {
   }
}
