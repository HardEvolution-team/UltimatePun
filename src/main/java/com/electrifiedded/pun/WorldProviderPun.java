package com.electrifiedded.pun;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** 1.7 WorldProviderPun: the "D-ice-mention" (nether-like, no sky, always snowing). */
public class WorldProviderPun extends WorldProvider {
   @Override
   protected void init() {
      this.nether = true;
      this.hasSkyLight = false;
      this.biomeProvider = new BiomeProviderSingle(PunMod.extremeicecaves);
   }

   @Override
   public DimensionType getDimensionType() {
      return PunMod.dimType;
   }

   @Override
   public boolean isSurfaceWorld() {
      return false;
   }

   @Override
   public boolean canRespawnHere() {
      return false;
   }

   @Override
   protected void generateLightBrightnessTable() {
      float f = 0.05F;
      for (int i = 0; i <= 15; i++) {
         float f1 = 1.0F - i / 15.0F;
         this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   public Vec3d getFogColor(float par1, float par2) {
      return new Vec3d(0.40000000298023225, 0.40999999329447745, 0.8999999932944774);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public boolean doesXZShowFog(int x, int z) {
      return true;
   }

   @Override
   public IChunkGenerator createChunkGenerator() {
      return new ChunkProviderPun(this.world, this.world.getSeed());
   }



   @Override
   public boolean canDoLightning(Chunk chunk) {
      return false;
   }

   @Override
   public boolean canDoRainSnowIce(Chunk chunk) {
      return true;
   }

   @Override
   public void resetRainAndThunder() {
      this.world.getWorldInfo().setRainTime(100);
      this.world.getWorldInfo().setRaining(true);
   }
}
