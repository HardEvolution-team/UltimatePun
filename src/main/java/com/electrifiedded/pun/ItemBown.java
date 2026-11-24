package com.electrifiedded.pun;
import net.minecraft.entity.EntityLivingBase;

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
import com.electrifiedded.archaiclib.v1710.api.client.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemBown extends ItemBow {
   @SideOnly(Side.CLIENT)
   private IIcon[] iconArray;
   private int drawback;

   public ItemBown() {
      this.setCreativeTab(PunMod.ThePunMod);
      this.setMaxDamage(400);
      this.setMaxStackSize(1);
      this.setFull3D();
   }

   public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityLivingBase livingIn, int par4) {
      if (!(livingIn instanceof EntityPlayer)) return;
      EntityPlayer par3EntityPlayer = (EntityPlayer)livingIn;
      int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
      this.drawback = 0;
      j = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(par1ItemStack, par3EntityPlayer.world, par3EntityPlayer, j, true);
      if (j >= 0) {
         boolean flag = par3EntityPlayer.capabilities.isCreativeMode
            || EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, par1ItemStack) > 0;
         if (flag || com.electrifiedded.archaiclib.legacy.entity.LegacyInventory.hasItem(par3EntityPlayer.inventory, Items.ARROW)) {
            float f = j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            if (f < 0.1) {
               return;
            }

            if (f > 1.0F) {
               f = 1.0F;
            }

            EntityArrow entityarrow = com.electrifiedded.archaiclib.legacy.entity.LegacyArrows.shot(par2World, par3EntityPlayer, f * 2.6F);
            EntityArrow entityarrow2 = com.electrifiedded.archaiclib.legacy.entity.LegacyArrows.shot(par2World, par3EntityPlayer, f * 2.0F);
            entityarrow2.posY = entityarrow.posY - 0.2;
            entityarrow.setDamage(2.6);
            entityarrow2.setDamage(2.6);
            if (f == 1.0F) {
               entityarrow.setIsCritical(true);
               entityarrow2.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.POWER, par1ItemStack);
            if (k > 0) {
               entityarrow.setDamage(entityarrow.getDamage() + k * 0.5 + 0.5);
               entityarrow2.setDamage(entityarrow.getDamage() + k * 0.5 + 0.5);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.PUNCH, par1ItemStack);
            if (l > 0) {
               entityarrow.setKnockbackStrength(l);
               entityarrow2.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FLAME, par1ItemStack) > 0) {
               entityarrow.setFire(100);
               entityarrow2.setFire(100);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            LegacySounds.playSoundAtEntity(par2World, par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if (flag) {
               entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
            } else {
               com.electrifiedded.archaiclib.legacy.entity.LegacyInventory.consumeInventoryItem(par3EntityPlayer.inventory, Items.ARROW);
               com.electrifiedded.archaiclib.legacy.entity.LegacyInventory.consumeInventoryItem(par3EntityPlayer.inventory, Items.ARROW);
            }

            if (!par2World.isRemote) {
               par2World.spawnEntity(entityarrow);
               par2World.spawnEntity(entityarrow2);
            }
         }
      }
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
      super.onUpdate(stack, world, entity, par4, par5);
      if (!(entity instanceof EntityPlayer)) return;
      EntityPlayer player = (EntityPlayer)entity;
      ItemStack equipped = player.getHeldItemMainhand();
      if (equipped == stack) {
         if (this.drawback == 1) {
            
         }

         if (this.drawback == 0) {
            
         }
      } else {
         
      }
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      ActionResult<ItemStack> nock = net.minecraftforge.event.ForgeEventFactory.onArrowNock(par1ItemStack, par3EntityPlayer.world, par3EntityPlayer, hand, true);
      if (nock != null) {
         this.drawback = 0;
         return nock;
      } else {
         if (par3EntityPlayer.capabilities.isCreativeMode || com.electrifiedded.archaiclib.legacy.entity.LegacyInventory.hasItem(par3EntityPlayer.inventory, Items.ARROW)) {
            this.drawback = 1;
            par3EntityPlayer.setActiveHand(hand);
         }

         return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
      }
   }

   public int getItemEnchantability() {
      return 1;
   }



   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
