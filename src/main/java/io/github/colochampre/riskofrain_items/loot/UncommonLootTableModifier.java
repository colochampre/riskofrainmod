package io.github.colochampre.riskofrain_items.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.datafixers.Products;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.colochampre.riskofrain_items.init.LootInit;
import net.minecraft.Util;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class UncommonLootTableModifier implements IGlobalLootModifier {

  protected final LootItemCondition[] conditions;
  private final Predicate<LootContext> combinedConditions;

  public static final Codec<UncommonLootTableModifier> CODEC =
          RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, UncommonLootTableModifier::new));

  protected static <T extends UncommonLootTableModifier> Products.P1<RecordCodecBuilder.Mu<T>, LootItemCondition[]> codecStart(RecordCodecBuilder.Instance<T> instance) {
    return instance.group(LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(lm -> lm.conditions));
  }

  protected UncommonLootTableModifier(LootItemCondition[] conditionsIn) {
    //super(conditionsIn);
    this.conditions = conditionsIn;
    this.combinedConditions = net.minecraft.Util.allOf(Arrays.asList(conditionsIn));
  }

  @NotNull
  @Override
  public final ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    return this.combinedConditions.test(context) ? this.doApply(generatedLoot, context) : generatedLoot;
  }

  @NotNull
  protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    if (context.getRandom().nextInt(1) == 0) { // 100% spawn chance
      generatedLoot.add(new ItemStack(io.github.colochampre.riskofrain_items.util.Util.getLargeChestItem()));
    }
    return generatedLoot;
  }

  @Override
  public MapCodec<? extends IGlobalLootModifier> codec() {
    return LootInit.ADD_UNCOMMON_LOOT.get();
  }
}
