package io.github.colochampre.riskofrainmod;

import io.github.colochampre.riskofrainmod.init.EntityInit;
import io.github.colochampre.riskofrainmod.init.ItemInit;
import io.github.colochampre.riskofrainmod.init.SoundInit;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RoRmod.MODID)
@Mod.EventBusSubscriber
public class RoRmod {
  public static final String MODID = "riskofrainmod";

  public RoRmod() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    bus.addListener(this::addItemsToTabs);

    SoundInit.SOUNDS.register(bus);
    ItemInit.ITEMS.register(bus);
    EntityInit.ENTITY_TYPES.register(bus);
  }

  private void addItemsToTabs(CreativeModeTabEvent.BuildContents event) {
    if (event.getTab() == CreativeModeTabs.SPAWN_EGGS)  {
      event.accept(ItemInit.LEMURIAN_SPAWN_EGG);
    }
  }
}
