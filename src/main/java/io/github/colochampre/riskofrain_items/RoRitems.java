package io.github.colochampre.riskofrain_items;

import com.mojang.logging.LogUtils;
import io.github.colochampre.riskofrain_items.events.ModCreativeModeTabs;
import io.github.colochampre.riskofrain_items.init.BlockEntityInit;
import io.github.colochampre.riskofrain_items.init.ItemInit;
import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(RoRitems.MODID)
public class RoRitems {
  public static final String MODID = "riskofrain_items";
  public static final Logger LOGGER = LogUtils.getLogger();

  public RoRitems() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    bus.addListener(ModCreativeModeTabs::addItemsToTabs);

    SoundInit.SOUNDS.register(bus);
    ItemInit.ITEMS.register(bus);
    BlockEntityInit.BLOCK_ENTITIES.register(bus);
  }
}
