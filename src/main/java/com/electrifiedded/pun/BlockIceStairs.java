package com.electrifiedded.pun;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockIceStairs extends BlockStairs {
   protected BlockIceStairs(Block base, int meta) {
      super(base.getDefaultState());
      this.setCreativeTab(PunMod.ThePunMod);
      this.slipperiness = 0.97F;
      this.useNeighborBrightness = true;
   }
}
