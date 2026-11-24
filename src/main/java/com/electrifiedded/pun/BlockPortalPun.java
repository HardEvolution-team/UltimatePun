package com.electrifiedded.pun;

import com.electrifiedded.archaiclib.legacy.world.LegacyPortalBlock;
import net.minecraft.init.Blocks;

/** 1.7 BlockPortalPun: packed-ice framed portal to the D-ice-mention (players only). */
public class BlockPortalPun extends LegacyPortalBlock {
   public BlockPortalPun() {
      super(() -> Blocks.PACKED_ICE, () -> PunMod.dimensionid);
      this.setCreativeTab(PunMod.ThePunMod);
      this.playersOnly = true;
   }
}
