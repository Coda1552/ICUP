package codyhuh.icup.client;

import codyhuh.icup.ICUP;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation SMURF_CAT = create("smurf_cat");


    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(ICUP.MOD_ID, "smurfcat"), "main");
    }
}
