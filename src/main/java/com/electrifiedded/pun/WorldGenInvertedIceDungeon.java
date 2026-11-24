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
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import com.electrifiedded.archaiclib.legacy.world.LegacyChestGenHooks;

public class WorldGenInvertedIceDungeon extends WorldGenerator {
   @Override
   public boolean generate(World w, Random r, BlockPos p) {
      return this.generate(w, r, p.getX(), p.getY(), p.getZ());
   }

   protected void setLegacy(World w, int x, int y, int z, net.minecraft.block.Block b) {
      LegacyWorld.setBlock(w, x, y, z, b, 0, 2);
   }

   public boolean generate(World world, Random rand, int x, int y, int z) {
      if (LegacyWorld.getBlock(world, x, y, z) == Blocks.AIR
         && LegacyWorld.getBlock(world, x, y + 40, z) == Blocks.AIR
         && LegacyWorld.getBlock(world, x, y + 20, z) == Blocks.AIR
         && LegacyWorld.getBlock(world, x, y - 1, z) == Blocks.ICE
         && LegacyWorld.getBlock(world, x, y - 64, z) == Blocks.ICE) {
         for (int Z = 1; Z <= 23; Z++) {
            for (int X = 1; X <= 23; X++) {
               LegacyWorld.setBlock(world, x + X, y, z + Z, Blocks.ICE);
               if (rand.nextInt(100) == 6) {
                  LegacyWorld.setBlock(world, x + X, y + 1, z + Z, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + X, y + 1, z + Z);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityColdKiller"));
                  }
               }

               if (rand.nextInt(500) == 6) {
                  LegacyWorld.setBlock(world, x + X, y + 1, z + Z, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + X, y + 1, z + Z);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Y = -64; Y <= -1; Y++) {
            for (int Z = 1; Z <= 23; Z++) {
               for (int X = 1; X <= 23; X++) {
                  LegacyWorld.setBlock(world, x + X, y + Y, z + Z, Blocks.AIR);
               }
            }
         }

         for (int Z = 1; Z <= 23; Z++) {
            for (int X = 1; X <= 23; X++) {
               LegacyWorld.setBlock(world, x + X, y - 64, z + Z, PunMod.icebrick);
            }
         }

         for (int Z = 1; Z <= 23; Z++) {
            for (int X = 1; X <= 23; X++) {
               LegacyWorld.setBlock(world, x + X, y - 63, z + Z, PunMod.icespike);
            }
         }

         for (int Z = 1; Z <= 19; Z++) {
            for (int X = 1; X <= 19; X++) {
               for (int Y = 0; Y <= 4; Y++) {
                  LegacyWorld.setBlock(world, x + X + 2, y - 63 + Y, z + Z + 2, PunMod.icebrick);
               }
            }
         }

         LegacyWorld.setBlock(world, x + 11, y - 59, z + 11, Blocks.MOB_SPAWNER);
         TileEntityMobSpawner tileentitymobspawner2 = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + 11, y - 59, z + 11);
         if (tileentitymobspawner2 != null) {
            tileentitymobspawner2.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityIcyYou"));
         }

         for (int Z = -1; Z <= 5; Z++) {
            for (int X = 0; X <= 1; X++) {
               for (int Y = 0; Y <= 3; Y++) {
                  LegacyWorld.setBlock(world, x + X + 22, y + Y, z - Z + 19, Blocks.AIR);
               }
            }
         }

         for (int Z = 2; Z <= 5; Z++) {
            for (int X = 0; X <= 2; X++) {
               for (int Y = -1; Y >= -9; Y--) {
                  if (Y - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + X + 22, y + Y + 1, z - Z + 23, PunMod.icestairs, 2, 2);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 4; Z++) {
            for (int X = 0; X <= 2; X++) {
               for (int Yx = -1; Yx >= -9; Yx--) {
                  if (Yx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + X + 22, y + Yx, z - Z + 23, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Z = 1; Z < 3; Z++) {
            for (int X = 1; X <= 23; X++) {
               LegacyWorld.setBlock(world, x + X, y - 4, z + Z, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + X, y - 3, z + Z, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + X, y - 3, z + Z);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(500) == 6) {
                  LegacyWorld.setBlock(world, x + X, y - 3, z + Z, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + X, y - 3, z + Z);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int X = 1; X <= 23; X++) {
            if (X % 2 == 0) {
               LegacyWorld.setBlock(world, x + X, y - 3, z + 2, PunMod.icebrick);
            }
         }

         for (int Z = 1; Z < 24; Z++) {
            for (int Xx = 2; Xx <= 3; Xx++) {
               LegacyWorld.setBlock(world, x + Xx + 20, y - 4, z + Z, PunMod.icebrick);
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xx = 0; Xx <= 1; Xx++) {
               for (int Yxx = -1; Yxx >= -9; Yxx--) {
                  if (Yxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xx + 1, y - 4 + Yxx, z + Z + 1, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xx = 0; Xx <= 1; Xx++) {
               for (int Yxxx = -1; Yxxx >= -9; Yxxx--) {
                  if (Yxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xx + 1, y - 3 + Yxxx, z + Z + 1, PunMod.icestairs, 3, 2);
                  }
               }
            }
         }

         for (int Z = 1; Z < 5; Z++) {
            for (int Xx = 1; Xx <= 23; Xx++) {
               LegacyWorld.setBlock(world, x + Xx, y - 20, z + Z + 3, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xx, y - 19, z + Z + 3, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xx, y - 19, z + Z + 3);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(500) == 6) {
                  LegacyWorld.setBlock(world, x + Xx, y - 19, z + Z + 3, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xx, y - 19, z + Z + 3);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Xx = 1; Xx <= 23; Xx++) {
            if (Xx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xx, y - 19, z + 7, PunMod.icebrick);
            }
         }

         for (int Xxx = 1; Xxx <= 23; Xxx++) {
            if (Xxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxx, y - 19, z + 4, PunMod.icebrick);
            }
         }

         for (int Z = 1; Z < 23; Z++) {
            for (int Xxxx = 2; Xxxx <= 3; Xxxx++) {
               LegacyWorld.setBlock(world, x + Xxxx + 20, y - 20, z + Z, PunMod.icebrick);
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxx = 0; Xxxx <= 1; Xxxx++) {
               for (int Yxxxx = -1; Yxxxx >= -9; Yxxxx--) {
                  if (Yxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxx + 1, y - 20 + Yxxxx, z + Z + 6, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxx = 0; Xxxx <= 1; Xxxx++) {
               for (int Yxxxxx = -1; Yxxxxx >= -9; Yxxxxx--) {
                  if (Yxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxx + 1, y - 19 + Yxxxxx, z + Z + 6, PunMod.icestairs, 3, 2);
                  }
               }
            }
         }

         for (int Z = 1; Z < 5; Z++) {
            for (int Xxxx = 1; Xxxx <= 23; Xxxx++) {
               LegacyWorld.setBlock(world, x + Xxxx, y - 36, z + Z + 5, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxx, y - 35, z + Z + 5, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxx, y - 35, z + Z + 5);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(300) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxx, y - 35, z + Z + 5, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxx, y - 35, z + Z + 5);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
                  }
               }
            }
         }

         for (int Xxxx = 1; Xxxx <= 23; Xxxx++) {
            if (Xxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxx, y - 35, z + 9, PunMod.icebrick);
            }
         }

         for (int Xxxxx = 1; Xxxxx <= 23; Xxxxx++) {
            if (Xxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxx, y - 35, z + 6, PunMod.icebrick);
            }
         }

         for (int Z = 1; Z < 23; Z++) {
            for (int Xxxxxx = 2; Xxxxxx <= 3; Xxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxx + 20, y - 36, z + Z, PunMod.icebrick);
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxx = 0; Xxxxxx <= 1; Xxxxxx++) {
               for (int Yxxxxxx = -1; Yxxxxxx >= -9; Yxxxxxx--) {
                  if (Yxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxx + 1, y - 36 + Yxxxxxx, z + Z + 8, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxx = 0; Xxxxxx <= 1; Xxxxxx++) {
               for (int Yxxxxxxx = -1; Yxxxxxxx >= -9; Yxxxxxxx--) {
                  if (Yxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxx + 1, y - 35 + Yxxxxxxx, z + Z + 8, PunMod.icestairs, 3, 2);
                  }
               }
            }
         }

         for (int Z = 1; Z < 5; Z++) {
            for (int Xxxxxx = 1; Xxxxxx <= 23; Xxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxx, y - 52, z + Z + 7, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxx, y - 51, z + Z + 7, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxx, y - 51, z + Z + 7);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(300) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxx, y - 51, z + Z + 7, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxx, y + 51, z + Z + 7);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
                  }
               }
            }
         }

