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
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class Frostbiter extends ModelBase {
   ModelRenderer bottommouth;
   ModelRenderer topmouth;
   ModelRenderer leg1;
   ModelRenderer leg2;
   ModelRenderer leg3;
   ModelRenderer leg4;

   public Frostbiter() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.bottommouth = new ModelRenderer(this, 0, 0);
      this.bottommouth.addBox(0.0F, 0.0F, 0.0F, 14, 14, 2);
      this.bottommouth.setRotationPoint(-7.0F, 20.0F, 8.0F);
      this.bottommouth.setTextureSize(64, 64);
      this.bottommouth.mirror = true;
      this.setRotation(this.bottommouth, -1.570796F, 0.0F, 0.0F);
      this.topmouth = new ModelRenderer(this, 32, 0);
      this.topmouth.addBox(0.0F, 0.0F, 0.0F, 14, 14, 2);
      this.topmouth.setRotationPoint(-7.0F, 11.0F, 5.0F);
      this.topmouth.setTextureSize(64, 64);
      this.topmouth.mirror = true;
      this.setRotation(this.topmouth, -1.003822F, 0.0F, 0.0F);
      this.leg1 = new ModelRenderer(this, 16, 0);
      this.leg1.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3);
      this.leg1.setRotationPoint(5.0F, 21.0F, -5.0F);
      this.leg1.setTextureSize(64, 64);
      this.leg1.mirror = true;
      this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
      this.leg2 = new ModelRenderer(this, 16, 0);
      this.leg2.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3);
      this.leg2.setRotationPoint(-10.0F, 21.0F, 3.0F);
      this.leg2.setTextureSize(64, 64);
      this.leg2.mirror = true;
      this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
      this.leg3 = new ModelRenderer(this, 16, 0);
      this.leg3.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3);
      this.leg3.setRotationPoint(5.0F, 21.0F, 3.0F);
      this.leg3.setTextureSize(64, 64);
      this.leg3.mirror = true;
      this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
      this.leg4 = new ModelRenderer(this, 16, 0);
      this.leg4.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3);
      this.leg4.setRotationPoint(-10.0F, 21.0F, -5.0F);
      this.leg4.setTextureSize(64, 64);
      this.leg4.mirror = true;
      this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.bottommouth.render(f5);
      this.topmouth.render(f5);
      this.leg1.render(f5);
      this.leg2.render(f5);
      this.leg3.render(f5);
      this.leg4.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float par1, float par2, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(par1, par2, f2, f3, f4, f5, entity);
      this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
      this.leg1.rotateAngleY = 0.0F;
      this.leg2.rotateAngleY = 0.0F;
      this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
      this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
      this.leg3.rotateAngleY = 0.0F;
      this.leg4.rotateAngleY = 0.0F;
   }
}
