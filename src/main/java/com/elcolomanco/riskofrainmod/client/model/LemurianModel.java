package com.elcolomanco.riskofrainmod.client.model;

import com.elcolomanco.riskofrainmod.entities.LemurianEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class LemurianModel<T extends LemurianEntity> extends EntityModel<T> {

	public ModelRenderer core;
    public ModelRenderer stomach_axis;
    public ModelRenderer stomach;
    public ModelRenderer rib_cage_axis;
    public ModelRenderer rib_cage;
    public ModelRenderer neck_axis;
    public ModelRenderer neck;
    public ModelRenderer head_axis;
    public ModelRenderer head;
    public ModelRenderer low_mouth_axis;
    public ModelRenderer low_mouth;
    public ModelRenderer top_head;
    public ModelRenderer top_mouth;
    
    public ModelRenderer left_arm_axis;
    public ModelRenderer left_arm;
    public ModelRenderer left_shoulder_plate;
    public ModelRenderer left_forearm_axis;
    public ModelRenderer left_forearm;
    public ModelRenderer left_claws;
    
    public ModelRenderer right_arm_axis;
    public ModelRenderer right_arm;
    public ModelRenderer right_shoulder_plate;
    public ModelRenderer right_forearm_axis;
    public ModelRenderer right_forearm;
    public ModelRenderer right_claws;
    
    public ModelRenderer tail1_axis;
    public ModelRenderer tail1;
    public ModelRenderer tail2_axis;
    public ModelRenderer tail2;
    public ModelRenderer tail3_axis;
    public ModelRenderer tail3;

    public ModelRenderer left_leg1_axis;
    public ModelRenderer left_leg1;
    public ModelRenderer left_leg2_axis;
    public ModelRenderer left_leg2;
    public ModelRenderer left_leg3_axis;
    public ModelRenderer left_leg3;
    public ModelRenderer left_foot_axis;
    public ModelRenderer left_foot;
    
    public ModelRenderer right_leg1_axis;
    public ModelRenderer right_leg1;
    public ModelRenderer right_leg2_axis;
    public ModelRenderer right_leg2;
    public ModelRenderer right_leg3_axis;
    public ModelRenderer right_leg3;
    public ModelRenderer right_foot_axis;
    public ModelRenderer right_foot;
    
    private int choice = 0;

    public LemurianModel() {
    	//Texture
        this.textureWidth = 110;
        this.textureHeight = 64;
        // Model parts
        this.core = new ModelRenderer(this, 1, 58);
        this.core.setRotationPoint(0.0F, 10.5F, 0.0F);
        this.core.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        
        this.stomach_axis = new ModelRenderer(this, 10, 60);
        this.stomach_axis.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.stomach_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(stomach_axis, 0.08726646259971647F, 0.0F, 0.0F);
        
        this.stomach = new ModelRenderer(this, 28, 10);
        this.stomach.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.stomach.addBox(-4.0F, -6.0F, -1.5F, 8, 7, 3, 0.0F);
        
        this.rib_cage_axis = new ModelRenderer(this, 15, 60);
        this.rib_cage_axis.setRotationPoint(0.0F, -5.0F, -0.25F);
        this.rib_cage_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(rib_cage_axis, 0.17453292519943295F, 0.0F, 0.0F);
        
        this.rib_cage = new ModelRenderer(this, 1, 10);
        this.rib_cage.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rib_cage.addBox(-4.5F, -5.0F, -2.0F, 9, 6, 4, 0.0F);
        
        this.neck_axis = new ModelRenderer(this, 20, 60);
        this.neck_axis.setRotationPoint(0.0F, -4.5F, 0.0F);
        this.neck_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(neck_axis, 0.2617993877991494F, 0.0F, 0.0F);
        
        this.neck = new ModelRenderer(this, 1, 1);
        this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neck.addBox(-3.0F, -4.0F, -1.5F, 6, 5, 3, 0.0F);
        
        this.head_axis = new ModelRenderer(this, 25, 60);
        this.head_axis.setRotationPoint(0.0F, -3.5F, 0.5F);
        this.head_axis.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(head_axis, -0.5235987755982988F, 0.0F, 0.0F);
        
        this.head = new ModelRenderer(this, 20, 2);
        this.head.setRotationPoint(0.0F, -1.25F, 0.25F);
        this.head.addBox(-2.5F, 0.0F, -4.0F, 5, 3, 4, 0.0F);
        
        this.low_mouth_axis = new ModelRenderer(this, 30, 60);
        this.low_mouth_axis.setRotationPoint(0.0F, 2.0F, -2.0F);
        this.low_mouth_axis.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(low_mouth_axis, 0.08726646259971647F, 0.0F, 0.0F);
        
        this.low_mouth = new ModelRenderer(this, 39, 3);
        this.low_mouth.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.low_mouth.addBox(-2.5F, 0.0F, -4.0F, 5, 1, 5, 0.0F);
        
        this.top_head = new ModelRenderer(this, 77, 3);
        this.top_head.setRotationPoint(0.0F, -0.25F, -3.5F);
        this.top_head.addBox(-2.0F, -0.5F, -2.5F, 4, 1, 5, 0.0F);
        
        this.top_mouth = new ModelRenderer(this, 60, 4);
        this.top_mouth.setRotationPoint(0.0F, 1.0F, -4.0F);
        this.top_mouth.addBox(-2.5F, -1.0F, -3.0F, 5, 2, 3, 0.0F);
        
        this.left_arm_axis = new ModelRenderer(this, 35, 60);
        this.left_arm_axis.setRotationPoint(5.0F, -4.0F, 0.0F);
        this.left_arm_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_arm_axis, -0.08726646259971647F, 0.0F, 0.0F);
        
        this.left_arm = new ModelRenderer(this, 1, 31);
        this.left_arm.setRotationPoint(0.5F, 0.5F, 0.0F);
        this.left_arm.addBox(-1.0F, -1.0F, -1.5F, 3, 11, 3, 0.0F);
        
        this.left_shoulder_plate = new ModelRenderer(this, 14, 36);
        this.left_shoulder_plate.setRotationPoint(1.0F, -0.5F, 0.0F);
        this.left_shoulder_plate.addBox(-2.0F, -1.0F, -2.0F, 4, 5, 4, 0.0F);
        
        this.left_forearm_axis = new ModelRenderer(this, 40, 60);
        this.left_forearm_axis.setRotationPoint(0.5F, 9.0F, 0.0F);
        this.left_forearm_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_forearm_axis, -0.2617993877991494F, 0.0F, 0.0F);
        
        this.left_forearm = new ModelRenderer(this, 31, 36);
        this.left_forearm.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.left_forearm.addBox(-1.0F, -1.0F, -1.0F, 2, 7, 2, 0.0F);
        
        this.left_claws = new ModelRenderer(this, 40, 36);
        this.left_claws.setRotationPoint(0.0F, 0.75F, 0.0F);
        this.left_claws.addBox(-0.5F, 0.0F, -1.5F, 2, 6, 3, 0.0F);
        
        this.right_arm_axis = new ModelRenderer(this, 45, 60);
        this.right_arm_axis.setRotationPoint(-5.0F, -4.0F, 0.0F);
        this.right_arm_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(right_arm_axis, -0.08726646259971647F, 0.0F, 0.0F);
        
        this.right_arm = new ModelRenderer(this, 51, 31);
        this.right_arm.setRotationPoint(-0.5F, 0.5F, 0.0F);
        this.right_arm.addBox(-2.0F, -1.0F, -1.5F, 3, 11, 3, 0.0F);
        
        this.right_shoulder_plate = new ModelRenderer(this, 64, 36);
        this.right_shoulder_plate.setRotationPoint(-1.0F, -0.5F, 0.0F);
        this.right_shoulder_plate.addBox(-2.0F, -1.0F, -2.0F, 4, 5, 4, 0.0F);
        
        this.right_forearm_axis = new ModelRenderer(this, 50, 60);
        this.right_forearm_axis.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.right_forearm_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(right_forearm_axis, -0.2617993877991494F, 0.0F, 0.0F);
        
        this.right_forearm = new ModelRenderer(this, 81, 36);
        this.right_forearm.setRotationPoint(-0.5F, 1.0F, 0.0F);
        this.right_forearm.addBox(-1.0F, -1.0F, -1.0F, 2, 7, 2, 0.0F);
        
        this.right_claws = new ModelRenderer(this, 90, 36);
        this.right_claws.setRotationPoint(0.0F, 0.75F, 0.0F);
        this.right_claws.addBox(-1.5F, 0.0F, -1.5F, 2, 6, 3, 0.0F);
        
        this.tail1_axis = new ModelRenderer(this, 95, 60);
        this.tail1_axis.setRotationPoint(0.0F, 0.0F, 1.5F);
        this.tail1_axis.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(tail1_axis, 0.3490658503988659F, 0.0F, 0.0F);
        
        this.tail1 = new ModelRenderer(this, 1, 21);
        this.tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail1.addBox(-2.0F, 0.0F, -3.0F, 4, 6, 3, 0.0F);
        
        this.tail2_axis = new ModelRenderer(this, 100, 60);
        this.tail2_axis.setRotationPoint(0.0F, 5.0F, -0.5F);
        this.tail2_axis.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(tail2_axis, 0.2617993877991494F, 0.0F, 0.0F);
        
        this.tail2 = new ModelRenderer(this, 16, 23);
        this.tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail2.addBox(-1.5F, 0.0F, -2.0F, 3, 5, 2, 0.0F);
        
        this.tail3_axis = new ModelRenderer(this, 105, 60);
        this.tail3_axis.setRotationPoint(0.0F, 4.5F, -0.75F);
        this.tail3_axis.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(tail3_axis, 0.2617993877991494F, 0.0F, 0.0F);
        
        this.tail3 = new ModelRenderer(this, 27, 25);
        this.tail3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail3.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
        
        this.left_leg1_axis = new ModelRenderer(this, 75, 60);
        this.left_leg1_axis.setRotationPoint(1.5F, 0.75F, 0.0F);
        this.left_leg1_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_leg1_axis, -0.4363323129985824F, 0.0F, 0.0F);
        
        this.left_leg1 = new ModelRenderer(this, 49, 46);
        this.left_leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_leg1.addBox(-1.0F, -1.0F, -1.75F, 4, 8, 3, 0.0F);
        
        this.left_leg2_axis = new ModelRenderer(this, 80, 60);
        this.left_leg2_axis.setRotationPoint(1.0F, 6.0F, -0.5F);
        this.left_leg2_axis.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_leg2_axis, 0.9599310885968813F, 0.0F, 0.0F);
        
        this.left_leg2 = new ModelRenderer(this, 64, 50);
        this.left_leg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_leg2.addBox(-1.5F, 0.0F, -1.0F, 3, 5, 2, 0.0F);
        
        this.left_leg3_axis = new ModelRenderer(this, 85, 60);
        this.left_leg3_axis.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.left_leg3_axis.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_leg3_axis, -0.8726646259971648F, 0.0F, 0.0F);
        
        this.left_leg3 = new ModelRenderer(this, 75, 51);
        this.left_leg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        
        this.left_foot_axis = new ModelRenderer(this, 90, 60);
        this.left_foot_axis.setRotationPoint(0.5F, 3.25F, 0.0F);
        this.left_foot_axis.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_foot_axis, 0.3490658503988659F, 0.0F, 0.0F);
        
        this.left_foot = new ModelRenderer(this, 84, 52);
        this.left_foot.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_foot.addBox(-1.5F, 0.0F, -3.5F, 2, 1, 4, 0.0F);
        
        this.right_leg1_axis = new ModelRenderer(this, 55, 60);
        this.right_leg1_axis.setRotationPoint(-1.5F, 0.75F, 0.0F);
        this.right_leg1_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(right_leg1_axis, -0.4363323129985824F, 0.0F, 0.0F);
        
        this.right_leg1 = new ModelRenderer(this, 1, 46);
        this.right_leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_leg1.addBox(-3.0F, -1.0F, -1.75F, 4, 8, 3, 0.0F);
        
        this.right_leg2_axis = new ModelRenderer(this, 60, 60);
        this.right_leg2_axis.setRotationPoint(-1.0F, 6.0F, -0.5F);
        this.right_leg2_axis.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(right_leg2_axis, 0.9599310885968813F, 0.0F, 0.0F);
        
        this.right_leg2 = new ModelRenderer(this, 16, 50);
        this.right_leg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_leg2.addBox(-1.5F, 0.0F, -1.0F, 3, 5, 2, 0.0F);
        
        this.right_leg3_axis = new ModelRenderer(this, 65, 60);
        this.right_leg3_axis.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.right_leg3_axis.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(right_leg3_axis, -0.8726646259971648F, 0.0F, 0.0F);
        
        this.right_leg3 = new ModelRenderer(this, 27, 51);
        this.right_leg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        
        this.right_foot_axis = new ModelRenderer(this, 70, 60);
        this.right_foot_axis.setRotationPoint(0.5F, 3.25F, 0.0F);
        this.right_foot_axis.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(right_foot_axis, 0.3490658503988659F, 0.0F, 0.0F);
        
        this.right_foot = new ModelRenderer(this, 36, 52);
        this.right_foot.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_foot.addBox(-1.5F, 0.0F, -3.5F, 2, 1, 4, 0.0F);

        // Child setup
        this.core.addChild(this.stomach_axis);
        this.core.addChild(this.tail1_axis);
        this.core.addChild(this.left_leg1_axis);
        this.core.addChild(this.right_leg1_axis);
        
        this.stomach_axis.addChild(this.stomach);
        this.stomach.addChild(this.rib_cage_axis);
        this.rib_cage_axis.addChild(this.rib_cage);
        this.rib_cage.addChild(this.neck_axis);
        this.neck_axis.addChild(this.neck);
        this.neck.addChild(this.head_axis);
        this.head_axis.addChild(this.head);
        this.head.addChild(this.low_mouth_axis);
        this.low_mouth_axis.addChild(this.low_mouth);
        this.head.addChild(this.top_head);
        this.head.addChild(this.top_mouth);
        
        this.rib_cage.addChild(this.left_arm_axis);
        this.left_arm_axis.addChild(this.left_arm);
        this.left_arm.addChild(this.left_shoulder_plate);
        this.left_arm.addChild(this.left_forearm_axis);
        this.left_forearm_axis.addChild(this.left_forearm);
        this.left_forearm.addChild(this.left_claws);
        
        this.rib_cage.addChild(this.right_arm_axis);
        this.right_arm_axis.addChild(this.right_arm);
        this.right_arm.addChild(this.right_shoulder_plate);
        this.right_arm.addChild(this.right_forearm_axis);
        this.right_forearm_axis.addChild(this.right_forearm);
        this.right_forearm.addChild(this.right_claws);
        
        this.tail1_axis.addChild(this.tail1);
        this.tail1.addChild(this.tail2_axis);
        this.tail2_axis.addChild(this.tail2);
        this.tail2.addChild(this.tail3_axis);
        this.tail3_axis.addChild(this.tail3);
        
        this.left_leg1_axis.addChild(this.left_leg1);
        this.left_leg1.addChild(this.left_leg2_axis);
        this.left_leg2_axis.addChild(this.left_leg2);
        this.left_leg2.addChild(this.left_leg3_axis);
        this.left_leg3_axis.addChild(this.left_leg3);
        this.left_leg3.addChild(this.left_foot_axis);
        this.left_foot_axis.addChild(this.left_foot);
        
        this.right_leg1_axis.addChild(this.right_leg1);
        this.right_leg1.addChild(this.right_leg2_axis);
        this.right_leg2_axis.addChild(this.right_leg2);
        this.right_leg2.addChild(this.right_leg3_axis);
        this.right_leg3_axis.addChild(this.right_leg3);
        this.right_leg3.addChild(this.right_foot_axis);
        this.right_foot_axis.addChild(this.right_foot);
    }
    
    @Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {

    	core.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    	// Looking rotations
    	this.head.rotateAngleX = headPitch * ((float)Math.PI / 135F);
    	this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F) / 2;
    	this.rib_cage_axis.rotateAngleY =  netHeadYaw * ((float)Math.PI / 180F) / 5;
    	this.stomach_axis.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F) / 5;

    	//============================================================================================================================================================
    	// Always
        this.low_mouth.rotateAngleX = MathHelper.cos(ageInTicks * 0.06F) * 0.09F;
        this.neck.rotateAngleX = MathHelper.cos(ageInTicks * 0.06F) * 0.06F;
        this.neck.rotateAngleY = MathHelper.cos(ageInTicks * 0.04F) * 0.06F;
    	this.neck.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.04F) * 0.06F;
        this.rib_cage.rotateAngleX = -MathHelper.cos(ageInTicks * 0.06F) * 0.06F;
        this.tail1.rotateAngleX = MathHelper.cos(ageInTicks * 0.08F) * 0.09F;
        this.tail1.rotateAngleZ = MathHelper.cos(ageInTicks * 0.12F) * 0.06F;
    	this.tail2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.12F) * 0.06F;
    	this.tail3.rotateAngleZ = MathHelper.cos(ageInTicks * 0.12F) * 0.06F;
    	//============================================================================================================================================================
    	// Idle entity
    	if(!entityIn.isEntityMoving()) {
    		//																				  Speed    Amount
    		this.head_axis.rotateAngleX = -0.5235987755982988F + (MathHelper.cos(ageInTicks * 0.06F) * 0.06F);
        	this.head_axis.rotateAngleY = MathHelper.cos(ageInTicks * 0.05F) * 0.06F;
        	this.head_axis.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.05F) * 0.06F;
        	this.left_arm.rotateAngleX = -0.17453292519943295F + (-MathHelper.cos(ageInTicks * 0.06F) * 0.03F);
    		this.left_arm.rotateAngleZ = MathHelper.cos(ageInTicks * 0.09F) * 0.03F;
    		this.right_arm.rotateAngleX = -0.17453292519943295F + (MathHelper.cos(ageInTicks * 0.06F) * 0.03F);
    		this.right_arm.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.09F) * 0.03F;
    	}
    	// Moving legs
    	this.left_leg1_axis.rotateAngleX = -0.3490658503988659F + (MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 1.2F * limbSwingAmount);
    	this.left_leg2_axis.rotateAngleX = 0.9599310885968813F + (-MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.0F * limbSwingAmount);
    	this.left_leg3_axis.rotateAngleX = -0.8726646259971648F + (MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.5F * limbSwingAmount);
    	this.left_foot_axis.rotateAngleX = 0.3490658503988659F + (-MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.4F * limbSwingAmount);
    	this.right_leg1_axis.rotateAngleX = -0.3490658503988659F + (MathHelper.cos(limbSwing * 0.75F) * 1.2F * limbSwingAmount);
   		this.right_leg2_axis.rotateAngleX = 0.9599310885968813F + (-MathHelper.cos(limbSwing * 0.5F) * 1.0F * limbSwingAmount);
   		this.right_leg3_axis.rotateAngleX = -0.8726646259971648F + (MathHelper.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount);
   		this.right_foot_axis.rotateAngleX = 0.3490658503988659F + (-MathHelper.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount);
   		// Moving tail
    	this.tail1_axis.rotateAngleZ = MathHelper.cos(limbSwing * 0.5F) * 0.31F * limbSwingAmount;
    	this.tail2_axis.rotateAngleZ = MathHelper.cos(limbSwing * 0.5F) * 0.31F * limbSwingAmount;
    	this.tail3_axis.rotateAngleZ = MathHelper.cos(limbSwing * 0.5F) * 0.31F * limbSwingAmount;
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {

    	int i = entityIn.getAttackTimer();
    	if (i > 0) {
    		if (entityIn.getSelectHand()) {
    			choice = entityIn.getChoice();
    			entityIn.setSelectHand(false);
    		}
    		if (choice == 0) { // Left punch
    			// Left arm
            	this.left_arm_axis.rotateAngleX = (-0.75F + 0.75F * this.triangleWave((float)i - partialTick, 10.0F));
            	this.left_arm_axis.rotateAngleZ = (-0.5F + 0.5F * this.triangleWave((float)i - partialTick, 10.0F));
            	this.left_forearm_axis.rotateAngleX = 0 + (-1.25F + 1.25F * this.triangleWave((float)i - partialTick, 5.0F));
            	// Right arm
            	this.right_arm_axis.rotateAngleZ = -(-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 10.0F));
            	// Rib cage and stomach
            	this.rib_cage_axis.rotateAngleX = -(-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 5.0F));
            	this.rib_cage_axis.rotateAngleZ = (-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 10.0F));
            	this.stomach_axis.rotateAngleX = -(-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 10.0F));
            	this.stomach_axis.rotateAngleZ = (-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 10.0F));
    		} else { // Right punch
    			// Right arm
        		this.right_arm_axis.rotateAngleX = (-0.75F + 0.75F * this.triangleWave((float)i - partialTick, 10.0F));
            	this.right_arm_axis.rotateAngleZ = -(-0.5F + 0.5F * this.triangleWave((float)i - partialTick, 10.0F));
            	this.right_forearm_axis.rotateAngleX = 0 + (-1.25F + 1.25F * this.triangleWave((float)i - partialTick, 5.0F));
            	// Left arm
            	this.left_arm_axis.rotateAngleZ = (-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 10.0F));
            	// Rib cage and stomach
            	this.rib_cage_axis.rotateAngleX = -(-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 5.0F));
            	this.rib_cage_axis.rotateAngleZ = -(-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 10.0F));
            	this.stomach_axis.rotateAngleX = -(-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 10.0F));
            	this.stomach_axis.rotateAngleZ = -(-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 10.0F));
        	}
        } else {
        	entityIn.setSelectHand(true);
        	// Moving arms
        	this.left_arm_axis.rotateAngleX = -0.08726646259971647F + (MathHelper.cos(limbSwing * 0.75F) * 0.75F * limbSwingAmount);
        	this.left_forearm_axis.rotateAngleX = -0.2617993877991494F + (MathHelper.cos(limbSwing * 0.75F) * 0.75F * limbSwingAmount);
        	this.right_arm_axis.rotateAngleX = -0.08726646259971647F + (MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 0.75F * limbSwingAmount);
        	this.right_forearm_axis.rotateAngleX = -0.2617993877991494F + (MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 0.75F * limbSwingAmount);
        	// Readjustments of arms
        	this.left_arm_axis.rotateAngleY = 0;
        	this.left_arm_axis.rotateAngleZ = 0;
            this.right_arm_axis.rotateAngleY = 0;
            this.right_arm_axis.rotateAngleZ = 0;
            // Moving neck and head
        	this.head_axis.rotateAngleX = -0.5235987755982988F + (-MathHelper.cos(limbSwing * 1.5F) * 0.3F * limbSwingAmount);
        	this.neck_axis.rotateAngleX = 0.2617993877991494F + (MathHelper.cos(limbSwing * 1.5F) * 0.3F * limbSwingAmount);
            // Moving rib cage and stomach
            this.rib_cage_axis.rotateAngleX = 0.17453292519943295F ;
            this.rib_cage_axis.rotateAngleZ = 0.0F;
        	this.stomach_axis.rotateAngleX = 0.08726646259971647F ;
        	this.stomach_axis.rotateAngleZ = 0.0F;
        }
    }

    private float triangleWave(float n1, float n2) {
    	return (Math.abs(n1 % n2 - n2 * 0.5F) - n2 * 0.25F) / (n2 * 0.25F);
    }
}
