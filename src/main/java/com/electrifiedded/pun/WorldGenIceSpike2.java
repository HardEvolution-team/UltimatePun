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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceSpike2 extends WorldGenerator {
   private static final String __OBFID = "CL_00000417";

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

      while (LegacyWorld.isAirBlock(par1World, par3, par4, par5) && par4 > 2) {
         par4--;
      }

      if (LegacyWorld.getBlock(par1World, par3, par4 - 1, par5) != Blocks.ICE) {
         return false;
      } else {
         par4 += par2Random.nextInt(4);
         int l = par2Random.nextInt(4) + 7;
         int i1 = l / 4 + par2Random.nextInt(2);
         if (i1 > 1 && par2Random.nextInt(60) == 0) {
            par4 += 10 + par2Random.nextInt(30);
         }

         for (int j1 = 0; j1 < l; j1++) {
            float f = (1.0F - (float)j1 / l) * i1;
            int k1 = MathHelper.ceil(f);

            for (int l1 = -k1; l1 <= k1; l1++) {
               float f1 = MathHelper.abs(l1) - 0.25F;

               for (int i2 = -k1; i2 <= k1; i2++) {
                  float f2 = MathHelper.abs(i2) - 0.25F;
                  if ((l1 == 0 && i2 == 0 || f1 * f1 + f2 * f2 <= f * f) && (l1 != -k1 && l1 != k1 && i2 != -k1 && i2 != k1 || par2Random.nextFloat() <= 0.75F)
                     )
                   {
                     Block block = LegacyWorld.getBlock(par1World, par3 + l1, par4 + j1, par5 + i2);
                     if (block.getDefaultState().getMaterial() == Material.AIR
                        || block == Blocks.DIRT
                        || block == Blocks.SNOW
                        || block == Blocks.ICE) {
                        this.setLegacy(par1World, par3 + l1, par4 + j1, par5 + i2, mblock);
                     }

                     if (j1 != 0 && k1 > 1) {
                        block = LegacyWorld.getBlock(par1World, par3 + l1, par4 - j1, par5 + i2);
                        if (block.getDefaultState().getMaterial() == Material.AIR
                           || block == Blocks.DIRT
                           || block == Blocks.SNOW
                           || block == Blocks.ICE) {
                           this.setLegacy(par1World, par3 + l1, par4 - j1, par5 + i2, mblock);
                        }
                     }
                  }
               }
            }
         }

         int var18 = i1 - 1;
         if (var18 < 0) {
            var18 = 0;
         } else if (var18 > 1) {
            var18 = 1;
         }

         for (int j2 = -var18; j2 <= var18; j2++) {
            for (int k1 = -var18; k1 <= var18; k1++) {
               int l1 = par4 - 1;
               int k2 = 50;
               if (Math.abs(j2) == 1 && Math.abs(k1) == 1) {
                  k2 = par2Random.nextInt(5);
               }

               while (l1 > 50) {
                  Block block1 = LegacyWorld.getBlock(par1World, par3 + j2, l1, par5 + k1);
                  if (block1.getDefaultState().getMaterial() != Material.AIR
                     && block1 != Blocks.DIRT
                     && block1 != Blocks.SNOW
                     && block1 != Blocks.ICE
                     && block1 != PunMod.glowice
                     && block1 != Blocks.PACKED_ICE) {
                     break;
                  }

                  this.setLegacy(par1World, par3 + j2, l1, par5 + k1, mblock);
                  l1--;
                  if (--k2 <= 0) {
                     l1 -= par2Random.nextInt(5) + 1;
                     k2 = par2Random.nextInt(5);
                  }
               }
            }
         }

         return true;
      }
   }
}
