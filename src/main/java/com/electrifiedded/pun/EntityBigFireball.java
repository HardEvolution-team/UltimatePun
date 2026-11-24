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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBigFireball extends EntityFireball {
   public EntityBigFireball(World par1World) {
      super(par1World);
   }

   @SideOnly(Side.CLIENT)
   public EntityBigFireball(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
      super(par1World, par2, par4, par6, par8, par10, par12);
   }

   public EntityBigFireball(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
      super(par1World, par2EntityLivingBase, par3, par5, par7);
      this.setSize(1.0F, 1.0F);
   }

   protected void onImpact(RayTraceResult par1MovingObjectPosition) {
      if (!this.world.isRemote) {
         List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(6.0, 6.0, 6.0));
         if (list != null) {
            for (int k2 = 0; k2 < list.size(); k2++) {
               if (list.get(k2) instanceof EntityLivingBase && !(list.get(k2) instanceof EntityFlamekenstein)) {
                  ((EntityLivingBase)list.get(k2)).setFire(8);
                  ((EntityLivingBase)list.get(k2)).motionY = 0.7 + this.rand.nextInt(10) / 10;
               }
            }
         }
      }

      LegacySounds.playSoundEffect(this.world, 
            this.posX,
            this.posY,
            this.posZ,
            "random.explode",
            4.0F,
            (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F
         );
      LegacyParticles.spawn(this.world, "hugeexplosion", this.posX, this.posY, this.posZ, 1.0, 0.0, 0.0);
      this.setDead();
   }
}
