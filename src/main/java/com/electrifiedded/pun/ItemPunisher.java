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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemPunisher extends ItemSword {
   int counter;
   int hits = 0;

   public ItemPunisher() {
      super(PunMod.PUN);
      this.setMaxStackSize(1);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
      EntityPlayer player = (EntityPlayer)entity;
      ItemStack equipped = player.getHeldItemMainhand();
      if (equipped == stack) {
         player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 0));
         player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 5, 0));
         player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 5, 0));
         player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 5, 1));
         if (this.hits > 15) {
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 2));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 3));
            LegacySounds.playSoundAtEntity(player.world, player, "mob.wither.spawn", 2.0F, 1.6F);
         } else if (this.hits > 10) {
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 2));
            LegacySounds.playSoundAtEntity(player.world, player, "mob.wither.spawn", 1.5F, 1.3F);
         } else if (this.hits > 5) {
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 1));
            LegacySounds.playSoundAtEntity(player.world, player, "mob.wither.spawn", 1.0F, 0.9F);
         }

         List list = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(15.0, 4.0, 15.0));
         if (list != null) {
            for (int k2 = 0; k2 < list.size(); k2++) {
               if (list.get(k2) instanceof EntityLivingBase && ((EntityLivingBase)list.get(k2)).onGround) {
                  double d0 = ((EntityLivingBase)list.get(k2)).posX - player.posX;
                  double d1 = ((EntityLivingBase)list.get(k2)).posZ - player.posZ;
                  ((EntityLivingBase)list.get(k2)).motionX = -d0 / (25.0F * ((EntityLivingBase)list.get(k2)).getDistance(player));
                  ((EntityLivingBase)list.get(k2)).motionZ = -d1 / (25.0F * ((EntityLivingBase)list.get(k2)).getDistance(player));
               }
            }
         }
      }

      this.counter++;
      if (this.counter > 60) {
         this.hits = 0;
         this.counter = 0;
      }

      super.onUpdate(stack, world, entity, par4, par5);
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
      if (this.counter < 60) {
         this.hits++;
         this.counter = 0;
      } else {
         this.hits = 0;
         this.counter = 0;
      }

      return true;
   }

   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
      EntityPunisher punisher = new EntityPunisher(entityLiving.world, entityLiving);
      entityLiving.world.spawnEntity(punisher);
      LegacySounds.playSoundAtEntity(entityLiving.world, entityLiving, "dig.cloth", 1.0F, 1.1F);
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.RED + "Increases in damage with combo attacks");
   }
}
