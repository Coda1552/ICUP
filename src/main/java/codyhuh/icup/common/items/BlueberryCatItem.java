package codyhuh.icup.common.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlueberryCatItem extends Item {

    public BlueberryCatItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity holder, int i, boolean held) {
        if (held && level.getRandom().nextInt(60) == 0) {
            level.playSound(holder, holder.blockPosition(), SoundEvents.CAT_STRAY_AMBIENT, SoundSource.PLAYERS, 0.6F, 1.0F);
        }
    }
}
