package com.electrifiedded.pun;

import com.electrifiedded.archaiclib.legacy.world.LegacyParticles;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** 1.7 BlockFleshLight: torch that withers undead within 10 blocks on random ticks. */
public class BlockFleshLight extends BlockTorch {
   public BlockFleshLight() {
      this.setCreativeTab(PunMod.ThePunMod);
      this.setLightOpacity(0);
      this.setLightLevel(1.0F);
      this.setSoundType(SoundType.WOOD);
      this.setTickRandomly(true);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random random) {
      EnumFacing f = state.getValue(FACING);
      double d0 = pos.getX() + 0.5F;
      double d1 = pos.getY() + 0.7F;
      double d2 = pos.getZ() + 0.5F;
      if (f.getAxis().isHorizontal()) {
         EnumFacing o = f.getOpposite();
         d0 += 0.27F * o.getXOffset();
         d1 += 0.22F;
         d2 += 0.27F * o.getZOffset();
      }
      LegacyParticles.spawn(worldIn, "smoke", d0, d1, d2, 0.0, 0.0, 0.0);
      LegacyParticles.spawn(worldIn, "portal", d0, d1, d2, 0.0, 0.0, 0.0);
   }

   @Override
   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
      super.updateTick(worldIn, pos, state, random);
      List<EntityLivingBase> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos).grow(10.0));
      for (EntityLivingBase e : list) {
         if (e.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
            e.addPotionEffect(new PotionEffect(MobEffects.WITHER, 200, 0));
         }
      }
   }
}