         for (int Xxxxxx = 1; Xxxxxx <= 23; Xxxxxx++) {
            if (Xxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxx, y - 51, z + 11, PunMod.icebrick);
            }
         }

         for (int Xxxxxxx = 1; Xxxxxxx <= 23; Xxxxxxx++) {
            if (Xxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxx, y - 51, z + 8, PunMod.icebrick);
            }
         }

         for (int Z = 1; Z < 23; Z++) {
            for (int Xxxxxxxx = 2; Xxxxxxxx <= 3; Xxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxx + 20, y - 52, z + Z, PunMod.icebrick);
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxxxx = 0; Xxxxxxxx <= 1; Xxxxxxxx++) {
               for (int Yxxxxxxxx = -1; Yxxxxxxxx >= -9; Yxxxxxxxx--) {
                  if (Yxxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxx + 1, y - 52 + Yxxxxxxxx, z + Z + 10, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxxxx = 0; Xxxxxxxx <= 1; Xxxxxxxx++) {
               for (int Yxxxxxxxxx = -1; Yxxxxxxxxx >= -9; Yxxxxxxxxx--) {
                  if (Yxxxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxx + 1, y - 51 + Yxxxxxxxxx, z + Z + 10, PunMod.icestairs, 3, 2);
                  }
               }
            }
         }

         for (int Xxxxxxxx = 1; Xxxxxxxx <= 2; Xxxxxxxx++) {
            for (int Z = 1; Z <= 23; Z++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxx, y - 12, z + Z, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxx = 1; Xxxxxxxx <= 23; Xxxxxxxx++) {
            if (Xxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxx, y - 11, z + 21, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxx = 1; Xxxxxxxxx <= 23; Xxxxxxxxx++) {
            for (int Z = 1; Z <= 3; Z++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxx, y - 12, z + Z + 20, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxx, y - 11, z + Z + 20, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxx, y - 11, z + Z + 20);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(300) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxx, y - 11, z + Z + 20, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxx, y - 11, z + Z + 20);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxxxxx = 0; Xxxxxxxxx <= 1; Xxxxxxxxx++) {
               for (int Yxxxxxxxxxx = -1; Yxxxxxxxxxx >= -9; Yxxxxxxxxxx--) {
                  if (Yxxxxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxx + 22, y - 12 + Yxxxxxxxxxx, z - Z + 22, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxxxxx = 0; Xxxxxxxxx <= 1; Xxxxxxxxx++) {
               for (int Yxxxxxxxxxxx = -1; Yxxxxxxxxxxx >= -9; Yxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxx + 22, y - 11 + Yxxxxxxxxxxx, z - Z + 22, PunMod.icestairs, 2, 2);
                  }
               }
            }
         }

         for (int Xxxxxxxxx = 1; Xxxxxxxxx <= 2; Xxxxxxxxx++) {
            for (int Z = 1; Z <= 23; Z++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxx, y - 28, z + Z, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxx = 1; Xxxxxxxxx <= 23; Xxxxxxxxx++) {
            if (Xxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxx, y - 27, z + 18, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxx = 1; Xxxxxxxxxx <= 23; Xxxxxxxxxx++) {
            if (Xxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxx, y - 27, z + 22, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxx = 1; Xxxxxxxxxxx <= 23; Xxxxxxxxxxx++) {
            for (int Z = 1; Z <= 5; Z++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxx, y - 28, z + Z + 17, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxx, y - 27, z + Z + 17, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxx, y - 27, z + Z + 17);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(300) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxx, y - 27, z + Z + 17, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxx, y - 27, z + Z + 17);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxxxxxxx = 0; Xxxxxxxxxxx <= 1; Xxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxx = -1; Yxxxxxxxxxxxx >= -9; Yxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxx + 22, y - 28 + Yxxxxxxxxxxxx, z - Z + 19, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxxxxxxx = 0; Xxxxxxxxxxx <= 1; Xxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxx >= -9; Yxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxx + 22, y - 27 + Yxxxxxxxxxxxxx, z - Z + 19, PunMod.icestairs, 2, 2);
                  }
               }
            }
         }

         for (int Xxxxxxxxxxx = 1; Xxxxxxxxxxx <= 2; Xxxxxxxxxxx++) {
            for (int Z = 1; Z <= 23; Z++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxx, y - 44, z + Z, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxx = 1; Xxxxxxxxxxx <= 23; Xxxxxxxxxxx++) {
            if (Xxxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxx, y - 43, z + 16, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxx = 1; Xxxxxxxxxxxx <= 23; Xxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxx, y - 43, z + 20, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxx = 1; Xxxxxxxxxxxxx <= 23; Xxxxxxxxxxxxx++) {
            for (int Z = 1; Z <= 5; Z++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxx, y - 44, z + Z + 15, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxx, y - 43, z + Z + 15, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxx, y - 43, z + Z + 15);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(300) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxx, y - 43, z + Z + 15, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxx, y - 43, z + Z + 15);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxxxxxxxxx = 0; Xxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxx >= -9; Yxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxx + 22, y - 44 + Yxxxxxxxxxxxxxx, z - Z + 17, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Z = 2; Z <= 9; Z++) {
            for (int Xxxxxxxxxxxxx = 0; Xxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxx >= -9; Yxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxx - 1 == Z * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxx + 22, y - 43 + Yxxxxxxxxxxxxxxx, z - Z + 17, PunMod.icestairs, 2, 2);
                  }
               }
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = -64; Yxxxxxxxxxxxxxxxx <= 4; Yxxxxxxxxxxxxxxxx++) {
            for (int Z = 0; Z <= 24; Z++) {
               LegacyWorld.setBlock(world, x, y + Yxxxxxxxxxxxxxxxx, z + Z, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = -64; Yxxxxxxxxxxxxxxxx <= 4; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxx = 0; Xxxxxxxxxxxxx <= 24; Xxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = -64; Yxxxxxxxxxxxxxxxx <= 4; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxx = 0; Xxxxxxxxxxxxx <= 24; Xxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 24, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = -64; Yxxxxxxxxxxxxxxxx <= 4; Yxxxxxxxxxxxxxxxx++) {
            for (int Z = 0; Z <= 24; Z++) {
               LegacyWorld.setBlock(world, x + 24, y + Yxxxxxxxxxxxxxxxx, z + Z, PunMod.icebrick);
            }
         }

         for (int Z = 0; Z <= 24; Z++) {
            if (Z % 2 == 0) {
               LegacyWorld.setBlock(world, x, y + 5, z + Z, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxx = 0; Xxxxxxxxxxxxx <= 24; Xxxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxx, y + 5, z, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxx <= 24; Xxxxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxx, y + 5, z + 24, PunMod.icebrick);
            }
         }

         for (int Zx = 0; Zx <= 24; Zx++) {
            if (Zx % 2 == 0) {
               LegacyWorld.setBlock(world, x + 24, y + 5, z + Zx, PunMod.icebrick);
            }
         }

         for (int Zxx = 1; Zxx <= 23; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 1; Xxxxxxxxxxxxxxx <= 23; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 4, z + Zxx, PunMod.icebrick);
            }
         }

         for (int Zxx = 7; Zxx <= 17; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 7; Xxxxxxxxxxxxxxx <= 17; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 18, z + Zxx, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 19, z + Zxx, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 19, z + Zxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(300) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 19, z + Zxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 19, z + Zxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Zxx = 7; Zxx <= 17; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 7; Xxxxxxxxxxxxxxx <= 17; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 24, z + Zxx, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 25, z + Zxx, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 25, z + Zxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(300) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 25, z + Zxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 25, z + Zxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Zxx = 9; Zxx <= 15; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 35, z + Zxx, PunMod.icebrick);
               if (rand.nextInt(25) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 36, z + Zxx, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 36, z + Zxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(100) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 36, z + Zxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 36, z + Zxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Zxx = 9; Zxx <= 15; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 39, z + Zxx, PunMod.icebrick);
               if (rand.nextInt(25) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 40, z + Zxx, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 40, z + Zxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(100) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 40, z + Zxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 40, z + Zxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Zxx = 9; Zxx <= 15; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 43, z + Zxx, PunMod.icebrick);
               if (rand.nextInt(25) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 44, z + Zxx, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 44, z + Zxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(100) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 44, z + Zxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 44, z + Zxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Zxx = 9; Zxx <= 15; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 47, z + Zxx, PunMod.icebrick);
               if (rand.nextInt(25) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 48, z + Zxx, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 48, z + Zxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(100) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 48, z + Zxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 48, z + Zxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Zxx = 9; Zxx <= 15; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 51, z + Zxx, PunMod.icebrick);
               if (rand.nextInt(25) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 52, z + Zxx, Blocks.MOB_SPAWNER);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 52, z + Zxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(100) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 52, z + Zxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxx, y + 52, z + Zxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }
            }
         }

         for (int Zxx = 9; Zxx <= 15; Zxx++) {
            for (int Xxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 55, z + Zxx, PunMod.icebrick);
            }
         }

         LegacyWorld.setBlock(world, x + 10, y + 56, z + 11, Blocks.MOB_SPAWNER);
         TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + 10, y + 56, z + 11);
         if (tileentitymobspawner != null) {
            tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciadorBoss"));
         }

         LegacyWorld.setBlock(world, x + 10, y + 56, z + 14, Blocks.MOB_SPAWNER);
         tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + 10, y + 56, z + 14);
         if (tileentitymobspawner != null) {
            tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciadorBoss"));
         }

         LegacyWorld.setBlock(world, x + 10, y + 56, z + 12, Blocks.CHEST, 0, 2);
         TileEntityChest tileentitychest2 = (TileEntityChest)LegacyWorld.getTileEntity(world, x + 10, y + 56, z + 12);
         if (tileentitychest2 != null) {
            LegacyChestGenHooks.fillChest(tileentitychest2, "pyramidJungleChest", rand);
         }

         LegacyWorld.setBlock(world, x + 10, y + 56, z + 13, Blocks.CHEST, 0, 2);
         tileentitychest2 = (TileEntityChest)LegacyWorld.getTileEntity(world, x + 10, y + 56, z + 13);
         if (tileentitychest2 != null) {
            LegacyChestGenHooks.fillChest(tileentitychest2, "pyramidJungleChest", rand);
         }

         for (int Yxxxxxxxxxxxxxxxx = 5; Yxxxxxxxxxxxxxxxx <= 12; Yxxxxxxxxxxxxxxxx++) {
            for (int Zxx = 3; Zxx <= 21; Zxx++) {
               LegacyWorld.setBlock(world, x + 3, y + Yxxxxxxxxxxxxxxxx, z + Zxx, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 5; Yxxxxxxxxxxxxxxxx <= 12; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxx = 3; Xxxxxxxxxxxxxxx <= 21; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 3, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 5; Yxxxxxxxxxxxxxxxx <= 12; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxx = 3; Xxxxxxxxxxxxxxx <= 21; Xxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 21, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 5; Yxxxxxxxxxxxxxxxx <= 12; Yxxxxxxxxxxxxxxxx++) {
            for (int Zxx = 3; Zxx <= 21; Zxx++) {
               LegacyWorld.setBlock(world, x + 21, y + Yxxxxxxxxxxxxxxxx, z + Zxx, PunMod.icebrick);
            }
         }

         for (int Zxx = 3; Zxx <= 21; Zxx++) {
            if (Zxx % 2 == 1) {
               LegacyWorld.setBlock(world, x + 3, y + 13, z + Zxx, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxxxx = 3; Xxxxxxxxxxxxxxx <= 21; Xxxxxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxxxxx % 2 == 1) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxx, y + 13, z + 3, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxxxxx = 3; Xxxxxxxxxxxxxxxx <= 21; Xxxxxxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxxxxxx % 2 == 1) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxx, y + 13, z + 21, PunMod.icebrick);
            }
         }

         for (int Zxxx = 3; Zxxx <= 21; Zxxx++) {
            if (Zxxx % 2 == 1) {
               LegacyWorld.setBlock(world, x + 21, y + 13, z + Zxxx, PunMod.icebrick);
            }
         }

         for (int Zxxxx = 4; Zxxxx <= 20; Zxxxx++) {
            for (int Xxxxxxxxxxxxxxxxx = 4; Xxxxxxxxxxxxxxxxx <= 20; Xxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxx, y + 12, z + Zxxxx, PunMod.icebrick);
               if (rand.nextInt(100) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxx, y + 13, z + Zxxxx, Blocks.MOB_SPAWNER);
                  tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxxxx, y + 13, z + Zxxxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(200) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxx, y + 13, z + Zxxxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxxxx, y + 13, z + Zxxxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
                  }
               }
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 13; Yxxxxxxxxxxxxxxxx <= 31; Yxxxxxxxxxxxxxxxx++) {
            for (int Zxxxx = 6; Zxxxx <= 18; Zxxxx++) {
               LegacyWorld.setBlock(world, x + 6, y + Yxxxxxxxxxxxxxxxx, z + Zxxxx, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 13; Yxxxxxxxxxxxxxxxx <= 31; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxx = 6; Xxxxxxxxxxxxxxxxx <= 18; Xxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 6, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 13; Yxxxxxxxxxxxxxxxx <= 31; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxx = 6; Xxxxxxxxxxxxxxxxx <= 18; Xxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 18, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 13; Yxxxxxxxxxxxxxxxx <= 31; Yxxxxxxxxxxxxxxxx++) {
            for (int Zxxxx = 6; Zxxxx <= 18; Zxxxx++) {
               LegacyWorld.setBlock(world, x + 18, y + Yxxxxxxxxxxxxxxxx, z + Zxxxx, PunMod.icebrick);
            }
         }

         for (int Zxxxx = 6; Zxxxx <= 18; Zxxxx++) {
            if (Zxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + 6, y + 32, z + Zxxxx, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxxxxxx = 6; Xxxxxxxxxxxxxxxxx <= 18; Xxxxxxxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxx, y + 32, z + 6, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxxxxxxx = 6; Xxxxxxxxxxxxxxxxxx <= 18; Xxxxxxxxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxx, y + 32, z + 18, PunMod.icebrick);
            }
         }

         for (int Zxxxxx = 6; Zxxxxx <= 18; Zxxxxx++) {
            if (Zxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + 18, y + 32, z + Zxxxxx, PunMod.icebrick);
            }
         }

         for (int Zxxxxxx = 7; Zxxxxxx <= 17; Zxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxx = 7; Xxxxxxxxxxxxxxxxxxx <= 17; Xxxxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxx, y + 31, z + Zxxxxxx, PunMod.icebrick);
               if (rand.nextInt(50) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxx, y + 32, z + Zxxxxxx, Blocks.MOB_SPAWNER);
                  tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxxxxxx, y + 32, z + Zxxxxxx);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
                  }
               }

               if (rand.nextInt(100) == 6) {
                  LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxx, y + 32, z + Zxxxxxx, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + Xxxxxxxxxxxxxxxxxxx, y + 32, z + Zxxxxxx);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
                  }
               }
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 32; Yxxxxxxxxxxxxxxxx <= 55; Yxxxxxxxxxxxxxxxx++) {
            for (int Zxxxxxx = 9; Zxxxxxx <= 15; Zxxxxxx++) {
               LegacyWorld.setBlock(world, x + 9, y + Yxxxxxxxxxxxxxxxx, z + Zxxxxxx, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 32; Yxxxxxxxxxxxxxxxx <= 55; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 9, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 32; Yxxxxxxxxxxxxxxxx <= 55; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 15, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 32; Yxxxxxxxxxxxxxxxx <= 55; Yxxxxxxxxxxxxxxxx++) {
            for (int Zxxxxxx = 9; Zxxxxxx <= 15; Zxxxxxx++) {
               LegacyWorld.setBlock(world, x + 15, y + Yxxxxxxxxxxxxxxxx, z + Zxxxxxx, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 55; Yxxxxxxxxxxxxxxxx <= 63; Yxxxxxxxxxxxxxxxx++) {
            for (int Zxxxxxx = 8; Zxxxxxx <= 16; Zxxxxxx++) {
               LegacyWorld.setBlock(world, x + 8, y + Yxxxxxxxxxxxxxxxx, z + Zxxxxxx, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 55; Yxxxxxxxxxxxxxxxx <= 63; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxx = 8; Xxxxxxxxxxxxxxxxxxx <= 16; Xxxxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 8, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 55; Yxxxxxxxxxxxxxxxx <= 63; Yxxxxxxxxxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxx = 8; Xxxxxxxxxxxxxxxxxxx <= 16; Xxxxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxx, y + Yxxxxxxxxxxxxxxxx, z + 16, PunMod.icebrick);
            }
         }

         for (int Yxxxxxxxxxxxxxxxx = 55; Yxxxxxxxxxxxxxxxx <= 63; Yxxxxxxxxxxxxxxxx++) {
            for (int Zxxxxxx = 8; Zxxxxxx <= 16; Zxxxxxx++) {
               LegacyWorld.setBlock(world, x + 16, y + Yxxxxxxxxxxxxxxxx, z + Zxxxxxx, PunMod.icebrick);
            }
         }

         for (int Zxxxxxx = 8; Zxxxxxx <= 16; Zxxxxxx++) {
            if (Zxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + 8, y + 64, z + Zxxxxxx, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxxxxxxxx = 8; Xxxxxxxxxxxxxxxxxxx <= 16; Xxxxxxxxxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxx, y + 64, z + 8, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxxxxxxxxx = 8; Xxxxxxxxxxxxxxxxxxxx <= 16; Xxxxxxxxxxxxxxxxxxxx++) {
            if (Xxxxxxxxxxxxxxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxx, y + 64, z + 16, PunMod.icebrick);
            }
         }

         for (int Zxxxxxxx = 8; Zxxxxxxx <= 16; Zxxxxxxx++) {
            if (Zxxxxxxx % 2 == 0) {
               LegacyWorld.setBlock(world, x + 16, y + 64, z + Zxxxxxxx, PunMod.icebrick);
            }
         }

         for (int Zxxxxxxxx = 9; Zxxxxxxxx <= 15; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 9; Xxxxxxxxxxxxxxxxxxxxx <= 15; Xxxxxxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx, y + 63, z + Zxxxxxxxx, PunMod.icebrick);
            }
         }

         for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
            for (int Zxxxxxxxx = 0; Zxxxxxxxx <= 4; Zxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 19, y + 4, z + Zxxxxxxxx + 16, Blocks.AIR);
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 5; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 19, y + Yxxxxxxxxxxxxxxxx + 5, z + Zxxxxxxxx + 14, PunMod.icestairs, 3, 2);
                  }
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 4; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxx >= -3; Yxxxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 19, y + Yxxxxxxxxxxxxxxxxx + 4, z + Zxxxxxxxx + 14, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 7, y + 12, z - Zxxxxxxxx + 15, Blocks.AIR);
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 8; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxx >= -8; Yxxxxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 7, y + 12 + Yxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 15, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 11; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxx >= -8; Yxxxxxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 7, y + 13 + Yxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 15, PunMod.icestairs, 2, 2);
                  }
               }
            }
         }

         for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
            for (int Zxxxxxxxx = 0; Zxxxxxxxx <= 4; Zxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 16, y + 18, z + Zxxxxxxxx + 11, Blocks.AIR);
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 7; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxx >= -6; Yxxxxxxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 16, y + Yxxxxxxxxxxxxxxxxxxxx + 19, z + Zxxxxxxxx + 9, PunMod.icestairs, 3, 2);
                  }
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 8; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxx >= -6; Yxxxxxxxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 16, y + Yxxxxxxxxxxxxxxxxxxxxx + 18, z + Zxxxxxxxx + 9, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 7, y + 24, z - Zxxxxxxxx + 15, Blocks.AIR);
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 7; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxx >= -7; Yxxxxxxxxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 7, y + 24 + Yxxxxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 15, PunMod.icebrick);
                  }
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 8; Zxxxxxxxx++) {
            for (int Xxxxxxxxxxxxxxxxxxxxx = 0; Xxxxxxxxxxxxxxxxxxxxx <= 1; Xxxxxxxxxxxxxxxxxxxxx++) {
               for (int Yxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxx >= -6; Yxxxxxxxxxxxxxxxxxxxxxxx--) {
                  if (Yxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                     LegacyWorld.setBlock(world, x + Xxxxxxxxxxxxxxxxxxxxx + 7, y + 25 + Yxxxxxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 15, PunMod.icestairs, 2, 2);
                  }
               }
            }
         }

         for (int Zxxxxxxxx = 0; Zxxxxxxxx <= 4; Zxxxxxxxx++) {
            LegacyWorld.setBlock(world, x + 14, y + 31, z + Zxxxxxxxx + 10, Blocks.AIR);
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 8; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxx >= -7; Yxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 14, y + Yxxxxxxxxxxxxxxxxxxxxxxxx + 32, z + Zxxxxxxxx + 8, PunMod.icestairs, 3, 2);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 8; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxx >= -7; Yxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 14, y + Yxxxxxxxxxxxxxxxxxxxxxxxxx + 31, z + Zxxxxxxxx + 8, PunMod.icebrick);
               }
            }
         }

         for (int Zxxxxxxxx = 0; Zxxxxxxxx <= 4; Zxxxxxxxx++) {
            LegacyWorld.setBlock(world, x + 14, y + 39, z + Zxxxxxxxx + 10, Blocks.AIR);
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 14, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxx + 40, z + Zxxxxxxxx + 8, PunMod.icestairs, 3, 2);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 14, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxx + 39, z + Zxxxxxxxx + 8, PunMod.icebrick);
               }
            }
         }

         for (int Zxxxxxxxx = 0; Zxxxxxxxx <= 4; Zxxxxxxxx++) {
            LegacyWorld.setBlock(world, x + 14, y + 47, z + Zxxxxxxxx + 10, Blocks.AIR);
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 7; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 14, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxx + 48, z + Zxxxxxxxx + 8, PunMod.icestairs, 3, 2);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 7; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 14, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxx + 47, z + Zxxxxxxxx + 8, PunMod.icebrick);
               }
            }
         }

         for (int Zxxxxxxxx = 0; Zxxxxxxxx <= 4; Zxxxxxxxx++) {
            LegacyWorld.setBlock(world, x + 14, y + 55, z + Zxxxxxxxx + 10, Blocks.AIR);
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 14, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx + 56, z + Zxxxxxxxx + 8, PunMod.icestairs, 3, 2);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 7; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 14, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx + 55, z + Zxxxxxxxx + 8, PunMod.icebrick);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            LegacyWorld.setBlock(world, x + 10, y + 35, z - Zxxxxxxxx + 16, Blocks.AIR);
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 10, y + 35 + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 16, PunMod.icebrick);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 9; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 10, y + 36 + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 16, PunMod.icestairs, 2, 2);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            LegacyWorld.setBlock(world, x + 10, y + 43, z - Zxxxxxxxx + 16, Blocks.AIR);
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 10, y + 43 + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 16, PunMod.icebrick);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 9; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 10, y + 44 + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 16, PunMod.icestairs, 2, 2);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            LegacyWorld.setBlock(world, x + 10, y + 51, z - Zxxxxxxxx + 16, Blocks.AIR);
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 6; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 10, y + 51 + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 16, PunMod.icebrick);
               }
            }
         }

         for (int Zxxxxxxxx = 2; Zxxxxxxxx <= 9; Zxxxxxxxx++) {
            for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx >= -4; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx--) {
               if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx - 1 == Zxxxxxxxx * -1) {
                  LegacyWorld.setBlock(world, x + 10, y + 52 + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z - Zxxxxxxxx + 16, PunMod.icestairs, 2, 2);
               }
            }
         }

         LegacyWorld.setBlock(world, x + 1, y + 4, z + 1, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 1, y + 4, z + 11, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 11, y + 4, z + 1, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 23, y + 4, z + 1, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 1, y + 4, z + 23, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 11, y + 4, z + 23, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 23, y + 4, z + 11, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 23, y + 4, z + 23, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 4, y + 12, z + 4, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 4, y + 12, z + 12, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 12, y + 12, z + 4, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 20, y + 12, z + 4, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 4, y + 12, z + 20, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 12, y + 12, z + 20, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 20, y + 12, z + 12, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 20, y + 12, z + 20, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 7, y + 31, z + 7, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 7, y + 31, z + 12, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 12, y + 31, z + 7, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 17, y + 31, z + 7, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 7, y + 31, z + 17, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 12, y + 31, z + 17, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 17, y + 31, z + 12, PunMod.glowice);
         LegacyWorld.setBlock(world, x + 17, y + 31, z + 17, PunMod.glowice);

         for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -64; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx <= -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++) {
            if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx % 4 == 0) {
               LegacyWorld.setBlock(world, x, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z, PunMod.glowice);
            }
         }

         for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -64; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx <= -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++) {
            if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx % 4 == 0) {
               LegacyWorld.setBlock(world, x + 11, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z, PunMod.glowice);
            }
         }

         for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -64; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx <= -1; Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++) {
            if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx % 4 == 0) {
               LegacyWorld.setBlock(world, x + 24, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z, PunMod.glowice);
            }
         }

         for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -64;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx <= -1;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++
         ) {
            if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx % 4 == 0) {
               LegacyWorld.setBlock(world, x, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z + 11, PunMod.glowice);
            }
         }

         for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -64;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx <= -1;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++
         ) {
            if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx % 4 == 0) {
               LegacyWorld.setBlock(world, x, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z + 24, PunMod.glowice);
            }
         }

         for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -64;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx <= -1;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++
         ) {
            if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx % 4 == 0) {
               LegacyWorld.setBlock(world, x + 11, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z + 24, PunMod.glowice);
            }
         }

         for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -64;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx <= -1;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++
         ) {
            if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx % 4 == 0) {
               LegacyWorld.setBlock(world, x + 24, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z + 11, PunMod.glowice);
            }
         }

         for (int Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = -64;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx <= -1;
            Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx++
         ) {
            if (Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx % 4 == 0) {
               LegacyWorld.setBlock(world, x + 24, y + Yxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx, z + 24, PunMod.glowice);
            }
         }
      }

      return true;
   }
}
