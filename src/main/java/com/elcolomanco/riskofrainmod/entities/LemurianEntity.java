package com.elcolomanco.riskofrainmod.entities;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.elcolomanco.riskofrainmod.entities.goals.LemurianAttackGoal;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LemurianEntity extends MonsterEntity {

	private static final DataParameter<Integer> LEMURIAN_TYPE = EntityDataManager.createKey(StoneGolemEntity.class, DataSerializers.VARINT);

	private boolean selectHand = true;
	private int attackTimer;
	private int choice = 0;

	public LemurianEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.experienceValue = 15;
		this.setCanPickUpLoot(true);
		((GroundPathNavigator)this.getNavigator()).setBreakDoors(true);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, IronGolemEntity.class, 8.0F, 0.8D, 1.0D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CreeperEntity.class, 8.0F, 0.8D, 1.0D));
		this.goalSelector.addGoal(4, new LemurianAttackGoal(this, 1.0D, 24.0F, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WanderingTraderEntity.class, true));
	}
	
	public static AttributeModifierMap.MutableAttribute setAttributes() {
		return MobEntity.registerAttributes()
				.createMutableAttribute(Attributes.ARMOR, 4.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.5D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
    public ItemStack getPickedResult(RayTraceResult target) {
    	return new ItemStack(RegistrySetup.LEMURIAN_SPAWN_EGG.get());
    }

	protected void registerData() {
		super.registerData();
		this.dataManager.register(LEMURIAN_TYPE, 0);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putString("Type", this.getVariantType().getName());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setVariantType(LemurianEntity.Type.getTypeByName(compound.getString("Type")));
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		Optional<RegistryKey<Biome>> optional = worldIn.func_242406_i(this.getPosition());
	      LemurianEntity.Type lemurianentity$type = LemurianEntity.Type.getTypeByBiome(optional);
	      if (spawnDataIn instanceof LemurianEntity.LemurianData) {
	         spawnDataIn = new LemurianEntity.LemurianData(lemurianentity$type);
	      }
		
		this.setVariantType(lemurianentity$type);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public LemurianEntity.Type getVariantType() {
		return LemurianEntity.Type.getTypeByIndex(this.dataManager.get(LEMURIAN_TYPE));
	}

	private void setVariantType(LemurianEntity.Type typeIn) {
		this.dataManager.set(LEMURIAN_TYPE, typeIn.getIndex());
	}

	public boolean attackEntityAsMob(Entity entityIn) {
		this.attackTimer = 10;
		this.world.setEntityState(this, (byte)4);
		boolean flag;
		flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
		if (flag) {
			this.applyEnchantments(this, entityIn);
		}
		this.playSound(this.getAttackSound(), 1.0F, 1.0F);
		return flag;
	}

	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (super.attackEntityFrom(source, amount)) {
			LivingEntity livingentity = this.getAttackTarget();
			if (livingentity == null && source.getTrueSource() instanceof LivingEntity) {
				livingentity = (LivingEntity)source.getTrueSource();
			}
			return true;
		} else {
			return false;
		}
	}

	public void livingTick() {
		super.livingTick();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
			this.playSound(this.getAttackSound(), 1.0F, 1.0F);
			super.handleStatusUpdate(id);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return RegistrySetup.LEMURIAN_AMBIENT.get();
	}

	protected SoundEvent getAttackSound() {
		return RegistrySetup.LEMURIAN_ATTACK.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return RegistrySetup.LEMURIAN_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return RegistrySetup.LEMURIAN_HURT.get();
	}

	protected SoundEvent getStepSound() {
		return RegistrySetup.LEMURIAN_STEP.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(this.getStepSound(), 0.15F, 1.0F);
	}
	
	@Override
    protected float getSoundVolume() {

		return 0.6F;
    }

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
	    return 1.62F;
	}

	public boolean isEntityMoving() {
		if(this.prevPosX != this.getPosX() || this.prevPosZ != this.getPosZ()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getSelectHand() {
		return this.selectHand;
	}
	
	public void setSelectHand(boolean value) {
		this.selectHand = value;
	}
	
	public int getChoice() {
		choice = ++choice % 2;
		return choice;
	}

	@SuppressWarnings("unchecked")
	public enum Type {
		NEUTRAL	(0, "neutral", Biomes.PLAINS),
		HOT_BIOMES	(1, "hot_biomes",  Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES, Biomes.BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_BADLANDS_PLATEAU, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU, Biomes.NETHER_WASTES, Biomes.CRIMSON_FOREST, Biomes.BASALT_DELTAS);
		
		private static final LemurianEntity.Type[] LEMURIAN_TYPES = (LemurianEntity.Type[])Arrays.stream(values()).sorted(Comparator.comparingInt(LemurianEntity.Type::getIndex)).toArray((p_221084_0_) -> {
			return new LemurianEntity.Type[p_221084_0_];
		});
		private static final Map<String, LemurianEntity.Type> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(LemurianEntity.Type::getName, (p_221081_0_) -> {
			return p_221081_0_;
		}));
		private final int index;
		private final String name;
		private final List<RegistryKey<Biome>> spawnBiomes;
		
		private Type(int index, String name, RegistryKey<Biome>... spawnBiomes) {
			this.index = index;
			this.name = name;
			this.spawnBiomes = Arrays.asList(spawnBiomes);
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getIndex() {
			return this.index;
		}
		
		public static LemurianEntity.Type getTypeByName(String nameIn) {
			return (LemurianEntity.Type)TYPES_BY_NAME.getOrDefault(nameIn, NEUTRAL);
		}
		
		public static LemurianEntity.Type getTypeByIndex(int indexIn) {
			if (indexIn < 0 || indexIn > LEMURIAN_TYPES.length) {
				indexIn = 0;
			}
			return LEMURIAN_TYPES[indexIn];
		}
		
		public static LemurianEntity.Type getTypeByBiome(Optional<RegistryKey<Biome>> spawnBiomes) {
			LemurianEntity.Type result = NEUTRAL;
			if (HOT_BIOMES.spawnBiomes.contains(spawnBiomes.get())) {
				result = HOT_BIOMES;
			}
			return result;
		}
	}
	
	public static class LemurianData implements ILivingEntityData {
		public final LemurianEntity.Type field_220366_a;

		public LemurianData(LemurianEntity.Type p_i50734_1_) {
			this.field_220366_a = p_i50734_1_;
		}
	}
}
