package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public class LensMakersGlassesItem extends Item {

  public LensMakersGlassesItem(Properties props) {
    super(props);
  }

  public static SoundEvent getProcSound() {
    return SoundInit.LENS_CRIT_PROC.get();
  }
}
