package io.github.colochampre.riskofrain_items.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.colochampre.riskofrain_items.util.Util;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class UncommonLootTableModifier implements IGlobalLootModifier {

    public static final Supplier<Codec<UncommonLootTableModifier>> CODEC = () ->
            RecordCodecBuilder.create(inst -> inst.group(LOOT_CONDITIONS_CODEC.fieldOf("conditions")
                    .forGetter(lm -> lm.conditions)).apply(inst, UncommonLootTableModifier::new));

    private final LootItemCondition[] conditions;

    private final Predicate<LootContext> orConditions;

    protected UncommonLootTableModifier(LootItemCondition[] conditionsIn) {
        this.conditions = conditionsIn;
        this.orConditions = LootItemConditions.orConditions(conditionsIn);
    }

    @NotNull
    @Override
    public ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        return this.orConditions.test(context) ? this.doApply(generatedLoot, context) : generatedLoot;
    }

    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextInt(1) == 0) { // 100% spawn chance
            generatedLoot.add(new ItemStack(Util.getLargeChestItem()));
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
