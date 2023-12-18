package lol.koblizek.stellarvoyage.block.entity.bloomery;

import lol.koblizek.stellarvoyage.block.ModBlockEntities;
import lol.koblizek.stellarvoyage.screen.bloomery.BloomeryHandledScreen;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class BloomeryBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    public static final int[] FUEL_SLOT = {0, 1}; // Fuel 1
    public static final int[] ORE_SLOT = new int[] {2, 3};
    public static final int FLUX_SLOT = 4;
    public static final int SLAG_SLOT = 5; // Byproduct
    private static final int OUTPUT_SLOT = 6;

    protected final PropertyDelegate propertyDelegate;
    private int fuelProgress;
    private int maxFuelProgress = 72;
    private int itemProgress; // TODO
    private int maxItemProgress = 72;


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public BloomeryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOOMERY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BloomeryBlockEntity.this.fuelProgress;
                    case 1 -> BloomeryBlockEntity.this.maxFuelProgress;
                    default -> BloomeryBlockEntity.this.maxFuelProgress;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BloomeryBlockEntity.this.fuelProgress = value;
                    case 1 -> BloomeryBlockEntity.this.maxFuelProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.stellarvoyage.bloomery.name");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("bloomery.progress", fuelProgress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        fuelProgress = nbt.getInt("bloomery.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BloomeryHandledScreen(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) {
            return;
        }
        if (isOutputAvailable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
        }
    }

    private void craftItem() {
        this.removeStack(getEatingFuel(),1);
        this.removeStack(getEatingOre(), 1);
        ItemStack itemStack = BloomeryRecipe.getOutputIfCan(getOres(), getStack(FLUX_SLOT), getFuelStack());

        this.setStack(OUTPUT_SLOT, new ItemStack(itemStack.getItem(), getStack(OUTPUT_SLOT).getCount() + itemStack.getCount()));
    }

    private boolean hasCraftingFinished() {
        return fuelProgress >= maxFuelProgress;
    }

    private void increaseCraftProgress() {
        fuelProgress++;
    }

    private void resetProgress() {
        this.fuelProgress = 0;
    }

    public ItemStack getFuelStack() {
        if (!getStack(FUEL_SLOT[0]).isEmpty()) return getStack(FUEL_SLOT[0]);
        else if (!getStack(FUEL_SLOT[1]).isEmpty()) return getStack(FUEL_SLOT[1]);
        else return ItemStack.EMPTY;
    }

    public int getEatingFuel() {
        if (!getStack(FUEL_SLOT[0]).isEmpty()) return FUEL_SLOT[0];
        else if (!getStack(FUEL_SLOT[1]).isEmpty()) return FUEL_SLOT[1];
        else return  0;
    }

    public ItemStack getOres() {
        if (!getStack(ORE_SLOT[0]).isEmpty()) return getStack(ORE_SLOT[0]);
        else if (!getStack(ORE_SLOT[1]).isEmpty()) return getStack(ORE_SLOT[1]);
        else return ItemStack.EMPTY;
    }

    public int getEatingOre() {
        if (!getStack(ORE_SLOT[0]).isEmpty()) return ORE_SLOT[0];
        else if (!getStack(ORE_SLOT[1]).isEmpty()) return ORE_SLOT[1];
        else return 0;
    }

    private boolean hasRecipe() {
        ItemStack result = BloomeryRecipe.getOutputIfCan(getOres(), getStack(FLUX_SLOT), getFuelStack());
        if (result == ItemStack.EMPTY) return false;
        boolean hasFuel = getFuelStack().getItem() == Items.COAL;

        return hasFuel && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack itemStack) {
        return getStack(OUTPUT_SLOT).getCount() + itemStack.getCount() < getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean isOutputAvailable() {
        return (this.getStack(OUTPUT_SLOT).isEmpty()
                || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount())
                && this.getStack(SLAG_SLOT).isEmpty() || this.getStack(SLAG_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    public record BloomeryRecipe(ItemStack out, ItemStack outSlag, Item ore, Item flux, Item specialFuel) {
        public boolean matchesRecipe(Item ore, Item flux, Item specialFuel) {
            return ore.equals(this.ore)
                    && (this.flux.equals(flux) || this.flux == null || this.flux == Items.AIR)
                    && (this.specialFuel.equals(specialFuel) || this.specialFuel == null || this.specialFuel == Items.AIR);
        }

        public static List<BloomeryRecipe> getRecipes() {
            return List.of(
                    new BloomeryRecipe(new ItemStack(Items.COPPER_INGOT), ItemStack.EMPTY, Items.COAL, Items.AIR, Items.AIR)
            );
        }

        public static ItemStack getOutputIfCan(ItemStack ore, ItemStack flux, ItemStack fuel) {
            try {
                for (BloomeryRecipe recipe : getRecipes()) {
                    if (recipe.matchesRecipe(ore.getItem(), flux.getItem(), fuel.getItem())) {
                        return recipe.out;
                    }
                }
                // if (IRON.matchesRecipe(ore.getItem(), flux.getItem(), fuel.getItem())) return IRON.out;
            } catch (Exception e) {
                return ItemStack.EMPTY;
            }
            return ItemStack.EMPTY;
        }
    }
}
