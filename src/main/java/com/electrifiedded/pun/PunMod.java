package com.electrifiedded.pun;
import com.electrifiedded.gibby_pun.Reference;

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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.biome.Biome;
import com.electrifiedded.archaiclib.legacy.world.LegacyChestGenHooks;
import com.electrifiedded.archaiclib.legacy.world.LegacySpawns;
import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.legacy.entity.LegacyEntityList;
import com.electrifiedded.archaiclib.original.OriginalModAssets;
import com.electrifiedded.archaiclib.v1710.api.item.ArchaicItem;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:archaiclib@[1.41.0,)")
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PunMod {
   public static final String GIBBY_DOMAIN = "gibby_pun";
   public static final String ORIGINAL_NAME = "The-Ultimate-Pun-Mod-1.7.10.jar - The Ultimate Pun Mod 1.5.1 for 1.7.10 (by Gibby008)";
   public static final String ORIGINAL_URL = "https://www.9minecraft.net/the-ultimate-pun-mod/";
   public static final List<Item> ITEMS = new ArrayList<>();
   public static final List<Block> BLOCKS = new ArrayList<>();
   public static final Map<Block, String> BLOCK_TEXTURES = new LinkedHashMap<>();
   public static DimensionType dimType;
   @Mod.Instance(Reference.MOD_ID)
   public static PunMod instance;
   public static final String modid = "gibby_pun";
   static int startEntityid = 580;
   @SidedProxy(clientSide = "com.electrifiedded.pun.ClientProxy", serverSide = "com.electrifiedded.pun.commonProxy")
   public static commonProxy proxy;
   public static Item holeysword;
   public static Item piecemaker;
   public static Item panemaker;
   public static Item crossbow;
   public static Item hardegg;
   public static Item eggsterminator;
   public static Item greatersword;
   public static Item batfur;
   public static Item batiron;
   public static Item batsword;
   public static Item mitestone;
   public static Item flyingpan;
   public static Item fleebag;
   public static Item morningstar;
   public static Item ublock;
   public static Item mintleaf;
   public static Item mintser;
   public static Item spearmint;
   public static Item dyemond;
   public static Item breakfast;
   public static Item stealknife;
   public static Item invisible;
   public static Item bown;
   public static Item obsidianshards;
   public static Item obbow;
   public static Item hammer;
   public static Item rainbow;
   public static Item drop;
   public static Item sandorcery;
   public static Item ink;
   public static Item canink;
   public static Item tentacannon;
   public static Item icesickle;
   public static Item arctcut;
   public static Item iceorb;
   public static Item frenchfrice;
   public static Item flamethwooler;
   public static Item iorepple;
   public static Item corepple;
   public static Item borepple;
   public static Item dorepple;
   public static Item eorepple;
   public static Item rorepple;
   public static Item lorepple;
   public static Item dyorepple;
   public static Item cattlepult;
   public static Item catapult;
   public static Item blackhole;
   public static Item porkchopper;
   public static Item beddersword;
   public static Item clawset;
   public static Item batpick;
   public static Item bataxe;
   public static Item batshovel;
   public static Item bathoe;
   public static Item bathelmet;
   public static Item batchest;
   public static Item batlegs;
   public static Item batboots;
   public static Item dyesword;
   public static Item dyeaxe;
   public static Item dyeshovel;
   public static Item dyehoe;
   public static Item dyepick;
   public static Item ndyesword;
   public static Item ndyeaxe;
   public static Item ndyeshovel;
   public static Item ndyehoe;
   public static Item ndyepick;
   public static Item edyesword;
   public static Item edyeaxe;
   public static Item edyeshovel;
   public static Item edyehoe;
   public static Item edyepick;
   public static Item odyesword;
   public static Item odyeaxe;
   public static Item odyeshovel;
   public static Item odyehoe;
   public static Item odyepick;
   public static Item dyehelmet;
   public static Item dyechest;
   public static Item dyelegs;
   public static Item dyeboots;
   public static Item ndyehelmet;
   public static Item ndyechest;
   public static Item ndyelegs;
   public static Item ndyeboots;
   public static Item edyehelmet;
   public static Item edyechest;
   public static Item edyelegs;
   public static Item edyeboots;
   public static Item odyehelmet;
   public static Item odyechest;
   public static Item odyelegs;
   public static Item odyeboots;
   public static Item osword;
   public static Item oaxe;
   public static Item opick;
   public static Item oshovel;
   public static Item ohoe;
   public static Item ohelm;
   public static Item ochest;
   public static Item olegs;
   public static Item oboots;
   public static Item punisher;
   public static Block mint;
   public static Block soulstand;
   public static Block portalpun;
   public static Block packedice;
   public static Block frozeniron;
   public static Block frozengold;
   public static Block frozenbone;
   public static Block frozenobsidian;
   public static Block frozendiamond;
   public static Block frozendyemond;
   public static Block frozenemerald;
   public static Block icebrick;
   public static Block icestairs;
   public static Block glowice;
   public static Block icespike;
   public static Block fleshlight;
   static int[] BatArray = new int[]{3, 6, 5, 3};
   static int[] DyemondArray = new int[]{4, 8, 6, 3};
   static int[] ObArray = new int[]{4, 9, 7, 3};
   static ToolMaterial BAT = EnumHelper.addToolMaterial("PUN_BAT", 3, 1000, 5.5F, 4.0F, 10);
   static ArmorMaterial ABAT = EnumHelper.addArmorMaterial("PUN_BAT", "gibby_pun:bat", 25, BatArray, 10, net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
   static ToolMaterial DYEMOND = EnumHelper.addToolMaterial("PUN_DYEMOND", 3, 3500, 9.0F, 5.0F, 15);
   static ArmorMaterial ADYEMOND = EnumHelper.addArmorMaterial("PUN_DYEMOND", "gibby_pun:dyemond", 60, DyemondArray, 15, net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
   static ToolMaterial FASTBREAK = EnumHelper.addToolMaterial("PUN_FASTBREAK", 2, 100, 10.0F, 2.0F, 15);
   static ArmorMaterial OB = EnumHelper.addArmorMaterial("PUN_OB", "gibby_pun:ob", 200, ObArray, 12, net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
   static ToolMaterial AOB = EnumHelper.addToolMaterial("PUN_OB", 3, 5000, 10.0F, 5.0F, 12);
   static ToolMaterial PUN = EnumHelper.addToolMaterial("PUN_PUN", 3, 150, 8.0F, 7.0F, 14);
   static ToolMaterial FROST = EnumHelper.addToolMaterial("PUN_FROST", 3, 1250, 8.0F, 8.0F, 14);
   static ToolMaterial BEDDER = EnumHelper.addToolMaterial("PUN_BEDDER", 3, 100, 8.0F, 2.0F, 25);
   public static final int dimensionid = -7;
   public static final CreativeTabs ThePunMod = new CreativeTabs("The Pun Mod") {
      private static final String __OBFID = "CL_00000032";

      @SideOnly(Side.CLIENT)
      public ItemStack createIcon() {
         return new ItemStack(PunMod.batsword);
      }
   };
   public static Biome extremeicecaves;

   public PunMod() {
      // names come from the lang file of the player's own original jar; ours only adds the missing keys
   }

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      OriginalModAssets.require(GIBBY_DOMAIN, GIBBY_DOMAIN, ORIGINAL_NAME, ORIGINAL_URL, GIBBY_DOMAIN);
      com.electrifiedded.archaiclib.legacy.sound.LegacySoundNames17.register();
      extremeicecaves = new BiomeGenExtremeIceCaves();
      holeysword = new ItemHoleySword(ToolMaterial.DIAMOND).setTranslationKey("holeysword");
      regItem(holeysword, "pholeysword", "holeysword");
      piecemaker = new ItemPieceMaker(ToolMaterial.DIAMOND).setTranslationKey("piecemaker");
      regItem(piecemaker, "ppiecemaker", "piecemaker");
      panemaker = new ItemPaneMaker().setTranslationKey("panemaker");
      regItem(panemaker, "ppanemaker", "panemaker");
      crossbow = new ItemCrossBow().setTranslationKey("crossbow");
      regItem(crossbow, "pcrossbow", "");
      hardegg = new ItemHardEgg().setTranslationKey("hardegg");
      regItem(hardegg, "phardegg", "hardegg");
      eggsterminator = new ItemEggsterminator().setTranslationKey("eggsterminator");
      regItem(eggsterminator, "peggsterminator", "eggsterminator");
      greatersword = new ItemGreaterSword().setTranslationKey("greatersword");
      regItem(greatersword, "pgreatersword", "greatersword");
      batfur = new ItemBatFur().setTranslationKey("batfur");
      regItem(batfur, "pbatfur", "batfur");
      batiron = new ItemBatIron().setTranslationKey("batiron");
      regItem(batiron, "batiron", "batiron");
      batsword = new ItemBatSword(BAT).setTranslationKey("batsword");
      regItem(batsword, "pbatsword", "batsword");
      batpick = new ItemBatPick(BAT).setTranslationKey("batpick");
      regItem(batpick, "pbatpick", "batpick");
      bataxe = new ItemBatAxe(BAT).setTranslationKey("bataxe");
      regItem(bataxe, "pbataxe", "bataxe");
      batshovel = new ItemBatShovel(BAT).setTranslationKey("batshovel");
      regItem(batshovel, "pbatsshovel", "batshovel");
      bathoe = new ItemBatHoe(BAT).setTranslationKey("bathoe");
      regItem(bathoe, "pbathoe", "bathoe");
      bathelmet = new ItemBatArmor(BatArray[0], 0).setTranslationKey("bathelmet");
      batchest = new ItemBatArmor(1, 1).setTranslationKey("batchest");
      batlegs = new ItemBatArmor(2, 2).setTranslationKey("batlegs");
      batboots = new ItemBatArmor(3, 3).setTranslationKey("batboots");
      regItem(bathelmet, "pbathelmet", "bathelmet");
      regItem(batchest, "pbatchest", "batchest");
      regItem(batlegs, "pbatlegs", "batlegs");
      regItem(batboots, "pbatboots", "batboots");
      PunMod.mitestone = new ItemMiteStone().setTranslationKey("mitestone");
      regItem(PunMod.mitestone, "pmitestone", "mitestone");
      flyingpan = new ItemFlyingPan().setTranslationKey("flyingpan");
      regItem(flyingpan, "pflyingpan", "flyingpan");
      fleebag = new ItemFleeBag().setTranslationKey("fleebag");
      regItem(fleebag, "pfleebag", "fleebag");
      morningstar = new ItemMorningStar().setTranslationKey("morningstar");
      regItem(morningstar, "pmorningstar", "morningstar");
      ublock = new ItemUBlock().setTranslationKey("ublock");
      regItem(ublock, "publock", "ublock");
      PunMod.mint = new BlockMint().setTranslationKey("mint");
      regBlock(PunMod.mint, "pmint", "mint");
      mintleaf = new ItemMint(0, 0.2F, Blocks.FARMLAND, PunMod.mint).setTranslationKey("mintleaf");
      regItem(mintleaf, "pmintleaf", "mintleaf");
      mintser = new ItemMintser().setTranslationKey("mintser");
      regItem(mintser, "pmintser", "mintser");
      spearmint = new ItemSpearMint().setTranslationKey("spearmint");
      regItem(spearmint, "pspearmint", "spearmint");
      breakfast = new ItemBreakFast().setTranslationKey("breakfast");
      regItem(breakfast, "pbreakfast", "breakfast");
      soulstand = new BlockSoulStand().setTranslationKey("soulstand");
      regBlock(soulstand, "psoulstand", "soulstand");
      stealknife = new ItemStealKnife().setTranslationKey("stealknife");
      regItem(stealknife, "pstealknife", "stealknife");
      PunMod.dyemond = new ItemDyemond().setTranslationKey("dyemond");
      regItem(PunMod.dyemond, "dyemond", "dyemond");
      dyehelmet = new ItemDyeArmor(0, 0).setTranslationKey("dyehelmet").setCreativeTab(ThePunMod);
      dyechest = new ItemDyeArmor(1, 1).setTranslationKey("dyechest").setCreativeTab(ThePunMod);
      dyelegs = new ItemDyeArmor(2, 2).setTranslationKey("dyelegs").setCreativeTab(ThePunMod);
      dyeboots = new ItemDyeArmor(3, 3).setTranslationKey("dyeboots").setCreativeTab(ThePunMod);
      regItem(dyehelmet, "pdyehelmet", "overworldhelmet");
      regItem(dyechest, "pdyechest", "overworldchest");
      regItem(dyelegs, "pdyelegs", "overworldlegs");
      regItem(dyeboots, "pdyeboots", "overworldboots");
      ndyehelmet = new ItemDyeArmor(0, 0).setTranslationKey("ndyehelmet").setCreativeTab(CreativeTabs.SEARCH);
      ndyechest = new ItemDyeArmor(1, 1).setTranslationKey("ndyechest").setCreativeTab(CreativeTabs.SEARCH);
      ndyelegs = new ItemDyeArmor(2, 2).setTranslationKey("ndyelegs").setCreativeTab(CreativeTabs.SEARCH);
      ndyeboots = new ItemDyeArmor(3, 3).setTranslationKey("ndyeboots").setCreativeTab(CreativeTabs.SEARCH);
      regItem(ndyehelmet, "pndyehelmet", "netherhelmet");
      regItem(ndyechest, "pndyechest", "netherchest");
      regItem(ndyelegs, "pndyelegs", "netherlegs");
      regItem(ndyeboots, "pndyeboots", "netherboots");
      edyehelmet = new ItemDyeArmor(0, 0).setTranslationKey("edyehelmet").setCreativeTab(CreativeTabs.SEARCH);
      edyechest = new ItemDyeArmor(1, 1).setTranslationKey("edyechest").setCreativeTab(CreativeTabs.SEARCH);
      edyelegs = new ItemDyeArmor(2, 2).setTranslationKey("edyelegs").setCreativeTab(CreativeTabs.SEARCH);
      edyeboots = new ItemDyeArmor(3, 3).setTranslationKey("edyeboots").setCreativeTab(CreativeTabs.SEARCH);
      regItem(edyehelmet, "pedyehelmet", "endhelmet");
      regItem(edyechest, "pedyechest", "endchest");
      regItem(edyelegs, "pedyelegs", "endlegs");
      regItem(edyeboots, "pedyeboots", "endboots");
      odyehelmet = new ItemDyeArmor(0, 0).setTranslationKey("odyehelmet").setCreativeTab(CreativeTabs.SEARCH);
      odyechest = new ItemDyeArmor(1, 1).setTranslationKey("odyechest").setCreativeTab(CreativeTabs.SEARCH);
      odyelegs = new ItemDyeArmor(2, 2).setTranslationKey("odyelegs").setCreativeTab(CreativeTabs.SEARCH);
      odyeboots = new ItemDyeArmor(3, 3).setTranslationKey("odyeboots").setCreativeTab(CreativeTabs.SEARCH);
      regItem(odyehelmet, "podyehelmet", "otherhelmet");
      regItem(odyechest, "podyechest", "otherchest");
      regItem(odyelegs, "podyelegs", "otherlegs");
      regItem(odyeboots, "podyeboots", "otherboots");
      dyesword = new ItemDyeSword(DYEMOND).setTranslationKey("dyesword");
      regItem(dyesword, "pdyesword", "dyesword");
      dyepick = new ItemDyePick(DYEMOND).setTranslationKey("dyepick");
      regItem(dyepick, "pdyepick", "dyepick");
      dyeaxe = new ItemDyeAxe(DYEMOND).setTranslationKey("dyeaxe");
      regItem(dyeaxe, "pdyeaxe", "dyeaxe");
      dyeshovel = new ItemDyeShovel(DYEMOND).setTranslationKey("dyeshovel");
      regItem(dyeshovel, "pdyesshovel", "dyeshovel");
      dyehoe = new ItemDyeHoe(DYEMOND).setTranslationKey("dyehoe");
      regItem(dyehoe, "pdyehoe", "dyehoe");
      ndyesword = new ItemNDyeSword(DYEMOND).setTranslationKey("ndyesword").setCreativeTab(CreativeTabs.SEARCH);
      regItem(ndyesword, "pndyesword", "ndyesword");
      ndyepick = new ItemNDyePick(DYEMOND).setTranslationKey("ndyepick").setCreativeTab(CreativeTabs.SEARCH);
      regItem(ndyepick, "pndyepick", "ndyepick");
      ndyeaxe = new ItemNDyeAxe(DYEMOND).setTranslationKey("ndyeaxe").setCreativeTab(CreativeTabs.SEARCH);
      regItem(ndyeaxe, "pndyeaxe", "ndyeaxe");
      ndyeshovel = new ItemNDyeShovel(DYEMOND)
         .setTranslationKey("ndyeshovel")
         .setCreativeTab(CreativeTabs.SEARCH);
      regItem(ndyeshovel, "pndyeshovel", "ndyeshovel");
      ndyehoe = new ItemNDyeHoe(DYEMOND).setTranslationKey("ndyehoe").setCreativeTab(CreativeTabs.SEARCH);
      regItem(ndyehoe, "pndyehoe", "ndyehoe");
      edyesword = new ItemEDyeSword(DYEMOND).setTranslationKey("edyesword").setCreativeTab(CreativeTabs.SEARCH);
      regItem(edyesword, "pedyesword", "edyesword");
      edyepick = new ItemEDyePick(DYEMOND).setTranslationKey("edyepick").setCreativeTab(CreativeTabs.SEARCH);
      regItem(edyepick, "pedyepick", "edyepick");
      edyeaxe = new ItemEDyeAxe(DYEMOND).setTranslationKey("edyeaxe").setCreativeTab(CreativeTabs.SEARCH);
      regItem(edyeaxe, "pedyeaxe", "edyeaxe");
      edyeshovel = new ItemEDyeShovel(DYEMOND)
         .setTranslationKey("edyeshovel")
         .setCreativeTab(CreativeTabs.SEARCH);
      regItem(edyeshovel, "pedyeshovel", "edyeshovel");
      edyehoe = new ItemEDyeHoe(DYEMOND).setTranslationKey("edyehoe").setCreativeTab(CreativeTabs.SEARCH);
      regItem(edyehoe, "pedyehoe", "edyehoe");
      odyesword = new ItemODyeSword(DYEMOND).setTranslationKey("odyesword").setCreativeTab(CreativeTabs.SEARCH);
      regItem(odyesword, "podyesword", "odyesword");
      odyepick = new ItemODyePick(DYEMOND).setTranslationKey("odyepick").setCreativeTab(CreativeTabs.SEARCH);
      regItem(odyepick, "podyepick", "odyepick");
      odyeaxe = new ItemODyeAxe(DYEMOND).setTranslationKey("odyeaxe").setCreativeTab(CreativeTabs.SEARCH);
      regItem(odyeaxe, "podyeaxe", "odyeaxe");
      odyeshovel = new ItemODyeShovel(DYEMOND)
         .setTranslationKey("odyeshovel")
         .setCreativeTab(CreativeTabs.SEARCH);
      regItem(odyeshovel, "podyeshovel", "odyeshovel");
      odyehoe = new ItemODyeHoe(DYEMOND).setTranslationKey("odyehoe").setCreativeTab(CreativeTabs.SEARCH);
      regItem(odyehoe, "podyehoe", "odyehoe");
      invisible = new ItemInvisible().setTranslationKey("invisible");
      regItem(invisible, "pinvisible", "invisible");
      bown = new ItemBown().setTranslationKey("bown");
      regItem(bown, "pbown", "");
      obsidianshards = new ItemObsidian().setTranslationKey("ob");
      regItem(obsidianshards, "pob", "ob");
      obbow = new ItemObBow().setTranslationKey("obbow");
      regItem(obbow, "pobbow", "");
      osword = new ItemOSword(AOB).setTranslationKey("osword");
      regItem(osword, "posword", "osword");
      opick = new ItemOPick(AOB).setTranslationKey("opick");
      regItem(opick, "popick", "opick");
      oaxe = new ItemOAxe(AOB).setTranslationKey("oaxe");
      regItem(oaxe, "poaxe", "oaxe");
      oshovel = new ItemOShovel(AOB).setTranslationKey("oshovel");
      regItem(oshovel, "poshovel", "oshovel");
      ohoe = new ItemOHoe(AOB).setTranslationKey("ohoe");
      regItem(ohoe, "pohoe", "ohoe");
      ohelm = new ItemObArmor(OB, 0, 0).setTranslationKey("ohelmet");
      ochest = new ItemObArmor(OB, 1, 1).setTranslationKey("ochest");
      olegs = new ItemObArmor(OB, 2, 2).setTranslationKey("olegs");
      oboots = new ItemObArmor(OB, 3, 3).setTranslationKey("oboots");
      regItem(ohelm, "pohelmet", "ohelmet");
      regItem(ochest, "pochest", "ochest");
      regItem(olegs, "polegs", "olegs");
      regItem(oboots, "poboots", "oboots");
      hammer = new ItemHammer().setTranslationKey("hammer");
      regItem(hammer, "phammer", "hammer");
      rainbow = new ItemRainbow().setTranslationKey("rainbow");
      regItem(rainbow, "prainbow", "rainbow");
      drop = new ItemDrop().setTranslationKey("drop");
      regItem(drop, "drop", "drop");
      punisher = new ItemPunisher().setTranslationKey("punisher");
      regItem(punisher, "punisher", "punisher");
      sandorcery = new ItemSandorcery().setTranslationKey("sandorcery");
      regItem(sandorcery, "sandorcery", "sandorcery");
      ink = new ItemInk().setTranslationKey("ink");
      regItem(ink, "pink", "ink");
      canink = new ItemCanInk().setTranslationKey("canink");
      regItem(canink, "pcanink", "canink");
      tentacannon = new ItemTentacannon().setTranslationKey("tentacannon");
      regItem(tentacannon, "ptentacannnon", "tentacannon");
      portalpun = new BlockPortalPun().setTranslationKey("portalpun");
      regBlock(portalpun, "pportalpun", "");
      icesickle = new ItemIceSickle(BAT).setTranslationKey("icesickle");
      regItem(icesickle, "picesickle", "icesickle");
      packedice = new BlockPackedIce().setTranslationKey("packedice").setHardness(7.0F).setResistance(11.0F);
      regBlock(packedice, "ppackedice", "ice_packed");
      frozeniron = new BlockIceOre(Material.ICE).setTranslationKey("frozeniron");
      regBlock(frozeniron, "pfrozeniron", "frozeniron");
      frozengold = new BlockIceOre(Material.ICE).setTranslationKey("frozengold");
      regBlock(frozengold, "pfrozengold", "frozengold");
      frozenbone = new BlockIceOre(Material.ICE).setTranslationKey("frozenbone");
      regBlock(frozenbone, "pfrozenbone", "frozenbone");
      frozenobsidian = new BlockIceOre(Material.ICE).setTranslationKey("frozenobsidian");
      regBlock(frozenobsidian, "pfrozenobsidian", "frozenobsidian");
      frozendiamond = new BlockIceOre(Material.ICE).setTranslationKey("frozendiamond");
      regBlock(frozendiamond, "pfrozendiamond", "frozendiamond");
      frozendyemond = new BlockIceOre(Material.ICE).setTranslationKey("frozendyemond");
      regBlock(frozendyemond, "pfrozendyemond", "frozendyemond");
      frozenemerald = new BlockIceOre(Material.ICE).setTranslationKey("frozenemerald");
      regBlock(frozenemerald, "pfrozenemerald", "frozenemerald");
      icebrick = new BlockPackedIce().setTranslationKey("icebrick").setHardness(15.0F).setResistance(24.0F);
      regBlock(icebrick, "picebrick", "icebrick");
      icestairs = new BlockIceStairs(icebrick, 0)
         .setTranslationKey("icestairs")
         .setHardness(10.0F)
         .setResistance(16.0F);
      regBlock(icestairs, "picestairs", "icebrick");
      glowice = new BlockPackedIce().setTranslationKey("glowice").setLightLevel(6.0F).setHardness(1.0F);
      regBlock(glowice, "pglowice", "glowice");
      icespike = new BlockIceSpike().setTranslationKey("icespike");
      regBlock(icespike, "picespice", "icespike");
      arctcut = new ItemArctcutSword(FROST).setTranslationKey("arctcut");
      regItem(arctcut, "parctcut", "arctcut");
      iceorb = new ItemIceOrb().setTranslationKey("iceorb");
      regItem(iceorb, "piceorb", "iceorb");
      frenchfrice = new ItemFrenchFrice(5, 2.5F, false).setTranslationKey("frenchfrice");
      regItem(frenchfrice, "pfrenchfrice", "frenchfrice");
      flamethwooler = new ItemFlameThwooler().setTranslationKey("flamethwooler");
      regItem(flamethwooler, "pflamethwooler", "flamethwooler");
      iorepple = new ItemOrepple(4, 4.0F, false).setTranslationKey("iorepple");
      regItem(iorepple, "piorepple", "iorepple");
      corepple = new ItemOrepple(2, 2.0F, false).setTranslationKey("corepple");
      regItem(corepple, "pcorepple", "corepple");
      borepple = new ItemOrepple(5, 5.0F, false).setTranslationKey("borepple");
      regItem(borepple, "pborepple", "borepple");
      dorepple = new ItemOrepple(7, 7.0F, false).setTranslationKey("dorepple");
      regItem(dorepple, "pdorepple", "dorepple");
      eorepple = new ItemOrepple(8, 8.0F, false).setTranslationKey("eorepple");
      regItem(eorepple, "peorepple", "eorepple");
      lorepple = new ItemOrepple(5, 5.0F, false).setTranslationKey("lorepple");
      regItem(lorepple, "plorepple", "lorepple");
      rorepple = new ItemOrepple(3, 3.0F, false).setTranslationKey("rorepple");
      regItem(rorepple, "prorepple", "rorepple");
      dyorepple = new ItemOrepple(9, 9.0F, false).setTranslationKey("dyorepple");
      regItem(dyorepple, "pdyorepple", "dyorepple");
      blackhole = new ItemBlackHole().setTranslationKey("blackhole");
      regItem(blackhole, "pblackhole", "blackhole");
      porkchopper = new ItemPorkChopper().setTranslationKey("porkchopper");
      regItem(porkchopper, "pporkchopper", "porkchopper");
      beddersword = new ItemBedderSword().setTranslationKey("beddersword");
      regItem(beddersword, "pbeddersword", "beddersword");
      clawset = new ItemClawset().setTranslationKey("clawset");
      regItem(clawset, "pclawset", "clawset");
      fleshlight = new BlockFleshLight().setTranslationKey("fleshlight");
      regBlock(fleshlight, "pfleshlight", "fleshlight");
      dimType = DimensionType.register("SnowverWorld", "_gibby_pun", dimensionid, WorldProviderPun.class, false);
      DimensionManager.registerDimension(dimensionid, dimType);
      String[] names = {"EntityPiece", "EntityGold", "EntityHardEgg", "EntitySpearMint", "EntityKnife", "EntityHSkeleton", "EntityXZombie",
         "EntityCloud", "EntityObsidian", "EntityFry", "EntityMiniFireBall", "EntityDayBreaker", "EntityMinion", "EntityRain",
         "EntityHammer", "EntitySandWitch", "EntitySandy", "EntityLiqsquid", "EntityInk", "EntityFlamekenstein", "EntityBigFireball",
         "EntityFire", "EntitySquidBomb", "EntityPunisher", "EntityColdKiller", "EntityIcyYou", "EntityIceBall", "EntityIceassin",
         "EntityGlaciador", "EntityIceOrb", "EntityFlicing", "EntityFrostBiter", "EntityAbombnible", "EntityGlaciadorBoss", "EntityBlackHole"};
      Class<?>[] classes = {EntityPiece.class, EntityGold.class, EntityHardEgg.class, EntitySpearMint.class, EntityKnife.class, EntityHSkeleton.class, EntityXZombie.class,
         EntityCloud.class, EntityOb.class, EntityFry.class, EntityMiniFireBall.class, EntityDayBreaker.class, EntityMinion.class, EntityRain.class,
         EntityHammer.class, EntitySandWitch.class, EntitySandy.class, EnitiyLiqsquid.class, EntityInk.class, EntityFlamekenstein.class, EntityBigFireball.class,
         EntityFire.class, EntitySquidBomb.class, EntityPunisher.class, EntityColdKiller.class, EntityIcyYou.class, EntityIceBall.class, EntityIceassin.class,
         EntityGlaciador.class, EntityIceOrb.class, EntityFlicing.class, EntityFrostBiter.class, EntityAbombnible.class, EntityGlaciadorBoss.class, EntityBlackHole.class};
      int[] range = {40, 60, 40, 60, 40, 60, 60, 120, 60, 60, 120, 3000, 100, 50, 50, 60, 30, 100, 40, 80, 60, 40, 30, 40, 50, 100, 50, 50, 50, 50, 50, 40, 40, 40, 60};
      int[] freq = {3, 3, 3, 3, 3, 3, 3, 5, 5, 4, 3, 6, 3, 3, 3, 4, 3, 6, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3};
      Map<Class<?>, int[]> eggs = new java.util.HashMap<>();
      eggs.put(EntityHSkeleton.class, new int[]{16711782, 11692907});
      eggs.put(EntityXZombie.class, new int[]{3368448, 52224});
      eggs.put(EntityCloud.class, new int[]{10066329, 2171169});
      eggs.put(EntityOb.class, new int[]{20, 7995392});
      eggs.put(EntityFry.class, new int[]{5373952, 7995392});
      eggs.put(EntityDayBreaker.class, new int[]{17, 273});
      eggs.put(EntityMinion.class, new int[]{1118481, 2236962});
      eggs.put(EntitySandWitch.class, new int[]{15460234, 33197640});
      eggs.put(EnitiyLiqsquid.class, new int[]{1325222, 11015735});
      eggs.put(EntityFlamekenstein.class, new int[]{140804, 7995392});
      eggs.put(EntityColdKiller.class, new int[]{11645619, 7782596});
      for (Class<?> c : new Class<?>[]{EntityIcyYou.class, EntityIceassin.class, EntityGlaciador.class, EntityGlaciadorBoss.class, EntityFlicing.class, EntityFrostBiter.class, EntityAbombnible.class}) {
         eggs.put(c, new int[]{11645619, 7717060});
      }
      for (int i = 0; i < classes.length; i++) {
         @SuppressWarnings("unchecked") Class<? extends Entity> c = (Class<? extends Entity>) classes[i];
         int[] egg = eggs.get(c);
         if (egg != null) {
            LegacyGameRegistry.registerModEntity(c, names[i], i + 1, this, range[i], freq[i], true, egg[0], egg[1] & 0xFFFFFF);
         } else {
            LegacyGameRegistry.registerModEntity(c, names[i], i + 1, this, range[i], freq[i], true);
         }
      }
      proxy.preInit();
   }

   /** 1.7 armor slot index 0..3 (helmet..boots) */
   public static net.minecraft.inventory.EntityEquipmentSlot slot(int i) {
      return com.electrifiedded.archaiclib.legacy.entity.LegacyEquipment.slot(4 - i);
   }

   /** 1.7 spawner names ("EntityMinion", "Zombie") -> 1.12 entity ids. */
   public static ResourceLocation spawnerId(String name) {
      EntityEntry e = LegacyEntityList.getEntryByName(name);
      return e != null && e.getRegistryName() != null ? e.getRegistryName() : new ResourceLocation(GIBBY_DOMAIN, name.toLowerCase());
   }

   private static void regItem(Item item, String name, String tex) {
      item.setRegistryName(GIBBY_DOMAIN, name);
      ITEMS.add(item);
      if (!tex.isEmpty()) {
         ArchaicItem.registerMetaTexture(item, 0, GIBBY_DOMAIN + ":" + tex);
      }
   }

   private static void regBlock(Block block, String name, String tex) {
      block.setRegistryName(GIBBY_DOMAIN, name);
      BLOCKS.add(block);
      BLOCK_TEXTURES.put(block, tex);
      if (block != portalpun && block != mint) {
         ITEMS.add(new ItemBlock(block).setRegistryName(GIBBY_DOMAIN, name));
      }
   }

   @SubscribeEvent
   public static void registerBlocks(RegistryEvent.Register<Block> e) {
      for (Block b : BLOCKS) e.getRegistry().register(b);
   }

   @SubscribeEvent
   public static void registerItems(RegistryEvent.Register<Item> e) {
      for (Item i : ITEMS) e.getRegistry().register(i);
   }

   @SubscribeEvent
   public static void registerBiomes(RegistryEvent.Register<Biome> e) {
      e.getRegistry().register(extremeicecaves.setRegistryName(GIBBY_DOMAIN, "snowverworld"));
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      List<Biome> overworld = LegacySpawns.biomes(b -> LegacySpawns.isOverworld(b) && b != extremeicecaves);
      List<Biome> cloudBiomes = LegacySpawns.biomes(b -> LegacySpawns.isOverworld(b) && b != extremeicecaves
         && b != Biomes.PLAINS && b != Biomes.ICE_PLAINS && b != Biomes.DESERT);
      LegacySpawns.add(EntityHSkeleton.class, 20, 4, 4, EnumCreatureType.MONSTER, overworld);
      LegacySpawns.add(EntityXZombie.class, 10, 4, 4, EnumCreatureType.MONSTER, overworld);
      LegacySpawns.add(EntityCloud.class, 3, 4, 4, EnumCreatureType.MONSTER, cloudBiomes);
      LegacySpawns.add(EntityMinion.class, 4, 4, 4, EnumCreatureType.MONSTER, overworld);
      LegacySpawns.addSpawn(EnitiyLiqsquid.class, 1, 4, 4, EnumCreatureType.WATER_CREATURE, Biomes.OCEAN);
      LegacySpawns.addSpawn(EntitySandWitch.class, 5, 4, 4, EnumCreatureType.AMBIENT, Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.BEACH);
      LegacySpawns.addSpawn(EntityOb.class, 1, 4, 4, EnumCreatureType.MONSTER, Biomes.HELL);
      LegacySpawns.addSpawn(EntityFlamekenstein.class, 20, 4, 4, EnumCreatureType.MONSTER, Biomes.HELL);
      LegacySpawns.addSpawn(EntityFry.class, 60, 4, 4, EnumCreatureType.MONSTER, Biomes.HELL);
      LegacySpawns.addSpawn(EntityColdKiller.class, 20, 4, 4, EnumCreatureType.MONSTER, extremeicecaves);
      LegacySpawns.addSpawn(EntitySnowman.class, 10, 4, 4, EnumCreatureType.MONSTER, extremeicecaves);
      LegacySpawns.addSpawn(EntityIceassin.class, 3, 4, 4, EnumCreatureType.MONSTER, extremeicecaves);
      LegacySpawns.addSpawn(EntityFrostBiter.class, 90, 4, 4, EnumCreatureType.MONSTER, extremeicecaves);
      LegacySpawns.addSpawn(EntityFlicing.class, 5, 4, 4, EnumCreatureType.MONSTER, extremeicecaves);
      LegacySpawns.addSpawn(EntityAbombnible.class, 20, 4, 4, EnumCreatureType.MONSTER, extremeicecaves);
      String J = "pyramidJungleChest";
      LegacyChestGenHooks.addItem(J, Items.STICK, 0, 5, 5, 15);
      LegacyChestGenHooks.addItem(J, Items.BONE, 0, 5, 10, 15);
      LegacyChestGenHooks.addItem(J, Items.COAL, 0, 5, 5, 15);
      LegacyChestGenHooks.addItem(J, Items.IRON_PICKAXE, 0, 2, 2, 5);
      LegacyChestGenHooks.addItem(J, Item.getItemFromBlock(Blocks.ICE), 0, 7, 14, 25);
      LegacyChestGenHooks.addItem(J, Item.getItemFromBlock(Blocks.PACKED_ICE), 0, 7, 14, 25);
      LegacyChestGenHooks.addItem(J, Item.getItemFromBlock(frozendyemond), 0, 2, 2, 5);
      LegacyChestGenHooks.addItem(J, Item.getItemFromBlock(frozenobsidian), 0, 2, 2, 5);
      LegacyChestGenHooks.addItem(J, Item.getItemFromBlock(frozenbone), 0, 4, 8, 15);
      LegacyChestGenHooks.addItem(J, Item.getItemFromBlock(frozenemerald), 0, 2, 2, 5);
      LegacyChestGenHooks.addItem(J, batiron, 0, 3, 5, 10);
      LegacyChestGenHooks.addItem(J, Items.IRON_INGOT, 0, 5, 10, 10);
      LegacyChestGenHooks.addItem(J, dorepple, 0, 1, 1, 3);
      LegacyChestGenHooks.addItem(J, iorepple, 0, 2, 3, 3);
      LegacyChestGenHooks.addItem(J, lorepple, 0, 1, 2, 3);
      LegacyChestGenHooks.addItem(J, corepple, 0, 3, 4, 3);
      LegacyChestGenHooks.addItem(J, dyehelmet, 0, 1, 1, 3);
      LegacyChestGenHooks.addItem(J, dyelegs, 0, 1, 1, 1);
      LegacyChestGenHooks.addItem(J, dyesword, 0, 1, 1, 1);
      LegacyChestGenHooks.addItem(J, Items.EMERALD, 0, 2, 3, 5);
      LegacyChestGenHooks.addItem(J, Items.DIAMOND_SWORD, 0, 1, 2, 3);
      LegacyChestGenHooks.addItem(J, Items.DIAMOND_CHESTPLATE, 0, 1, 2, 3);
      String[] all = {"dungeonChest", "mineshaftCorridor", "strongholdLibrary", "pyramidDesertyChest", "pyramidJungleChest", "strongholdCrossing", "strongholdCorridor"};
      LegacyChestGenHooks.addItem(all, new ItemStack(mintleaf), 2, 4, 4);
      LegacyChestGenHooks.addItem(all, new ItemStack(mitestone), 1, 3, 4);
      LegacyChestGenHooks.addItem(all, new ItemStack(fleebag), 1, 2, 4);
      LegacyChestGenHooks.addItem(all, new ItemStack(crossbow), 1, 1, 1);
      LegacyChestGenHooks.addItem(all, new ItemStack(holeysword, 1, 750), 1, 1, 1);
      LegacyChestGenHooks.addItem(all, new ItemStack(dyemond), 1, 1, 1);
      LegacyChestGenHooks.addItem(all, new ItemStack(dyemond), 1, 1, 1);
      LegacyChestGenHooks.addItem(all, new ItemStack(dyemond), 1, 2, 1);
      PunCrafting.addWeaponsRecipes();
      PunCrafting.otherRecipes();
      PunCrafting.armorRecipes();
      PunCrafting.toolRecipes();
      PunCrafting.smeltingRecipes();
   }
}
