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

public class WorldGenFlatDungeon extends WorldGenerator {
   @Override
   public boolean generate(World w, Random r, BlockPos p) {
      return this.generate(w, r, p.getX(), p.getY(), p.getZ());
   }

   protected void setLegacy(World w, int x, int y, int z, net.minecraft.block.Block b) {
      LegacyWorld.setBlock(w, x, y, z, b, 0, 2);
   }

   public boolean generate(World world, Random rand, int x, int y, int z) {
      if (LegacyWorld.getBlock(world, x, y + 1, z) == Blocks.ICE && LegacyWorld.getBlock(world, x, y + 2, z) == Blocks.AIR) {
         for (int Y = 0; Y <= 5; Y++) {
            for (int X = 0; X <= 15; X++) {
               for (int Z = 0; Z <= 15; Z++) {
                  LegacyWorld.setBlock(world, x + X, y - Y, z + Z, PunMod.icebrick);
               }
            }
         }

         for (int X = 0; X <= 13; X++) {
            for (int Z = 0; Z <= 13; Z++) {
               LegacyWorld.setBlock(world, x + X + 1, y, z + Z + 1, Blocks.AIR);
            }
         }

         for (int X = 0; X <= 9; X++) {
            for (int Z = 0; Z <= 9; Z++) {
               LegacyWorld.setBlock(world, x + X + 3, y - 1, z + Z + 3, Blocks.AIR);
            }
         }

         for (int X = 0; X <= 5; X++) {
            for (int Z = 0; Z <= 5; Z++) {
               LegacyWorld.setBlock(world, x + X + 5, y - 2, z + Z + 5, Blocks.AIR);
            }
         }

         for (int X = 0; X <= 3; X++) {
            for (int Z = 0; Z <= 3; Z++) {
               LegacyWorld.setBlock(world, x + X + 6, y - 3, z + Z + 6, Blocks.AIR);
            }
         }

         for (int X = 0; X <= 3; X++) {
            for (int Z = 0; Z <= 3; Z++) {
               LegacyWorld.setBlock(world, x + X + 6, y - 4, z + Z + 6, PunMod.glowice);
            }
         }

         LegacyWorld.setBlock(world, x + 8, y - 3, z + 8, Blocks.MOB_SPAWNER);
         TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + 8, y - 3, z + 8);
         if (tileentitymobspawner != null) {
            tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
         }

         LegacyWorld.setBlock(world, x + 7, y - 3, z + 8, Blocks.MOB_SPAWNER);
         tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + 7, y - 3, z + 8);
         if (tileentitymobspawner != null) {
            tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
         }

         LegacyWorld.setBlock(world, x + 8, y - 3, z + 7, Blocks.MOB_SPAWNER);
         tileentitymobspawner = (TileEntityMobSpawner)LegacyWorld.getTileEntity(world, x + 8, y - 3, z + 7);
         if (tileentitymobspawner != null) {
            tileentitymobspawner.getSpawnerBaseLogic().setEntityId(PunMod.spawnerId("EntityGlaciador"));
         }

         LegacyWorld.setBlock(world, x + 7, y - 3, z + 7, Blocks.CHEST, 0, 2);
         TileEntityChest tileentitychest = (TileEntityChest)LegacyWorld.getTileEntity(world, x + 7, y - 3, z + 7);
         if (tileentitychest != null) {
            LegacyChestGenHooks.fillChest(tileentitychest, "strongholdCorridor", rand);
         }
      }

      return true;
   }
}
