package com.elcolomanco.riskofrainmod.entities;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.elcolomanco.riskofrainmod.RoRconfig;
import com.elcolomanco.riskofrainmod.entities.goals.StoneGolemAttackGoal;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
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
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class StoneGolemEntity extends MonsterEntity {
	private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.createKey(StoneGolemEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> STONE_GOLEM_TYPE = EntityDataManager.createKey(StoneGolemEntity.class, DataSerializers.VARINT);
	
	private final HurtByTargetGoal hurtByTargetGoal = new HurtByTargetGoal(this);

	private int attackTimer;
	private LivingEntity targetedEntity;
	public int clientSideAttackTime;

	public StoneGolemEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.stepHeight = 1.0F;
		this.experienceValue = 20;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new StoneGolemAttackGoal(this, 0.8D, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 12.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractIllagerEntity.class, true));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractRaiderEntity.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, VexEntity.class, true));
		this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, WanderingTraderEntity.class, true));
	}
	
	public static AttributeModifierMap.MutableAttribute setAttributes() {
		return MobEntity.registerAttributes()
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 12.0D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 24.0D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.createMutableAttribute(Attributes.MAX_HEALTH, 100.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
	}
	
	@Override
	protected void updateAITasks() {
		LivingEntity target = this.getAttackTarget();
		if (target == null) {
			this.goalSelector.addGoal(9, this.hurtByTargetGoal);
		}
		super.updateAITasks();
	}

	@Override
    public ItemStack getPickedResult(RayTraceResult target) {
    	return new ItemStack(RegistrySetup.STONE_GOLEM_SPAWN_EGG.get());
    }

	protected void registerData() {
		super.registerData();
		this.dataManager.register(TARGET_ENTITY, 0);
		this.dataManager.register(STONE_GOLEM_TYPE, 0);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putString("Type", this.getVariantType().getName());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setVariantType(StoneGolemEntity.Type.getTypeByName(compound.getString("Type")));
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		this.attackTimer = 15;
		this.world.setEntityState(this, (byte)4);
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
		if (flag) {
			entityIn.setMotion(entityIn.getMotion().add(0.0D, (double)0.75F, 0.0D));
			this.applyEnchantments(this, entityIn);
		}
		this.playSound(this.getClapSound(), 1.5F, 1.0F);
		return flag;
	}
	
	protected SoundEvent getClapSound() {
		return RegistrySetup.STONE_GOLEM_CLAP.get();
	}

	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.attackTimer = 15;
			this.playSound(this.getClapSound(), 1.5F, 1.0F);
			super.handleStatusUpdate(id);
		}
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
	
	@Override
	public boolean isNoDespawnRequired() {
		return RoRconfig.DESPAWN_STONE_GOLEM;
	}
	
	protected int decreaseAirSupply(int air) {
		return air;
	}
	
	public boolean isPushedByWater() {
		return false;
	}
	
	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		this.playSound(this.getSpawnSound(), 1.0F, 1.0F);

		Optional<RegistryKey<Biome>> optional = world.func_242406_i(this.getPosition());
		StoneGolemEntity.Type stonegolementity$type = StoneGolemEntity.Type.getTypeByBiome(optional);
		if (spawnData instanceof StoneGolemEntity.StoneGolemData) {
			spawnData = new StoneGolemEntity.StoneGolemData(stonegolementity$type);
		}
		this.setVariantType(stonegolementity$type);
		
		return super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	public StoneGolemEntity.Type getVariantType() {
		return StoneGolemEntity.Type.getTypeByIndex(this.dataManager.get(STONE_GOLEM_TYPE));
	}

	private void setVariantType(StoneGolemEntity.Type typeIn) {
		this.dataManager.set(STONE_GOLEM_TYPE, typeIn.getIndex());
	}

	public int getAttackDuration() {
		return 75;
	}

	public void setTargetedEntity(int entityId) {
		this.dataManager.set(TARGET_ENTITY, entityId);
	}

	public boolean hasTargetedEntity() {
		return this.dataManager.get(TARGET_ENTITY) != 0;
	}

	@Nullable
	public LivingEntity getTargetedEntity() {
		if (!this.hasTargetedEntity()) {
			return null;
		} else if (this.world.isRemote) {
			if (this.targetedEntity != null) {
				return this.targetedEntity;
			} else {
				Entity entity = this.world.getEntityByID(this.dataManager.get(TARGET_ENTITY));
				if (entity instanceof LivingEntity) {
					this.targetedEntity = (LivingEntity)entity;
					return this.targetedEntity;
				} else {
					return null;
				}
			}
		} else {
			return this.getAttackTarget();
		}
	}
	
	public void notifyDataManagerChange(DataParameter<?> key) {
		super.notifyDataManagerChange(key);
		if (TARGET_ENTITY.equals(key)) {
			this.clientSideAttackTime = 0;
			this.targetedEntity = null;
		}
	}

	public void livingTick() {
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}

		if (this.isAlive()) {
			if (this.collidedHorizontally && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
				boolean flag = false;
				AxisAlignedBB axisalignedbb = this.getBoundingBox().grow(0.2D);;

				for(BlockPos blockpos : BlockPos.getAllInBoxMutable(MathHelper.floor(axisalignedbb.minX), MathHelper.floor(axisalignedbb.minY), MathHelper.floor(axisalignedbb.minZ), MathHelper.floor(axisalignedbb.maxX), MathHelper.floor(axisalignedbb.maxY), MathHelper.floor(axisalignedbb.maxZ))) {
					BlockState blockstate = this.world.getBlockState(blockpos);
					Block block = blockstate.getBlock();
					if (block instanceof LeavesBlock) {
						flag = this.world.destroyBlock(blockpos, true, this) || flag;
					}
				}
			}
		}
		super.livingTick();
	}

	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public void onDeath(DamageSource cause) {
		this.playSound(this.getDeathVoiceSound(), 0.8F, 1.0F);
		super.onDeath(cause);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return RegistrySetup.STONE_GOLEM_DEATH.get();
	}

	protected SoundEvent getDeathVoiceSound() {
		return RegistrySetup.STONE_GOLEM_DEATH_VOICE.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return RegistrySetup.STONE_GOLEM_HURT.get();
	}

	protected SoundEvent getSpawnSound() {
		return RegistrySetup.STONE_GOLEM_SPAWN.get();
	}

	protected SoundEvent getStepSound() {
		return RegistrySetup.STONE_GOLEM_STEP.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(this.getStepSound(), 0.5F, 1.0F);
	}

	@Override
    protected float getSoundVolume() {
		return 0.8F;
    }
	
	public boolean canBePushed() {
		return false;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
	    return 3.5F;
	}

	public boolean isEntityMoving() {
		if(this.prevPosX != this.getPosX() || this.prevPosZ != this.getPosZ()) {
			return true;
		} else {
			return false;
		}
	}

	public int getMaxFallHeight() {
		return 24;
	}

	public boolean onLivingFall(float distance, float damageMultiplier) {
		this.playSound(this.getStepSound(), 1.5F, 1.5F);
		this.playSound(this.getStepSound(), 1.5F, 1.5F);
		return false;
	}
	
	public float getAttackAnimationScale(float p_175477_1_) {
	      return ((float)this.clientSideAttackTime + p_175477_1_) / (float)this.getAttackDuration();
	   }

	protected PathNavigator createNavigator(World worldIn) {
		return new StoneGolemEntity.Navigator(this, worldIn);
	}

	static class Navigator extends GroundPathNavigator {
		public Navigator(MobEntity p_i50754_1_, World p_i50754_2_) {
			super(p_i50754_1_, p_i50754_2_);
		}

		protected PathFinder getPathFinder(int p_179679_1_) {
			this.nodeProcessor = new StoneGolemEntity.Processor();
			return new PathFinder(this.nodeProcessor, p_179679_1_);
		}
	}

	static class Processor extends WalkNodeProcessor {
		private Processor() {
		}

		protected PathNodeType func_215744_a(IBlockReader p_215744_1_, boolean p_215744_2_, boolean p_215744_3_, BlockPos p_215744_4_, PathNodeType p_215744_5_) {
			return p_215744_5_ == PathNodeType.LEAVES ? PathNodeType.OPEN : super.func_215744_a(p_215744_1_, p_215744_2_, p_215744_3_, p_215744_4_, p_215744_5_);
		}
	}
	
	public static class StoneGolemData implements ILivingEntityData {
		public final StoneGolemEntity.Type field_220366_a;

		public StoneGolemData(StoneGolemEntity.Type p_i50734_1_) {
			this.field_220366_a = p_i50734_1_;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static enum Type {
		NEUTRAL(0, "neutral", Biomes.BEACH, Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.RIVER, Biomes.STONE_SHORE),
		BADLANDS(1, "badlands", Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.WOODED_BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_BADLANDS_PLATEAU),
		DARK_FOREST(2, "dark_forest", Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS),
		DESERT(3, "desert", Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES),
		EXTREME_HILLS(4, "extreme_hills", Biomes.MOUNTAINS, Biomes.WOODED_MOUNTAINS),
		FOREST(5, "forest", Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS,Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, Biomes.FLOWER_FOREST),
		JUNGLE(6, "jungle", Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE, Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE),
		PLAINS(7, "plains", Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS),
		SAVANNA(8, "savanna", Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU),
		SNOW(9, "snow", Biomes.FROZEN_RIVER, Biomes.SNOWY_TUNDRA, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_BEACH, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.ICE_SPIKES),
		SWAMP(10, "swamp", Biomes.SWAMP, Biomes.SWAMP_HILLS),
		TAIGA(11, "taiga", Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.TAIGA_MOUNTAINS, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS);

		private static final StoneGolemEntity.Type[] field_221088_c = Arrays.stream(values()).sorted(Comparator.comparingInt(StoneGolemEntity.Type::getIndex)).toArray((p_221084_0_) -> {
			return new StoneGolemEntity.Type[p_221084_0_];
		});
		private static final Map<String, StoneGolemEntity.Type> TYPES_BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(StoneGolemEntity.Type::getName, (p_221081_0_) -> {
			return p_221081_0_;
		}));
		private final int index;
		private final String name;
		private final List<RegistryKey<Biome>> spawnBiomes;

		private Type(int indexIn, String nameIn, RegistryKey<Biome>... spawnBiomes) {
			this.index = indexIn;
			this.name = nameIn;
			this.spawnBiomes = Arrays.asList(spawnBiomes);
		}

		public String getName() {
			return this.name;
		}

		public int getIndex() {
			return this.index;
		}

		public static StoneGolemEntity.Type getTypeByName(String nameIn) {
			return TYPES_BY_NAME.getOrDefault(nameIn, NEUTRAL);
		}

		public static StoneGolemEntity.Type getTypeByIndex(int indexIn) {
			if (indexIn < 0 || indexIn > field_221088_c.length) {
				indexIn = 0;
			}

			return field_221088_c[indexIn];
		}

		public static StoneGolemEntity.Type getTypeByBiome(Optional<RegistryKey<Biome>> biomeIn) {

			StoneGolemEntity.Type result = NEUTRAL;

			if (BADLANDS.spawnBiomes.contains(biomeIn.get())) {
				result = BADLANDS;
			} else if (DARK_FOREST.spawnBiomes.contains(biomeIn.get())) {
				result = DARK_FOREST;
			} else if (DESERT.spawnBiomes.contains(biomeIn.get())) {
				result = DESERT;
			} else if (EXTREME_HILLS.spawnBiomes.contains(biomeIn.get())) {
				result = EXTREME_HILLS;
			}else if (FOREST.spawnBiomes.contains(biomeIn.get())) {
				result = FOREST;
			} else if (JUNGLE.spawnBiomes.contains(biomeIn.get())) {
				result = JUNGLE;
			} else if (PLAINS.spawnBiomes.contains(biomeIn.get())) {
				result = PLAINS;
			} else if (SAVANNA.spawnBiomes.contains(biomeIn.get())) {
				result = SAVANNA;
			} else if (SNOW.spawnBiomes.contains(biomeIn.get())) {
				result = SNOW;
			} else if (SWAMP.spawnBiomes.contains(biomeIn.get())) {
				result = SWAMP;
			} else if (TAIGA.spawnBiomes.contains(biomeIn.get())) {
				result = TAIGA;
			}
			return result;
		}
	}
}
