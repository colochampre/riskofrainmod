package io.github.colochampre.riskofrain_items.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.colochampre.riskofrain_items.util.Util;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class UncommonLootTableModifier extends LootModifier {

  public static final Supplier<MapCodec<UncommonLootTableModifier>> CODEC = Suppliers.memoize(() ->
          RecordCodecBuilder.mapCodec(inst -> codecStart(inst).apply(inst, UncommonLootTableModifier::new)));

  /*public static final Supplier<Codec<UncommonLootTableModifier>> CODEC = () ->
          RecordCodecBuilder.create(inst -> inst.group(LOOT_CONDITIONS_CODEC.fieldOf("conditions")
                  .forGetter(lm -> lm.conditions)).apply(inst, UncommonLootTableModifier::new));*/

  //private final LootItemCondition[] conditions;

  //private final Predicate<LootContext> orConditions;

  public UncommonLootTableModifier(LootItemCondition[] conditionsIn) {
    super(conditionsIn);
    //this.conditions = conditionsIn;
    //this.orConditions = LootItemConditions.orConditions(conditionsIn);
  }

  /*
  @NotNull
  @Override
  public ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
      return this.orConditions.test(context) ? this.doApply(generatedLoot, context) : generatedLoot;
  }
  */
  @Override
  protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    if (context.getRandom().nextInt(1) == 0) { // 100% spawn chance
      generatedLoot.add(new ItemStack(Util.getLargeChestItem()));
    }
    return generatedLoot;
  }

  @Override
  public MapCodec<? extends IGlobalLootModifier> codec() {
    return CODEC.get();
  }
}
