package lol.koblizek.stellarvoyage.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Unique
    private static final Item[] DUST_PRODUCING_ITEMS = new Item[] {
            Items.COAL,
            Items.CHARCOAL,
            Items.COAL_BLOCK,
    };

    /**
     * @author KoblizekXD
     * @reason Add necessary tooltips for coal burning items etc.
     */
    @Overwrite
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Item item = stack.getItem();
        for (Item items : DUST_PRODUCING_ITEMS) {
            if (item == items) {
                tooltip.add(Text.literal("[Coal Dust]").formatted(Formatting.GRAY));
            }
        }
    }
}
