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

public class WorldGenSmallIceDungeon extends WorldGenerator {
   boolean switchstairs = false;

   @Override
   public boolean generate(World w, Random r, BlockPos p) {
      return this.generate(w, r, p.getX(), p.getY(), p.getZ());
   }

   protected void setLegacy(World w, int x, int y, int z, net.minecraft.block.Block b) {
      LegacyWorld.setBlock(w, x, y, z, b, 0, 2);
   }

   public boolean generate(World world, Random rand, int x, int y, int z) {
      if (LegacyWorld.getBlock(world, x, y - 1, z) == Blocks.ICE
         && LegacyWorld.getBlock(world, x, y, z) == Blocks.AIR
         && LegacyWorld.getBlock(world, x, y - 10, z) == Blocks.ICE) {
         int Y = y + 5;
         LegacyWorld.setBlock(world, x, y + 5, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, y + 5, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, y + 5, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x, y + 5, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x, y + 5, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, y + 5, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, y + 5, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, y + 5, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, y + 5, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, y + 5, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, y + 5, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, y + 5, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, y + 5, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, y + 5, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, y + 5, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, y + 5, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, y + 5, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, y + 5, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, y + 5, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, y + 5, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x, y + 5, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, y + 5, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, y + 5, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x, y + 5, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, y + 5, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 3, y + 7, z + 3, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 3, y + 7, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 3, y + 7, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 3, y + 7, z - 3, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 3, y + 7, z + 3, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 3, y + 7, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 3, y + 7, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 3, y + 7, z - 3, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, y + 7, z + 3, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, y + 7, z + 3, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, y + 7, z - 3, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, y + 7, z - 3, PunMod.icebrick);

         for (int i = 0; i <= 4; i++) {
            LegacyWorld.setBlock(world, x + 3, y + 6 - i, z + 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 3, y + 6 - i, z + 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 3, y + 6 - i, z + 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 3, y + 6 - i, z, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 3, y + 6 - i, z - 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 3, y + 6 - i, z - 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 3, y + 6 - i, z - 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 3, y + 6 - i, z + 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 3, y + 6 - i, z + 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 3, y + 6 - i, z + 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 3, y + 6 - i, z, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 3, y + 6 - i, z - 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 3, y + 6 - i, z - 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 3, y + 6 - i, z - 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 2, y + 6 - i, z + 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 1, y + 6 - i, z + 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x, y + 6 - i, z + 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 1, y + 6 - i, z + 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 2, y + 6 - i, z + 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 2, y + 6 - i, z - 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 1, y + 6 - i, z - 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x, y + 6 - i, z - 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 1, y + 6 - i, z - 3, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 2, y + 6 - i, z - 3, PunMod.icebrick);
         }

         for (int i = 0; i <= 3; i++) {
            LegacyWorld.setBlock(world, x, y + 4, z, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 1, y + 4, z, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 1, y + 4, z, Blocks.AIR);
            LegacyWorld.setBlock(world, x, y + 4, z + 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x, y + 4, z - 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 1, y + 4, z + 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 1, y + 4, z - 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 1, y + 4, z + 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 1, y + 4, z - 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 2, y + 4, z + 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 2, y + 4, z + 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 2, y + 4, z, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 2, y + 4, z - 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 2, y + 4, z - 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 2, y + 4, z + 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 2, y + 4, z + 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 2, y + 4, z, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 2, y + 4, z - 1, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 2, y + 4, z - 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 1, y + 4, z + 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x, y + 4, z + 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 1, y + 4, z + 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x + 1, y + 4, z - 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x, y + 4, z - 2, Blocks.AIR);
            LegacyWorld.setBlock(world, x - 1, y + 4, z - 2, Blocks.AIR);
         }

         do {
            Y -= 4;
            LegacyWorld.setBlock(world, x, Y, z, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 1, Y, z, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 1, Y, z, PunMod.icebrick);
            LegacyWorld.setBlock(world, x, Y, z + 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x, Y, z - 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 1, Y, z + 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 1, Y, z - 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 1, Y, z + 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 1, Y, z - 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 2, Y, z + 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 2, Y, z + 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 2, Y, z, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 2, Y, z - 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 2, Y, z - 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 2, Y, z + 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 2, Y, z + 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 2, Y, z, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 2, Y, z - 1, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 2, Y, z - 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 1, Y, z + 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x, Y, z + 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 1, Y, z + 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x + 1, Y, z - 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x, Y, z - 2, PunMod.icebrick);
            LegacyWorld.setBlock(world, x - 1, Y, z - 2, PunMod.icebrick);

            for (int i = 1; i <= 4; i++) {
               LegacyWorld.setBlock(world, x, Y - i, z, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 1, Y - i, z, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 1, Y - i, z, Blocks.AIR);
               LegacyWorld.setBlock(world, x, Y - i, z + 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x, Y - i, z - 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 1, Y - i, z + 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 1, Y - i, z - 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 1, Y - i, z + 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 1, Y - i, z - 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 2, Y - i, z + 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 2, Y - i, z + 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 2, Y - i, z, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 2, Y - i, z - 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 2, Y - i, z - 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 2, Y - i, z + 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 2, Y - i, z + 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 2, Y - i, z, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 2, Y - i, z - 1, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 2, Y - i, z - 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 1, Y - i, z + 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x, Y - i, z + 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 1, Y - i, z + 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x + 1, Y - i, z - 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x, Y - i, z - 2, Blocks.AIR);
               LegacyWorld.setBlock(world, x - 1, Y - i, z - 2, Blocks.AIR);
            }

            for (int i = 0; i <= 4; i++) {
               LegacyWorld.setBlock(world, x + 3, Y - i, z + 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 3, Y - i, z + 2, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 3, Y - i, z + 1, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 3, Y - i, z, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 3, Y - i, z - 1, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 3, Y - i, z - 2, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 3, Y - i, z - 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 3, Y - i, z + 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 3, Y - i, z + 2, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 3, Y - i, z + 1, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 3, Y - i, z, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 3, Y - i, z - 1, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 3, Y - i, z - 2, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 3, Y - i, z - 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 2, Y - i, z + 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 1, Y - i, z + 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x, Y - i, z + 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 1, Y - i, z + 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 2, Y - i, z + 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 2, Y - i, z - 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 1, Y - i, z - 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x, Y - i, z - 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 1, Y - i, z - 3, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 2, Y - i, z - 3, PunMod.icebrick);
            }

            if (!this.switchstairs) {
               LegacyWorld.setBlockToAir(world, x + 2, Y, z + 2);
               LegacyWorld.setBlockToAir(world, x + 2, Y, z + 1);
               LegacyWorld.setBlockToAir(world, x + 2, Y, z);
               LegacyWorld.setBlockToAir(world, x + 2, Y, z - 1);

               for (int i = 1; i <= 3; i++) {
                  LegacyWorld.setBlock(world, x + 2, Y - i, z + 2, PunMod.icebrick);
               }

               LegacyWorld.setBlock(world, x + 2, Y, z + 2, PunMod.icestairs, 2, 2);

               for (int i = 2; i <= 3; i++) {
                  LegacyWorld.setBlock(world, x + 2, Y - i, z + 1, PunMod.icebrick);
               }

               LegacyWorld.setBlock(world, x + 2, Y - 1, z + 1, PunMod.icestairs, 2, 2);
               LegacyWorld.setBlock(world, x + 2, Y - 3, z, PunMod.icebrick);
               LegacyWorld.setBlock(world, x + 2, Y - 2, z, PunMod.icestairs, 2, 2);
               LegacyWorld.setBlock(world, x + 2, Y - 3, z - 1, PunMod.icestairs, 2, 2);
               if (rand.nextInt(3) == 0) {
                  LegacyWorld.setBlock(world, x, Y - 1, z, Blocks.MOB_SPAWNER, 0, 2);
                  TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x, Y - 1, z);
                  if (tileentitymobspawner != null) {
                     tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityColdKiller"));
                  }

                  LegacyWorld.setBlock(world, x + 1, Y - 3, z + 2, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + 1, Y - 3, z + 2);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }

               this.switchstairs = true;
            } else if (this.switchstairs) {
               LegacyWorld.setBlockToAir(world, x - 2, Y, z + 2);
               LegacyWorld.setBlockToAir(world, x - 2, Y, z + 1);
               LegacyWorld.setBlockToAir(world, x - 2, Y, z);
               LegacyWorld.setBlockToAir(world, x - 2, Y, z - 1);

               for (int i = 1; i <= 3; i++) {
                  LegacyWorld.setBlock(world, x - 2, Y - i, z + 2, PunMod.icebrick);
               }

               LegacyWorld.setBlock(world, x - 2, Y, z + 2, PunMod.icestairs, 2, 2);

               for (int i = 2; i <= 3; i++) {
                  LegacyWorld.setBlock(world, x - 2, Y - i, z + 1, PunMod.icebrick);
               }

               LegacyWorld.setBlock(world, x - 2, Y - 1, z + 1, PunMod.icestairs, 2, 2);
               LegacyWorld.setBlock(world, x - 2, Y - 3, z, PunMod.icebrick);
               LegacyWorld.setBlock(world, x - 2, Y - 2, z, PunMod.icestairs, 2, 2);
               LegacyWorld.setBlock(world, x - 2, Y - 3, z - 1, PunMod.icestairs, 2, 2);
               if (rand.nextInt(3) == 0) {
                  LegacyWorld.setBlock(world, x, Y - 1, z, Blocks.MOB_SPAWNER, 0, 2);
                  TileEntityMobSpawner tileentitymobspawnerx = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x, Y - 1, z);
                  if (tileentitymobspawnerx != null) {
                     tileentitymobspawnerx.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityColdKiller"));
                  }

                  LegacyWorld.setBlock(world, x - 1, Y - 3, z + 2, Blocks.CHEST, 0, 2);
                  TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x - 1, Y - 3, z + 2);
                  if (tileentitychest != null) {
                     LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
                  }
               }

               this.switchstairs = false;
            }
         } while (LegacyWorld.getBlock(world, x, Y - 5, z) == Blocks.ICE);

         Y -= 4;
         LegacyWorld.setBlock(world, x, Y, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, Y, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, Y, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x, Y, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x, Y, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, Y, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, Y, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, Y, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, Y, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, Y, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, Y, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, Y, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, Y, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 2, Y, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, Y, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, Y, z + 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, Y, z, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, Y, z - 1, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 2, Y, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, Y, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x, Y, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, Y, z + 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x + 1, Y, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x, Y, z - 2, PunMod.icebrick);
         LegacyWorld.setBlock(world, x - 1, Y, z - 2, PunMod.icebrick);
         if (this.switchstairs) {
            LegacyWorld.setBlock(world, x, Y + 3, z, Blocks.MOB_SPAWNER, 0, 2);
            TileEntityMobSpawner tileentitymobspawnerxx = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x, Y + 3, z);
            if (tileentitymobspawnerxx != null) {
               tileentitymobspawnerxx.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
            }

            for (int c = 0; c < 2; c++) {
               LegacyWorld.setBlock(world, x + 1, Y + 1, z + 2 - c, Blocks.CHEST, 0, 2);
               TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + 1, Y + 1, z + 2 - c);
               if (tileentitychest != null) {
                  LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
               }
            }
         } else if (!this.switchstairs) {
            LegacyWorld.setBlock(world, x, Y + 3, z, Blocks.MOB_SPAWNER, 0, 2);
            TileEntityMobSpawner tileentitymobspawnerxx = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x, Y + 3, z);
            if (tileentitymobspawnerxx != null) {
               tileentitymobspawnerxx.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
            }

            for (int cx = 0; cx < 2; cx++) {
               LegacyWorld.setBlock(world, x - 1, Y + 1, z + 2 - cx, Blocks.CHEST, 0, 2);
               TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x - 1, Y + 1, z + 2 - cx);
               if (tileentitychest != null) {
                  LegacyChestGenHooks.fillChest(tileentitychest, "pyramidJungleChest", rand);
               }
            }
         }
      }

      return true;
   }
}
