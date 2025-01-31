package com.Fishmod.mod_LavaCow.client.model.entity;

import com.Fishmod.mod_LavaCow.entities.flying.FlyingMobEntity;
import com.Fishmod.mod_LavaCow.entities.flying.VespaEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * ModelVespa - Fish0416
 * Created using Tabula 7.0.1
 */
public class VespaModel<T extends VespaEntity> extends FlyingBaseModel<T> implements IHasHead {
    public ModelRenderer Throax_base;
    public ModelRenderer Head;
    public ModelRenderer Throax_0;
    public ModelRenderer Wing_0_r;
    public ModelRenderer Wing_0_l;
    public ModelRenderer Wing_1_r;
    public ModelRenderer Wing_1_l;
    public ModelRenderer leg_r_0;
    public ModelRenderer leg_r_1;
    public ModelRenderer leg_r_2;
    public ModelRenderer leg_l_0;
    public ModelRenderer leg_l_1;
    public ModelRenderer leg_l_2;
    public ModelRenderer Waist;
    public ModelRenderer Cheek_r;
    public ModelRenderer Cheek_l;
    public ModelRenderer Eye_r;
    public ModelRenderer Eye_l;
    public ModelRenderer Mouth;
    public ModelRenderer Antennae_r;
    public ModelRenderer Antennae_l;
    public ModelRenderer UAbdomen1;
    public ModelRenderer UAbdomen2;
    public ModelRenderer UAbdomen3;
    public ModelRenderer UAbdomen4;
    public ModelRenderer Stinger;

