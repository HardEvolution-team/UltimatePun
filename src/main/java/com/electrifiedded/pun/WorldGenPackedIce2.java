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
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPackedIce2 extends WorldGenerator {
   private static final String __OBFID = "CL_00000413";

   @Override
   public boolean generate(World w, Random r, BlockPos p) {
      return this.generate(w, r, p.getX(), p.getY(), p.getZ());
   }

   protected void setLegacy(World w, int x, int y, int z, net.minecraft.block.Block b) {
      LegacyWorld.setBlock(w, x, y, z, b, 0, 2);
   }

   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
      Block mblock = Blocks.PACKED_ICE;
      if (par2Random.nextInt(2) == 0) {
         mblock = PunMod.glowice;
      }

      if (!LegacyWorld.isAirBlock(par1World, par3, par4, par5)) {
         return false;
      } else if (LegacyWorld.getBlock(par1World, par3, par4 + 1, par5) != Blocks.ICE) {
         return false;
      } else {
         LegacyWorld.setBlock(par1World, par3, par4, par5, mblock, 0, 2);

         for (int l = 0; l < 1500; l++) {
            int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            int j1 = par4 - par2Random.nextInt(12);
            int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
            if (LegacyWorld.getBlock(par1World, i1, j1, k1).getDefaultState().getMaterial() == Material.AIR) {
               int l1 = 0;

               for (int i2 = 0; i2 < 6; i2++) {
                  Block block = null;
                  if (i2 == 0) {
                     block = LegacyWorld.getBlock(par1World, i1 - 1, j1, k1);
                  }

                  if (i2 == 1) {
                     block = LegacyWorld.getBlock(par1World, i1 + 1, j1, k1);
                  }

                  if (i2 == 2) {
                     block = LegacyWorld.getBlock(par1World, i1, j1 - 1, k1);
                  }

                  if (i2 == 3) {
                     block = LegacyWorld.getBlock(par1World, i1, j1 + 1, k1);
                  }

                  if (i2 == 4) {
                     block = LegacyWorld.getBlock(par1World, i1, j1, k1 - 1);
                  }

                  if (i2 == 5) {
                     block = LegacyWorld.getBlock(par1World, i1, j1, k1 + 1);
                  }

                  if (block == mblock) {
                     l1++;
                  }
               }

               if (l1 == 1) {
                  LegacyWorld.setBlock(par1World, i1, j1, k1, mblock, 0, 2);
               }
            }
         }

         return true;
      }
   }
}
