package com.electrifiedded.pun;
import com.electrifiedded.gibby_pun.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
public class ClientProxy extends commonProxy {
   private static <T extends Entity> void snowball(Class<T> cls, java.util.function.Supplier<Item> item) {
      RenderingRegistry.registerEntityRenderingHandler(cls, m -> new RenderSnowball<>(m, item.get(), Minecraft.getMinecraft().getRenderItem()));
   }

   @Override
   public void preInit() {
      snowball(EntityPiece.class, () -> Items.FEATHER);
      snowball(EntityGold.class, () -> Items.GOLD_INGOT);
      snowball(EntityHardEgg.class, () -> Items.EGG);
      snowball(EntityKnife.class, () -> PunMod.stealknife);
      snowball(EntityMiniFireBall.class, () -> PunMod.invisible);
      snowball(EntityRain.class, () -> PunMod.drop);
      snowball(EntityHammer.class, () -> PunMod.hammer);
      snowball(EntityInk.class, () -> PunMod.ink);
      snowball(EntityBigFireball.class, () -> Items.FIRE_CHARGE);
      snowball(EntityFire.class, () -> Items.BLAZE_POWDER);
      snowball(EntityPunisher.class, () -> PunMod.invisible);
      snowball(EntityIceBall.class, () -> PunMod.iceorb);
      RenderingRegistry.registerEntityRenderingHandler(EntitySandy.class, m -> new RenderModelEntity<>(m, new Sandy(), new ResourceLocation("gibby_pun:textures/texturemaps/armor/sandy.png"), 0.0F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F));
      RenderingRegistry.registerEntityRenderingHandler(EntitySquidBomb.class, m -> new RenderModelEntity<>(m, new net.minecraft.client.model.ModelSquid(), new ResourceLocation("textures/entity/squid.png"), 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.1F));
      RenderingRegistry.registerEntityRenderingHandler(EntityIceOrb.class, m -> new RenderModelEntity<>(m, new ModelIceOrb(), new ResourceLocation("gibby_pun:textures/texturemaps/armor/IceOrb.png"), 0.2F, 1.0F, 0.0F, 1.0F, 0.0F, 0.1F));
      RenderingRegistry.registerEntityRenderingHandler(EntityBlackHole.class, m -> new RenderModelEntity<>(m, new ModelIceOrb(), new ResourceLocation("gibby_pun:textures/texturemaps/armor/Daybreak-er_5.png"), 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.1F));
      RenderingRegistry.registerEntityRenderingHandler(EntityHSkeleton.class, RenderHSkeleton::new);
      RenderingRegistry.registerEntityRenderingHandler(EntityXZombie.class, RenderXZombie::new);
      RenderingRegistry.registerEntityRenderingHandler(EntityCloud.class, m -> new RenderCloud(m, new ModelCloud(), 2.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityOb.class, RenderOb::new);
      RenderingRegistry.registerEntityRenderingHandler(EntityFry.class, RenderFry::new);
      RenderingRegistry.registerEntityRenderingHandler(EntityMinion.class, m -> new RenderGhost(m, new Ghost(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntitySandWitch.class, m -> new RenderSandWitch(m, new ModelWitch(0.5F), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityDayBreaker.class, m -> new RenderDayBreaker(m, new Modeldaybreak(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EnitiyLiqsquid.class, m -> new RenderLiqSquid(m, new liqsquid(), 1.0F));
      RenderingRegistry.registerEntityRenderingHandler(EntityFlamekenstein.class, m -> new RenderFlamekenstein(m, new ModelZombie(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityColdKiller.class, m -> new RenderColdKiller(m, new ModelSkeleton(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityIcyYou.class, m -> new RenderIcyYou(m, new ModelIcyYou(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityAbombnible.class, m -> new RenderAbombnible(m, new Abombnible(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityFrostBiter.class, m -> new RenderFrostBiter(m, new Frostbiter(), 0.5F));
      RenderingRegistry.registerEntityRenderingHandler(EntityFlicing.class, m -> new RenderTerrajin(m, new Terrajin(), 0.5F));
   }

   /** Item-blocks use the block model; blockstate/model jsons are shipped in assets/gibby_pun. */
   @SubscribeEvent
   public static void registerModels(ModelRegistryEvent e) {
      for (Item json : new Item[]{PunMod.bown, PunMod.obbow, PunMod.crossbow}) {
         ModelLoader.setCustomModelResourceLocation(json, 0, new ModelResourceLocation(json.getRegistryName(), "inventory"));
      }
      ModelLoader.setCustomStateMapper(PunMod.soulstand, new net.minecraft.client.renderer.block.statemap.StateMap.Builder()
         .ignore(net.minecraft.block.BlockBrewingStand.HAS_BOTTLE).build());
      for (Block b : PunMod.BLOCKS) {
         Item ib = Item.getItemFromBlock(b);
         if (ib != net.minecraft.init.Items.AIR) {
            ModelLoader.setCustomModelResourceLocation(ib, 0, new ModelResourceLocation(b.getRegistryName(), "inventory"));
         }
      }
   }
}
