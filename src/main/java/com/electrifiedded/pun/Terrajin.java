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

public class Terrajin extends ModelBase {
   ModelRenderer Head;
   ModelRenderer Body;
   ModelRenderer tail_big;
   ModelRenderer tail_med;
   ModelRenderer tail_small;
   ModelRenderer Fin1;
   ModelRenderer Fin2;
   ModelRenderer Spike_Big;
   ModelRenderer spike1;
   ModelRenderer spike2;

   public Terrajin() {
      this.textureWidth = 128;
      this.textureHeight = 128;
      this.Head = new ModelRenderer(this, 39, 0);
      this.Head.addBox(0.0F, 0.0F, 0.0F, 6, 4, 6);
      this.Head.setRotationPoint(-3.0F, -4.0F, 1.0F);
      this.Head.setTextureSize(128, 128);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Body = new ModelRenderer(this, 0, 0);
      this.Body.addBox(0.0F, 0.0F, 0.0F, 10, 12, 8);
      this.Body.setRotationPoint(-5.0F, 0.0F, 0.0F);
      this.Body.setTextureSize(128, 128);
      this.Body.mirror = true;
      this.setRotation(this.Body, 0.0F, 0.0F, 0.0F);
      this.tail_big = new ModelRenderer(this, 0, 0);
      this.tail_big.addBox(0.0F, 0.0F, 0.0F, 6, 3, 6);
      this.tail_big.setRotationPoint(-3.0F, 12.0F, 1.0F);
      this.tail_big.setTextureSize(128, 128);
      this.tail_big.mirror = true;
      this.setRotation(this.tail_big, 0.0F, 0.0F, 0.0F);
      this.tail_med = new ModelRenderer(this, 0, 0);
      this.tail_med.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3);
      this.tail_med.setRotationPoint(-2.0F, 15.0F, 2.0F);
      this.tail_med.setTextureSize(128, 128);
      this.tail_med.mirror = true;
      this.setRotation(this.tail_med, 0.0F, 0.0F, 0.0F);
      this.tail_small = new ModelRenderer(this, 0, 0);
      this.tail_small.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1);
      this.tail_small.setRotationPoint(-1.0F, 17.0F, 3.0F);
      this.tail_small.setTextureSize(128, 128);
      this.tail_small.mirror = true;
      this.setRotation(this.tail_small, 0.0F, 0.0F, 0.0F);
      this.Fin1 = new ModelRenderer(this, 0, 0);
      this.Fin1.addBox(0.0F, 0.0F, 0.0F, 13, 4, 1);
      this.Fin1.setRotationPoint(-10.5F, 12.0F, 3.0F);
      this.Fin1.setTextureSize(128, 128);
      this.Fin1.mirror = true;
      this.setRotation(this.Fin1, 0.0F, 0.0F, -1.22173F);
      this.Fin2 = new ModelRenderer(this, 0, 0);
      this.Fin2.addBox(0.0F, 0.0F, 0.0F, 13, 4, 1);
      this.Fin2.setRotationPoint(6.0F, 0.0F, 3.0F);
      this.Fin2.setTextureSize(128, 128);
      this.Fin2.mirror = true;
      this.setRotation(this.Fin2, 0.0F, 0.0F, 1.22173F);
      this.Spike_Big = new ModelRenderer(this, 0, 0);
      this.Spike_Big.addBox(0.0F, 0.0F, 0.0F, 4, 1, 3);
      this.Spike_Big.setRotationPoint(-2.0F, -5.0F, 3.0F);
      this.Spike_Big.setTextureSize(128, 128);
      this.Spike_Big.mirror = true;
      this.setRotation(this.Spike_Big, 0.0F, 0.0F, 0.0F);
      this.spike1 = new ModelRenderer(this, 0, 0);
      this.spike1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.spike1.setRotationPoint(2.0F, -4.0F, 4.0F);
      this.spike1.setTextureSize(128, 128);
      this.spike1.mirror = true;
      this.setRotation(this.spike1, 0.0174533F, 0.0F, -2.617994F);
      this.spike2 = new ModelRenderer(this, 0, 0);
      this.spike2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.spike2.setRotationPoint(-1.0F, -4.5F, 4.0F);
      this.spike2.setTextureSize(128, 128);
      this.spike2.mirror = true;
      this.setRotation(this.spike2, 0.0174533F, 0.0F, 2.617994F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Head.render(f5);
      this.Body.render(f5);
      this.tail_big.render(f5);
      this.tail_med.render(f5);
      this.tail_small.render(f5);
      this.Fin1.render(f5);
      this.Fin2.render(f5);
      this.Spike_Big.render(f5);
      this.spike1.render(f5);
      this.spike2.render(f5);
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
