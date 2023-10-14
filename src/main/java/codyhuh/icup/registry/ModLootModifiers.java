package codyhuh.icup.registry;

import codyhuh.icup.ICUP;
import codyhuh.icup.common.loot.BlueberryCatLootModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ICUP.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> BLUEBERRY_CAT_LOOT_MODIFIER = LOOT_MODIFIERS.register("blueberry_cat_glm", BlueberryCatLootModifier.CODEC);
}
