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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemDyeArmor extends ItemArmor {
   public ItemDyeArmor(int armor, int reduction) {
      super(PunMod.ADYEMOND, armor, PunMod.slot(reduction));
      this.setMaxStackSize(1);
   }

   public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
      if (stack.getItem() == PunMod.dyehelmet || stack.getItem() == PunMod.dyeboots || stack.getItem() == PunMod.dyechest) {
         return "gibby_pun:textures/texturemaps/armor/Overworld_layer_1.png";
      } else if (stack.getItem() == PunMod.dyelegs) {
         return "gibby_pun:textures/texturemaps/armor/Overworld_layer_2.png";
      } else if (stack.getItem() == PunMod.ndyehelmet || stack.getItem() == PunMod.ndyeboots || stack.getItem() == PunMod.ndyechest) {
         return "gibby_pun:textures/texturemaps/armor/Nether_layer_1.png";
      } else if (stack.getItem() == PunMod.ndyelegs) {
         return "gibby_pun:textures/texturemaps/armor/Nether_layer_2.png";
      } else if (stack.getItem() == PunMod.edyehelmet || stack.getItem() == PunMod.edyeboots || stack.getItem() == PunMod.edyechest) {
         return "gibby_pun:textures/texturemaps/armor/End_layer_1.png";
      } else if (stack.getItem() == PunMod.edyelegs) {
         return "gibby_pun:textures/texturemaps/armor/End_layer_2.png";
      } else if (stack.getItem() == PunMod.odyehelmet || stack.getItem() == PunMod.odyeboots || stack.getItem() == PunMod.odyechest) {
         return "gibby_pun:textures/texturemaps/armor/Other_layer_1.png";
      } else {
         return stack.getItem() == PunMod.odyelegs ? "gibby_pun:textures/texturemaps/armor/Other_layer_2.png" : null;
      }
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
      super.onUpdate(stack, world, entity, par4, par5);
      if (!(entity instanceof EntityPlayer)) return;
      EntityPlayer player = (EntityPlayer)entity;
      ItemStack equipped = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
      if (equipped == stack) {
         player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 5, 1));
      }
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.GOLD + "What color should it be?");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
