package io.github.colochampre.riskofrainmod.events;

import io.github.colochampre.riskofrainmod.RoRmod;
import io.github.colochampre.riskofrainmod.entities.LemurianEntity;
import io.github.colochampre.riskofrainmod.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {

  @SubscribeEvent
  public static void entityAttributes(EntityAttributeCreationEvent event) {
    event.put(EntityInit.LEMURIAN_ENTITY.get(), LemurianEntity.createAttributes().build());
  }
}
