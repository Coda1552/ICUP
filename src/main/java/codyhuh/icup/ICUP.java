package codyhuh.icup;

import codyhuh.icup.common.entities.SmurfCat;
import codyhuh.icup.registry.ModEntities;
import codyhuh.icup.registry.ModItems;
import codyhuh.icup.registry.ModTabs;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ICUP.MOD_ID)
public class ICUP {
    public static final String MOD_ID = "icup";

    public ICUP() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(bus);
        ModTabs.TABS.register(bus);
        ModEntities.ENTITIES.register(bus);

        bus.addListener(this::createAttributes);
    }

    private void createAttributes(EntityAttributeCreationEvent e) {
        e.put(ModEntities.SMURF_CAT.get(), SmurfCat.createAttributes().build());
    }
}
