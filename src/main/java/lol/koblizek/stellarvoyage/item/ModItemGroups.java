package lol.koblizek.stellarvoyage.item;

import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups implements References {
    public static final ItemGroup STELLAR_VOYAGE = Registry.register(Registries.ITEM_GROUP,
            new Identifier(MOD_ID, "stellarvoyage"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.stellarvoyage"))
                    .icon(() -> new ItemStack(ModItems.ALUMINIUM_INGOT)).entries((displayContext, entries) -> {
                        entries.add(ModItems.ALUMINIUM_INGOT);
                        entries.add(ModItems.ALUMINIUM_PLATE);
                        entries.add(ModBlocks.BAUXITE_ORE);
                        entries.add(ModBlocks.BLOOMERY_FIRED);
                        entries.add(ModBlocks.BLOOMERY_UNFIRED);
                        entries.add(ModItems.PEBBLE_AXE);
                        entries.add(ModItems.PEBBLE_PICKAXE);
                        entries.add(ModItems.WOODEN_BAT);
                        entries.add(ModItems.FLINT_KNIFE);
                        entries.add(ModItems.GRASS_FIBER);
                    }).build());
    public static void registerItemGroups() {

    }
}
