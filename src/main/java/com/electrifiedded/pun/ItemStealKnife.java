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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStealKnife extends Item {
   public ItemStealKnife() {
      this.setCreativeTab(PunMod.ThePunMod);
      this.setMaxStackSize(16);
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      Random random = new Random();
      EntityKnife knife = new EntityKnife(par2World, par3EntityPlayer);
      par2World.spawnEntity(knife);
      if (!par3EntityPlayer.capabilities.isCreativeMode) {
         par1ItemStack.shrink(1);
      }

      LegacySounds.playSoundAtEntity(par2World, par3EntityPlayer, "random.bow", 1.4F, ((random.nextFloat() - random.nextFloat()) * 0.2F + 0.5F) * 1.8F);
      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }
}
