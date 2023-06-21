package io.github.colochampre.riskofrain_items.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LootTableModifier extends LootModifier {
  public static final Supplier<Codec<LootTableModifier>> CODEC = Suppliers.memoize(() ->
          RecordCodecBuilder.create(inst -> codecStart(inst)
                  .and(ResourceLocation.CODEC.fieldOf("loot_table").forGetter((m) -> m.lootTableId))
                  .apply(inst, LootTableModifier::new)));

  private final ResourceLocation lootTableId;

  protected LootTableModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTableId) {
    super(conditionsIn);
    this.lootTableId = lootTableId;
  }

  @Override
  protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    LootTable extraTable = context.getLootTable(this.lootTableId);

    extraTable.getRandomItems(context, generatedLoot::add);
    return generatedLoot;
  }

  @Override
  public Codec<? extends IGlobalLootModifier> codec() {
    return CODEC.get();
  }
}
