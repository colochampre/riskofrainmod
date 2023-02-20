package io.github.colochampre.riskofrainmod;

import io.github.colochampre.riskofrainmod.init.EntityInit;
import io.github.colochampre.riskofrainmod.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RoRmod.MODID)
public class RoRmod {
  public static final String MODID = "riskofrainmod";

  public RoRmod() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    ItemInit.ITEMS.register(bus);
    EntityInit.ENTITY_TYPES.register(bus);
  }
}
