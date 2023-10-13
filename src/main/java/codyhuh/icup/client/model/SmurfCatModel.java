package codyhuh.icup.client.model;


import codyhuh.icup.common.entities.SmurfCat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SmurfCatModel<T extends SmurfCat> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart lArm;
	private final ModelPart rArm;
	private final ModelPart lLeg;
	private final ModelPart rLeg;

	public SmurfCatModel(ModelPart root) {
		this.body = root.getChild("body");
		this.lArm = body.getChild("lArm");
		this.rArm = body.getChild("rArm");
		this.lLeg = body.getChild("lLeg");
		this.rLeg = body.getChild("rLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 18).addBox(-2.0F, -7.0F, -1.5F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -3.0F, -2.5F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(17, 13).addBox(-3.0F, -8.0F, -2.5F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -5.0F, -3.5F, 8.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(17, 9).addBox(-2.0F, -1.0F, -3.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition lArm = body.addOrReplaceChild("lArm", CubeListBuilder.create().texOffs(14, 18).addBox(0.0F, -1.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -6.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition rArm = body.addOrReplaceChild("rArm", CubeListBuilder.create().texOffs(14, 18).addBox(0.0F, -1.5F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition lLeg = body.addOrReplaceChild("lLeg", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -1.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 3.0F, -2.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, 0.0F));

		PartDefinition rLeg = body.addOrReplaceChild("rLeg", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -1.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 3.0F, -2.0F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rArm.xRot = Mth.cos(-1.0F + limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
		this.lArm.xRot = Mth.cos(-1.0F + limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
		this.rLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.lLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}