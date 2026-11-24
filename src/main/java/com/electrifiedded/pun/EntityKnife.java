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
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityKnife extends EntityThrowable {
   Random rand = new Random();

   public EntityKnife(World par1World) {
      super(par1World);
      this.setDead();
   }

   public EntityKnife(World par1World, EntityLivingBase par2EntityLivingBase) {
      super(par1World, par2EntityLivingBase);
      com.electrifiedded.archaiclib.legacy.entity.LegacyThrowable.applyLegacyThrowerInit(this, par2EntityLivingBase, 1.5F, 1.0F, 0.0F);
   }

   public EntityKnife(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6);
      this.setDead();
   }

   protected void onImpact(RayTraceResult var1) {
      if (var1.entityHit != null) {
         var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 4.0F);
         int i = this.rand.nextInt(3);
         Item it = Items.STICK;
         if (i == 0) {
            it = Items.BONE;
         }

         if (i == 1) {
            it = Items.ARROW;
         }

         if (i == 2) {
            it = Items.BOW;
         }

         if (var1.entityHit instanceof EntitySkeleton) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityPigZombie) {
            i = this.rand.nextInt(2);
            if (i == 0) {
               it = Items.GOLD_NUGGET;
            }

            if (i == 1) {
               it = Items.ROTTEN_FLESH;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         } else if (var1.entityHit instanceof EntityZombie) {
            i = this.rand.nextInt(3);
            if (i == 0) {
               it = Items.ROTTEN_FLESH;
            }

            if (i == 1) {
               it = Items.IRON_INGOT;
            }

            if (i == 2) {
               it = Items.IRON_SHOVEL;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntitySpider) {
            i = this.rand.nextInt(2);
            if (i == 0) {
               it = Items.STRING;
            }

            if (i == 1) {
               it = Items.SPIDER_EYE;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityCreeper) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(Items.GUNPOWDER)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntitySheep) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(Blocks.WOOL)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityMagmaCube) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(Items.MAGMA_CREAM)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntitySlime) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(Items.SLIME_BALL)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityBlaze) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(Items.BLAZE_ROD)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityPig) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(Items.PORKCHOP, 2)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntitySquid) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(Items.DYE, 1, 0)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityVillager) {
            i = this.rand.nextInt(2);
            if (i == 0) {
               it = Items.EMERALD;
            }

            if (i == 1) {
               it = null;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityGhast) {
            i = this.rand.nextInt(2);
            if (i == 0) {
               it = Items.GUNPOWDER;
            }

            if (i == 1) {
               it = Items.GHAST_TEAR;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntitySnowman) {
            i = this.rand.nextInt(2);
            if (i == 0) {
               it = Items.SNOWBALL;
            }

            if (i == 1) {
               it = Items.SNOWBALL;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityIronGolem) {
            i = this.rand.nextInt(2);
            if (i == 0) {
               it = Items.IRON_INGOT;
            }

            if (i == 1) {
               it = Items.IRON_INGOT;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityWitch) {
            i = this.rand.nextInt(4);
            if (i == 0) {
               it = Items.GLASS_BOTTLE;
            }

            if (i == 1) {
               it = Items.SUGAR;
            }

            if (i == 2) {
               it = Items.POTIONITEM;
            }

            if (i == 3) {
               it = Items.GLOWSTONE_DUST;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it, 1, 1)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityCow) {
            i = this.rand.nextInt(2);
            if (i == 0) {
               it = Items.LEATHER;
            }

            if (i == 1) {
               it = Items.BEEF;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         if (var1.entityHit instanceof EntityMooshroom) {
            i = this.rand.nextInt(2);
            if (i == 0) {
               it = Items.MUSHROOM_STEW;
            }

            if (i == 1) {
               it = Items.BEEF;
            }

            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         i = this.rand.nextInt(2);
         if (i == 0) {
            it = PunMod.obsidianshards;
         }

         if (i == 1) {
            it = Item.getItemFromBlock(Blocks.OBSIDIAN);
         }

         if (var1.entityHit instanceof EntityOb) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         i = this.rand.nextInt(3);
         if (i == 0) {
            it = Items.BLAZE_POWDER;
         }

         if (i == 1) {
            it = Item.getItemFromBlock(Blocks.FIRE);
         }

         if (i == 2) {
            it = Items.FIRE_CHARGE;
         }

         if (var1.entityHit instanceof EntityFry) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         i = this.rand.nextInt(3);
         if (i == 0) {
            it = PunMod.sandorcery;
         }

         if (i <= 1) {
            it = Item.getItemFromBlock(Blocks.SAND);
         }

         if (var1.entityHit instanceof EntitySandWitch) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         i = this.rand.nextInt(2);
         if (i == 0) {
            it = Items.DYE;
         }

         if (i <= 1) {
            it = Item.getItemFromBlock(Blocks.WATER);
         }

         if (var1.entityHit instanceof EnitiyLiqsquid) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         i = this.rand.nextInt(4);
         if (i < 3) {
            it = Item.getItemFromBlock(Blocks.PACKED_ICE);
         }

         if (i == 3) {
            it = PunMod.icesickle;
         }

         if (var1.entityHit instanceof EntityColdKiller) {
            EntityItem item = new EntityItem(
               this.getThrower().world,
               this.getThrower().posX,
               this.getThrower().posY,
               this.getThrower().posZ,
               new ItemStack(it)
            );
            this.getThrower().world.spawnEntity(item);
         }

         this.setDead();
      }

      if (var1 != null) {
         this.setDead();
      }
   }
}
