package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public class CrowbarItem extends Item {

  public CrowbarItem(Properties props) {
    super(props);
  }

  public static SoundEvent getProcSound() {
    return SoundInit.CROWBAR_PROC.get();
  }
}
