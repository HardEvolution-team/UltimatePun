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

public class ModelIcyYou extends ModelBase {
   ModelRenderer Eyeball;
   ModelRenderer Eyepart1;
   ModelRenderer Eyepart2;
   ModelRenderer Eyepart3;
   ModelRenderer Crystal1;
   ModelRenderer Crystal2;
   ModelRenderer Crystal3;
   ModelRenderer Crystal4;
   ModelRenderer Crystal5;
   ModelRenderer Crystal6;
   ModelRenderer Crystal7;
   ModelRenderer InnerEye1;
   ModelRenderer InnerEye2;

   public ModelIcyYou() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.Eyeball = new ModelRenderer(this, 0, 0);
      this.Eyeball.addBox(0.0F, 0.0F, 0.0F, 24, 24, 24);
      this.Eyeball.setRotationPoint(-12.0F, -3.0F, -12.0F);
      this.Eyeball.setTextureSize(256, 128);
      this.Eyeball.mirror = true;
      this.setRotation(this.Eyeball, 0.0F, 0.0F, 0.0F);
      this.Eyepart1 = new ModelRenderer(this, 150, 0);
      this.Eyepart1.addBox(0.0F, 0.0F, 0.0F, 28, 20, 20);
      this.Eyepart1.setRotationPoint(-14.0F, -1.0F, -10.0F);
      this.Eyepart1.setTextureSize(256, 128);
      this.Eyepart1.mirror = true;
      this.setRotation(this.Eyepart1, 0.0F, 0.0F, 0.0F);
      this.Eyepart2 = new ModelRenderer(this, 0, 0);
      this.Eyepart2.addBox(0.0F, 0.0F, 0.0F, 20, 20, 28);
      this.Eyepart2.setRotationPoint(-10.0F, -1.0F, -14.0F);
      this.Eyepart2.setTextureSize(256, 128);
      this.Eyepart2.mirror = true;
      this.setRotation(this.Eyepart2, 0.0F, 0.0F, 0.0F);
      this.Eyepart3 = new ModelRenderer(this, 0, 0);
      this.Eyepart3.addBox(0.0F, 0.0F, 0.0F, 20, 28, 20);
      this.Eyepart3.setRotationPoint(-10.0F, -5.0F, -10.0F);
      this.Eyepart3.setTextureSize(256, 128);
      this.Eyepart3.mirror = true;
      this.setRotation(this.Eyepart3, 0.0F, 0.0F, 0.0F);
      this.Crystal1 = new ModelRenderer(this, 0, 0);
      this.Crystal1.addBox(0.0F, 0.0F, 0.0F, 12, 6, 12);
      this.Crystal1.setRotationPoint(-3.0F, 0.0F, 17.0F);
      this.Crystal1.setTextureSize(256, 128);
      this.Crystal1.mirror = true;
      this.setRotation(this.Crystal1, -1.115358F, 1.152537F, -0.5205006F);
      this.Crystal2 = new ModelRenderer(this, 0, 0);
      this.Crystal2.addBox(0.0F, 0.0F, 0.0F, 13, 12, 12);
      this.Crystal2.setRotationPoint(0.0F, 2.8F, 3.0F);
      this.Crystal2.setTextureSize(256, 128);
      this.Crystal2.mirror = true;
      this.setRotation(this.Crystal2, -0.1858931F, 0.2602503F, 0.0F);
      this.Crystal3 = new ModelRenderer(this, 0, 0);
      this.Crystal3.addBox(0.0F, 0.0F, 0.0F, 13, 12, 14);
      this.Crystal3.setRotationPoint(0.0F, -5.0F, -11.0F);
      this.Crystal3.setTextureSize(256, 128);
      this.Crystal3.mirror = true;
      this.setRotation(this.Crystal3, -0.6320364F, -0.4461433F, 0.1115358F);
      this.Crystal4 = new ModelRenderer(this, 0, 0);
      this.Crystal4.addBox(0.0F, 0.0F, 0.0F, 13, 10, 13);
      this.Crystal4.setRotationPoint(1.0F, 7.0F, -8.533334F);
      this.Crystal4.setTextureSize(256, 128);
      this.Crystal4.mirror = true;
      this.setRotation(this.Crystal4, 0.5576792F, 0.2602503F, 0.4833219F);
      this.Crystal5 = new ModelRenderer(this, 0, 0);
      this.Crystal5.addBox(0.0F, 0.0F, 0.0F, 14, 14, 14);
      this.Crystal5.setRotationPoint(-10.0F, 7.0F, -2.0F);
      this.Crystal5.setTextureSize(256, 128);
      this.Crystal5.mirror = true;
      this.setRotation(this.Crystal5, 0.5576792F, 0.7063936F, -0.9666439F);
      this.Crystal6 = new ModelRenderer(this, 0, 0);
      this.Crystal6.addBox(0.0F, 0.0F, 0.0F, 9, 9, 7);
      this.Crystal6.setRotationPoint(-5.8F, 7.0F, -11.0F);
      this.Crystal6.setTextureSize(256, 128);
      this.Crystal6.mirror = true;
      this.setRotation(this.Crystal6, -0.4461433F, -0.5948578F, 0.5576792F);
      this.Crystal7 = new ModelRenderer(this, 0, 0);
      this.Crystal7.addBox(0.0F, 0.0F, 0.0F, 9, 10, 13);
      this.Crystal7.setRotationPoint(2.0F, 10.0F, 0.0F);
      this.Crystal7.setTextureSize(256, 128);
      this.Crystal7.mirror = true;
      this.setRotation(this.Crystal7, 0.4461433F, -0.5205006F, 0.9294653F);
      this.InnerEye1 = new ModelRenderer(this, 151, 3);
      this.InnerEye1.addBox(0.0F, 0.0F, 0.0F, 18, 18, 18);
      this.InnerEye1.setRotationPoint(-16.0F, 0.0F, -9.0F);
      this.InnerEye1.setTextureSize(256, 128);
      this.InnerEye1.mirror = true;
      this.setRotation(this.InnerEye1, 0.0F, 0.0F, 0.0221075F);
      this.InnerEye2 = new ModelRenderer(this, 153, 9);
      this.InnerEye2.addBox(0.0F, 0.0F, 0.0F, 14, 14, 14);
      this.InnerEye2.setRotationPoint(-18.0F, 2.0F, -7.0F);
      this.InnerEye2.setTextureSize(256, 128);
      this.InnerEye2.mirror = true;
      this.setRotation(this.InnerEye2, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Eyeball.render(f5);
      this.Eyepart1.render(f5);
      this.Eyepart2.render(f5);
      this.Eyepart3.render(f5);
      this.Crystal1.render(f5);
      this.Crystal2.render(f5);
      this.Crystal3.render(f5);
      this.Crystal4.render(f5);
      this.Crystal5.render(f5);
      this.Crystal6.render(f5);
      this.Crystal7.render(f5);
      this.InnerEye1.render(f5);
      this.InnerEye2.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }
}
