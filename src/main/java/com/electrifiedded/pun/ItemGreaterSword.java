package com.electrifiedded.pun;

import net.minecraft.world.World;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextFormatting;

public class ItemGreaterSword extends ItemSword {
   Entity entity;

   public ItemGreaterSword() {
      super(ToolMaterial.WOOD);
      this.setMaxDamage(300);
      this.setMaxStackSize(1);
      this.setCreativeTab(PunMod.ThePunMod);
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
      this.entity = par2EntityLivingBase;
      if (this.entity instanceof EntityCow) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.BEEF)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntityPig) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.PORKCHOP)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntityChicken) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.CHICKEN)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntityZombie) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.ROTTEN_FLESH)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntitySkeleton) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.DYE, 1, 15)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntitySlime) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.SLIME_BALL)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntityBlaze) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.BLAZE_POWDER)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntitySheep) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.STRING)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntityCreeper) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Items.GUNPOWDER)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntityBat) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(PunMod.batfur)
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntitySandWitch) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Item.getItemFromBlock(Blocks.SAND))
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      if (this.entity instanceof EntityColdKiller) {
         EntityItem item = new EntityItem(
            par2EntityLivingBase.world,
            par2EntityLivingBase.posX,
            par2EntityLivingBase.posY,
            par2EntityLivingBase.posZ,
            new ItemStack(Item.getItemFromBlock(Blocks.PACKED_ICE))
         );
         par2EntityLivingBase.world.spawnEntity(item);
         LegacyParticles.spawn(par2EntityLivingBase.world, "portal", par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, 0.0, 0.0, 0.0);
      }

      return true;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.GRAY + "This Great sword will Grate anything off entities");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
