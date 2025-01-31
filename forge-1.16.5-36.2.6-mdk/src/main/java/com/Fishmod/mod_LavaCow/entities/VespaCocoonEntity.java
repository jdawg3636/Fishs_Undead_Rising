package com.Fishmod.mod_LavaCow.entities;

import com.Fishmod.mod_LavaCow.entities.flying.VespaEntity;
import com.Fishmod.mod_LavaCow.init.FUREntityRegistry;
import com.Fishmod.mod_LavaCow.init.FURSoundRegistry;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class VespaCocoonEntity extends MonsterEntity {	
	private int Lifespan = 8 * 20;
	
	public VespaCocoonEntity(EntityType<? extends VespaCocoonEntity> p_i48549_1_, World worldIn) {
        super(p_i48549_1_, worldIn);
    }
	
	@Override
    protected void registerGoals() {
    }
    
    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
        		.add(Attributes.MAX_HEALTH, 20.0D)
        		.add(Attributes.ARMOR, 10.0D)
        		.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }
    
    /**
     * Called to update the entity's position/logic.
     */
	@Override
    public void aiStep() {
        super.aiStep();
        if(this.tickCount >= Lifespan) {
        	this.playSound(SoundEvents.SLIME_SQUISH, 1.0F, 1.0F);
        	
    		if (!this.level.isClientSide) {		   			
	    		VespaEntity pupa = FUREntityRegistry.VESPA.create(this.level);
	    		pupa.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
	    		this.level.addFreshEntity(pupa);
    		}
        	
    		this.remove();
        }
    }
    
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void tick() {   	
        super.tick();
        this.setDeltaMovement(0.0D, this.getDeltaMovement().y, 0.0D);
    }
    
    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
		if (source.equals(DamageSource.IN_WALL) || source.equals(DamageSource.DROWN))
			return false;
		return super.hurt(source, amount);
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
	@Override
    public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("lifespan", Lifespan);
	}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		Lifespan = compound.getInt("lifespan");
	}
    
    @Override
    public boolean isImmobile() {
        return true;
    }
    
    /**
     * Applies a velocity to the entities, to push them away from eachother.
     */
    @Override
    public void push(Entity entityIn) {
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return 0.8F;
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.WOOL_BREAK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return FURSoundRegistry.PARASITE_DEATH;
    }

    public CreatureAttribute getMobType() {
	    return CreatureAttribute.ARTHROPOD;
	}
}
