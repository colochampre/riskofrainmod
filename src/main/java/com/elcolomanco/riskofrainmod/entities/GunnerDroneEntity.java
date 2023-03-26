package com.elcolomanco.riskofrainmod.entities;

import com.elcolomanco.riskofrainmod.entities.goals.GunnerDroneAttackGoal;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class GunnerDroneEntity extends AbstractFlyingDroneEntity implements IRangedAttackMob {
	
	private final GunnerDroneAttackGoal attackGoal = new GunnerDroneAttackGoal(this, 16.0F);

	public GunnerDroneEntity(EntityType<? extends GunnerDroneEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new FlyingMovementController(this, 16, true);
		this.setPathPriority(PathNodeType.DANGER_FIRE, -1.0F);
		this.setPathPriority(PathNodeType.DAMAGE_FIRE, -1.0F);
		this.setPathPriority(PathNodeType.WATER, -1.0F);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SitGoal(this));
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 5, false, false, (entity) -> {
			return entity instanceof IMob && !(entity instanceof CreeperEntity);
		}));
	}
	
	public static AttributeModifierMap.MutableAttribute setAttributes() {
		return MobEntity.registerAttributes()
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D)
				.createMutableAttribute(Attributes.FLYING_SPEED, (double)0.8F)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D)
				.createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D);
	}
	
	@SuppressWarnings("deprecation")
	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
		return worldIn.getBlockState(pos).isAir() ? 10.0F : 0.0F;
	}
	
	@Override
	protected void updateAITasks() {
		if (this.isTamed()) {
			this.goalSelector.addGoal(2, this.attackGoal);
		}
		super.updateAITasks();
	}
	
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
	    return 0.055F;
	}
	
	@Override
    public ItemStack getPickedResult(RayTraceResult target) {
    	return new ItemStack(RegistrySetup.GUNNER_DRONE_SPAWN_EGG.get());
    }

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		
	}
}
