package io.github.colochampre.riskofrain_items.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.colochampre.riskofrain_items.init.LootInit;
import io.github.colochampre.riskofrain_items.util.Util;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class CommonLootTableModifier extends LootModifier {

  public static final Supplier<MapCodec<CommonLootTableModifier>> CODEC =
          Suppliers.memoize(() -> RecordCodecBuilder.mapCodec(inst -> codecStart(inst).apply(inst, CommonLootTableModifier::new)));

  /*public static final Supplier<MapCodec<CommonLootTableModifier>> CODEC = () ->
          RecordCodecBuilder.create(inst -> inst.group(LOOT_CONDITIONS_CODEC.fieldOf("conditions")
                  .forGetter(lm -> lm.conditions)).apply(inst, CommonLootTableModifier::new));*/

  //private final LootItemCondition[] conditions;

  //private final Predicate<LootContext> orConditions;

  protected CommonLootTableModifier(LootItemCondition[] conditionsIn) {
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
  protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext context) {
    if (context.getRandom().nextInt(2) == 0) { // 50% spawn chance
      Random random = new Random();
      int randomInt = random.nextInt(2) + 1; // 50/50 1-2 items
      objectArrayList.add(new ItemStack(Util.getSmallChestItem(), randomInt));
    }
    return objectArrayList;
  }

  @Override
  public MapCodec<? extends IGlobalLootModifier> codec() {
    return LootInit.ADD_COMMON_LOOT.get();
  }
}
