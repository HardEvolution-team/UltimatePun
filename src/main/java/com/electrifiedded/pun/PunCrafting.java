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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PunCrafting {
   public static void addWeaponsRecipes() {
      ItemStack sword = new ItemStack(PunMod.holeysword);
      sword.setItemDamage(750);
      LegacyRecipes.addRecipe(sword, new Object[]{"S", "S", "s", 'S', Blocks.SPONGE, 's', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.piecemaker), new Object[]{"sss", "sPs", "sss", 'P', Items.DIAMOND_SWORD, 's', Items.FEATHER});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.panemaker), new Object[]{"PPP", "PPP", " s ", 'P', Blocks.GLASS_PANE, 's', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.eggsterminator), new Object[]{"III", "F  ", "III", 'I', Items.IRON_INGOT, 'F', Items.FEATHER});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.crossbow), new Object[]{" G ", "GGG", " G ", 'G', Items.GOLD_INGOT});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.greatersword), new Object[]{"III", "III", " S ", 'I', Items.IRON_INGOT, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.batsword), new Object[]{"I", "I", "S", 'I', PunMod.batiron, 'S', Items.STICK});
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.flyingpan), new Object[]{"III", "IRI", " S ", 'I', Items.IRON_INGOT, 'S', Items.STICK, 'R', Items.REDSTONE}
      );
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.mintser), new Object[]{"MIM", "MIM", "MSM", 'I', Items.IRON_INGOT, 'S', Items.STICK, 'M', PunMod.mintleaf}
      );
      LegacyRecipes.addRecipe(new ItemStack(PunMod.spearmint, 3), new Object[]{"M", "M", "I", 'I', Items.IRON_INGOT, 'M', PunMod.mintleaf});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.stealknife, 3), new Object[]{"  I", " I ", "S  ", 'I', Items.IRON_INGOT, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.bown), new Object[]{"bBb", "bBb", "bbb", 'B', Items.BOW, 'b', Items.BONE});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.obbow), new Object[]{"bbb", "bBb", "bbb", 'B', Items.BOW, 'b', PunMod.obsidianshards});
      LegacyRecipes.addShapelessRecipe(
         new ItemStack(PunMod.punisher),
         new Object[]{
            PunMod.osword,
            PunMod.edyesword,
            PunMod.ndyesword,
            PunMod.osword,
            PunMod.edyesword,
            PunMod.ndyesword,
            PunMod.osword,
            PunMod.edyesword,
            PunMod.ndyesword
         }
      );
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.rainbow),
         new Object[]{
            " dr",
            "iBe",
            "lg ",
            'B',
            Items.BOW,
            'd',
            Items.DIAMOND,
            'r',
            Items.REDSTONE,
            'e',
            Items.EMERALD,
            'g',
            Items.GOLD_INGOT,
            'l',
            new ItemStack(Items.DYE, 1, 4),
            'i',
            Items.IRON_INGOT
         }
      );
      LegacyRecipes.addRecipe(new ItemStack(PunMod.hammer), new Object[]{"HHH", " S ", " S ", 'S', Items.STICK, 'H', Items.COOKED_PORKCHOP});
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.canink),
         new Object[]{"ww ", "www", " wI", 'w', Item.getItemFromBlock(Blocks.WATER), 'I', new ItemStack(Items.DYE, 1, 0)}
      );
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.tentacannon), new Object[]{PunMod.canink, PunMod.rainbow});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.flamethwooler), new Object[]{"III", "F  ", "III", 'F', Items.FLINT_AND_STEEL, 'I', Items.IRON_INGOT});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.porkchopper), new Object[]{"FFF", "FFF", " I ", 'F', Items.PORKCHOP, 'I', Items.STICK});
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.beddersword), new Object[]{"SFS", "SFS", "SIS", 'F', Blocks.BED, 'I', Items.STICK, 'S', PunMod.morningstar}
      );
      LegacyRecipes.addRecipe(new ItemStack(PunMod.clawset), new Object[]{"III", "III", " C ", 'C', Blocks.CHEST, 'I', Items.IRON_INGOT});
   }

   public static void otherRecipes() {
      LegacyRecipes.addShapelessRecipe(
         new ItemStack(Blocks.SPONGE), new Object[]{Blocks.MOSSY_COBBLESTONE, Items.FISH, Items.FISH, Items.WATER_BUCKET}
      );
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.batiron), new Object[]{Items.IRON_INGOT, Items.IRON_INGOT, PunMod.batfur});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.mitestone), new Object[]{"SSS", "SSS", "SSS", 'S', Blocks.STONEBRICK});
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.fleebag), new Object[]{"LgL", "LRL", "LLL", 'L', Items.LEATHER, 'R', Items.REDSTONE, 'g', Items.GOLD_NUGGET}
      );
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.morningstar), new Object[]{Blocks.WOOL, Items.FEATHER, Items.FEATHER});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.ublock), new Object[]{Blocks.DIAMOND_BLOCK, Blocks.GOLD_BLOCK, Blocks.EMERALD_BLOCK});
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.soulstand), new Object[]{"s", "S", "S", 'S', Blocks.SOUL_SAND, 's', new ItemStack(Items.SKULL, 1, 1)}
      );
      GameRegistry.addSmelting(new ItemStack(Items.EGG), new ItemStack(PunMod.hardegg, 3), 0.5F);
      LegacyRecipes.addShapelessRecipe(
         new ItemStack(PunMod.dyemond),
         new Object[]{
            Items.DIAMOND,
            Items.EMERALD,
            new ItemStack(Items.DYE, 1, 1),
            new ItemStack(Items.DYE, 1, 3),
            new ItemStack(Items.DYE, 1, 5),
            new ItemStack(Items.DYE, 1, 11)
         }
      );
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.drop, 32), new Object[]{Items.GOLD_INGOT, Blocks.WATER});
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.sandorcery), new Object[]{"HRH", "HSH", "HSH", 'S', Items.STICK, 'H', Blocks.SAND, 'R', Items.REDSTONE}
      );
      LegacyRecipes.addShapelessRecipe(
         new ItemStack(PunMod.icebrick, 2), new Object[]{Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.PACKED_ICE, Blocks.PACKED_ICE}
      );
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.glowice), new Object[]{Blocks.ICE, Items.GLOWSTONE_DUST});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.icestairs, 6), new Object[]{"i  ", "ii ", "iii", 'i', Blocks.PACKED_ICE});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.icestairs, 6), new Object[]{"  i", " ii", "iii", 'i', Blocks.PACKED_ICE});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.frenchfrice, 1), new Object[]{PunMod.icespike});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.corepple), new Object[]{"ooo", "oao", "ooo", 'a', Items.APPLE, 'o', Items.COAL});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.iorepple), new Object[]{"ooo", "oao", "ooo", 'a', Items.APPLE, 'o', Items.IRON_INGOT});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.borepple), new Object[]{"ooo", "oao", "ooo", 'a', Items.APPLE, 'o', PunMod.batiron});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dorepple), new Object[]{"ooo", "oao", "ooo", 'a', Items.APPLE, 'o', Items.DIAMOND});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.eorepple), new Object[]{"ooo", "oao", "ooo", 'a', Items.APPLE, 'o', Items.EMERALD});
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.lorepple), new Object[]{"ooo", "oao", "ooo", 'a', Items.APPLE, 'o', new ItemStack(Items.DYE, 1, 4)}
      );
      LegacyRecipes.addRecipe(new ItemStack(PunMod.rorepple), new Object[]{"ooo", "oao", "ooo", 'a', Items.APPLE, 'o', Items.REDSTONE});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dyorepple), new Object[]{"ooo", "oao", "ooo", 'a', Items.APPLE, 'o', PunMod.dyemond});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.iceorb), new Object[]{" d ", "ddd", " d ", 'd', Items.DIAMOND});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.fleshlight, 2), new Object[]{"I", "C", 'C', Items.STICK, 'I', Items.ROTTEN_FLESH});
   }

   public static void armorRecipes() {
      LegacyRecipes.addRecipe(new ItemStack(PunMod.batboots), new Object[]{"I I", "I I", 'I', PunMod.batiron});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.bathelmet), new Object[]{"III", "I I", 'I', PunMod.batiron});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.batchest), new Object[]{"I I", "III", "III", 'I', PunMod.batiron});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.batlegs), new Object[]{"III", "I I", "I I", 'I', PunMod.batiron});
      ItemStack o = new ItemStack(PunMod.dyeboots);
      Item x = o.getItem();
      x.setCreativeTab(PunMod.ThePunMod);
      ItemStack e = new ItemStack(PunMod.dyechest);
      x = e.getItem();
      x.setCreativeTab(PunMod.ThePunMod);
      ItemStack r = new ItemStack(PunMod.dyelegs);
      x = r.getItem();
      x.setCreativeTab(PunMod.ThePunMod);
      ItemStack t = new ItemStack(PunMod.dyehelmet);
      x = t.getItem();
      x.setCreativeTab(PunMod.ThePunMod);
      LegacyRecipes.addRecipe(o, new Object[]{"I I", "I I", 'I', PunMod.dyemond});
      LegacyRecipes.addRecipe(t, new Object[]{"III", "I I", 'I', PunMod.dyemond});
      LegacyRecipes.addRecipe(e, new Object[]{"I I", "III", "III", 'I', PunMod.dyemond});
      LegacyRecipes.addRecipe(r, new Object[]{"III", "I I", "I I", 'I', PunMod.dyemond});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeboots), new Object[]{PunMod.ndyeboots, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeboots), new Object[]{PunMod.edyeboots, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeboots), new Object[]{PunMod.odyeboots, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyehelmet), new Object[]{PunMod.ndyehelmet, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyehelmet), new Object[]{PunMod.edyehelmet, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyehelmet), new Object[]{PunMod.odyehelmet, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyechest), new Object[]{PunMod.ndyechest, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyechest), new Object[]{PunMod.edyechest, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyechest), new Object[]{PunMod.odyechest, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyelegs), new Object[]{PunMod.ndyelegs, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyelegs), new Object[]{PunMod.edyelegs, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyelegs), new Object[]{PunMod.odyelegs, Blocks.DIRT});
      ItemStack boots = new ItemStack(PunMod.ndyeboots);
      boots.addEnchantment(net.minecraft.init.Enchantments.FEATHER_FALLING, 1);
      boots.addEnchantment(net.minecraft.init.Enchantments.FIRE_PROTECTION, 4);
      ItemStack legs = new ItemStack(PunMod.ndyelegs);
      legs.addEnchantment(net.minecraft.init.Enchantments.FIRE_PROTECTION, 4);
      ItemStack chest = new ItemStack(PunMod.ndyechest);
      chest.addEnchantment(net.minecraft.init.Enchantments.THORNS, 3);
      chest.addEnchantment(net.minecraft.init.Enchantments.FIRE_PROTECTION, 4);
      ItemStack helmet = new ItemStack(PunMod.ndyehelmet);
      helmet.addEnchantment(net.minecraft.init.Enchantments.FIRE_PROTECTION, 4);
      LegacyRecipes.addShapelessRecipe(boots, new Object[]{PunMod.dyeboots, Blocks.NETHER_BRICK});
      LegacyRecipes.addShapelessRecipe(legs, new Object[]{PunMod.dyelegs, Blocks.NETHER_BRICK});
      LegacyRecipes.addShapelessRecipe(chest, new Object[]{PunMod.dyechest, Blocks.NETHER_BRICK});
      LegacyRecipes.addShapelessRecipe(helmet, new Object[]{PunMod.dyehelmet, Blocks.NETHER_BRICK});
      boots = new ItemStack(PunMod.edyeboots);
      boots.addEnchantment(net.minecraft.init.Enchantments.FEATHER_FALLING, 4);
      legs = new ItemStack(PunMod.edyelegs);
      legs.addEnchantment(net.minecraft.init.Enchantments.BLAST_PROTECTION, 2);
      legs.addEnchantment(net.minecraft.init.Enchantments.PROTECTION, 2);
      chest = new ItemStack(PunMod.edyechest);
      chest.addEnchantment(net.minecraft.init.Enchantments.PROTECTION, 4);
      helmet = new ItemStack(PunMod.edyehelmet);
      helmet.addEnchantment(net.minecraft.init.Enchantments.PROTECTION, 2);
      helmet.addEnchantment(net.minecraft.init.Enchantments.BLAST_PROTECTION, 2);
      LegacyRecipes.addShapelessRecipe(boots, new Object[]{PunMod.dyeboots, Blocks.END_STONE});
      LegacyRecipes.addShapelessRecipe(legs, new Object[]{PunMod.dyelegs, Blocks.END_STONE});
      LegacyRecipes.addShapelessRecipe(chest, new Object[]{PunMod.dyechest, Blocks.END_STONE});
      LegacyRecipes.addShapelessRecipe(helmet, new Object[]{PunMod.dyehelmet, Blocks.END_STONE});
      boots = new ItemStack(PunMod.odyeboots);
      boots.addEnchantment(net.minecraft.init.Enchantments.FEATHER_FALLING, 4);
      legs = new ItemStack(PunMod.odyelegs);
      legs.addEnchantment(net.minecraft.init.Enchantments.PROTECTION, 4);
      chest = new ItemStack(PunMod.odyechest);
      chest.addEnchantment(net.minecraft.init.Enchantments.PROTECTION, 4);
      helmet = new ItemStack(PunMod.odyehelmet);
      helmet.addEnchantment(net.minecraft.init.Enchantments.PROTECTION, 2);
      helmet.addEnchantment(net.minecraft.init.Enchantments.PROJECTILE_PROTECTION, 2);
      LegacyRecipes.addShapelessRecipe(boots, new Object[]{PunMod.dyeboots, Blocks.OBSIDIAN});
      LegacyRecipes.addShapelessRecipe(legs, new Object[]{PunMod.dyelegs, Blocks.OBSIDIAN});
      LegacyRecipes.addShapelessRecipe(chest, new Object[]{PunMod.dyechest, Blocks.OBSIDIAN});
      LegacyRecipes.addShapelessRecipe(helmet, new Object[]{PunMod.dyehelmet, Blocks.OBSIDIAN});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.oboots), new Object[]{"I I", "I I", 'I', PunMod.obsidianshards});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.ohelm), new Object[]{"III", "I I", 'I', PunMod.obsidianshards});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.ochest), new Object[]{"I I", "III", "III", 'I', PunMod.obsidianshards});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.olegs), new Object[]{"III", "I I", "I I", 'I', PunMod.obsidianshards});
   }

   public static void toolRecipes() {
      LegacyRecipes.addRecipe(new ItemStack(PunMod.bataxe), new Object[]{"II", "SI", "S ", 'I', PunMod.batiron, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.bataxe), new Object[]{"II", "IS", " S", 'I', PunMod.batiron, 'S', Items.STICK});
      ItemStack hoe = new ItemStack(PunMod.bathoe);
      LegacyRecipes.addRecipe(hoe, new Object[]{"II", "S ", "S ", 'I', PunMod.batiron, 'S', Items.STICK});
      LegacyRecipes.addRecipe(hoe, new Object[]{"II", " S", " S", 'I', PunMod.batiron, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.batpick), new Object[]{"III", " S ", " S ", 'I', PunMod.batiron, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.batshovel), new Object[]{"I", "S", "S", 'I', PunMod.batiron, 'S', Items.STICK});
      LegacyRecipes.addRecipe(
         new ItemStack(PunMod.breakfast), new Object[]{"IEI", " S ", " S ", 'I', Items.PORKCHOP, 'S', Items.STICK, 'E', Items.EGG}
      );
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dyeaxe), new Object[]{"II", "SI", "S ", 'I', PunMod.dyemond, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dyeaxe), new Object[]{"II", "IS", " S", 'I', PunMod.dyemond, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dyehoe), new Object[]{"II", "S ", "S ", 'I', PunMod.dyemond, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dyehoe), new Object[]{"II", " S", " S", 'I', PunMod.dyemond, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dyepick), new Object[]{"III", " S ", " S ", 'I', PunMod.dyemond, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dyeshovel), new Object[]{"I", "S", "S", 'I', PunMod.dyemond, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.dyesword), new Object[]{"I", "I", "S", 'I', PunMod.dyemond, 'S', Items.STICK});
      ItemStack axe = new ItemStack(PunMod.ndyeaxe);
      axe.addEnchantment(net.minecraft.init.Enchantments.FIRE_ASPECT, 9);
      Item a = axe.getItem();
      a.setCreativeTab(PunMod.ThePunMod);
      ItemStack dhoe = new ItemStack(PunMod.ndyehoe);
      dhoe.addEnchantment(net.minecraft.init.Enchantments.FIRE_ASPECT, 6);
      a = dhoe.getItem();
      a.setCreativeTab(PunMod.ThePunMod);
      ItemStack pick = new ItemStack(PunMod.ndyepick);
      pick.addEnchantment(net.minecraft.init.Enchantments.FIRE_ASPECT, 8);
      a = pick.getItem();
      a.setCreativeTab(PunMod.ThePunMod);
      ItemStack sword = new ItemStack(PunMod.ndyesword);
      sword.addEnchantment(net.minecraft.init.Enchantments.FIRE_ASPECT, 10);
      a = sword.getItem();
      a.setCreativeTab(PunMod.ThePunMod);
      ItemStack shovel = new ItemStack(PunMod.ndyeshovel);
      shovel.addEnchantment(net.minecraft.init.Enchantments.FIRE_ASPECT, 7);
      a = shovel.getItem();
      a.setCreativeTab(PunMod.ThePunMod);
      LegacyRecipes.addShapelessRecipe(axe, new Object[]{PunMod.dyeaxe, Blocks.NETHER_BRICK});
      LegacyRecipes.addShapelessRecipe(dhoe, new Object[]{PunMod.dyehoe, Blocks.NETHER_BRICK});
      LegacyRecipes.addShapelessRecipe(pick, new Object[]{PunMod.dyepick, Blocks.NETHER_BRICK});
      LegacyRecipes.addShapelessRecipe(sword, new Object[]{PunMod.dyesword, Blocks.NETHER_BRICK});
      LegacyRecipes.addShapelessRecipe(shovel, new Object[]{PunMod.dyeshovel, Blocks.NETHER_BRICK});
      axe = new ItemStack(PunMod.edyeaxe);
      axe.addEnchantment(net.minecraft.init.Enchantments.KNOCKBACK, 9);
      dhoe = new ItemStack(PunMod.edyehoe);
      dhoe.addEnchantment(net.minecraft.init.Enchantments.KNOCKBACK, 6);
      pick = new ItemStack(PunMod.edyepick);
      pick.addEnchantment(net.minecraft.init.Enchantments.KNOCKBACK, 8);
      sword = new ItemStack(PunMod.edyesword);
      sword.addEnchantment(net.minecraft.init.Enchantments.KNOCKBACK, 10);
      shovel = new ItemStack(PunMod.edyeshovel);
      shovel.addEnchantment(net.minecraft.init.Enchantments.KNOCKBACK, 7);
      LegacyRecipes.addShapelessRecipe(axe, new Object[]{PunMod.dyeaxe, Blocks.END_STONE});
      LegacyRecipes.addShapelessRecipe(dhoe, new Object[]{PunMod.dyehoe, Blocks.END_STONE});
      LegacyRecipes.addShapelessRecipe(pick, new Object[]{PunMod.dyepick, Blocks.END_STONE});
      LegacyRecipes.addShapelessRecipe(sword, new Object[]{PunMod.dyesword, Blocks.END_STONE});
      LegacyRecipes.addShapelessRecipe(shovel, new Object[]{PunMod.dyeshovel, Blocks.END_STONE});
      axe = new ItemStack(PunMod.odyeaxe);
      axe.addEnchantment(net.minecraft.init.Enchantments.UNBREAKING, 5);
      axe.addEnchantment(net.minecraft.init.Enchantments.EFFICIENCY, 5);
      dhoe = new ItemStack(PunMod.odyehoe);
      dhoe.addEnchantment(net.minecraft.init.Enchantments.UNBREAKING, 10);
      pick = new ItemStack(PunMod.odyepick);
      pick.addEnchantment(net.minecraft.init.Enchantments.FORTUNE, 4);
      sword = new ItemStack(PunMod.odyesword);
      sword.addEnchantment(net.minecraft.init.Enchantments.LOOTING, 5);
      shovel = new ItemStack(PunMod.odyeshovel);
      shovel.addEnchantment(net.minecraft.init.Enchantments.EFFICIENCY, 5);
      shovel.addEnchantment(net.minecraft.init.Enchantments.SILK_TOUCH, 1);
      LegacyRecipes.addShapelessRecipe(axe, new Object[]{PunMod.dyeaxe, Blocks.OBSIDIAN});
      LegacyRecipes.addShapelessRecipe(dhoe, new Object[]{PunMod.dyehoe, Blocks.OBSIDIAN});
      LegacyRecipes.addShapelessRecipe(pick, new Object[]{PunMod.dyepick, Blocks.OBSIDIAN});
      LegacyRecipes.addShapelessRecipe(sword, new Object[]{PunMod.dyesword, Blocks.OBSIDIAN});
      LegacyRecipes.addShapelessRecipe(shovel, new Object[]{PunMod.dyeshovel, Blocks.OBSIDIAN});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyepick), new Object[]{PunMod.ndyepick, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeaxe), new Object[]{PunMod.ndyeaxe, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyesword), new Object[]{PunMod.ndyesword, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeshovel), new Object[]{PunMod.ndyeshovel, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyehoe), new Object[]{PunMod.ndyehoe, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyepick), new Object[]{PunMod.edyepick, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeaxe), new Object[]{PunMod.edyeaxe, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyesword), new Object[]{PunMod.edyesword, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeshovel), new Object[]{PunMod.edyeshovel, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyehoe), new Object[]{PunMod.edyehoe, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyepick), new Object[]{PunMod.odyepick, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeaxe), new Object[]{PunMod.odyeaxe, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyesword), new Object[]{PunMod.odyesword, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyeshovel), new Object[]{PunMod.odyeshovel, Blocks.DIRT});
      LegacyRecipes.addShapelessRecipe(new ItemStack(PunMod.dyehoe), new Object[]{PunMod.odyehoe, Blocks.DIRT});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.oaxe), new Object[]{"II", "SI", "S ", 'I', PunMod.obsidianshards, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.oaxe), new Object[]{"II", "IS", " S", 'I', PunMod.obsidianshards, 'S', Items.STICK});
      hoe = new ItemStack(PunMod.ohoe);
      LegacyRecipes.addRecipe(hoe, new Object[]{"II", "S ", "S ", 'I', PunMod.obsidianshards, 'S', Items.STICK});
      LegacyRecipes.addRecipe(hoe, new Object[]{"II", " S", " S", 'I', PunMod.obsidianshards, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.opick), new Object[]{"III", " S ", " S ", 'I', PunMod.obsidianshards, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.oshovel), new Object[]{"I", "S", "S", 'I', PunMod.obsidianshards, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.osword), new Object[]{"I", "I", "S", 'I', PunMod.obsidianshards, 'S', Items.STICK});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.obbow), new Object[]{"III", "IBI", "III", 'I', PunMod.obsidianshards, 'B', Items.BOW});
      LegacyRecipes.addRecipe(new ItemStack(PunMod.bown), new Object[]{"III", "IBI", "III", 'I', Items.BONE, 'B', Items.BOW});
   }

   public static void smeltingRecipes() {
      GameRegistry.addSmelting(PunMod.frozeniron, new ItemStack(Blocks.IRON_ORE), 0.2F);
      GameRegistry.addSmelting(PunMod.frozengold, new ItemStack(Blocks.GOLD_ORE), 0.4F);
      GameRegistry.addSmelting(PunMod.frozenbone, new ItemStack(Items.BONE), 0.2F);
      GameRegistry.addSmelting(PunMod.frozenobsidian, new ItemStack(PunMod.obsidianshards), 0.5F);
      GameRegistry.addSmelting(PunMod.frozendiamond, new ItemStack(Blocks.DIAMOND_ORE), 0.6F);
      GameRegistry.addSmelting(PunMod.frozendyemond, new ItemStack(PunMod.dyemond), 1.5F);
      GameRegistry.addSmelting(PunMod.frozenemerald, new ItemStack(Blocks.EMERALD_ORE), 0.6F);
   }
}
