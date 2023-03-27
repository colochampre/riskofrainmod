package io.github.colochampre.riskofrain_items.events;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.init.ItemInit;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RoRitems.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
  public static CreativeModeTab RISKOFRAIN_TAB;

  @SubscribeEvent
  public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
    RISKOFRAIN_TAB = event.registerCreativeModeTab(new ResourceLocation(RoRitems.MODID, "riskofrain_tab"),
            builder -> builder.icon(() -> new ItemStack(ItemInit.COMMON_CHEST.get()))
                    .title(Component.translatable("creativemodetab.riskofrain_tab")));
  }
}
