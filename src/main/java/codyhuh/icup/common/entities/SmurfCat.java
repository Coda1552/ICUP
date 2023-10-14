package codyhuh.icup.common.entities;

import codyhuh.icup.registry.ModItems;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SmurfCat extends AbstractVillager {

    public SmurfCat(EntityType<? extends AbstractVillager> type, Level level) {
        super(type, level);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Evoker.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vindicator.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vex.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Pillager.class, 15.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Illusioner.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zoglin.class, 10.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    public boolean showProgressBar() {
        return false;
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!itemstack.is(Items.VILLAGER_SPAWN_EGG) && this.isAlive() && !this.isTrading() && !this.isBaby()) {
            if (hand == InteractionHand.MAIN_HAND) {
                player.awardStat(Stats.TALKED_TO_VILLAGER);
            }

            if (this.getOffers().isEmpty()) {
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else {
                if (!this.level().isClientSide) {
                    this.setTradingPlayer(player);
                    this.openTradingScreen(player, this.getDisplayName(), 1);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    protected void rewardTradeXp(MerchantOffer offer) {
        if (offer.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }

    }

    public static final Int2ObjectMap<VillagerTrades.ItemListing[]> SMURF_CAT_TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(
            1, new VillagerTrades.ItemListing[]{new ItemsForCurrency(Items.BROWN_MUSHROOM, 1, 3, 5, 1), new ItemsForCurrency(Items.RED_MUSHROOM, 1, 3, 5, 1), new EnchantedItemForEmeralds(Items.BOOK, 5, 1, 5, 1), new ItemsForCurrency(Items.ENCHANTED_GOLDEN_APPLE, 3, 1, 3, 1), new ItemsForCurrency(Items.GOLDEN_APPLE, 1, 2, 5, 1), new ItemsForCurrency(Items.NAME_TAG, 1, 2, 5, 1), new ItemsForCurrency(Items.SADDLE, 2, 1, 5, 1), new ItemsForCurrency(Items.TOTEM_OF_UNDYING, 4, 1, 5, 1), new ItemsForCurrency(Items.ENDER_EYE, 4, 1, 5, 1), new ItemsForCurrency(Items.EXPERIENCE_BOTTLE, 1, 2, 5, 1)},
            2, new VillagerTrades.ItemListing[]{new ItemsForCurrency(Items.NAUTILUS_SHELL, 2, 1, 5, 1), new ItemsForCurrency(Items.HEART_OF_THE_SEA, 2, 1, 4, 1), new ItemsForCurrency(Items.SCUTE, 3, 1, 4, 1), new ItemsForCurrency(Items.HONEYCOMB, 1, 3, 4, 1), new ItemsForCurrency(ModItems.SKEWERED_SNAIL.get(), 12, 1, 1, 2), new ItemsForMiscCurrency(ModItems.BLUEBERRY_CAT.get(), Items.BROWN_MUSHROOM_BLOCK, 32, 1, 5, 2), new ItemsForMiscCurrency(ModItems.BLUEBERRY_CAT.get(), Items.RED_MUSHROOM_BLOCK, 32, 1, 1, 2)}));

    @Override
    protected void updateTrades() {
        VillagerTrades.ItemListing[] itemListing1 = SMURF_CAT_TRADES.get(1);
        VillagerTrades.ItemListing[] itemListing2 = SMURF_CAT_TRADES.get(2);

        if (itemListing1 != null && itemListing2 != null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addOffersFromItemListings(merchantoffers, itemListing1, 5);
            int i = this.random.nextInt(itemListing2.length);
            VillagerTrades.ItemListing itemListing = itemListing2[i];
            MerchantOffer merchantOffer = itemListing.getOffer(this, this.random);

            if (merchantOffer != null) {
                merchantoffers.add(merchantOffer);
            }

        }
    }

    @Override
    protected float getStandingEyeHeight(Pose p_35297_, EntityDimensions p_35298_) {
        return p_35298_.height * 0.7F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes().add(Attributes.MAX_HEALTH, 12.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public float getVoicePitch() {
        return 4.5F;
    }

    protected SoundEvent getAmbientSound() {
        return this.isTrading() ? SoundEvents.WANDERING_TRADER_TRADE : SoundEvents.WANDERING_TRADER_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_35870_) {
        return SoundEvents.WANDERING_TRADER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.WANDERING_TRADER_DEATH;
    }

    protected SoundEvent getTradeUpdatedSound(boolean p_35890_) {
        return p_35890_ ? SoundEvents.WANDERING_TRADER_YES : SoundEvents.WANDERING_TRADER_NO;
    }

    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.WANDERING_TRADER_YES;
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    static class ItemsForCurrency implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int cost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForCurrency(Item item, int cost, int numberOfItems, int maxUses, int xpGranted) {
            this(new ItemStack(item), cost, numberOfItems, maxUses, xpGranted);
        }

        public ItemsForCurrency(ItemStack stack, int cost, int numberOfItems, int maxUses, int xpGranted) {
            this(stack, cost, numberOfItems, maxUses, xpGranted, 0.05F);
        }

        public ItemsForCurrency(ItemStack stack, int cost, int numberOfItems, int maxUses, int xpGranted, float priceMultiplier) {
            this.itemStack = stack;
            this.cost = cost;
            this.numberOfItems = numberOfItems;
            this.maxUses = maxUses;
            this.villagerXp = xpGranted;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
            return new MerchantOffer(new ItemStack(ModItems.BLUEBERRY_CAT.get(), this.cost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForMiscCurrency implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final ItemStack currency;
        private final int cost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForMiscCurrency(Item item, Item currency, int cost, int numberOfItems, int maxUses, int xpGranted) {
            this(new ItemStack(item), new ItemStack(currency), cost, numberOfItems, maxUses, xpGranted);
        }

        public ItemsForMiscCurrency(ItemStack stack, ItemStack currency, int cost, int numberOfItems, int maxUses, int xpGranted) {
            this(stack, currency, cost, numberOfItems, maxUses, xpGranted, 0.05F);
        }

        public ItemsForMiscCurrency(ItemStack stack, ItemStack currency, int cost, int numberOfItems, int maxUses, int xpGranted, float priceMultiplier) {
            this.itemStack = stack;
            this.currency = currency;
            this.cost = cost;
            this.numberOfItems = numberOfItems;
            this.maxUses = maxUses;
            this.villagerXp = xpGranted;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
            return new MerchantOffer(new ItemStack(this.currency.getItem(), this.cost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class EnchantedItemForEmeralds implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EnchantedItemForEmeralds(Item item, int cost, int maxUses, int xp) {
            this(item, cost, maxUses, xp, 0.05F);
        }

        public EnchantedItemForEmeralds(Item item, int cost, int maxUses, int xp, float priceMultiplier) {
            this.itemStack = new ItemStack(item);
            this.cost = cost;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity entity, RandomSource rand) {
            ItemStack itemstack = EnchantmentHelper.enchantItem(rand, new ItemStack(this.itemStack.getItem()), 30, false);
            ItemStack itemstack1 = new ItemStack(ModItems.BLUEBERRY_CAT.get(), 5);
            return new MerchantOffer(itemstack1, itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

}

