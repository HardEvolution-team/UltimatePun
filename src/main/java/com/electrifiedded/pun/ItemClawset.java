package com.electrifiedded.pun;

import com.google.common.collect.Multimap;
import java.util.List;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 1.7 ItemClawset: a claw weapon that is also a 9-slot portable chest. The contents live in the player's
 * persistent entity data ("ClawsetItems"), like the original, but each player has their own inventory.
 */
public class ItemClawset extends Item {
   public ItemClawset() {
      this.setCreativeTab(PunMod.ThePunMod);
      this.setMaxDamage(250);
      this.setMaxStackSize(1);
      this.setFull3D();
   }

   @Override
   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
      ItemStack stack = player.getHeldItem(hand);
      if (!world.isRemote) {
         final NBTTagCompound data = player.getEntityData();
         InventoryBasic chest = new InventoryBasic("Clawset", true, 9) {
            @Override
            public void markDirty() {
               super.markDirty();
               save(this, data);
            }

            @Override
            public void closeInventory(EntityPlayer p) {
               super.closeInventory(p);
               save(this, data);
            }
         };
         NBTTagList list = data.getTagList("ClawsetItems", 10);
         for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            int slot = tag.getByte("ClawsetSlot") & 255;
            if (slot < chest.getSizeInventory()) {
               chest.setInventorySlotContents(slot, new ItemStack(tag));
            }
         }
         player.displayGUIChest(chest);
      }
      return new ActionResult<>(EnumActionResult.SUCCESS, stack);
   }

   private static void save(InventoryBasic chest, NBTTagCompound data) {
      NBTTagList list = new NBTTagList();
      for (int i = 0; i < chest.getSizeInventory(); i++) {
         ItemStack s = chest.getStackInSlot(i);
         if (!s.isEmpty()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("ClawsetSlot", (byte) i);
            s.writeToNBT(tag);
            list.appendTag(tag);
         }
      }
      data.setTag("ClawsetItems", list);
   }

   @Override
   public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot slot) {
      Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(slot);
      if (slot == EntityEquipmentSlot.MAINHAND) {
         multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 5.0, 0));
      }
      return multimap;
   }

   @Override
   public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean held) {
      super.onUpdate(stack, world, entity, slot, held);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void addInformation(ItemStack stack, World worldIn, List<String> list, ITooltipFlag flagIn) {
      list.add(TextFormatting.GOLD + "The Clawset is a claw and a closet.");
      list.add(TextFormatting.GRAY + "Has " + (this.getMaxDamage() - this.getDamage(stack)) + " uses left");
   }
}
