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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSandorcery extends Item {
   public ItemSandorcery() {
      this.setCreativeTab(PunMod.ThePunMod);
      this.setMaxStackSize(1);
      this.setMaxDamage(100);
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
      super.onUpdate(stack, world, entity, par4, par5);
      if (this.isDamaged(stack)) {
         this.setDamage(stack, stack.getMetadata() - 5);
      }

      EntityPlayer player = (EntityPlayer)entity;
      ItemStack equipped = player.getHeldItemMainhand();
      if (equipped == stack) {
         player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 0));
      }
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      if (!par1ItemStack.isItemDamaged()) {
         EntitySandy sand = new EntitySandy(par2World, par3EntityPlayer);
         par2World.spawnEntity(sand);
         if (!par3EntityPlayer.capabilities.isCreativeMode) {
            par1ItemStack.damageItem(99, par3EntityPlayer);
         }
      }

      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }
}
