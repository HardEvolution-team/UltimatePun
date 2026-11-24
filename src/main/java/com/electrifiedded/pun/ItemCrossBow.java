package com.electrifiedded.pun;
import net.minecraft.entity.EntityLivingBase;

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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import com.electrifiedded.archaiclib.v1710.api.client.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemCrossBow extends ItemBow {
   private IIcon[] iconArray;
   public static final String[] cbowPullIconNameArray = new String[]{"pulling_0", "pulling_1", "pulling_2"};

   public ItemCrossBow() {
      this.maxStackSize = 1;
      this.setMaxDamage(300);
      this.setCreativeTab(PunMod.ThePunMod);
      this.setFull3D();
   }

   public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityLivingBase living, int par4) {
      if (!(living instanceof EntityPlayer)) return;
      EntityPlayer par3EntityPlayer = (EntityPlayer)living;
      int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
      EntityGold entitygold = new EntityGold(par2World, par3EntityPlayer);
      entitygold.setVelocity(j);
      j = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(par1ItemStack, par2World, par3EntityPlayer, j, true);
      if (j >= 0) {
         float f = j / 20.0F;
         f = (f * f + f * 2.0F) / 3.0F;
         if (!(f < 0.1)) {
            if (f > 1.0F) {
               f = 1.0F;
            }

            if (f == 1.0F) {
               entitygold.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.POWER, par1ItemStack);
            if (k > 0) {
               entitygold.setPower(k);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.PUNCH, par1ItemStack);
            if (l > 0) {
               entitygold.punch(true);
            }

            if (EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FLAME, par1ItemStack) > 0) {
               entitygold.setFire(true);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            LegacySounds.playSoundAtEntity(par2World, par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if (!par2World.isRemote) {
               par2World.spawnEntity(entitygold);
            }
         }
      }
   }

   public ActionResult<ItemStack> onItemRightClick(World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
      ItemStack par1ItemStack = par3EntityPlayer.getHeldItem(hand);
      ActionResult<ItemStack> nock = net.minecraftforge.event.ForgeEventFactory.onArrowNock(par1ItemStack, par2World, par3EntityPlayer, hand, true);
      if (nock != null) return nock;
      par3EntityPlayer.setActiveHand(hand);
      return new ActionResult<>(EnumActionResult.SUCCESS, par1ItemStack);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, World worldIn, List<String> par3List, ITooltipFlag flagIn) {
      par3List.add(TextFormatting.YELLOW + "The real crossbow");
      par3List.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(par1ItemStack)) + " uses left");
   }
}
