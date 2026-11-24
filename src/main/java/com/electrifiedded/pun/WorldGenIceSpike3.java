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

public class WorldGenIceSpike3 extends WorldGenerator {
   int X = 0;
   int Z = 0;
   int X1 = 0;
   int Z1 = 0;

   @Override
   public boolean generate(World w, Random r, BlockPos p) {
      return this.generate(w, r, p.getX(), p.getY(), p.getZ());
   }

   protected void setLegacy(World w, int x, int y, int z, net.minecraft.block.Block b) {
      LegacyWorld.setBlock(w, x, y, z, b, 0, 2);
   }

   public boolean generate(World world, Random rand, int x, int y, int z) {
      if (LegacyWorld.getBlock(world, x, y, z) == Blocks.ICE && LegacyWorld.getBlock(world, x, y + 1, z) == Blocks.AIR) {
         Block mblock = Blocks.PACKED_ICE;
         if (rand.nextInt(2) == 0) {
            mblock = PunMod.glowice;
         }

         for (int Y = 0; Y < 40 + rand.nextInt(80); Y++) {
            if (Y <= 10) {
               if (Y == 11) {
                  this.X1 = this.X1 + (rand.nextInt(3) - 1);
                  this.Z1 = this.Z1 + (rand.nextInt(3) - 1);
               }

               this.X = this.X + this.X1 + rand.nextInt(3) - 1;
               this.Z = this.Z + this.Z1 + rand.nextInt(3) - 1;
               LegacyWorld.setBlock(world, x + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x + this.X, y + Y, z - 1 + this.Z, mblock);
               LegacyWorld.setBlock(world, x + this.X, y + Y, z + 1 + this.Z, mblock);
               LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z + 1 + this.Z, mblock);
               LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z - 1 + this.Z, mblock);
               LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z + 1 + this.Z, mblock);
               LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z - 1 + this.Z, mblock);
               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + 2, y + Y, z + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + 2, y + Y, z + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x, y + Y, z + 2 + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x, y + Y, z - 2 + this.Z, mblock);
               }
            } else if (Y <= 25) {
               this.X = this.X + this.X1 + rand.nextInt(3) - 1;
               this.Z = this.Z + this.Z1 + rand.nextInt(3) - 1;
               LegacyWorld.setBlock(world, x + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x + this.X, y + Y, z - 1 + this.Z, mblock);
               LegacyWorld.setBlock(world, x + this.X, y + Y, z + 1 + this.Z, mblock);
               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z + 1 + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z - 1 + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z + 1 + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z - 1 + this.Z, mblock);
               }
            } else if (Y <= 50) {
               this.X = this.X + this.X1 + rand.nextInt(3) - 1;
               this.Z = this.Z + this.Z1 + rand.nextInt(3) - 1;
               LegacyWorld.setBlock(world, x + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z + this.Z, mblock);
               LegacyWorld.setBlock(world, x + this.X, y + Y, z - 1 + this.Z, mblock);
               LegacyWorld.setBlock(world, x + this.X, y + Y, z + 1 + this.Z, mblock);
               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z + 1 + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z - 1 + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z + 1 + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z - 1 + this.Z, mblock);
               }
            } else if (Y <= 70) {
               this.X = this.X + this.X1 + rand.nextInt(3) - 1;
               this.Z = this.Z + this.Z1 + rand.nextInt(3) - 1;
               LegacyWorld.setBlock(world, x + this.X, y + Y, z + this.Z, mblock);
               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + this.X, y + Y, z - 1 + this.Z, mblock);
               }

               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + this.X, y + Y, z + 1 + this.Z, mblock);
               }
            } else {
               this.X = this.X + this.X1 + rand.nextInt(3) - 1;
               this.Z = this.Z + this.Z1 + rand.nextInt(3) - 1;
               if (rand.nextInt(2) == 0) {
                  LegacyWorld.setBlock(world, x + this.X, y + Y, z + this.Z, mblock);
               }

               if (rand.nextInt(3) == 0) {
                  LegacyWorld.setBlock(world, x - 1 + this.X, y + Y, z + this.Z, mblock);
               }

               if (rand.nextInt(3) == 0) {
                  LegacyWorld.setBlock(world, x + 1 + this.X, y + Y, z + this.Z, mblock);
               }

               if (rand.nextInt(3) == 0) {
                  LegacyWorld.setBlock(world, x + this.X, y + Y, z - 1 + this.Z, mblock);
               }

               if (rand.nextInt(3) == 0) {
                  LegacyWorld.setBlock(world, x + this.X, y + Y, z + 1 + this.Z, mblock);
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }
}
