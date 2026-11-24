package com.electrifiedded.pun;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Shared base for the 1.7 RenderSandy / RenderSquidBomb / RenderIceOrb / RenderBlackHole: a ModelBase drawn at the
 * entity position with fixed animation args. (The 1.7 classes translated twice by the camera offset, which drew
 * them in the wrong place; this draws them where the entity is.)
 */
public class RenderModelEntity<T extends Entity> extends Render<T> {
   protected final ModelBase model;
   protected final ResourceLocation texture;
   protected final float yOffset;
   protected final float[] args;

   public RenderModelEntity(RenderManager rm, ModelBase model, ResourceLocation texture, float yOffset, float... args) {
      super(rm);
      this.model = model;
      this.texture = texture;
      this.yOffset = yOffset;
      this.args = args;
   }

   @Override
   public void doRender(T e, double x, double y, double z, float yaw, float partial) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float) x, (float) y + this.yOffset, (float) z);
      this.bindEntityTexture(e);
      this.model.render(e, this.args[0], this.args[1], this.args[2], this.args[3], this.args[4], 0.0625F);
      GlStateManager.popMatrix();
      super.doRender(e, x, y, z, yaw, partial);
   }

   @Override
   protected ResourceLocation getEntityTexture(T e) {
      return this.texture;
   }
}