    public VespaModel() {
        this.texWidth = 128;
        this.texHeight = 64;
        this.Waist = new ModelRenderer(this, 100, 50);
        this.Waist.setPos(0.0F, 0.0F, 1.0F);
        this.Waist.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 8, 0.0F);
        this.leg_r_2 = new ModelRenderer(this, 56, 0);
        this.leg_r_2.setPos(-4.0F, 6.0F, -2.5F);
        this.leg_r_2.addBox(-11.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(leg_r_2, 0.0F, 0.39269908169872414F, -0.5811946409141118F);
        this.Mouth = new ModelRenderer(this, 24, 22);
        this.Mouth.setPos(0.0F, 2.0F, -12.0F);
        this.Mouth.addBox(-2.0F, -3.0F, -2.0F, 4, 6, 2, 0.0F);
        this.leg_l_2 = new ModelRenderer(this, 56, 0);
        this.leg_l_2.setPos(4.0F, 6.0F, -2.5F);
        this.leg_l_2.addBox(-1.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(leg_l_2, 0.0F, -0.39269908169872414F, 0.5811946409141118F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setPos(0.0F, 2.0F, -8.0F);
        this.Head.addBox(-4.0F, -4.0F, -12.0F, 8, 8, 12, 0.0F);
        this.Eye_l = new ModelRenderer(this, 8, 22);
        this.Eye_l.setPos(4.0F, -1.0F, -10.0F);
        this.Eye_l.addBox(0.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
        this.Wing_1_l = new ModelRenderer(this, 0, 30);
        this.Wing_1_l.mirror = true;
        this.Wing_1_l.setPos(6.0F, -4.0F, -5.0F);
        this.Wing_1_l.addBox(0.0F, 0.0F, -1.0F, 16, 0, 6, 0.0F);
        this.setRotateAngle(Wing_1_l, 0.0F, -0.40980330836826856F, 0.0F);
        this.Wing_0_l = new ModelRenderer(this, 0, 36);
        this.Wing_0_l.mirror = true;
        this.Wing_0_l.setPos(6.0F, -4.0F, -8.0F);
        this.Wing_0_l.addBox(0.0F, 0.0F, -1.0F, 24, 0, 8, 0.0F);
        this.setRotateAngle(Wing_0_l, 0.0F, 0.22759093446006054F, 0.0F);
        this.Antennae_r = new ModelRenderer(this, 0, 22);
        this.Antennae_r.setPos(-2.5F, -3.0F, -10.0F);
        this.Antennae_r.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(Antennae_r, -0.27314402793711257F, 0.0F, 0.0F);
        this.Cheek_l = new ModelRenderer(this, 40, 0);
        this.Cheek_l.mirror = true;
        this.Cheek_l.setPos(4.0F, 0.0F, -4.0F);
        this.Cheek_l.addBox(0.0F, -2.0F, -4.0F, 4, 6, 8, 0.0F);
        this.Cheek_r = new ModelRenderer(this, 40, 0);
        this.Cheek_r.setPos(-4.0F, 0.0F, -4.0F);
        this.Cheek_r.addBox(-4.0F, -2.0F, -4.0F, 4, 6, 8, 0.0F);
        this.UAbdomen2 = new ModelRenderer(this, 88, 0);
        this.UAbdomen2.setPos(0.0F, 2.0F, 4.0F);
        this.UAbdomen2.addBox(-6.0F, -8.0F, 0.0F, 12, 12, 8, 0.0F);
        this.setRotateAngle(UAbdomen2, -0.22759093446006054F, 0.0F, 0.0F);
        this.Eye_r = new ModelRenderer(this, 8, 22);
        this.Eye_r.setPos(-4.0F, -1.0F, -10.0F);
        this.Eye_r.addBox(-4.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
        this.Throax_base = new ModelRenderer(this, 0, 44);
        this.Throax_base.mirror = true;
        this.Throax_base.setPos(0.0F, 12.0F, 3.5F);
        this.Throax_base.addBox(-6.0F, -6.0F, -4.0F, 12, 12, 8, 0.0F);
        this.Stinger = new ModelRenderer(this, 56, 26);
        this.Stinger.setPos(0.0F, 0.0F, 3.0F);
        this.Stinger.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F);
        this.leg_l_1 = new ModelRenderer(this, 56, 0);
        this.leg_l_1.setPos(4.0F, 6.0F, -3.5F);
        this.leg_l_1.addBox(-1.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(leg_l_1, 0.0F, 0.39269908169872414F, 0.5811946409141118F);
        this.UAbdomen3 = new ModelRenderer(this, 100, 28);
        this.UAbdomen3.setPos(0.0F, 0.0F, 6.0F);
        this.UAbdomen3.addBox(-4.0F, -6.0F, 0.0F, 8, 8, 6, 0.0F);
        this.setRotateAngle(UAbdomen3, -0.22759093446006054F, 0.0F, 0.0F);
        this.leg_r_0 = new ModelRenderer(this, 56, 0);
        this.leg_r_0.setPos(-4.0F, 6.0F, -4.5F);
        this.leg_r_0.addBox(-11.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(leg_r_0, 0.0F, -0.7853981633974483F, -0.7853981633974483F);
        this.Wing_0_r = new ModelRenderer(this, 0, 36);
        this.Wing_0_r.setPos(-6.0F, -4.0F, -8.0F);
        this.Wing_0_r.addBox(-24.0F, 0.0F, -1.0F, 24, 0, 8, 0.0F);
        this.setRotateAngle(Wing_0_r, 0.0F, -0.22759093446006054F, 0.0F);
        this.Antennae_l = new ModelRenderer(this, 0, 22);
        this.Antennae_l.setPos(2.5F, -3.0F, -10.0F);
        this.Antennae_l.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2, 0.0F);
        this.setRotateAngle(Antennae_l, -0.27314402793711257F, 0.0F, 0.0F);
        this.UAbdomen4 = new ModelRenderer(this, 84, 28);
        this.UAbdomen4.setPos(0.0F, -1.0F, 5.0F);
        this.UAbdomen4.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(UAbdomen4, -0.22759093446006054F, 0.0F, 0.0F);
        this.Throax_0 = new ModelRenderer(this, 0, 44);
        this.Throax_0.setPos(0.0F, 0.0F, -5.0F);
        this.Throax_0.addBox(-6.0F, -6.0F, -4.0F, 12, 12, 8, 0.0F);
        this.setRotateAngle(Throax_0, 0.27314402793711257F, 0.0F, 0.0F);
        this.leg_r_1 = new ModelRenderer(this, 56, 0);
        this.leg_r_1.setPos(-4.0F, 6.0F, -3.5F);
        this.leg_r_1.addBox(-11.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(leg_r_1, 0.0F, -0.39269908169872414F, -0.5811946409141118F);
        this.UAbdomen1 = new ModelRenderer(this, 66, 48);
        this.UAbdomen1.setPos(0.0F, 1.0F, 5.0F);
        this.UAbdomen1.addBox(-5.0F, -5.0F, 0.0F, 10, 10, 6, 0.0F);
        this.setRotateAngle(UAbdomen1, 0.22759093446006054F, 0.0F, 0.0F);
        this.Wing_1_r = new ModelRenderer(this, 0, 30);
        this.Wing_1_r.setPos(-6.0F, -4.0F, -5.0F);
        this.Wing_1_r.addBox(-16.0F, 0.0F, -1.0F, 16, 0, 6, 0.0F);
        this.setRotateAngle(Wing_1_r, 0.0F, 0.40980330836826856F, 0.0F);
        this.leg_l_0 = new ModelRenderer(this, 56, 0);
        this.leg_l_0.setPos(4.0F, 6.0F, -4.5F);
        this.leg_l_0.addBox(-1.0F, -1.0F, -1.0F, 12, 2, 2, 0.0F);
        this.setRotateAngle(leg_l_0, 0.0F, 0.7853981633974483F, 0.7853981633974483F);
        this.Throax_base.addChild(this.Waist);
        this.Throax_base.addChild(this.leg_r_2);
        this.Head.addChild(this.Mouth);
        this.Throax_base.addChild(this.leg_l_2);
        this.Throax_base.addChild(this.Head);
        this.Head.addChild(this.Eye_l);
        this.Throax_base.addChild(this.Wing_1_l);
        this.Throax_base.addChild(this.Wing_0_l);
        this.Head.addChild(this.Antennae_r);
        this.Head.addChild(this.Cheek_l);
        this.Head.addChild(this.Cheek_r);
        this.UAbdomen1.addChild(this.UAbdomen2);
        this.Head.addChild(this.Eye_r);
        this.UAbdomen4.addChild(this.Stinger);
        this.Throax_base.addChild(this.leg_l_1);
        this.UAbdomen2.addChild(this.UAbdomen3);
        this.Throax_base.addChild(this.leg_r_0);
        this.Throax_base.addChild(this.Wing_0_r);
        this.Head.addChild(this.Antennae_l);
        this.UAbdomen3.addChild(this.UAbdomen4);
        this.Throax_base.addChild(this.Throax_0);
        this.Throax_base.addChild(this.leg_r_1);
        this.Waist.addChild(this.UAbdomen1);
        this.Throax_base.addChild(this.Wing_1_r);
        this.Throax_base.addChild(this.leg_l_0);
    }
    
    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.Throax_base).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }
    
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
  	  	float vibrate_rate = 0.5F;
    	
    	this.Head.xRot = headPitch * 0.017453292F;
    	this.Head.yRot = netHeadYaw * 0.017453292F;

    	this.Wing_0_r.zRot = 0.5F * MathHelper.sin(4.0F * ageInTicks);
    	this.Wing_1_r.zRot = 0.5F * MathHelper.sin(4.0F * ageInTicks + (float)Math.PI);
    	this.Wing_0_l.zRot = 0.5F * MathHelper.sin(4.0F * ageInTicks + (float)Math.PI);
    	this.Wing_1_l.zRot = 0.5F * MathHelper.sin(4.0F * ageInTicks);
      
    	this.Throax_base.y = 7.0F + 5.0F * MathHelper.sin(ageInTicks * vibrate_rate);
    	
    	if(this.state.equals(FlyingBaseModel.State.FLYING)) {
	    	this.Waist.xRot = 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.5F * (float)Math.PI) * (float)Math.PI;
	    	this.UAbdomen1.xRot = 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.10F * (float)Math.PI) * (float)Math.PI;
	    	this.UAbdomen2.xRot = 0.01F * MathHelper.cos(ageInTicks * vibrate_rate + 0.15F * (float)Math.PI) * (float)Math.PI;
	    	this.UAbdomen3.xRot = 0.01F * MathHelper.cos(ageInTicks * vibrate_rate + 0.20F * (float)Math.PI) * (float)Math.PI;
	    	this.UAbdomen4.xRot = 0.01F * MathHelper.cos(ageInTicks * vibrate_rate + 0.15F * (float)Math.PI) * (float)Math.PI;
	    	this.Stinger.xRot = 0.01F * MathHelper.cos(ageInTicks * vibrate_rate + 0.10F * (float)Math.PI) * (float)Math.PI;
	    	
	    	this.setRotateAngle(leg_r_0, 0.0F, 0.4553564018453205F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.5F * (float)Math.PI) * (float)Math.PI, -1.6845917940249266F);
	    	this.setRotateAngle(leg_r_1, 0.0F, 0.7740535232594852F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.10F * (float)Math.PI) * (float)Math.PI, -1.593485607070823F);
	    	this.setRotateAngle(leg_r_2, 0.0F, 1.2292353921796064F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.15F * (float)Math.PI) * (float)Math.PI, -1.3658946726107624F);
	    	this.setRotateAngle(leg_l_0, 0.0F, -0.4553564018453205F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.5F * (float)Math.PI) * (float)Math.PI, 1.6845917940249266F);
	    	this.setRotateAngle(leg_l_1, 0.0F, -0.7740535232594852F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.10F * (float)Math.PI) * (float)Math.PI, 1.593485607070823F);
	    	this.setRotateAngle(leg_l_2, 0.0F, -1.2292353921796064F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.15F * (float)Math.PI) * (float)Math.PI, 1.3658946726107624F);
    	}
    	else if(this.state.equals(FlyingBaseModel.State.ATTACKING)) {
	    	this.Waist.xRot = -0.18F * MathHelper.abs(10 - ((FlyingMobEntity)entityIn).getAttackTimer());
	    	this.UAbdomen1.xRot = -0.18F * MathHelper.abs(10 - ((FlyingMobEntity)entityIn).getAttackTimer());
	    	this.UAbdomen2.xRot = -0.20F * MathHelper.abs(10 - ((FlyingMobEntity)entityIn).getAttackTimer());
	    	this.UAbdomen3.xRot = -0.20F * MathHelper.abs(10 - ((FlyingMobEntity)entityIn).getAttackTimer());
	    	this.UAbdomen4.xRot = -0.22F * MathHelper.abs(10 - ((FlyingMobEntity)entityIn).getAttackTimer());
	    	this.Stinger.xRot = -0.24F * MathHelper.abs(10 - ((FlyingMobEntity)entityIn).getAttackTimer());
	    	
	    	this.setRotateAngle(leg_r_0, 0.0F, -0.7853981633974483F, -0.7853981633974483F);
	    	this.setRotateAngle(leg_r_1, 0.0F, -0.39269908169872414F, -0.5811946409141118F);
	    	this.setRotateAngle(leg_r_2, 0.0F, 0.39269908169872414F, -0.5811946409141118F);
	    	this.setRotateAngle(leg_l_0, 0.0F, 0.7853981633974483F, 0.7853981633974483F);
	    	this.setRotateAngle(leg_l_1, 0.0F, 0.39269908169872414F, 0.5811946409141118F);
	    	this.setRotateAngle(leg_l_2, 0.0F, -0.39269908169872414F, 0.5811946409141118F);
    	}
    	else {
	    	this.Waist.xRot = -0.028F * (10 - this.HoverCounter) + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.5F * (float)Math.PI) * (float)Math.PI;
	    	this.UAbdomen1.xRot = -0.028F * (10 - this.HoverCounter) + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.10F * (float)Math.PI) * (float)Math.PI;
	    	this.UAbdomen2.xRot = -0.028F * (10 - this.HoverCounter) + 0.01F * MathHelper.cos(ageInTicks * vibrate_rate + 0.15F * (float)Math.PI) * (float)Math.PI;
	    	this.UAbdomen3.xRot = -0.028F * (10 - this.HoverCounter) + 0.01F * MathHelper.cos(ageInTicks * vibrate_rate + 0.20F * (float)Math.PI) * (float)Math.PI;
	    	this.UAbdomen4.xRot = -0.028F * (10 - this.HoverCounter) + 0.01F * MathHelper.cos(ageInTicks * vibrate_rate + 0.15F * (float)Math.PI) * (float)Math.PI;
	    	this.Stinger.xRot = -0.028F * (10 - this.HoverCounter) + 0.01F * MathHelper.cos(ageInTicks * vibrate_rate + 0.10F * (float)Math.PI) * (float)Math.PI;
	    	
	    	this.setRotateAngle(leg_r_0, 0.0F, 0.4553564018453205F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.5F * (float)Math.PI) * (float)Math.PI, -1.6845917940249266F);
	    	this.setRotateAngle(leg_r_1, 0.0F, 0.7740535232594852F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.10F * (float)Math.PI) * (float)Math.PI, -1.593485607070823F);
	    	this.setRotateAngle(leg_r_2, 0.0F, 1.2292353921796064F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.15F * (float)Math.PI) * (float)Math.PI, -1.3658946726107624F);
	    	this.setRotateAngle(leg_l_0, 0.0F, -0.4553564018453205F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.5F * (float)Math.PI) * (float)Math.PI, 1.6845917940249266F);
	    	this.setRotateAngle(leg_l_1, 0.0F, -0.7740535232594852F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.10F * (float)Math.PI) * (float)Math.PI, 1.593485607070823F);
	    	this.setRotateAngle(leg_l_2, 0.0F, -1.2292353921796064F + 0.02F * MathHelper.cos(ageInTicks * vibrate_rate + 0.15F * (float)Math.PI) * (float)Math.PI, 1.3658946726107624F);
    	}
    }

	@Override
	public ModelRenderer getHead() {
		return this.Head;
	}
}
