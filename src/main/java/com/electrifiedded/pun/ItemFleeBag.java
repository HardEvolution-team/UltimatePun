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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemFleeBag extends Item {
   public ItemFleeBag() {
      this.setMaxStackSize(1);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer Player, EnumHand hand) {
      ItemStack par1ItemStack = Player.getHeldItem(hand);
      if (Player.dimension == 0) {
         Random rand = new Random();
         Player.setPosition(Player.posX, 130.0, Player.posZ);
         Player.motionY = 1.0;
         int randX = rand.nextInt(10) - 5;
         int randZ = rand.nextInt(10) - 5;
         Player.motionX = randX;
         Player.motionZ = randZ;
         Player.fallDistance = -150.0F;
         if (!Player.capabilities.isCreativeMode) {
            par1ItemStack.shrink(1);
         }
      }

      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.GREEN + "Get Away from anything! Even if you are underground!");
      par3List.add(TextFormatting.ITALIC + "Does not work in nether or end");
   }
}
