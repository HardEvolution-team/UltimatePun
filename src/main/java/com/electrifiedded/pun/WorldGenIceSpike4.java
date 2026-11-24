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
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceSpike4 extends WorldGenerator {
   int X = 0;
   int Z = 0;
   int Y = 0;
   Block mblock;

   @Override
   public boolean generate(World w, Random r, BlockPos p) {
      return this.generate(w, r, p.getX(), p.getY(), p.getZ());
   }

   protected void setLegacy(World w, int x, int y, int z, net.minecraft.block.Block b) {
      LegacyWorld.setBlock(w, x, y, z, b, 0, 2);
   }

   public boolean generate(World world, Random rand, int x, int y, int z) {
      if (LegacyWorld.getBlock(world, x, y, z) == Blocks.AIR && y > 100 && rand.nextInt(300) == 0) {
         int i3 = rand.nextInt(3);
         if (i3 == 0) {
            this.mblock = Blocks.ICE;
         } else if (i3 == 1) {
            this.mblock = Blocks.PACKED_ICE;
         } else if (i3 == 2) {
            this.mblock = PunMod.glowice;
         }

         int X1 = rand.nextInt(3) - 1;
         int Z1 = rand.nextInt(3) - 1;

         for (int i = 0; i < 150 + rand.nextInt(100); i++) {
            this.X = this.X + X1 + rand.nextInt(3) - 1;
            this.Z = this.Z + Z1 + rand.nextInt(3) - 1;

            for (int i2 = rand.nextInt(3); i2 < rand.nextInt(10) + 10; i2++) {
               LegacyWorld.setBlock(world, x + this.X, y + i2, z + this.Z, this.mblock);
               System.out.print(y);
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
