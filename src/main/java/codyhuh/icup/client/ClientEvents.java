package codyhuh.icup.client;

import codyhuh.icup.ICUP;
import codyhuh.icup.client.model.SmurfCatModel;
import codyhuh.icup.client.render.SmurfCatRenderer;
import codyhuh.icup.common.items.BlueberryCatItem;
import codyhuh.icup.registry.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ICUP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    private static final HumanoidModel.ArmPose STICK_POSE = HumanoidModel.ArmPose.create("stick_pose", false, (model, entity, arm) -> {
        if (arm.equals(HumanoidArm.LEFT)) {
            model.leftArm.xRot = -1.35F;
        }
        else if (arm.equals(HumanoidArm.RIGHT)) {
            model.rightArm.xRot = -1.35F;
        }
    });

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers e) {
        e.registerEntityRenderer(ModEntities.SMURF_CAT.get(), SmurfCatRenderer::new);
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(ModModelLayers.SMURF_CAT, SmurfCatModel::createBodyLayer);
    }

    @Mod.EventBusSubscriber(modid = ICUP.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    static class ForgeBusEvents {

        // Code adapted ochotonida's pumpkinlauncher mod, licensed under MIT
        @SubscribeEvent
        public static void setArmPose(RenderLivingEvent.Pre<?, ?> e) {
            boolean offHand = e.getEntity().getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof BlueberryCatItem;
            boolean mainHand = e.getEntity().getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof BlueberryCatItem;
            if ((mainHand && Minecraft.getInstance().options.mainHand().get() == HumanoidArm.RIGHT) || (offHand && Minecraft.getInstance().options.mainHand().get() == HumanoidArm.LEFT)) {
                ((HumanoidModel<?>) e.getRenderer().getModel()).rightArmPose = STICK_POSE;
            } else if ((mainHand && Minecraft.getInstance().options.mainHand().get() == HumanoidArm.LEFT) || (offHand && Minecraft.getInstance().options.mainHand().get() == HumanoidArm.RIGHT)) {
                ((HumanoidModel<?>) e.getRenderer().getModel()).leftArmPose = STICK_POSE;
            }
        }
    }
}
