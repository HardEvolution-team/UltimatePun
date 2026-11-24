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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemEggsterminator extends Item {
   public ItemEggsterminator() {
      this.setMaxDamage(400);
      this.setMaxStackSize(1);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      if (par3EntityPlayer.capabilities.isCreativeMode || com.electrifiedded.archaiclib.legacy.entity.LegacyInventory.hasItem(par3EntityPlayer.inventory, PunMod.hardegg)) {
         EntityHardEgg egg = new EntityHardEgg(par2World, par3EntityPlayer);
         par2World.spawnEntity(egg);
         LegacySounds.playSoundAtEntity(par2World, par3EntityPlayer, "random.pop", 1.0F, 1.0F);
         LegacySounds.playSoundAtEntity(par2World, par3EntityPlayer, "random.pop", 1.0F, 1.0F);
         par1ItemStack.damageItem(1, par3EntityPlayer);
         com.electrifiedded.archaiclib.legacy.entity.LegacyInventory.consumeInventoryItem(par3EntityPlayer.inventory, PunMod.hardegg);
      }

      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.RED + "PREPARE FOR EXTERMINATION");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
