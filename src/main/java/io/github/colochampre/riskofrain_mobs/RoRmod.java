package io.github.colochampre.riskofrain_mobs;

import io.github.colochampre.riskofrain_mobs.init.BiomeModifierInit;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import io.github.colochampre.riskofrain_mobs.init.ItemInit;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(RoRmod.MODID)
@Mod.EventBusSubscriber
public class RoRmod {
  public static final Logger LOGGER = LogManager.getLogger();
  public static final String MODID = "riskofrain_mobs";

  public RoRmod() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    bus.addListener(this::addItemsToTabs);

    SoundInit.SOUNDS.register(bus);
    ItemInit.ITEMS.register(bus);
    EntityInit.ENTITY_TYPES.register(bus);
    BiomeModifierInit.BIOME_MODIFIER_SERIALIZERS.register(bus);

    MinecraftForge.EVENT_BUS.register(this);
    ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, RoRConfig.SERVER_SPEC);
  }

  private void addItemsToTabs(CreativeModeTabEvent.BuildContents event) {
    if (event.getTab() == CreativeModeTabs.SPAWN_EGGS) {
      event.accept(ItemInit.LEMURIAN_SPAWN_EGG);
      event.accept(ItemInit.STONE_GOLEM_SPAWN_EGG);
      event.accept(ItemInit.GUNNER_DRONE_SPAWN_EGG);
    }
  }
}
