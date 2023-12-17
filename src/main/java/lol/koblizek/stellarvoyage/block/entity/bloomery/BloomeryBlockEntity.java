package lol.koblizek.stellarvoyage.block.entity.bloomery;

import lol.koblizek.stellarvoyage.block.ModBlockEntities;
import lol.koblizek.stellarvoyage.screen.bloomery.BloomeryHandledScreen;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.tag.ItemTags;
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
import software.bernie.geckolib.util.RenderUtils;

import java.lang.reflect.Field;

public class BloomeryBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    public static final int FUEL_SLOT = 0; // Fuel 1
    public static final int FUEL_SLOT_2 = 1;
    public static final int[] ORE_SLOT = new int[] {2, 3};
    public static final int FLUX_SLOT = 4;
    public static final int SLAG_SLOT = 5; // Byproduct
    private static final int OUTPUT_SLOT = 6;

    protected final PropertyDelegate propertyDelegate;
    private int progress;
    private int maxProgress = 72;


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public BloomeryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOOMERY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BloomeryBlockEntity.this.progress;
                    case 1 -> BloomeryBlockEntity.this.maxProgress;
                    default -> BloomeryBlockEntity.this.maxProgress;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BloomeryBlockEntity.this.progress = value;
                    case 1 -> BloomeryBlockEntity.this.maxProgress = value;
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
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
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
        nbt.putInt("bloomery.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("bloomery.proggress");
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

    public void tick(World world1, BlockPos pos, BlockState state1) {
        if (world1.isClient) {
            return;
        }
        if (isOutPotSlotEmptyOrReceivable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world1, pos, state1);

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
        this.removeStack(FUEL_SLOT,1);
        ItemStack itemStack = BloomeryRecipe.getOutputIfCan(getOres(), getStack(FLUX_SLOT));

        this.setStack(OUTPUT_SLOT, new ItemStack(itemStack.getItem(), getStack(OUTPUT_SLOT).getCount() + itemStack.getCount()));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public ItemStack getFuelStack() {
        if (!getStack(FUEL_SLOT).isEmpty()) return getStack(FUEL_SLOT);
        else if (!getStack(FUEL_SLOT_2).isEmpty()) return getStack(FUEL_SLOT_2);
        else return ItemStack.EMPTY;
    }

    public ItemStack getOres() {
        if (!getStack(ORE_SLOT[0]).isEmpty()) return getStack(ORE_SLOT[0]);
        else if (!getStack(ORE_SLOT[1]).isEmpty()) return getStack(ORE_SLOT[1]);
        else return ItemStack.EMPTY;
    }

    private boolean hasRecipe() {
        ItemStack result = BloomeryRecipe.getOutputIfCan(getOres(), getStack(FLUX_SLOT));
        boolean hasFuel = getFuelStack().getItem() == Items.COAL;

        return hasFuel && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack itemStack) {
        return getStack(OUTPUT_SLOT).getCount() + itemStack.getCount() < getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean isOutPotSlotEmptyOrReceivable() {
        return true;
    }

    public record BloomeryRecipe(ItemStack out, Item ore, Item flux) {
        public static final BloomeryRecipe IRON = new BloomeryRecipe(new ItemStack(Items.COPPER_INGOT), Items.COAL, null);

        public boolean matchesRecipe(Item ore, Item flux) {
            return ore.equals(this.ore) && (this.flux == null || this.flux.equals(flux));
        }

        public static ItemStack getOutputIfCan(ItemStack ore, ItemStack flux) {
            try {
                if (IRON.matchesRecipe(ore.getItem(), flux.getItem())) return IRON.out;
            } catch (Exception e) {
                return ItemStack.EMPTY;
            }
            return ItemStack.EMPTY;
        }
    }
}
