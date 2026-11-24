package com.electrifiedded.pun;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

/** 1.7 BlockMint: crop, ages 0-7 shown with mint_0..3 (see blockstate). */
public class BlockMint extends BlockCrops {
   @Override
   protected Item getSeed() {
      return PunMod.mintleaf;
   }

   @Override
   protected Item getCrop() {
      return PunMod.mintleaf;
   }
}
