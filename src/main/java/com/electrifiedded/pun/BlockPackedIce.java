package com.electrifiedded.pun;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** 1.7 BlockPackedIce (packed ice, ice brick, glow ice); glow ice renders in the translucent pass. */
public class BlockPackedIce extends Block {
   public BlockPackedIce() {
      super(Material.ICE);
      this.slipperiness = 0.99F;
      this.setTickRandomly(false);
      this.setSoundType(SoundType.GLASS);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public BlockRenderLayer getRenderLayer() {
      return this == PunMod.glowice ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
   }

   @Override
   public boolean isOpaqueCube(IBlockState state) {
      return this != PunMod.glowice;
   }
}
