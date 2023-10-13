package codyhuh.icup.client.render;

import codyhuh.icup.ICUP;
import codyhuh.icup.client.ModModelLayers;
import codyhuh.icup.client.model.SmurfCatModel;
import codyhuh.icup.common.entities.SmurfCat;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SmurfCatRenderer extends MobRenderer<SmurfCat, SmurfCatModel<SmurfCat>> {
    private static final ResourceLocation LOCATION = new ResourceLocation(ICUP.MOD_ID, "textures/entity/smurf_cat.png");

    public SmurfCatRenderer(EntityRendererProvider.Context context) {
        super(context, new SmurfCatModel<>(context.bakeLayer(ModModelLayers.SMURF_CAT)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(SmurfCat cat) {
        return LOCATION;
    }
}
