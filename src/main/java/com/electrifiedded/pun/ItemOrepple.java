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
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemOrepple extends ItemFood {
   public int heal = 0;

   public ItemOrepple(int heal, float sat, boolean wolf) {
      super(heal, sat, wolf);
      this.heal = heal;
      this.setCreativeTab(PunMod.ThePunMod);
      this.setAlwaysEdible();
   }

   protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if (this == PunMod.iorepple) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 3600, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2400, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, 0));
      }

      if (this == PunMod.corepple) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 2400, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 120, 0));
      }

      if (this == PunMod.borepple) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 4800, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 4800, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2400, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
      }

      if (this == PunMod.dorepple) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 4800, 1));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 2));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 4800, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 4800, 1));
      }

      if (this == PunMod.eorepple) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 6000, 4));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 2));
      }

      if (this == PunMod.lorepple) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 2400, 1));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2400, 2));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0));
      }

      if (this == PunMod.rorepple) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 1));
      }

      if (this == PunMod.dyorepple) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 3));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 6000, 4));
         par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 6000, 0));
      }
   }

   @SideOnly(Side.CLIENT)
   public boolean hasEffect(ItemStack par1ItemStack) {
      return par1ItemStack.getItem() != PunMod.corepple;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.BLUE + "Wait, can I eat this?");
   }
}
