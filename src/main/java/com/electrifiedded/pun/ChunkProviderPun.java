package com.electrifiedded.pun;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

/** 1.7 ChunkProviderPun: nether-shaped terrain (128 high) made of ice, with the ice structures populated on top. */
public class ChunkProviderPun implements IChunkGenerator {
   private final Random hellRNG;
   private final NoiseGeneratorOctaves netherNoiseGen1;
   private final NoiseGeneratorOctaves netherNoiseGen2;
   private final NoiseGeneratorOctaves netherNoiseGen3;
   private final NoiseGeneratorOctaves netherNoiseGen6;
   private final NoiseGeneratorOctaves netherNoiseGen7;
   private final World world;
   private double[] noiseField;
   private double[] noiseData1;
   private double[] noiseData2;
   private double[] noiseData3;
   private double[] noiseData4;
   private double[] noiseData5;
   private static final IBlockState ICE = Blocks.ICE.getDefaultState();

   public ChunkProviderPun(World world, long seed) {
      this.world = world;
      this.hellRNG = new Random(seed);
      this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
      this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
      this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
      this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
      this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);
   }

   private void generateTerrain(int cx, int cz, ChunkPrimer primer) {
      byte b0 = 4;
      byte b1 = 32;
      int k = b0 + 1;
      byte b2 = 17;
      int l = b0 + 1;
      this.noiseField = this.initializeNoiseField(this.noiseField, cx * b0, 0, cz * b0, k, b2, l);
      for (int i1 = 0; i1 < b0; i1++) {
         for (int j1 = 0; j1 < b0; j1++) {
            for (int k1 = 0; k1 < 16; k1++) {
               double d0 = 0.125;
               double d1 = this.noiseField[((i1) * l + j1) * b2 + k1];
               double d2 = this.noiseField[((i1) * l + j1 + 1) * b2 + k1];
               double d3 = this.noiseField[((i1 + 1) * l + j1) * b2 + k1];
               double d4 = this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1];
               double d5 = (this.noiseField[((i1) * l + j1) * b2 + k1 + 1] - d1) * d0;
               double d6 = (this.noiseField[((i1) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
               double d7 = (this.noiseField[((i1 + 1) * l + j1) * b2 + k1 + 1] - d3) * d0;
               double d8 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;
               for (int l1 = 0; l1 < 8; l1++) {
                  double d9 = 0.25;
                  double d10 = d1;
                  double d11 = d2;
                  double d12 = (d3 - d1) * d9;
                  double d13 = (d4 - d2) * d9;
                  for (int i2 = 0; i2 < 4; i2++) {
                     int x = i2 + i1 * 4;
                     int y = k1 * 8 + l1;
                     double d14 = 0.25;
                     double d15 = d10;
                     double d16 = (d11 - d10) * d14;
                     for (int k2 = 0; k2 < 4; k2++) {
                        int z = k2 + j1 * 4;
                        if (y >= b1 && d15 <= 0.0) {
                           primer.setBlockState(x, y, z, ICE);
                        }
                        d15 += d16;
                     }
                     d10 += d12;
                     d11 += d13;
                  }
                  d1 += d5;
                  d2 += d6;
                  d3 += d7;
                  d4 += d8;
               }
            }
         }
      }
   }

   @Override
   public Chunk generateChunk(int x, int z) {
      this.hellRNG.setSeed(x * 341873128712L + z * 132897987541L);
      ChunkPrimer primer = new ChunkPrimer();
      this.generateTerrain(x, z, primer);
      Chunk chunk = new Chunk(this.world, primer, x, z);
      Biome[] biomes = this.world.getBiomeProvider().getBiomes(null, x * 16, z * 16, 16, 16);
      byte[] abyte = chunk.getBiomeArray();
      for (int k = 0; k < abyte.length; k++) {
         abyte[k] = (byte) Biome.getIdForBiome(biomes[k]);
      }
      chunk.resetRelightChecks();
      return chunk;
   }

   private double[] initializeNoiseField(double[] field, int px, int py, int pz, int sx, int sy, int sz) {
      if (field == null) {
         field = new double[sx * sy * sz];
      }
      double d0 = 684.412;
      double d1 = 2053.236;
      this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, px, py, pz, sx, 1, sz, 1.0, 0.0, 1.0);
      this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, px, py, pz, sx, 1, sz, 100.0, 0.0, 100.0);
      this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, px, py, pz, sx, sy, sz, d0 / 80.0, d1 / 60.0, d0 / 80.0);
      this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, px, py, pz, sx, sy, sz, d0, d1, d0);
      this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, px, py, pz, sx, sy, sz, d0, d1, d0);
      int k1 = 0;
      double[] adouble1 = new double[sy];
      for (int i2 = 0; i2 < sy; i2++) {
         adouble1[i2] = Math.cos(i2 * Math.PI * 6.0 / sy) * 2.0;
         double d2 = i2;
         if (i2 > sy / 2) {
            d2 = sy - 1 - i2;
         }
         if (d2 < 4.0) {
            d2 = 4.0 - d2;
            adouble1[i2] -= d2 * d2 * d2 * 10.0;
         }
      }
      for (int a = 0; a < sx; a++) {
         for (int b = 0; b < sz; b++) {
            double d4 = 0.0;
            for (int j2 = 0; j2 < sy; j2++) {
               double d6;
               double d7 = adouble1[j2];
               double d8 = this.noiseData2[k1] / 512.0;
               double d9 = this.noiseData3[k1] / 512.0;
               double d10 = (this.noiseData1[k1] / 10.0 + 1.0) / 2.0;
               if (d10 < 0.0) {
                  d6 = d8;
               } else if (d10 > 1.0) {
                  d6 = d9;
               } else {
                  d6 = d8 + (d9 - d8) * d10;
               }
               d6 -= d7;
               if (j2 > sy - 4) {
                  double d11 = (j2 - (sy - 4)) / 3.0F;
                  d6 = d6 * (1.0 - d11) + -10.0 * d11;
               }
               if (j2 < d4) {
                  double d11 = Math.max(0.0, Math.min(1.0, (d4 - j2) / 4.0));
                  d6 = d6 * (1.0 - d11) + -10.0 * d11;
               }
               field[k1] = d6;
               k1++;
            }
         }
      }
      return field;
   }

   @Override
   public void populate(int cx, int cz) {
      BlockFalling.fallInstantly = true;
      int k = cx * 16;
      int l = cz * 16;
      Random r = this.hellRNG;
      int n = r.nextInt(r.nextInt(2) + 1);
      for (int j = 0; j < n; j++) {
         new WorldGenSmallIceDungeon().generate(this.world, r, k + r.nextInt(16) + 8, r.nextInt(120) + 4, l + r.nextInt(16) + 8);
      }
      n = r.nextInt(r.nextInt(2) + 1);
      for (int j = 0; j < n; j++) {
         new WorldGenInvertedIceDungeon().generate(this.world, r, k + r.nextInt(16) + 8, r.nextInt(120) + 4, l + r.nextInt(16) + 8);
      }
      n = r.nextInt(r.nextInt(16) + 1);
      for (int j = 0; j < n; j++) {
         new WorldGenPackedIce1().generate(this.world, r, k + r.nextInt(16) + 8, r.nextInt(120) + 4, l + r.nextInt(16) + 8);
      }
      r.nextInt(r.nextInt(16) + 1);
      for (int j = 0; j < 10; j++) {
         new WorldGenPackedIce2().generate(this.world, r, k + r.nextInt(16) + 8, r.nextInt(128), l + r.nextInt(16) + 8);
      }
      n = r.nextInt(r.nextInt(2) + 1);
      for (int j = 0; j < n; j++) {
         new WorldGenIceSpike2().generate(this.world, r, k + r.nextInt(16) + 8, r.nextInt(120) + 4, l + r.nextInt(16) + 8);
      }
      n = r.nextInt(r.nextInt(2) + 1);
      for (int j = 0; j < n; j++) {
         new WorldGenIceSpike3().generate(this.world, r, k + r.nextInt(16) + 8, r.nextInt(100) + 4, l + r.nextInt(16) + 8);
      }
      n = r.nextInt(r.nextInt(2) + 1);
      for (int j = 0; j < n; j++) {
         new WorldGenIceSpike4().generate(this.world, r, k + r.nextInt(16) + 8, r.nextInt(5) + 140, l + r.nextInt(16) + 8);
      }
      n = r.nextInt(r.nextInt(2) + 1);
      for (int j = 0; j < n; j++) {
         new WorldGenFlatDungeon().generate(this.world, r, k + r.nextInt(16) + 8, r.nextInt(100) + 4, l + r.nextInt(16) + 8);
      }
      BlockFalling.fallInstantly = false;
   }

   @Override
   public boolean generateStructures(Chunk chunk, int x, int z) {
      return false;
   }

   @Override
   public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos) {
      return this.world.getBiome(pos).getSpawnableList(type);
   }

   @Nullable
   @Override
   public BlockPos getNearestStructurePos(World world, String name, BlockPos pos, boolean findUnexplored) {
      return null;
   }

   @Override
   public void recreateStructures(Chunk chunk, int x, int z) {
   }

   @Override
   public boolean isInsideStructure(World world, String name, BlockPos pos) {
      return false;
   }
}
