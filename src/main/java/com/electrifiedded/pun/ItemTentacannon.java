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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemTentacannon extends Item {
   public ItemTentacannon() {
      this.setMaxDamage(100);
      this.setMaxStackSize(1);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
      super.onUpdate(stack, world, entity, par4, par5);
      if (this.isDamaged(stack)) {
         this.setDamage(stack, stack.getMetadata() - 4);
      }

      EntityPlayer player = (EntityPlayer)entity;
      ItemStack equipped = player.getHeldItemMainhand();
      if (equipped == stack) {
         player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0));
      }
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      if (!par1ItemStack.isItemDamaged()) {
         EntitySquidBomb bomb = new EntitySquidBomb(par2World, par3EntityPlayer);
         par2World.spawnEntity(bomb);
         LegacySounds.playSoundAtEntity(par3EntityPlayer.world, par3EntityPlayer, "random.explode", 1.0F, 0.8F);
         new Random();
         Vec3d look = par3EntityPlayer.getLookVec();
         par3EntityPlayer.motionX = -look.x;
         par3EntityPlayer.motionY = -look.y;
         par3EntityPlayer.motionZ = -look.z;
         if (!par3EntityPlayer.capabilities.isCreativeMode) {
            par1ItemStack.damageItem(99, par3EntityPlayer);
         }
      }

      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.BLUE + "Who said squids were useless?");
   }
}
