package io.github.colochampre.riskofrain_items.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.init.ItemInit;
import io.github.colochampre.riskofrain_items.util.ItemUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class GPTLootTableModifier extends LootModifier {
  //private final Item lootItem;

  protected GPTLootTableModifier(LootItemCondition[] conditions) {
    super(conditions);
    //this.lootItem = lootItem;
  }

  //public Item getLootItem() { return lootItem; }

  @Override
  protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
    RoRitems.LOGGER.info("RoR LootModifier applied!");
    if (lootContext.getRandom().nextInt(2) == 0) { // 50% spawn chance
      RoRitems.LOGGER.info("Adding custom item to loot");
      int randomInt = lootContext.getRandom().nextInt(2) + 1; // 50/50 1-2 items
      //generatedLoot.add(new ItemStack(ItemUtil.getSmallChestItem(), randomInt));
      generatedLoot.add(new ItemStack(ItemInit.TOUGHER_TIMES.get()));
    }
    return generatedLoot;
  }

  @Override
  public MapCodec<? extends IGlobalLootModifier> codec() {
    return CODEC;
  }

  public static final MapCodec<GPTLootTableModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
          instance.group(
                  LootModifier.LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(modifier -> modifier.conditions)
          ).apply(instance, GPTLootTableModifier::new)
  );
}
