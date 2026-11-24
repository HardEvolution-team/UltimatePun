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

public class ModelCloud extends ModelBase {
   ModelRenderer cloud1;
   ModelRenderer cloud2;
   ModelRenderer cloud3;
   ModelRenderer cloud4;
   ModelRenderer cloud5;
   ModelRenderer cloud6;
   ModelRenderer cloud7;
   ModelRenderer cloud8;

   public ModelCloud() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.cloud1 = new ModelRenderer(this, 0, 0);
      this.cloud1.addBox(0.0F, 0.0F, 0.0F, 16, 16, 16);
      this.cloud1.setRotationPoint(-2.0F, 2.0F, -23.0F);
      this.cloud1.setTextureSize(64, 32);
      this.cloud1.mirror = true;
      this.setRotation(this.cloud1, 0.0F, 0.0F, 0.0F);
      this.cloud2 = new ModelRenderer(this, 0, 0);
      this.cloud2.addBox(0.0F, 0.0F, 0.0F, 17, 17, 17);
      this.cloud2.setRotationPoint(4.0F, -2.0F, -12.0F);
      this.cloud2.setTextureSize(64, 32);
      this.cloud2.mirror = true;
      this.setRotation(this.cloud2, 0.0F, 0.0F, 0.0F);
      this.cloud3 = new ModelRenderer(this, 0, 0);
      this.cloud3.addBox(0.0F, 0.0F, 0.0F, 15, 15, 15);
      this.cloud3.setRotationPoint(-11.0F, -3.0F, 7.0F);
      this.cloud3.setTextureSize(64, 32);
      this.cloud3.mirror = true;
      this.setRotation(this.cloud3, 0.0F, 0.0F, 0.0F);
      this.cloud4 = new ModelRenderer(this, 0, 0);
      this.cloud4.addBox(0.0F, 0.0F, 0.0F, 14, 14, 14);
      this.cloud4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.cloud4.setTextureSize(64, 32);
      this.cloud4.mirror = true;
      this.setRotation(this.cloud4, 0.0F, 0.0F, 0.0F);
      this.cloud5 = new ModelRenderer(this, 0, 0);
      this.cloud5.addBox(0.0F, 0.0F, 0.0F, 17, 17, 17);
      this.cloud5.setRotationPoint(-22.0F, 0.0F, -1.0F);
      this.cloud5.setTextureSize(64, 32);
      this.cloud5.mirror = true;
      this.setRotation(this.cloud5, 0.0F, 0.0F, 0.0F);
      this.cloud6 = new ModelRenderer(this, 0, 0);
      this.cloud6.addBox(0.0F, 0.0F, 0.0F, 16, 16, 16);
      this.cloud6.setRotationPoint(-24.0F, -2.0F, -14.0F);
      this.cloud6.setTextureSize(64, 32);
      this.cloud6.mirror = true;
      this.setRotation(this.cloud6, 0.0F, 0.0F, 0.0F);
      this.cloud7 = new ModelRenderer(this, 0, 0);
      this.cloud7.addBox(0.0F, 0.0F, 0.0F, 17, 17, 17);
      this.cloud7.setRotationPoint(-16.0F, -4.0F, -19.0F);
      this.cloud7.setTextureSize(64, 32);
      this.cloud7.mirror = true;
      this.setRotation(this.cloud7, 0.0F, 0.0F, 0.0F);
      this.cloud8 = new ModelRenderer(this, 0, 0);
      this.cloud8.addBox(0.0F, 0.0F, 0.0F, 17, 17, 17);
      this.cloud8.setRotationPoint(-8.0F, 4.0F, -9.0F);
      this.cloud8.setTextureSize(64, 32);
      this.cloud8.mirror = true;
      this.setRotation(this.cloud8, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.cloud1.render(f5);
      this.cloud2.render(f5);
      this.cloud3.render(f5);
      this.cloud4.render(f5);
      this.cloud5.render(f5);
      this.cloud6.render(f5);
      this.cloud7.render(f5);
      this.cloud8.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.setRotationAngles(f5, f, f1, f2, f3, f4, entity);
   }
}
