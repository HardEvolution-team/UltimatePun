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
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemMiteStone extends Item {
   int posX;
   int posY;
   int posZ;

   public ItemMiteStone() {
      this.setCreativeTab(PunMod.ThePunMod);
      this.setMaxStackSize(1);
      this.setMaxDamage(2);
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      if (!LegacyWorld.isAirBlock(par2World, 
         Math.round((float)Math.round(par3EntityPlayer.posX)) + 2,
         Math.round((float)Math.round(par3EntityPlayer.posY)) - 2,
         Math.round((float)Math.round(par3EntityPlayer.posZ)) + 1
      )) {
         Random rand = new Random();
         int randX = rand.nextInt(30) - 15;
         int randZ = rand.nextInt(30) - 15;
         this.posX = Math.round((float)Math.round(par3EntityPlayer.posX)) + randX;
         this.posY = Math.round((float)Math.round(par3EntityPlayer.posY));
         this.posZ = Math.round((float)Math.round(par3EntityPlayer.posZ)) + randZ;
         if (par2World.isBlockLoaded(new BlockPos(this.posX, this.posY - 1, this.posZ))) {
            LegacyWorld.setBlock(par2World, this.posX, this.posY, this.posZ, Blocks.CHEST);
            TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(par2World, this.posX, this.posY, this.posZ);
            if (tileentitychest != null) {
               com.electrifiedded.archaiclib.legacy.world.LegacyChestGenHooks.fillChest(tileentitychest, "dungeonChest", rand);
            }
         }

         for (int i = 0; i < 250; i++) {
            Entity entity = par2World.isRemote ? null : spawnCreature(par2World, 60, this.posX + rand.nextInt(50) - 25, this.posY, this.posZ + rand.nextInt(50) - 25);
            if (entity != null) ((EntityLiving)entity).setCustomNameTag("Mite");
         }

         if (!par3EntityPlayer.capabilities.isCreativeMode) {
            par1ItemStack.damageItem(1, par3EntityPlayer);
         }
      }

      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }

   public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
      {
         Entity entity = null;

         for (int j = 0; j < 1; j++) {
            entity = new net.minecraft.entity.monster.EntitySilverfish(par0World);
            if (entity != null && entity instanceof EntityLivingBase) {
               EntityLiving entityliving = (EntityLiving)entity;
               entity.setLocationAndAngles(par2, par4, par6, MathHelper.wrapDegrees(par0World.rand.nextFloat() * 360.0F), 0.0F);
               entityliving.rotationYawHead = entityliving.rotationYaw;
               entityliving.renderYawOffset = entityliving.rotationYaw;
               entityliving.onInitialSpawn(par0World.getDifficultyForLocation(new BlockPos(entity)), (IEntityLivingData)null);
               par0World.spawnEntity(entity);
               entityliving.playLivingSound();
            }
         }

         return entity;
      }
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.BLUE + "What does this do?");
   }
}
