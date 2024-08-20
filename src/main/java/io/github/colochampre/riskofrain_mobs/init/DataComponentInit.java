package io.github.colochampre.riskofrain_mobs.init;

import com.mojang.serialization.Codec;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public class DataComponentInit {
  public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, RoRmod.MODID);

  public static final RegistryObject<DataComponentType<Integer>> COLOR_ID = COMPONENTS.register("color_id",
          () -> DataComponentType.<Integer>builder()
                  .persistent(ExtraCodecs.intRange(0, 15))
                  .networkSynchronized(ByteBufCodecs.VAR_INT)
                  .build()
  );

  public static final RegistryObject<DataComponentType<Float>> HEALTH = COMPONENTS.register("health",
          () -> DataComponentType.<Float>builder()
                  .persistent(Codec.FLOAT)
                  .networkSynchronized(ByteBufCodecs.FLOAT)
                  .build()
  );

  public static final RegistryObject<DataComponentType<UUID>> OWNER_UUID = COMPONENTS.register("owner_uuid",
          () -> DataComponentType.<UUID>builder()
                  .persistent(UUIDUtil.CODEC)
                  .networkSynchronized(UUIDUtil.STREAM_CODEC)
                  .build()
  );

  public static void register(IEventBus bus) {
    COMPONENTS.register(bus);
  }
}
