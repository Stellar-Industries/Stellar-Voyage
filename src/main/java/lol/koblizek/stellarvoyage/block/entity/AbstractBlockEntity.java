package lol.koblizek.stellarvoyage.block.entity;

import lol.koblizek.stellarvoyage.block.entity.bloomery.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public abstract class AbstractBlockEntity extends BlockEntity
    implements ExtendedScreenHandlerFactory, GeoBlockEntity, ImplementedInventory {

    private int fuelProgress;
    private int maxFuelProgress;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected final PropertyDelegate propertyDelegate;

    public AbstractBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AbstractBlockEntity.this.fuelProgress;
                    case 1 -> AbstractBlockEntity.this.maxFuelProgress;
                    default -> AbstractBlockEntity.this.maxFuelProgress;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AbstractBlockEntity.this.fuelProgress = value;
                    case 1 -> AbstractBlockEntity.this.maxFuelProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    public abstract int[] getFuelSlots();
    public abstract int[] getOreSlots();
    public abstract List<Recipe> getRecipes();

    public record Recipe(ItemStack out, ItemStack ore, ItemStack fuel /* AIR for any */) {

    }
}
