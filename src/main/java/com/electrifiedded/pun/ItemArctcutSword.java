package com.electrifiedded.pun;

import net.minecraft.world.World;
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
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.math.MathHelper;

public class ItemArctcutSword extends ItemSword {
   public ItemArctcutSword(ToolMaterial material) {
      super(material);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
      par1ItemStack.damageItem(1, par3EntityLivingBase);
      if (itemRand.nextInt(10) == 1) {
         for (int i = 0; i < 400; i++) {
            LegacyParticles.spawn(par2EntityLivingBase.world, 
                  "blockcrack_" + Block.getIdFromBlock(Blocks.ICE) + "_0",
                  par2EntityLivingBase.posX,
                  par2EntityLivingBase.posY + itemRand.nextGaussian(),
                  par2EntityLivingBase.posZ,
                  itemRand.nextGaussian(),
                  itemRand.nextGaussian() * 2.0,
                  itemRand.nextGaussian()
               );
         }

         LegacySounds.playSoundAtEntity(par2EntityLivingBase.world, par2EntityLivingBase, "dig.glass", 1.0F, 1.0F);
         int X = MathHelper.floor(par2EntityLivingBase.posX);
         int Y = MathHelper.floor(par2EntityLivingBase.posY);
         int Z = MathHelper.floor(par2EntityLivingBase.posZ);

         for (int i = 0; i <= 1; i++) {
            LegacyWorld.setBlock(par2EntityLivingBase.world, X, Y + i, Z + 1, Blocks.ICE);
            LegacyWorld.setBlock(par2EntityLivingBase.world, X, Y + i, Z - 1, Blocks.ICE);
            LegacyWorld.setBlock(par2EntityLivingBase.world, X - 1, Y + i, Z, Blocks.ICE);
            LegacyWorld.setBlock(par2EntityLivingBase.world, X + 1, Y + i, Z, Blocks.ICE);
         }

         LegacyWorld.setBlock(par2EntityLivingBase.world, X, Y + 2, Z, Blocks.ICE);
      }

      return true;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.BLUE + "Chance to imprison enemies with ice");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
