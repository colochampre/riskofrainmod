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
import java.util.Random;
import java.util.function.Predicate;

public class CommonLootTableModifier implements IGlobalLootModifier {

  protected final LootItemCondition[] conditions;
  private final Predicate<LootContext> combinedConditions;

  public static final Codec<CommonLootTableModifier> CODEC = RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, CommonLootTableModifier::new));

  protected static <T extends CommonLootTableModifier> Products.P1<RecordCodecBuilder.Mu<T>, LootItemCondition[]> codecStart(RecordCodecBuilder.Instance<T> instance) {
    return instance.group(LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(lm -> lm.conditions));
  }

  protected CommonLootTableModifier(LootItemCondition[] conditionsIn) {
    //super(conditionsIn);
    this.conditions = conditionsIn;
    this.combinedConditions = Util.allOf(Arrays.asList(conditionsIn));
  }

  @NotNull
  @Override
  public final ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    return this.combinedConditions.test(context) ? this.doApply(generatedLoot, context) : generatedLoot;
  }

  @NotNull
  protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
    if (context.getRandom().nextInt(2) == 0) { // 50% spawn chance
      Random random = new Random();
      int randomInt = random.nextInt(2) + 1; // 50/50 1-2 items
      generatedLoot.add(new ItemStack(io.github.colochampre.riskofrain_items.util.Util.getSmallChestItem(), randomInt));
    }
    return generatedLoot;
  }

  @Override
  public MapCodec<? extends IGlobalLootModifier> codec() {
    return LootInit.ADD_COMMON_LOOT.get();
  }
}
