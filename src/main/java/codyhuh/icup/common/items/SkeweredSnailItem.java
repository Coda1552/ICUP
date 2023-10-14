package codyhuh.icup.common.items;

import codyhuh.icup.common.menu.SkeweredSnailMenu;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SkeweredSnailItem extends Item {

    public SkeweredSnailItem(Properties builder) {
        super(builder);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        ItemStack itemstack = player.getItemInHand(player.getUsedItemHand());

        player.openMenu(new SimpleMenuProvider((windowId, inv, owner) -> new SkeweredSnailMenu(windowId, inv, itemstack), itemstack.getDisplayName()));
        player.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pUsedHand) {
        ItemStack stack = player.getItemInHand(pUsedHand);

        player.openMenu(new SimpleMenuProvider((windowId, inv, owner) -> new SkeweredSnailMenu(windowId, inv, stack), stack.getDisplayName()));
        player.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide);
    }
}
