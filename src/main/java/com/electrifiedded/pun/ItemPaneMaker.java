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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemPaneMaker extends Item {
   public ItemPaneMaker() {
      this.setMaxStackSize(1);
      this.setMaxDamage(90);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
      par1ItemStack.damageItem(1, par3EntityLivingBase);
      if (this.getDamage(par1ItemStack) == 30) {
         par1ItemStack.addEnchantment(net.minecraft.init.Enchantments.SHARPNESS, 2);
      }

      if (this.getDamage(par1ItemStack) == 45) {
         par1ItemStack.addEnchantment(net.minecraft.init.Enchantments.SHARPNESS, 1);
      }

      if (this.getDamage(par1ItemStack) == 60) {
         par1ItemStack.addEnchantment(net.minecraft.init.Enchantments.SHARPNESS, 1);
      }

      if (this.getDamage(par1ItemStack) == 75) {
         par1ItemStack.addEnchantment(net.minecraft.init.Enchantments.SHARPNESS, 2);
      }

      if (this.getDamage(par1ItemStack) == 85) {
         par1ItemStack.addEnchantment(net.minecraft.init.Enchantments.SHARPNESS, 7);
      }

      return true;
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      par3EntityPlayer.setActiveHand(hand);
      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.WHITE + "Gets more powerful when it is damaged");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
