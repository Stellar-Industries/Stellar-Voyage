package lol.koblizek.stellarvoyage.item;

import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups implements References {
    public static ItemGroup STELLAR_VOYAGE;
    public static void registerItemGroups() {
        STELLAR_VOYAGE = Registry.register(Registries.ITEM_GROUP,
                new Identifier(MOD_ID, "stellarvoyage"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.stellarvoyage"))
                        .icon(() -> new ItemStack(ModItems.ALUMINIUM_INGOT)).entries((displayContext, entries) -> {
                            for (Item item : ModItems.ITEMS) {
                                entries.add(new ItemStack(item));
                            }
                        }).build());
    }
}
