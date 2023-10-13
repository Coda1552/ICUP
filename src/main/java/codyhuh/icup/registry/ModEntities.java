package codyhuh.icup.registry;

import codyhuh.icup.ICUP;
import codyhuh.icup.common.entities.SmurfCat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ICUP.MOD_ID);

    public static final RegistryObject<EntityType<SmurfCat>> SMURF_CAT = ENTITIES.register("smurf_cat", () -> EntityType.Builder.of(SmurfCat::new, MobCategory.CREATURE).sized(0.4F, 0.7F).build("smurf_cat"));
}
