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
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityIceOrb extends EntityXPOrb {
   int charge;

   public EntityIceOrb(World par1World) {
      super(par1World);
   }

   public EntityIceOrb(World par1World, double par2, double par4, double par6) {
      super(par1World, par2, par4, par6, 0);
   }

   public void onUpdate() {
      super.onUpdate();
      this.motionY -= 0.03F;
      this.motionX = 0.0;
      this.motionZ = 0.0;
      this.motionY *= 0.98F;
      if (this.onGround) {
         this.motionY *= -0.9F;
      }

      this.xpColor++;
      this.xpOrbAge++;
      if (this.xpOrbAge >= 6000) {
         this.setDead();
      }

      this.charge++;
      LegacyParticles.spawn(this.world, 
            "snowballpoof",
            this.posX,
            this.posY,
            this.posZ,
            this.rand.nextGaussian(),
            this.rand.nextGaussian(),
            this.rand.nextGaussian()
         );
      if (this.charge % 10 == 0) {
         LegacySounds.playSoundAtEntity(this.world, this, "random.fizz", 1.0F, 1.0F);
      }

      if (this.charge > 100) {
         int x = MathHelper.floor(this.posX);
         int y = MathHelper.floor(this.posY);
         int z = MathHelper.floor(this.posZ);

         for (int X = -5; X < 5; X++) {
            for (int Z = -5; Z < 5; Z++) {
               for (int Y = -5; Y < 5; Y++) {
                  if (LegacyWorld.isBlockNormalCubeDefault(this.world, x + X, y + Y, z + Z, false)
                     && LegacyWorld.getBlock(this.world, x + X, y + Y + 1, z + Z) == Blocks.AIR) {
                     LegacyWorld.setBlock(this.world, x + X, y + Y + 1, z + Z, Blocks.SNOW_LAYER);
                  }
               }
            }
         }

         LegacyWorld.setBlock(this.world, x, y, z - 1, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y, z, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y, z + 1, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y, z + 2, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 1, z - 1, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 2, z - 1, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 3, z - 1, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 4, z - 1, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 1, z + 2, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 2, z + 2, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 3, z + 2, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 4, z + 2, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 4, z, Blocks.PACKED_ICE);
         LegacyWorld.setBlock(this.world, x, y + 4, z + 1, Blocks.PACKED_ICE);
         this.world.setBlockState(new BlockPos(x, y + 1, z), PunMod.portalpun.getDefaultState().withProperty(com.electrifiedded.archaiclib.legacy.world.LegacyPortalBlock.AXIS, EnumFacing.Axis.Z), 2);
         this.world.setBlockState(new BlockPos(x, y + 1, z + 1), PunMod.portalpun.getDefaultState().withProperty(com.electrifiedded.archaiclib.legacy.world.LegacyPortalBlock.AXIS, EnumFacing.Axis.Z), 2);
         this.world.setBlockState(new BlockPos(x, y + 2, z), PunMod.portalpun.getDefaultState().withProperty(com.electrifiedded.archaiclib.legacy.world.LegacyPortalBlock.AXIS, EnumFacing.Axis.Z), 2);
         this.world.setBlockState(new BlockPos(x, y + 2, z + 1), PunMod.portalpun.getDefaultState().withProperty(com.electrifiedded.archaiclib.legacy.world.LegacyPortalBlock.AXIS, EnumFacing.Axis.Z), 2);
         this.world.setBlockState(new BlockPos(x, y + 3, z), PunMod.portalpun.getDefaultState().withProperty(com.electrifiedded.archaiclib.legacy.world.LegacyPortalBlock.AXIS, EnumFacing.Axis.Z), 2);
         this.world.setBlockState(new BlockPos(x, y + 3, z + 1), PunMod.portalpun.getDefaultState().withProperty(com.electrifiedded.archaiclib.legacy.world.LegacyPortalBlock.AXIS, EnumFacing.Axis.Z), 2);
         LegacySounds.playSoundAtEntity(this.world, this, "dig.glass", 1.0F, 1.0F);
         this.isDead = true;
      }

      this.motionX = 0.0;
      this.motionZ = 0.0;
   }

   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
   }
}
