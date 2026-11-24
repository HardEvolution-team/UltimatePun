package com.electrifiedded.pun;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEquipment;

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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityHSkeleton extends EntitySkeleton {
   public EntityHSkeleton(World par1World) {
      super(par1World);
      this.setSize(1.3F, 2.4F);
      this.experienceValue = 10;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0);
   }

   protected void dropFewItems(boolean par1, int par2) {
      if (false) {
         int j = this.rand.nextInt(3 + par2) - 1;

         for (int k = 0; k < j; k++) {
            this.dropItem(Items.COAL, 1);
         }
      } else {
         int j = this.rand.nextInt(3 + par2);

         for (int k = 0; k < j; k++) {
            this.dropItem(Items.ARROW, 1);
         }
      }

      int var6 = this.rand.nextInt(3 + par2);

      for (int var8 = -1; var8 < var6; var8++) {
         this.dropItem(Items.BONE, 3);
      }
   }

   @Override
   protected void setEquipmentBasedOnDifficulty(net.minecraft.world.DifficultyInstance difficulty) {
      ItemStack bow = new ItemStack(Items.BOW);
      bow.addEnchantment(net.minecraft.init.Enchantments.POWER, 1);
      bow.addEnchantment(net.minecraft.init.Enchantments.PUNCH, 1);
      super.setEquipmentBasedOnDifficulty(difficulty);
      LegacyEquipment.set(this, 0, bow);
   }

   /** 1.7 drops replace the vanilla ones: no 1.12 loot table, so dropFewItems runs. */
   @Override
   protected net.minecraft.util.ResourceLocation getLootTable() {
      return null;
   }
}
