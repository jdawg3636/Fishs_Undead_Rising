package com.Fishmod.mod_LavaCow.entities.tameable;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.Fishmod.mod_LavaCow.config.FURConfig;
import com.Fishmod.mod_LavaCow.core.SpawnUtil;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FURTameableEntity extends TameableEntity {
	protected FURTameableEntity.State state;
	protected Goal wander;
	protected Goal follow;
	protected SitGoal aiSit;
	
	public FURTameableEntity(EntityType<? extends FURTameableEntity> p_i50240_1_, World worldIn) {
		super(p_i50240_1_, worldIn);
		this.setTame(false);
	}
	
	@Override
    protected void defineSynchedData() {
		super.defineSynchedData();
		this.state = FURTameableEntity.State.WANDERING;
	}
	
	@Override
	protected void registerGoals() {
    	this.wander = this.wanderGoal();
    	this.follow = this.followGoal();
    	this.aiSit = new SitGoal(this);    	
    	this.goalSelector.addGoal(7, this.wander);
	}
	
	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader p_205022_2_) {
		return 10.0F;
	}
	
	public static boolean isDarkEnoughToSpawn(IServerWorld p_223323_0_, BlockPos pos, Random p_223323_2_) {
		if (p_223323_0_.getBrightness(LightType.SKY, pos) > p_223323_2_.nextInt(32)) {
			return false;
		} else {
			int i = p_223323_0_.getLevel().isThundering() ? p_223323_0_.getMaxLocalRawBrightness(pos, 10) : p_223323_0_.getMaxLocalRawBrightness(pos);
			return i <= p_223323_2_.nextInt(8);
		}
	}

	public static boolean checkMonsterSpawnRules(EntityType<? extends FURTameableEntity> p_223325_0_, IServerWorld p_223325_1_, SpawnReason p_223325_2_, BlockPos p_223325_3_, Random p_223325_4_) {
		return p_223325_1_.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(p_223325_1_, p_223325_3_, p_223325_4_) && checkMobSpawnRules(p_223325_0_, p_223325_1_, p_223325_2_, p_223325_3_, p_223325_4_);
	}

	@Override
	public boolean removeWhenFarAway(double p_213397_1_) {
		return !(this.isTame() && this.getOwner() instanceof PlayerEntity);
	}
	
	@Override
    protected boolean shouldDespawnInPeaceful() {
	    return !(this.isTame() && this.getOwner() instanceof PlayerEntity);
    }   
	
	@Override
	public boolean requiresCustomPersistence() {
		return (this.isTame() && this.getOwner() instanceof PlayerEntity) || super.requiresCustomPersistence();
	}
    
    protected boolean isCommandable() {
    	return true;
    }
    
    protected boolean canSitCondition() {
    	return true;
    }
    
    public void doSitCommand(PlayerEntity playerIn) {    	
    	this.goalSelector.removeGoal(this.wander);
        this.jumping = false;
        this.getNavigation().stop();
		this.state = FURTameableEntity.State.SITTING;		
		this.setInSittingPose(true);
		if(playerIn != null)
			playerIn.displayClientMessage(new TranslationTextComponent("command.mod_lavacow.sitting", this.getName()), true);
    }
    
    public void doFollowCommand(PlayerEntity playerIn) {
		this.follow = this.followGoal();
		this.goalSelector.addGoal(6, this.follow);
		this.getNavigation().stop();
		this.state = FURTameableEntity.State.FOLLOWING;
		this.setInSittingPose(false);
		if(playerIn != null)
			playerIn.displayClientMessage(new TranslationTextComponent("command.mod_lavacow.following", this.getName()), true);
    }
    
    public void doWanderCommand(PlayerEntity playerIn) {
		this.goalSelector.removeGoal(this.follow);
		this.wander = this.wanderGoal();
		this.goalSelector.addGoal(7, this.wander);
		this.getNavigation().stop();
		this.state = FURTameableEntity.State.WANDERING;
		this.setInSittingPose(false);
		if(playerIn != null)
			playerIn.displayClientMessage(new TranslationTextComponent("command.mod_lavacow.wandering", this.getName()), true);
    }
    
    protected Goal wanderGoal() {
    	return new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F);
    }
    
    protected Goal followGoal() {
    	return new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false);
    }
    
    protected int TameRate(ItemStack stack) {
    	return 3;
    }
    
    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!this.level.isClientSide) {  
	    	if (this.isFood(itemstack) && canTameCondition()) {
	            if (!player.abilities.instabuild) {	               
	               if(itemstack.getItem() instanceof BucketItem) {
	            	   itemstack.shrink(1);
	            	   player.setItemInHand(hand, new ItemStack(Items.BUCKET));
	               } else {
	            	   itemstack.shrink(1);
	               }
	            }
	
	            if (this.random.nextInt(this.TameRate(itemstack)) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
	               this.tame(player);
	               this.navigation.stop();
	               this.setTarget((LivingEntity)null);
	               this.setHealth(this.getMaxHealth());
	               this.level.broadcastEntityEvent(this, (byte)7);
	            } else {
	               this.level.broadcastEntityEvent(this, (byte)6);
	            }
	
	            return ActionResultType.CONSUME;
	    	} else if (this.isTame() && this.isOwnedBy(player) && this.isCommandable() && this.getUsedItemHand().equals(hand)) {  
	    		if (!this.isFood(itemstack) && this.getPassengers().isEmpty() && !this.level.isClientSide) {
	    			if (this.state.equals(FURTameableEntity.State.WANDERING)) {
	    				if (this.canSitCondition()) {
	    					this.doSitCommand(player);
	    				}
	    			} else if (this.state.equals(FURTameableEntity.State.SITTING)) {
	    				this.doFollowCommand(player);
	    			} else if (this.state.equals(FURTameableEntity.State.FOLLOWING)) {
	    				this.doWanderCommand(player);
	    			}
			    		
	    			return ActionResultType.SUCCESS;
	    		}
	    	}
        }

        return super.mobInteract(player, hand);
    }
    
    protected boolean canTameCondition() {
    	return !this.isTame();
    }
    
    @Override
    public void tame(PlayerEntity player) {   	
    	super.tame(player);
    	this.doSitCommand(null);
    	this.doFollowCommand(null);
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    public void tick()
    {
        super.tick();
        
        if (!this.level.isClientSide && FURConfig.Suicidal_Minion.get() && (this.getOwner() != null && (!(this.getOwner() instanceof PlayerEntity) && !this.getOwner().isAlive()))) {
        	this.hurt(DamageSource.mobAttack(this).bypassInvul().bypassArmor(), this.getMaxHealth());
        	this.addTag("FUR_noLoot");
        }
    }   
    
    @Override
    @Nullable
    public LivingEntity getOwner()
    {
    	try
        {
            UUID uuid = this.getOwnerUUID();
            LivingEntity owner = null;
            if(uuid == null) {
            	return null;
            } else {
            	owner = this.level.getPlayerByUUID(uuid);
            	if(owner == null)
                    if (this.level instanceof ServerWorld) {                 	
                    	owner = SpawnUtil.getEntityByUniqueId(uuid, (ServerWorld)this.level);
                    }
            }
            
            return owner;
        }
        catch (IllegalArgumentException var2)
        {
            return null;
        }
    }
    
    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    @Override
    public boolean isFood(ItemStack stack)
    {
        return false;
    }
    
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return null;
	}
	
    /**
     * Writes the extra NBT data specific to this type of entity. Should <em>not</em> be called from outside this class;
     * use {@link #writeUnlessPassenger} or {@link #writeWithoutTypeId} instead.
     */
	@Override
    public void addAdditionalSaveData(CompoundNBT compound) {
       super.addAdditionalSaveData(compound);
       if(this.state.equals(FURTameableEntity.State.WANDERING)) {
    	    compound.putByte("state", (byte)0);
		}
		else if(this.state.equals(FURTameableEntity.State.SITTING)) {
			compound.putByte("state", (byte)1);
		}
		else if(this.state.equals(FURTameableEntity.State.FOLLOWING)) {
			compound.putByte("state", (byte)2);
		}
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
       super.readAdditionalSaveData(compound);
       switch(compound.getByte("state")) {
	       case (byte)0:
	    	   this.state = FURTameableEntity.State.WANDERING;
	       		this.getNavigation().stop();
	  			break;
	       case (byte)1:
	    	   this.state = FURTameableEntity.State.SITTING;
	    	   this.goalSelector.removeGoal(this.wander);
               this.jumping = false;
               this.getNavigation().stop();
	  			break;
	       case (byte)2:
	    	   this.state = FURTameableEntity.State.FOLLOWING;
	       	   this.follow = new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false);
	       	   this.getNavigation().stop();
	    	   this.goalSelector.removeGoal(this.wander);
	    	   this.goalSelector.addGoal(6, this.follow);
	   			break;
	   		default:
	   			break;
       }
    }
	
    static enum State
    {
        SITTING,
        WANDERING,
        FOLLOWING;
    }
}
