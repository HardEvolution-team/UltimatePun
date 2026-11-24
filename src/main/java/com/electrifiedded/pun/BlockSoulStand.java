package com.electrifiedded.pun;

import java.util.Random;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** 1.7 BlockSoulStand: brewing-stand shaped block giving the soul effects on right click. */
public class BlockSoulStand extends BlockBrewingStand {
   public BlockSoulStand() {
      this.setCreativeTab(PunMod.ThePunMod);
      this.setHardness(1.0F);
      this.setLightLevel(4.0F / 15.0F);
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
      player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200));
      player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200));
      player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 1));
      player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1800));
      return true;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random random, int fortune) {
      return Item.getItemFromBlock(this);
   }
}
