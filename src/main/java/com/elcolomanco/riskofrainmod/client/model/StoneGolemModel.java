package com.elcolomanco.riskofrainmod.client.model;

import com.elcolomanco.riskofrainmod.entities.StoneGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class StoneGolemModel<T extends StoneGolemEntity> extends EntityModel<T> {

	public ModelRenderer core;
    public ModelRenderer torso_axis;
    public ModelRenderer torso;
    public ModelRenderer torso2;
    public ModelRenderer torso3;
    public ModelRenderer torso4;
    public ModelRenderer torso5;
    public ModelRenderer torso6;
    public ModelRenderer neck_axis;
    public ModelRenderer neck;

    public ModelRenderer head_axis;
    public ModelRenderer head;
    public ModelRenderer head2;
    public ModelRenderer eye;
    public ModelRenderer head3_1;
    public ModelRenderer head3_2;
    public ModelRenderer head4_1;
    public ModelRenderer head5_1;
    public ModelRenderer head4_2;
    public ModelRenderer head5_2;
    public ModelRenderer head6;
    public ModelRenderer head7_1;
    public ModelRenderer head7_2;
    public ModelRenderer head7_3;
    public ModelRenderer head8_1;
    public ModelRenderer head8_2_1;
    public ModelRenderer head8_2_2;

    public ModelRenderer left_shoulder_axis;
    public ModelRenderer left_shoulder;
    public ModelRenderer left_shoulder2;
    public ModelRenderer left_shoulder3;
    public ModelRenderer left_arm1_axis;
    public ModelRenderer left_arm1;
    public ModelRenderer left_arm1_1;
    public ModelRenderer left_arm2_axis;
    public ModelRenderer left_arm2;
    public ModelRenderer left_forearm_axis;
    public ModelRenderer left_forearm;
    public ModelRenderer left_forearm2;
    public ModelRenderer left_forearm3;
    public ModelRenderer left_thumb;
    public ModelRenderer left_hand1_axis;
    public ModelRenderer left_hand1;
    public ModelRenderer left_hand1_1;
    public ModelRenderer left_hand2_axis;
    public ModelRenderer left_hand2;
    public ModelRenderer left_hand2_1;

    public ModelRenderer right_shoulder_axis;
    public ModelRenderer right_shoulder;
    public ModelRenderer right_arm_axis;
    public ModelRenderer right_arm;
    public ModelRenderer right_arm2;
    public ModelRenderer right_arm3;
    public ModelRenderer right_elbow_axis;
    public ModelRenderer right_elbow;
    public ModelRenderer right_forearm_axis;
    public ModelRenderer right_forearm;
    public ModelRenderer right_forearm2;
    public ModelRenderer right_forearm3;
    public ModelRenderer right_thumb;
    public ModelRenderer right_hand1_axis;
    public ModelRenderer right_hand1;
    public ModelRenderer right_hand1_1;
    public ModelRenderer right_hand2_axis;
    public ModelRenderer right_hand2;
    public ModelRenderer right_hand2_1;

    public ModelRenderer hip_axis;
    public ModelRenderer hip;
    public ModelRenderer hip2;
    public ModelRenderer hip3_1;
    public ModelRenderer hip3_2;

    public ModelRenderer left_leg_axis;
    public ModelRenderer left_leg;
    public ModelRenderer left_leg1_1;
    public ModelRenderer left_leg1_2;
    public ModelRenderer left_leg2_axis;
    public ModelRenderer left_leg2;
    public ModelRenderer left_leg2_1;
    public ModelRenderer left_leg2_2;

    public ModelRenderer right_leg_axis;
    public ModelRenderer right_leg;
    public ModelRenderer right_leg1_1;
    public ModelRenderer right_leg1_2;
    public ModelRenderer right_leg2_axis;
    public ModelRenderer right_leg2;
    public ModelRenderer right_leg2_1;
    public ModelRenderer right_leg2_2;

    public StoneGolemModel() {
    	this.textureWidth = 422;
        this.textureHeight = 157;

        this.core = new ModelRenderer(this, 1, 149);
        this.core.setRotationPoint(0.0F, -8.5F, 0.0F);
        this.core.addBox(-5.0F, -1.5F, -2.5F, 10, 2, 5, 0.0F);

        this.torso_axis = new ModelRenderer(this, 32, 154);
        this.torso_axis.setRotationPoint(0.0F, -2.5F, 0.0F);
        this.torso_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.torso = new ModelRenderer(this, 1, 49);
        this.torso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.torso.addBox(-12.5F, -0.5F, -3.5F, 25, 1, 7, 0.0F);

        this.torso2 = new ModelRenderer(this, 66, 41);
        this.torso2.setRotationPoint(0.0F, -0.5F, 0.0F);
        this.torso2.addBox(-13.5F, -8.0F, -4.0F, 27, 8, 8, 0.0F);

        this.torso3 = new ModelRenderer(this, 137, 47);
        this.torso3.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.torso3.addBox(-12.5F, -2.0F, -4.0F, 25, 2, 8, 0.0F);

        this.torso4 = new ModelRenderer(this, 204, 47);
        this.torso4.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.torso4.addBox(-12.5F, -3.0F, -4.0F, 24, 2, 8, 0.0F);

        this.torso5 = new ModelRenderer(this, 269, 49);
        this.torso5.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.torso5.addBox(-11.5F, -2.0F, -3.5F, 22, 1, 7, 0.0F);

        this.torso6 = new ModelRenderer(this, 328, 54);
        this.torso6.setRotationPoint(-9.5F, -2.0F, -2.5F);
        this.torso6.addBox(-1.0F, -1.0F, -1.0F, 2, 1, 2, 0.0F);

        this.neck_axis = new ModelRenderer(this, 37, 154);
        this.neck_axis.setRotationPoint(-0.0F, -3.0F, 0.0F);
        this.neck_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.neck = new ModelRenderer(this, 1, 35);
        this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neck.addBox(-3.0F, -0.5F, -2.0F, 6, 1, 4, 0.0F);

        this.head_axis = new ModelRenderer(this, 42, 154);
        this.head_axis.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.head = new ModelRenderer(this, 1, 26);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-9.0F, -0.5F, -3.5F, 18, 1, 7, 0.0F);

        this.head2 = new ModelRenderer(this, 52, 25);
        this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head2.addBox(-11.0F, -1.5F, -4.0F, 22, 1, 8, 0.0F);

        this.head3_1 = new ModelRenderer(this, 137, 15);
        this.head3_1.setRotationPoint(-2.0F, -1.5F, 0.0F);
        this.head3_1.addBox(-10.0F, -1.0F, -4.0F, 10, 1, 8, 0.0F);

        this.head3_2 = new ModelRenderer(this, 1, 15);
        this.head3_2.setRotationPoint(2.0F, -1.5F, 0.0F);
        this.head3_2.addBox(0.0F, -1.0F, -4.0F, 10, 1, 8, 0.0F);

        this.head4_1 = new ModelRenderer(this, 174, 12);
        this.head4_1.setRotationPoint(-1.0F, -1.0F, 0.0F);
        this.head4_1.addBox(-9.0F, -4.0F, -4.0F, 9, 4, 8, 0.0F);

        this.head4_2 = new ModelRenderer(this, 38, 12);
        this.head4_2.setRotationPoint(1.0F, -1.0F, 0.0F);
        this.head4_2.addBox(0.0F, -4.0F, -4.0F, 9, 4, 8, 0.0F);

        this.head5_1 = new ModelRenderer(this, 209, 15);
        this.head5_1.setRotationPoint(1.0F, -4.0F, 0.0F);
        this.head5_1.addBox(-10.0F, -1.0F, -4.0F, 10, 1, 8, 0.0F);

        this.head5_2 = new ModelRenderer(this, 73, 15);
        this.head5_2.setRotationPoint(-1.0F, -4.0F, 0.0F);
        this.head5_2.addBox(0.0F, -1.0F, -4.0F, 10, 1, 8, 0.0F);

        this.head6 = new ModelRenderer(this, 1, 1);
        this.head6.setRotationPoint(-2.0F, -1.0F, 0.0F);
        this.head6.addBox(-11.0F, -1.0F, -4.0F, 23, 1, 8, 0.0F);

        this.head7_1 = new ModelRenderer(this, 130, 4);
        this.head7_1.setRotationPoint(-8.5F, -1.0F, 1.5F);
        this.head7_1.addBox(-1.5F, -1.0F, -2.5F, 3, 1, 5, 0.0F);

        this.head7_2 = new ModelRenderer(this, 111, 2);
        this.head7_2.setRotationPoint(-5.0F, -1.0F, 0.5F);
        this.head7_2.addBox(-2.0F, -1.0F, -3.5F, 2, 1, 7, 0.0F);

        this.head7_3 = new ModelRenderer(this, 64, 2);
        this.head7_3.setRotationPoint(-5.0F, -1.0F, 0.0F);
        this.head7_3.addBox(0.0F, -1.0F, -3.5F, 16, 1, 7, 0.0F);

        this.head8_1 = new ModelRenderer(this, 173, 8);
        this.head8_1.setRotationPoint(7.0F, -1.0F, -3.0F);
        this.head8_1.addBox(-1.0F, -1.0F, -0.5F, 2, 1, 1, 0.0F);

        this.head8_2_1 = new ModelRenderer(this, 147, 5);
        this.head8_2_1.setRotationPoint(13.0F, -1.0F, -2.0F);
        this.head8_2_1.addBox(-2.0F, -1.0F, -1.5F, 4, 1, 4, 0.0F);

        this.head8_2_2 = new ModelRenderer(this, 164, 7);
        this.head8_2_2.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.head8_2_2.addBox(-1.0F, -1.0F, -1.0F, 2, 1, 2, 0.0F);

        this.eye = new ModelRenderer(this, 110, 11);
        this.eye.setRotationPoint(0.0F, -1.5F, 0.5F);
        this.eye.addBox(-3.0F, -6.0F, -3.5F, 6, 6, 7, 0.0F);

        this.left_shoulder_axis = new ModelRenderer(this, 47, 154);
        this.left_shoulder_axis.setRotationPoint(16.5F, -1.5F, 0.0F);
        this.left_shoulder_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.left_shoulder = new ModelRenderer(this, 1, 67);
        this.left_shoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_shoulder.addBox(-2.5F, -0.5F, -2.5F, 5, 1, 5, 0.0F);
        this.setRotateAngle(left_shoulder, 0.0F, 0.0F, -0.5235987755982988F);

        this.left_shoulder2 = new ModelRenderer(this, 23, 58);
        this.left_shoulder2.setRotationPoint(0.5F, 1.5F, 0.0F);
        this.left_shoulder2.addBox(-4.0F, -1.0F, -4.0F, 8, 7, 8, 0.0F);

        this.left_shoulder3 = new ModelRenderer(this, 56, 66);
        this.left_shoulder3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_shoulder3.addBox(-3.0F, 6.0F, -3.0F, 6, 1, 6, 0.0F);

        this.left_arm1_axis = new ModelRenderer(this, 52, 154);
        this.left_arm1_axis.setRotationPoint(-2.0F, 9.0F, 0.0F);
        this.left_arm1_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_arm1_axis, 0.0F, 0.0F, 0.5235987755982988F);

        this.left_arm1 = new ModelRenderer(this, 81, 68);
        this.left_arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_arm1.addBox(-3.0F, -0.5F, -2.0F, 6, 1, 4, 0.0F);
        this.setRotateAngle(left_arm1, 0.0F, -0.08726646259971647F, -0.2617993877991494F);

        this.left_arm1_1 = new ModelRenderer(this, 102, 67);
        this.left_arm1_1.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.left_arm1_1.addBox(-3.5F, 0.0F, -2.5F, 7, 1, 5, 0.0F);

        this.left_arm2_axis = new ModelRenderer(this, 57, 154);
        this.left_arm2_axis.setRotationPoint(0.0F, 2.5F, 0.0F);
        this.left_arm2_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_arm2_axis, 0.0F, 0.0F, 0.2617993877991494F);

        this.left_arm2 = new ModelRenderer(this, 127, 67);
        this.left_arm2.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.left_arm2.addBox(-4.5F, -0.5F, -2.0F, 9, 2, 4, 0.0F);
        this.setRotateAngle(left_arm2, 0.0F, 0.0F, -0.08726646259971647F);

        this.left_forearm_axis = new ModelRenderer(this, 62, 154);
        this.left_forearm_axis.setRotationPoint(0.0F, 3.5F, 0.0F);
        this.left_forearm_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(left_forearm_axis, 0.0F, 0.0F, 0.08726646259971647F);

        this.left_forearm = new ModelRenderer(this, 1, 87);
        this.left_forearm.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.left_forearm.addBox(-3.0F, -0.5F, -4.0F, 6, 1, 8, 0.0F);
        this.setRotateAngle(left_forearm, 0.0F, -0.08726646259971647F, 0.0F);

        this.left_forearm2 = new ModelRenderer(this, 30, 74);
        this.left_forearm2.setRotationPoint(0.5F, 0.5F, 0.5F);
        this.left_forearm2.addBox(-4.0F, 0.0F, -5.0F, 8, 12, 10, 0.0F);

        this.left_forearm3 = new ModelRenderer(this, 67, 87);
        this.left_forearm3.setRotationPoint(0.0F, 12.0F, -0.5F);
        this.left_forearm3.addBox(-3.0F, 0.0F, -4.0F, 6, 1, 8, 0.0F);

        this.left_hand1 = new ModelRenderer(this, 96, 88);
        this.left_hand1.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.left_hand1.addBox(-2.5F, -0.5F, -3.5F, 5, 1, 7, 0.0F);

        this.left_hand1_axis = new ModelRenderer(this, 67, 154);
        this.left_hand1_axis.setRotationPoint(-0.5F, 2.0F, 0.5F);
        this.left_hand1_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.left_hand1_1 = new ModelRenderer(this, 121, 88);
        this.left_hand1_1.setRotationPoint(0.5F, 0.0F, 0.0F);
        this.left_hand1_1.addBox(-2.0F, 0.5F, -3.5F, 4, 1, 7, 0.0F);

        this.left_hand2_axis = new ModelRenderer(this, 72, 154);
        this.left_hand2_axis.setRotationPoint(1.0F, 2.5F, 0.0F);
        this.left_hand2_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.left_hand2 = new ModelRenderer(this, 144, 86);
        this.left_hand2.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.left_hand2.addBox(-2.5F, -0.5F, -3.5F, 5, 3, 7, 0.0F);

        this.left_hand2_1 = new ModelRenderer(this, 169, 88);
        this.left_hand2_1.setRotationPoint(-0.5F, 2.5F, 0.0F);
        this.left_hand2_1.addBox(-2.0F, 0.0F, -3.0F, 4, 2, 6, 0.0F);

        this.left_thumb = new ModelRenderer(this, 190, 88);
        this.left_thumb.setRotationPoint(-2.0F, 6.5F, -6.0F);
        this.left_thumb.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);

        this.right_shoulder_axis = new ModelRenderer(this, 77, 154);
        this.right_shoulder_axis.setRotationPoint(-15.5F, 0.0F, 0.0F);
        this.right_shoulder_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.right_shoulder = new ModelRenderer(this, 154, 59);
        this.right_shoulder.setRotationPoint(0.0F, 0.0F, 0.5F);
        this.right_shoulder.addBox(-3.0F, -0.5F, -6.0F, 6, 2, 12, 0.0F);
        this.setRotateAngle(right_shoulder, 0.0F, 0.0F, 0.7853981633974483F);

        this.right_arm_axis = new ModelRenderer(this, 82, 154);
        this.right_arm_axis.setRotationPoint(1.0F, 4.0F, 0.0F);
        this.right_arm_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(right_arm_axis, 0.0F, 0.0F, -0.7853981633974483F);

        this.right_arm = new ModelRenderer(this, 191, 67);
        this.right_arm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_arm.addBox(-2.5F, -0.5F, -2.5F, 5, 1, 5, 0.0F);
        this.setRotateAngle(right_arm, 0.0F, 0.08726646259971647F, 0.39269908169872414F);

        this.right_arm2 = new ModelRenderer(this, 212, 58);
        this.right_arm2.setRotationPoint(-0.5F, 1.5F, 0.0F);
        this.right_arm2.addBox(-4.0F, -1.0F, -4.0F, 8, 7, 8, 0.0F);

        this.right_arm3 = new ModelRenderer(this, 245, 66);
        this.right_arm3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_arm3.addBox(-3.0F, 6.0F, -3.0F, 6, 1, 6, 0.0F);

        this.right_elbow_axis = new ModelRenderer(this, 87, 154);
        this.right_elbow_axis.setRotationPoint(0.5F, 9.0F, 0.0F);
        this.right_elbow_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(right_elbow_axis, 0.0F, 0.0F, -0.39269908169872414F);

        this.right_elbow = new ModelRenderer(this, 203, 91);
        this.right_elbow.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_elbow.addBox(-2.0F, -0.5F, -2.0F, 4, 1, 4, 0.0F);

        this.right_forearm_axis = new ModelRenderer(this, 92, 154);
        this.right_forearm_axis.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.right_forearm_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.right_forearm = new ModelRenderer(this, 220, 87);
        this.right_forearm.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.right_forearm.addBox(-3.0F, -0.5F, -4.0F, 6, 1, 8, 0.0F);
        this.setRotateAngle(right_forearm, 0.0F, 0.08726646259971647F, 0.0F);

        this.right_forearm2 = new ModelRenderer(this, 249, 74);
        this.right_forearm2.setRotationPoint(0.5F, 0.5F, 0.5F);
        this.right_forearm2.addBox(-4.0F, 0.0F, -5.0F, 8, 12, 10, 0.0F);

        this.right_forearm3 = new ModelRenderer(this, 286, 87);
        this.right_forearm3.setRotationPoint(0.0F, 12.0F, -0.5F);
        this.right_forearm3.addBox(-3.0F, 0.0F, -4.0F, 6, 1, 8, 0.0F);

        this.right_hand1_axis = new ModelRenderer(this, 97, 154);
        this.right_hand1_axis.setRotationPoint(0.5F, 2.0F, 0.5F);
        this.right_hand1_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.right_hand1 = new ModelRenderer(this, 315, 88);
        this.right_hand1.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.right_hand1.addBox(-2.5F, -0.5F, -3.5F, 5, 1, 7, 0.0F);

        this.right_hand1_1 = new ModelRenderer(this, 340, 88);
        this.right_hand1_1.setRotationPoint(-0.5F, 0.0F, 0.0F);
        this.right_hand1_1.addBox(-2.0F, 0.5F, -3.5F, 4, 1, 7, 0.0F);

        this.right_hand2_axis = new ModelRenderer(this, 102, 154);
        this.right_hand2_axis.setRotationPoint(0.5F, 2.5F, 0.0F);
        this.right_hand2_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.right_hand2 = new ModelRenderer(this, 363, 86);
        this.right_hand2.setRotationPoint(-0.5F, 0.0F, 0.0F);
        this.right_hand2.addBox(-2.5F, -0.5F, -3.5F, 5, 3, 7, 0.0F);

        this.right_hand2_1 = new ModelRenderer(this, 388, 88);
        this.right_hand2_1.setRotationPoint(0.5F, 2.5F, 0.0F);
        this.right_hand2_1.addBox(-2.0F, 0.0F, -3.0F, 4, 2, 6, 0.0F);

        this.right_thumb = new ModelRenderer(this, 409, 88);
        this.right_thumb.setRotationPoint(2.0F, 6.5F, -6.0F);
        this.right_thumb.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);

        this.hip_axis = new ModelRenderer(this, 107, 154);
        this.hip_axis.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.hip_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.hip = new ModelRenderer(this, 1, 97);
        this.hip.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.hip.addBox(-11.0F, -2.5F, -3.0F, 22, 4, 6, 0.0F);

        this.hip2 = new ModelRenderer(this, 58, 101);
        this.hip2.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.hip2.addBox(-5.0F, 0.0F, -2.5F, 10, 1, 5, 0.0F);

        this.hip3_1 = new ModelRenderer(this, 110, 101);
        this.hip3_1.setRotationPoint(-8.0F, -5.0F, 0.0F);
        this.hip3_1.addBox(-2.5F, 0.0F, -2.5F, 5, 1, 5, 0.0F);

        this.hip3_2 = new ModelRenderer(this, 89, 101);
        this.hip3_2.setRotationPoint(8.0F, -5.0F, 0.0F);
        this.hip3_2.addBox(-2.5F, 0.0F, -2.5F, 5, 1, 5, 0.0F);

        this.left_leg_axis = new ModelRenderer(this, 112, 154);
        this.left_leg_axis.setRotationPoint(9.0F, 3.0F, 0.0F);
        this.left_leg_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.left_leg = new ModelRenderer(this, 1, 121);
        this.left_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_leg.addBox(-3.0F, -0.5F, -3.0F, 6, 1, 6, 0.0F);
        this.setRotateAngle(left_leg, 0.0F, -0.17453292519943295F, 0.0F);

        this.left_leg1_1 = new ModelRenderer(this, 26, 109);
        this.left_leg1_1.setRotationPoint(0.5F, 0.5F, 0.5F);
        this.left_leg1_1.addBox(-4.5F, 0.0F, -4.5F, 8, 11, 8, 0.0F);

        this.left_leg1_2 = new ModelRenderer(this, 59, 120);
        this.left_leg1_2.setRotationPoint(-0.5F, 11.0F, -0.5F);
        this.left_leg1_2.addBox(-3.5F, 0.0F, -3.5F, 7, 1, 7, 0.0F);

        this.left_leg2_axis = new ModelRenderer(this, 117, 154);
        this.left_leg2_axis.setRotationPoint(-0.5F, 2.0F, 0.0F);
        this.left_leg2_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.left_leg2 = new ModelRenderer(this, 1, 141);
        this.left_leg2.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.left_leg2.addBox(-2.5F, -0.5F, -3.0F, 5, 1, 6, 0.0F);
        this.setRotateAngle(left_leg2, 0.0F, -0.08726646259971647F, 0.0F);

        this.left_leg2_1 = new ModelRenderer(this, 24, 129);
        this.left_leg2_1.setRotationPoint(0.5F, 0.5F, 1.0F);
        this.left_leg2_1.addBox(-3.5F, 0.0F, -4.5F, 7, 10, 9, 0.0F);

        this.left_leg2_2 = new ModelRenderer(this, 57, 139);
        this.left_leg2_2.setRotationPoint(1.0F, 10.0F, 0.0F);
        this.left_leg2_2.addBox(-3.5F, 0.0F, -3.5F, 5, 2, 7, 0.0F);

        this.right_leg_axis = new ModelRenderer(this, 122, 154);
        this.right_leg_axis.setRotationPoint(-10.0F, 3.0F, 0.0F);
        this.right_leg_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.right_leg = new ModelRenderer(this, 88, 121);
        this.right_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_leg.addBox(-3.0F, -0.5F, -3.0F, 7, 1, 6, 0.0F);
        this.setRotateAngle(right_leg, 0.0F, 0.17453292519943295F, 0.0F);

        this.right_leg1_1 = new ModelRenderer(this, 115, 108);
        this.right_leg1_1.setRotationPoint(0.5F, 0.5F, 0.0F);
        this.right_leg1_1.addBox(-4.5F, 0.0F, -4.0F, 9, 12, 8, 0.0F);

        this.right_leg1_2 = new ModelRenderer(this, 150, 121);
        this.right_leg1_2.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.right_leg1_2.addBox(-3.5F, 0.0F, -3.0F, 7, 1, 6, 0.0F);

        this.right_leg2_axis = new ModelRenderer(this, 127, 154);
        this.right_leg2_axis.setRotationPoint(0.5F, 2.0F, 0.0F);
        this.right_leg2_axis.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

        this.right_leg2 = new ModelRenderer(this, 82, 141);
        this.right_leg2.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.right_leg2.addBox(-2.5F, -0.5F, -3.0F, 5, 1, 6, 0.0F);
        this.setRotateAngle(right_leg2, 0.0F, 0.08726646259971647F, 0.0F);

        this.right_leg2_1 = new ModelRenderer(this, 105, 130);
        this.right_leg2_1.setRotationPoint(0.0F, 0.5F, -0.5F);
        this.right_leg2_1.addBox(-3.5F, 0.0F, -4.5F, 7, 9, 9, 0.0F);

        this.right_leg2_2 = new ModelRenderer(this, 138, 139);
        this.right_leg2_2.setRotationPoint(1.0F, 9.0F, 0.0F);
        this.right_leg2_2.addBox(-3.5F, 0.0F, -3.5F, 5, 2, 7, 0.0F);

        // Child setup
        this.core.addChild(this.torso_axis);
        this.core.addChild(this.hip_axis);
        
        this.torso_axis.addChild(this.torso);
        this.torso.addChild(this.torso2);
        this.torso2.addChild(this.torso3);
        this.torso3.addChild(this.torso4);
        this.torso4.addChild(this.torso5);
        this.torso5.addChild(this.torso6);
        this.torso5.addChild(this.neck_axis);
        this.torso5.addChild(this.left_shoulder_axis);
        this.torso5.addChild(this.right_shoulder_axis);

        this.neck_axis.addChild(this.neck);
        this.neck.addChild(this.head_axis);

        this.head_axis.addChild(this.head);
        this.head.addChild(this.head2);
        this.head2.addChild(this.eye);
        this.head2.addChild(this.head3_1);
        this.head2.addChild(this.head3_2);
        this.head3_1.addChild(this.head4_1);
        this.head3_2.addChild(this.head4_2);
        this.head4_1.addChild(this.head5_1);
        this.head4_2.addChild(this.head5_2);
        this.head5_2.addChild(this.head6);
        this.head6.addChild(this.head7_1);
        this.head6.addChild(this.head7_2);
        this.head6.addChild(this.head7_3);
        this.head7_3.addChild(this.head8_1);
        this.head7_3.addChild(this.head8_2_1);
        this.head8_2_1.addChild(this.head8_2_2);

        this.left_shoulder_axis.addChild(this.left_shoulder);
        this.left_shoulder.addChild(this.left_shoulder2);
        this.left_shoulder2.addChild(this.left_shoulder3);
        this.left_shoulder3.addChild(this.left_arm1_axis);
        this.left_arm1_axis.addChild(this.left_arm1);
        this.left_arm1.addChild(this.left_arm1_1);
        this.left_arm1_1.addChild(this.left_arm2_axis);
        this.left_arm2_axis.addChild(this.left_arm2);
        this.left_arm2.addChild(this.left_forearm_axis);
        this.left_forearm_axis.addChild(this.left_forearm);
        this.left_forearm.addChild(this.left_forearm2);
        this.left_forearm2.addChild(this.left_forearm3);
        this.left_forearm2.addChild(this.left_thumb);
        this.left_forearm3.addChild(this.left_hand1_axis);
        this.left_hand1_axis.addChild(this.left_hand1);
        this.left_hand1.addChild(this.left_hand1_1);
        this.left_hand1_1.addChild(this.left_hand2_axis);
        this.left_hand2_axis.addChild(this.left_hand2);
        this.left_hand2.addChild(this.left_hand2_1);

        this.right_shoulder_axis.addChild(this.right_shoulder);
        this.right_shoulder.addChild(this.right_arm_axis);
        this.right_arm_axis.addChild(this.right_arm);
        this.right_arm.addChild(this.right_arm2);
        this.right_arm2.addChild(this.right_arm3);
        this.right_arm3.addChild(this.right_elbow_axis);
        this.right_elbow_axis.addChild(this.right_elbow);
        this.right_elbow.addChild(this.right_forearm_axis);
        this.right_forearm_axis.addChild(this.right_forearm);
        this.right_forearm.addChild(this.right_forearm2);
        this.right_forearm2.addChild(this.right_forearm3);
        this.right_forearm2.addChild(this.right_thumb);
        this.right_forearm3.addChild(this.right_hand1_axis);
        this.right_hand1_axis.addChild(this.right_hand1);
        this.right_hand1.addChild(this.right_hand1_1);
        this.right_hand1_1.addChild(this.right_hand2_axis);
        this.right_hand2_axis.addChild(this.right_hand2);
        this.right_hand2.addChild(this.right_hand2_1);

        this.hip_axis.addChild(this.hip);
        this.hip.addChild(this.left_leg_axis);
        this.hip.addChild(this.right_leg_axis);
        this.hip.addChild(this.hip2);
        this.hip2.addChild(this.hip3_1);
        this.hip2.addChild(this.hip3_2);
        
        this.left_leg_axis.addChild(this.left_leg);
        this.left_leg.addChild(this.left_leg1_1);
        this.left_leg1_1.addChild(this.left_leg1_2);
        this.left_leg1_2.addChild(this.left_leg2_axis);
        this.left_leg2_axis.addChild(this.left_leg2);
        this.left_leg2.addChild(this.left_leg2_1);
        this.left_leg2_1.addChild(this.left_leg2_2);

        this.right_leg_axis.addChild(this.right_leg);
        this.right_leg.addChild(this.right_leg1_1);
        this.right_leg1_1.addChild(this.right_leg1_2);
        this.right_leg1_2.addChild(this.right_leg2_axis);
        this.right_leg2_axis.addChild(this.right_leg2);
        this.right_leg2.addChild(this.right_leg2_1);
        this.right_leg2_1.addChild(this.right_leg2_2);
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
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
    	this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F / 3);
    	this.neck.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F / 3);
    	this.torso.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F / 3);
    	// Always
        this.torso.rotateAngleX = -MathHelper.cos(ageInTicks * 0.06F) * 0.06F;
        this.neck.rotateAngleX = MathHelper.cos(ageInTicks * 0.06F) * 0.06F;
        // Idle entity
        this.left_shoulder.rotateAngleX = (-MathHelper.cos(ageInTicks * 0.06F) * 0.03F);	// Arms
        this.left_shoulder.rotateAngleZ = -0.5235987755982988F + (MathHelper.cos(ageInTicks * 0.06F) * 0.03F);

        this.right_shoulder.rotateAngleX = (MathHelper.cos(ageInTicks * 0.06F) * 0.03F);
        this.right_shoulder.rotateAngleZ = 0.7853981633974483F+ (-MathHelper.cos(ageInTicks * 0.06F) * 0.03F);
    	// Moving legs
    	this.hip_axis.rotateAngleY = (MathHelper.cos(limbSwing * 0.5F) * 1.2F * limbSwingAmount);
    	this.left_leg_axis.rotateAngleX = 0 + (MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 1.2F * limbSwingAmount);
    	this.left_leg2_axis.rotateAngleX = 0 + (-MathHelper.cos(limbSwing * 1.0F + (float)Math.PI) * 0.6F * limbSwingAmount);
    	this.right_leg_axis.rotateAngleX = 0 + (MathHelper.cos(limbSwing * 0.5F) * 1.2F * limbSwingAmount);
    	this.right_leg2_axis.rotateAngleX = 0 + (MathHelper.cos(limbSwing * 1.0F) * 0.6F * limbSwingAmount);
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		int i = entityIn.getAttackTimer();
		if (i > 0) {
			// Clap
			this.left_shoulder_axis.rotateAngleX	= (-0.75F + 0.75F * this.triangleWave((float)i - partialTick, 15.0F));
			this.left_shoulder_axis.rotateAngleY	= -(-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 10.0F));
			this.left_shoulder_axis.rotateAngleZ	= (-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 10.0F));
			this.left_arm1_axis.rotateAngleZ		= -(-0.5F + 0.5F * this.triangleWave((float)i - partialTick, 15.0F));
			this.left_forearm_axis.rotateAngleX		= (-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 15.0F));
			this.left_forearm_axis.rotateAngleZ		= -(-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 15.0F));
			this.left_hand1_axis.rotateAngleZ		= (-0.5F + 0.5F * this.triangleWave((float)i - partialTick, 15.0F));
			// Right arm
			this.right_shoulder_axis.rotateAngleX	= (-0.75F + 0.75F * this.triangleWave((float)i - partialTick, 15.0F));
			this.right_shoulder_axis.rotateAngleY	= (-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 10-.0F));
			this.right_shoulder_axis.rotateAngleZ	= -(-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 10.0F));
			this.right_elbow_axis.rotateAngleZ		= (-0.5F + 0.5F * this.triangleWave((float)i - partialTick, 15.0F));
			this.right_forearm_axis.rotateAngleX	= (-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 15.0F));
			this.right_forearm_axis.rotateAngleZ	= (-0.25F + 0.25F * this.triangleWave((float)i - partialTick, 15.0F));
			this.right_hand1_axis.rotateAngleZ		= -(-0.5F + 0.5F * this.triangleWave((float)i - partialTick, 15.0F));
			// Torso
			this.torso_axis.rotateAngleX			= -(-0.10F + 0.10F * this.triangleWave((float)i - partialTick, 15.0F));
		} else {
			// Moving arms
			this.left_shoulder_axis.rotateAngleX	= (MathHelper.cos(limbSwing * 0.5F) * 0.8F * limbSwingAmount);
			this.left_arm1_axis.rotateAngleX		= (MathHelper.cos(limbSwing * 0.5F) * 0.8F * limbSwingAmount);
			this.left_forearm_axis.rotateAngleX		= (-MathHelper.cos(limbSwing * 0.75F) * 0.8F * limbSwingAmount);
			this.left_hand1_axis.rotateAngleX		= (-MathHelper.cos(limbSwing * 0.75F) * 0.8F * limbSwingAmount);
			
			this.right_shoulder_axis.rotateAngleX	= (MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 0.8F * limbSwingAmount);
			this.right_arm_axis.rotateAngleX		= (MathHelper.cos(limbSwing * 0.5F + (float)Math.PI) * 0.8F * limbSwingAmount);
			this.right_forearm_axis.rotateAngleX	= (-MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 0.8F * limbSwingAmount);
			this.right_hand1_axis.rotateAngleX		= (-MathHelper.cos(limbSwing * 0.75F + (float)Math.PI) * 0.8F * limbSwingAmount);
			// Readjustments of arms
			this.left_shoulder_axis.rotateAngleY	= 0;
			this.left_shoulder_axis.rotateAngleZ	= 0;
			this.left_arm1_axis.rotateAngleZ		= 0.5235987755982988F;
			this.left_forearm_axis.rotateAngleZ		= 0.08726646259971647F;
			this.left_hand1_axis.rotateAngleZ		= 0;

			this.right_shoulder_axis.rotateAngleY	= 0;
			this.right_shoulder_axis.rotateAngleZ	= 0;
			this.right_elbow_axis.rotateAngleZ		= -0.39269908169872414F;
			this.right_forearm_axis.rotateAngleZ	= 0;
			this.right_hand1_axis.rotateAngleZ		= 0;
		}
	}

	private float triangleWave(float n1, float n2) {
		return (Math.abs(n1 % n2 - n2 * 0.5F) - n2 * 0.25F) / (n2 * 0.25F);
	}
}
