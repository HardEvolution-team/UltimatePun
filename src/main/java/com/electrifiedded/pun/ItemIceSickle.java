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
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextFormatting;

public class ItemIceSickle extends ItemSword {
   public static final Block[] blocksEffectiveAgainst = new Block[0];

   protected ItemIceSickle(ToolMaterial par3EnumToolMaterial) {
      super(par3EnumToolMaterial);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   @Override
   public float getDestroySpeed(ItemStack p_150893_1_, IBlockState state) {
      Block p_150893_2_ = state.getBlock();
      if (p_150893_2_ == Blocks.WEB) {
         return 15.0F;
      } else if (p_150893_2_ != Blocks.ICE && p_150893_2_ != Blocks.PACKED_ICE) {
         Material material = state.getMaterial();
         return material != Material.ICE ? 1.0F : 6.0F;
      } else {
         return 15.0F;
      }
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.AQUA + "Let me get rid of some ice for you");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
