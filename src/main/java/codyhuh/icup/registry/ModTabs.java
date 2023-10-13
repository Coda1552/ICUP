package codyhuh.icup.registry;

import codyhuh.icup.ICUP;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ICUP.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ICUP_TAB = TABS.register("icup_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BLUEBERRY_CAT.get()))
            .title(Component.translatable("itemGroup.icup"))
            .displayItems((pParameters, pOutput) -> {
                for (var item : ModItems.ITEMS.getEntries()) {
                    pOutput.accept(item.get());
                }
            }).build());}
