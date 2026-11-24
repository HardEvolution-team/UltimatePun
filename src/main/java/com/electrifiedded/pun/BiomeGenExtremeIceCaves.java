package com.electrifiedded.pun;

import net.minecraft.world.biome.Biome;

public class BiomeGenExtremeIceCaves extends Biome {
   public BiomeGenExtremeIceCaves() {
      super(new BiomeProperties("Sn-owverWorld").setSnowEnabled().setTemperature(0.0F).setRainfall(0.5F));
      this.spawnableMonsterList.clear();
      this.spawnableCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableCaveCreatureList.clear();
   }
}
