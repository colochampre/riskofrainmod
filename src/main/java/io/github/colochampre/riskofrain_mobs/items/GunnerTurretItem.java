package io.github.colochampre.riskofrain_mobs.items;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerTurretEntity;
import io.github.colochampre.riskofrain_mobs.init.DataComponentInit;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public class GunnerTurretItem extends Item {
  private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);

  public GunnerTurretItem(Properties props) {
    super(props);
  }

  @Override
  public @NotNull InteractionResultHolder<ItemStack> use(@SuppressWarnings("null") @NotNull Level level, @SuppressWarnings("null") @NotNull Player player, @SuppressWarnings("null") @NotNull InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
    if (hitresult.getType() == HitResult.Type.MISS) {
      return InteractionResultHolder.pass(itemstack);
    } else {
      Vec3 vec3 = player.getViewVector(1.0F);
      List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
      if (!list.isEmpty()) {
        Vec3 vec31 = player.getEyePosition();
        for (Entity entity : list) {
          AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
          if (aabb.contains(vec31)) {
            return InteractionResultHolder.pass(itemstack);
          }
        }
      }
      if (hitresult.getType() == HitResult.Type.BLOCK) {
        GunnerTurretEntity turret = new GunnerTurretEntity(EntityInit.GUNNER_TURRET_ENTITY.get(), level);
        turret.moveTo(hitresult.getLocation().x, hitresult.getLocation().y, hitresult.getLocation().z);
        turret.setYRot(player.getYRot());

        float maxHealth = RoRConfig.SERVER.GUNNER_TURRET_MAX_HEALTH.get().floatValue();
        float health = itemstack.getOrDefault(DataComponentInit.HEALTH.get(), maxHealth);
        int colorId = itemstack.getOrDefault(DataComponentInit.COLOR_ID.get(), 3);
        UUID ownerUUID = itemstack.getOrDefault(DataComponentInit.OWNER_UUID.get(), player.getUUID());

        turret.setHealth(health);
        turret.setBodyColor(DyeColor.byId(colorId));
        turret.tame(Objects.requireNonNull(level.getPlayerByUUID(ownerUUID)));

        if (!level.noCollision(turret, turret.getBoundingBox())) {
          return InteractionResultHolder.fail(itemstack);
        } else {
          if (!level.isClientSide) {
            level.addFreshEntity(turret);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, hitresult.getLocation());
            if (!player.getAbilities().instabuild) {
              itemstack.shrink(1);
            }
          }
          return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }
      } else {
        return InteractionResultHolder.pass(itemstack);
      }
    }
  }
}
