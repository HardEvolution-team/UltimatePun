package com.electrifiedded.pun;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** 1.7 BlockIceOre: frozen ores only drop themselves with Silk Touch or the Ice Sickle. */
public class BlockIceOre extends Block {
   public BlockIceOre(Material material) {
      super(material);
      this.setCreativeTab(PunMod.ThePunMod);
      this.setHardness(7.0F);
      this.setResistance(11.0F);
      this.setSoundType(SoundType.GLASS);
      this.slipperiness = 0.98F;
      this.setLightLevel(3.0F / 15.0F);
   }

   static void frozenHarvest(Block self, World world, EntityPlayer player, BlockPos pos) {
      ItemStack held = player.getHeldItemMainhand();
      boolean sickle = !held.isEmpty() && held.getItem() == PunMod.icesickle;
      if (sickle || EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, held) > 0) {
         Block.spawnAsEntity(world, pos, new ItemStack(self));
      } else if (world.provider.isNether()) {
         world.setBlockToAir(pos);
      }
   }

   @Override
   public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
      player.addExhaustion(0.005F);
      frozenHarvest(this, world, player, pos);
   }
}
