package lol.koblizek.stellarvoyage.item;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ItemCallbacks {
    private static final Item[] DUST_PRODUCING_ITEMS = new Item[] {
            Items.COAL,
            Items.CHARCOAL,
            Items.COAL_BLOCK,
    };

    public static void register() {
        ItemTooltipCallback.EVENT.register(ItemCallbacks::getTooltip);
    }

    public static void getTooltip(ItemStack stack, TooltipContext context, List<Text> lines) {
        Item item = stack.getItem();
        for (Item items : DUST_PRODUCING_ITEMS) {
            if (item == items) {
                lines.add(Text.literal("[Coal Dust]").formatted(Formatting.GRAY));
                return;
            }
        }
    }
}
