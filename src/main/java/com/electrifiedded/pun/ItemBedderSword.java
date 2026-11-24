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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBedderSword extends ItemSword {
   public ItemBedderSword() {
      super(PunMod.BEDDER);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer Player, EnumHand hand) {
      ItemStack par1ItemStack = Player.getHeldItem(hand);
      par2World.setWorldTime(0L);
      if (!Player.capabilities.isCreativeMode) {
         par1ItemStack.damageItem(10, Player);
      }

      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
      if (itemRand.nextInt(7) == 0) {
         par2EntityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 1));
         par2EntityLivingBase.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 1));
      }

      par1ItemStack.damageItem(1, par3EntityLivingBase);
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.GOLD + "Set Night to Day!");
      par3List.add(TextFormatting.GOLD + "Chance to make enemies drowsy.");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
