package com.elcolomanco.riskofrainmod.client.model;

import com.elcolomanco.riskofrainmod.entities.GunnerDroneEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ModelUtils;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GunnerDroneModel <T extends GunnerDroneEntity> extends EntityModel<T> {

    public ModelRenderer core;
    public ModelRenderer eye;
    public ModelRenderer eye_ring;
    public ModelRenderer top_case;
    public ModelRenderer lower_case;
    public ModelRenderer eye_ring_left;
    public ModelRenderer eye_ring_right;
    public ModelRenderer eye_ring_front;
    public ModelRenderer eye_ring_back;
    public ModelRenderer top_case_left;
    public ModelRenderer top_case_right;
    public ModelRenderer top_case__front_1_left;
    public ModelRenderer top_case_front_1_right;
    public ModelRenderer top_case_front_2;
    public ModelRenderer top_case_back;
    public ModelRenderer top_case_top;
    public ModelRenderer top_case_propeller;
    public ModelRenderer propeller_rod;
    public ModelRenderer wing_left;
    public ModelRenderer wing_right;
    public ModelRenderer wing_front;
    public ModelRenderer wing_back;
    public ModelRenderer wing_left_2;
    public ModelRenderer wing_right_2;
    public ModelRenderer wing_front_2;
    public ModelRenderer wing_front_2_1;
    public ModelRenderer lower_case_left;
    public ModelRenderer lower_case_right;
    public ModelRenderer lower_case_front;
    public ModelRenderer lower_case_back;
    public ModelRenderer gun_yaw;
    public ModelRenderer gun_pitch_axis;
    public ModelRenderer gun_pitch;
    public ModelRenderer gun_pitch_2;
    
    private float bodyPitch;

    public GunnerDroneModel() {
    	this.textureWidth = 112;
        this.textureHeight = 72;
        this.core = new ModelRenderer(this, 1, 35);
        this.core.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.core.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.top_case_right = new ModelRenderer(this, 53, 17);
        this.top_case_right.setRotationPoint(4.5F, 0.0F, 0.0F);
        this.top_case_right.addBox(-0.5F, -2.5F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.eye = new ModelRenderer(this, 10, 31);
        this.eye.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.eye.addBox(-2.0F, -1.0F, -1.75F, 4.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.eye_ring_back = new ModelRenderer(this, 91, 47);
        this.eye_ring_back.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.eye_ring_back.addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.top_case_back = new ModelRenderer(this, 91, 24);
        this.top_case_back.setRotationPoint(0.0F, -1.0F, 4.5F);
        this.top_case_back.addBox(-4.0F, -1.5F, -0.5F, 8.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.wing_right = new ModelRenderer(this, 15, 5);
        this.wing_right.setRotationPoint(0.25F, -4.0F, 0.0F);
        this.wing_right.addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.gun_pitch_axis = new ModelRenderer(this, 10, 67);
        this.gun_pitch_axis.setRotationPoint(0.0F, 3.0F, 0.5F);
        this.gun_pitch_axis.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.lower_case_right = new ModelRenderer(this, 53, 52);
        this.lower_case_right.setRotationPoint(4.5F, 0.0F, 0.0F);
        this.lower_case_right.addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.lower_case = new ModelRenderer(this, 20, 51);
        this.lower_case.setRotationPoint(0.0F, 3.5F, 3.0F);
        this.lower_case.addBox(-4.0F, -0.5F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.wing_front = new ModelRenderer(this, 29, 5);
        this.wing_front.setRotationPoint(0.0F, -4.0F, -0.75F);
        this.wing_front.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.wing_right_2 = new ModelRenderer(this, 17, 4);
        this.wing_right_2.setRotationPoint(3.5F, 0.0F, -0.5F);
        this.wing_right_2.addBox(-2.5F, 0.0F, -1.0F, 5.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.gun_pitch_2 = new ModelRenderer(this, 30, 63);
        this.gun_pitch_2.setRotationPoint(0.0F, 0.0F, -3.5F);
        this.gun_pitch_2.addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.propeller_rod = new ModelRenderer(this, 47, 10);
        this.propeller_rod.setRotationPoint(0.0F, -0.5F, 0.0F);
        this.propeller_rod.addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(propeller_rod, 0.0F, 0.7853981633974483F, 0.0F);
        this.gun_yaw = new ModelRenderer(this, 1, 63);
        this.gun_yaw.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.gun_yaw.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.eye_ring_left = new ModelRenderer(this, 1, 40);
        this.eye_ring_left.setRotationPoint(-4.5F, 0.0F, 0.0F);
        this.eye_ring_left.addBox(0.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.top_case_top = new ModelRenderer(this, 1, 7);
        this.top_case_top.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.top_case_top.addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.lower_case_front = new ModelRenderer(this, 72, 59);
        this.lower_case_front.setRotationPoint(0.0F, 0.0F, -4.5F);
        this.lower_case_front.addBox(-4.0F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.wing_back = new ModelRenderer(this, 37, 5);
        this.wing_back.setRotationPoint(0.0F, -4.0F, 0.75F);
        this.wing_back.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.wing_front_2 = new ModelRenderer(this, 28, 1);
        this.wing_front_2.setRotationPoint(-0.5F, 0.0F, -3.0F);
        this.wing_front_2.addBox(-1.0F, 0.0F, -2.5F, 2.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.top_case_front_1_right = new ModelRenderer(this, 84, 27);
        this.top_case_front_1_right.setRotationPoint(3.0F, 1.5F, -4.5F);
        this.top_case_front_1_right.addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.lower_case_back = new ModelRenderer(this, 91, 59);
        this.lower_case_back.setRotationPoint(0.0F, 0.0F, 4.5F);
        this.lower_case_back.addBox(-4.0F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.eye_ring_front = new ModelRenderer(this, 72, 47);
        this.eye_ring_front.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.eye_ring_front.addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.top_case__front_1_left = new ModelRenderer(this, 72, 27);
        this.top_case__front_1_left.setRotationPoint(-3.0F, 1.5F, -4.5F);
        this.top_case__front_1_left.addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.lower_case_left = new ModelRenderer(this, 1, 52);
        this.lower_case_left.setRotationPoint(-4.5F, 0.0F, 0.0F);
        this.lower_case_left.addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.eye_ring_right = new ModelRenderer(this, 53, 40);
        this.eye_ring_right.mirror = true;
        this.eye_ring_right.setRotationPoint(4.5F, 0.0F, 0.0F);
        this.eye_ring_right.addBox(-1.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.wing_left = new ModelRenderer(this, 1, 5);
        this.wing_left.setRotationPoint(-0.25F, -4.0F, 0.0F);
        this.wing_left.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.gun_pitch = new ModelRenderer(this, 15, 62);
        this.gun_pitch.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.gun_pitch.addBox(-0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(gun_pitch, 0.0F, 0.0F, 0.7853981633974483F);
        this.wing_left_2 = new ModelRenderer(this, 3, 4);
        this.wing_left_2.setRotationPoint(-3.5F, 0.0F, 0.5F);
        this.wing_left_2.addBox(-2.5F, 0.0F, -1.0F, 5.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.wing_front_2_1 = new ModelRenderer(this, 36, 1);
        this.wing_front_2_1.setRotationPoint(0.5F, 0.0F, 3.0F);
        this.wing_front_2_1.addBox(-1.0F, 0.0F, -2.5F, 2.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.top_case_left = new ModelRenderer(this, 1, 17);
        this.top_case_left.setRotationPoint(-4.5F, 0.0F, 0.0F);
        this.top_case_left.addBox(-0.5F, -2.5F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.top_case_propeller = new ModelRenderer(this, 34, 12);
        this.top_case_propeller.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.top_case_propeller.addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.eye_ring = new ModelRenderer(this, 20, 40);
        this.eye_ring.setRotationPoint(0.0F, 2.0F, 3.0F);
        this.eye_ring.addBox(-4.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.top_case = new ModelRenderer(this, 20, 17);
        this.top_case.setRotationPoint(0.0F, -1.5F, 3.0F);
        this.top_case.addBox(-4.0F, -2.5F, -4.0F, 8.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.top_case_front_2 = new ModelRenderer(this, 72, 22);
        this.top_case_front_2.setRotationPoint(0.0F, -1.0F, -4.5F);
        this.top_case_front_2.addBox(-4.0F, -1.5F, -0.5F, 8.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.top_case.addChild(this.top_case_right);
        this.core.addChild(this.eye);
        this.eye_ring.addChild(this.eye_ring_back);
        this.top_case.addChild(this.top_case_back);
        this.propeller_rod.addChild(this.wing_right);
        this.gun_yaw.addChild(this.gun_pitch_axis);
        this.lower_case.addChild(this.lower_case_right);
        this.eye.addChild(this.lower_case);
        this.propeller_rod.addChild(this.wing_front);
        this.wing_right.addChild(this.wing_right_2);
        this.gun_pitch.addChild(this.gun_pitch_2);
        this.top_case_propeller.addChild(this.propeller_rod);
        this.lower_case.addChild(this.gun_yaw);
        this.eye_ring.addChild(this.eye_ring_left);
        this.top_case.addChild(this.top_case_top);
        this.lower_case.addChild(this.lower_case_front);
        this.propeller_rod.addChild(this.wing_back);
        this.wing_front.addChild(this.wing_front_2);
        this.top_case.addChild(this.top_case_front_1_right);
        this.lower_case.addChild(this.lower_case_back);
        this.eye_ring.addChild(this.eye_ring_front);
        this.top_case.addChild(this.top_case__front_1_left);
        this.lower_case.addChild(this.lower_case_left);
        this.eye_ring.addChild(this.eye_ring_right);
        this.propeller_rod.addChild(this.wing_left);
        this.gun_pitch_axis.addChild(this.gun_pitch);
        this.wing_left.addChild(this.wing_left_2);
        this.wing_back.addChild(this.wing_front_2_1);
        this.top_case.addChild(this.top_case_left);
        this.top_case.addChild(this.top_case_propeller);
        this.eye.addChild(this.eye_ring);
        this.eye.addChild(this.top_case);
        this.top_case.addChild(this.top_case_front_2);
    }

    @Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    	core.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.core.rotateAngleZ = 0;
		this.core.rotateAngleX = 0;
		this.eye.rotateAngleX = 0;
		//Looking rotations
		this.gun_pitch_axis.rotateAngleX = headPitch * ((float)Math.PI / 180F);
    	this.gun_yaw.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
    	//Propeller
    	if(entityIn.isTamed() && entityIn.isFlying() && !entityIn.isSitting()) {
    		float f = ageInTicks * (float)Math.PI * 0.3F;
    		this.eye.rotationPointY = (MathHelper.cos(ageInTicks * 0.18F) * 0.9F);
    		this.propeller_rod.rotateAngleY = f;
    	} else if (entityIn.isOnGround()) {
    		this.eye.rotationPointY = 0;
    		this.propeller_rod.rotateAngleY = 0.7853981633974483F;
    	}
    	//Buried position
    	if (this.bodyPitch > 0.0F && !entityIn.isTamed()) {
    		this.core.rotateAngleZ = ModelUtils.func_228283_a_(this.core.rotateAngleZ, 0.2617993877991494F, this.bodyPitch);
    		this.core.rotateAngleX = ModelUtils.func_228283_a_(this.core.rotateAngleZ, -0.2617993877991494F, this.bodyPitch);
    		this.core.rotationPointY = (float) 23.5;
    	} else if (entityIn.isOnGround() && entityIn.isTamed()) {
    		this.core.rotationPointY = 18.75F;
    	} else {
    		this.core.rotationPointY = 15.0F;
    	}
    	//Moving inclination
    	if (this.bodyPitch > 0.0F && entityIn.isDroneMoving()) {
    		float f1 = MathHelper.cos(ageInTicks * 0.18F);
            this.eye.rotateAngleX = 0.1F + f1 * (float)Math.PI * 0.025F;
    	}
	}
	
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.bodyPitch = entityIn.getBodyPitch(partialTick);
	}
	
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
