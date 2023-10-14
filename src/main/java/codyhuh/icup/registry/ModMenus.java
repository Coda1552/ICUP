package codyhuh.icup.registry;

import codyhuh.icup.ICUP;
import codyhuh.icup.common.menu.SkeweredSnailMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ICUP.MOD_ID);

    public static final RegistryObject<MenuType<SkeweredSnailMenu>> SKEWERED_SNAIL = MENUS.register("skewered_snail", () -> new MenuType<>(SkeweredSnailMenu::new, FeatureFlags.DEFAULT_FLAGS));
}