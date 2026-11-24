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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlackHole extends Item {
   public ItemBlackHole() {
      this.setMaxDamage(100);
      this.setMaxStackSize(1);
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      if (par1ItemStack.getMetadata() > 0) {
         par1ItemStack.damageItem(-1, par3EntityPlayer);
      } else {
         EntityBlackHole hole = new EntityBlackHole(par2World, par3EntityPlayer);
         par2World.spawnEntity(hole);
         par1ItemStack.damageItem(99, par3EntityPlayer);
      }

      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }
}
