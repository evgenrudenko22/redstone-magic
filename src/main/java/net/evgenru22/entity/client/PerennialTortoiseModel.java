package net.evgenru22.entity.client;

import net.evgenru22.entity.animation.ModAnimations;
import net.evgenru22.entity.custom.PerennialTortoiseEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class PerennialTortoiseModel<T extends PerennialTortoiseEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart head;

	public PerennialTortoiseModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(44, 47).cuboid(-2.0F, -2.0F, -5.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.0F, -6.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, 0.625F, -6.875F, 16.0F, 2.0F, 14.0F, new Dilation(0.0F))
		.uv(0, 16).cuboid(-7.0F, 2.625F, -6.875F, 14.0F, 2.0F, 13.0F, new Dilation(0.0F))
		.uv(0, 31).cuboid(-7.0F, -3.375F, -5.875F, 14.0F, 4.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 47).cuboid(-6.0F, -4.375F, -4.875F, 12.0F, 1.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.625F, 0.875F));

		ModelPartData legr = root.addChild("legr", ModelPartBuilder.create().uv(52, 31).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -3.0F, 5.0F));

		ModelPartData legl = root.addChild("legl", ModelPartBuilder.create().uv(52, 39).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -3.0F, 5.0F));

		ModelPartData armr = root.addChild("armr", ModelPartBuilder.create().uv(54, 16).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -3.0F, -4.0F));

		ModelPartData arml = root.addChild("arml", ModelPartBuilder.create().uv(44, 57).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -3.0F, -4.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);

		this.animateMovement(ModAnimations.WALK, limbAngle, limbDistance, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.IDLE, animationProgress, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 10.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw * 0.017453292f;
		this.head.pitch = headPitch * 0.017453292f;
	}
}