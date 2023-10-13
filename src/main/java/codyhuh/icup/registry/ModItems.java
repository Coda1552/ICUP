package codyhuh.icup.registry;

import codyhuh.icup.ICUP;
import codyhuh.icup.common.items.BlueberryCatItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ICUP.MOD_ID);

    public static final RegistryObject<Item> BLUEBERRY_CAT = ITEMS.register("blueberry_cat", () -> new BlueberryCatItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).fast().build()).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> SMURF_CAT_SPAWN_EGG = ITEMS.register("smurf_cat_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SMURF_CAT, 0x52a0cf, 0xfcf5ee, new Item.Properties()));
}
