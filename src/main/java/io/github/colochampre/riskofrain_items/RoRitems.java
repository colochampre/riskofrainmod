package io.github.colochampre.riskofrain_items;

import com.mojang.logging.LogUtils;
import io.github.colochampre.riskofrain_items.events.ModCreativeModeTabs;
import io.github.colochampre.riskofrain_items.init.BlockInit;
import io.github.colochampre.riskofrain_items.init.ItemInit;
import io.github.colochampre.riskofrain_items.init.PaintingInit;
import io.github.colochampre.riskofrain_items.init.SoundInit;
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

    ItemInit.ITEMS.register(bus);
    BlockInit.BLOCKS.register(bus);
    PaintingInit.PAINTING_VARIANTS.register(bus);
    SoundInit.SOUNDS.register(bus);
  }
}
