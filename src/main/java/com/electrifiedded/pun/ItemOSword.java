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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemOSword extends ItemSword {
   public ItemOSword(ToolMaterial p_i45356_1_) {
      super(p_i45356_1_);
      this.setCreativeTab(PunMod.ThePunMod);
      this.setMaxStackSize(1);
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
      super.onUpdate(stack, world, entity, par4, par5);
      if (!(entity instanceof EntityPlayer)) return;
      EntityPlayer player = (EntityPlayer)entity;
      ItemStack equipped = player.getHeldItemMainhand();
      if (equipped == stack) {
         player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 0));
         player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5, 0));
         List list = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(15.0, 4.0, 15.0));
         if (list != null) {
            for (int k2 = 0; k2 < list.size(); k2++) {
               if (list.get(k2) instanceof EntityLivingBase && ((EntityLivingBase)list.get(k2)).onGround) {
                  double d0 = ((EntityLivingBase)list.get(k2)).posX - player.posX;
                  double d1 = ((EntityLivingBase)list.get(k2)).posZ - player.posZ;
                  ((EntityLivingBase)list.get(k2)).motionX = -d0 / (25.0F * ((EntityLivingBase)list.get(k2)).getDistance(player));
                  ((EntityLivingBase)list.get(k2)).motionZ = -d1 / (25.0F * ((EntityLivingBase)list.get(k2)).getDistance(player));
               }
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.GRAY + "Made to last");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
