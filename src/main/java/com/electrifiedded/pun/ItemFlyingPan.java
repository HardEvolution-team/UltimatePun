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
import java.util.Random;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemFlyingPan extends ItemSword {
   Random random = new Random();

   public ItemFlyingPan() {
      super(ToolMaterial.STONE);
      this.setCreativeTab(PunMod.ThePunMod);
      this.setMaxStackSize(1);
      this.setMaxDamage(500);
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      par3EntityPlayer.fallDistance = 0.0F;
      par3EntityPlayer.motionY = 1.5;
      if (!par3EntityPlayer.capabilities.isCreativeMode) {
         par1ItemStack.damageItem(1, par3EntityPlayer);
      }

      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
      par1ItemStack.damageItem(1, par3EntityLivingBase);
      LegacySounds.playSoundAtEntity(par3EntityLivingBase.world, par3EntityLivingBase, "random.anvil_land", 1.4F, 0.8F);
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.GRAY + "How do you LAND this thing?");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
