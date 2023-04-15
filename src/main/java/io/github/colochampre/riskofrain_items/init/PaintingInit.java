package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PaintingInit {
  public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, RoRitems.MODID);

  public static final RegistryObject<PaintingVariant> FANART_BETAWHO_1 = PAINTING_VARIANTS.register("fanart_betawho_1",
          () -> new PaintingVariant(32, 16));
  public static final RegistryObject<PaintingVariant> FANART_BETAWHO_2 = PAINTING_VARIANTS.register("fanart_betawho_2",
          () -> new PaintingVariant(64, 32));
  public static final RegistryObject<PaintingVariant> FANART_BETAWHO_3 = PAINTING_VARIANTS.register("fanart_betawho_3",
          () -> new PaintingVariant(64, 32));
  public static final RegistryObject<PaintingVariant> FANART_BETAWHO_4 = PAINTING_VARIANTS.register("fanart_betawho_4",
          () -> new PaintingVariant(32, 32));
  public static final RegistryObject<PaintingVariant> FANART_BETAWHO_5 = PAINTING_VARIANTS.register("fanart_betawho_5",
          () -> new PaintingVariant(64, 32));
  public static final RegistryObject<PaintingVariant> FANART_CORRECT_APPLE = PAINTING_VARIANTS.register("fanart_correct_apple_1",
          () -> new PaintingVariant(16, 16));
  public static final RegistryObject<PaintingVariant> FANART_DINAMITTIN = PAINTING_VARIANTS.register("fanart_dinamittin_1",
          () -> new PaintingVariant(64, 32));
  public static final RegistryObject<PaintingVariant> FANART_DRANSVITRY = PAINTING_VARIANTS.register("fanart_dransvitry_1",
          () -> new PaintingVariant(32, 16));
  public static final RegistryObject<PaintingVariant> FANART_LAMBOSAURUS = PAINTING_VARIANTS.register("fanart_lambosaurus_1",
          () -> new PaintingVariant(32, 32));
  public static final RegistryObject<PaintingVariant> FANART_MAFFI1996 = PAINTING_VARIANTS.register("fanart_maffi1996_1",
          () -> new PaintingVariant(32, 16));
  public static final RegistryObject<PaintingVariant> FANART_PCMYTH = PAINTING_VARIANTS.register("fanart_pcmyth_1",
          () -> new PaintingVariant(32, 16));
  public static final RegistryObject<PaintingVariant> FANART_SHAMAN_INFINITUS = PAINTING_VARIANTS.register("fanart_shaman_1",
          () -> new PaintingVariant(32, 16));
  public static final RegistryObject<PaintingVariant> FANART_SUPERUBERKRUBER = PAINTING_VARIANTS.register("fanart_superuberkruber_1",
          () -> new PaintingVariant(32, 32));
  public static final RegistryObject<PaintingVariant> FANART_THICK_JUDGE_JUDY = PAINTING_VARIANTS.register("fanart_thick_judge_judy_1",
          () -> new PaintingVariant(16, 16));
  public static final RegistryObject<PaintingVariant> FANART_TINK29 = PAINTING_VARIANTS.register("fanart_tink29_1",
          () -> new PaintingVariant(64, 32));
  public static final RegistryObject<PaintingVariant> FANART_ZOOCHMAN_1 = PAINTING_VARIANTS.register("fanart_zoochman_1",
          () -> new PaintingVariant(16, 32));
  public static final RegistryObject<PaintingVariant> FANART_ZOOCHMAN_2 = PAINTING_VARIANTS.register("fanart_zoochman_2",
          () -> new PaintingVariant(16, 32));
  public static final RegistryObject<PaintingVariant> OFFICIAL_COMMANDO = PAINTING_VARIANTS.register("official_commando",
          () -> new PaintingVariant(16, 16));
  public static final RegistryObject<PaintingVariant> OFFICIAL_ENGINEER = PAINTING_VARIANTS.register("official_engineer",
          () -> new PaintingVariant(16, 16));
  public static final RegistryObject<PaintingVariant> OFFICIAL_OMEGA = PAINTING_VARIANTS.register("official_omega_1",
          () -> new PaintingVariant(64, 32));
  public static final RegistryObject<PaintingVariant> OFFICIAL_ROR_RETURNS = PAINTING_VARIANTS.register("official_risk_of_rain_returns",
          () -> new PaintingVariant(64, 32));
  public static final RegistryObject<PaintingVariant> OFFICIAL_STONE_GOLEM = PAINTING_VARIANTS.register("official_stone_golem",
          () -> new PaintingVariant(16, 16));
}
