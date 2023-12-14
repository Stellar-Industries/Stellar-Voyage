package lol.koblizek.item;

import lol.koblizek.ExampleMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ALUMINIUM_PLATE = registerItem("aluminium_plate", new Item(new FabricItemSettings()));


    public static final Item ALUMINIUM_INGOT = registerItem("aluminium_ingot", new Item(new FabricItemSettings()));
    private static void addToItemGroup(FabricItemGroupEntries entries) {
        entries.add(ALUMINIUM_INGOT);
        entries.add(ALUMINIUM_PLATE);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ExampleMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
    }
}
