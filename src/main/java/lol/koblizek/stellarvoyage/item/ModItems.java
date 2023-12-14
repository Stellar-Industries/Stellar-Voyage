package lol.koblizek.stellarvoyage.item;

import lol.koblizek.stellarvoyage.StellarVoyage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ALUMINIUM_PLATE = registerItem("aluminium_plate", new Item(new FabricItemSettings()));
    public static final Item ALUMINIUM_INGOT = registerItem("aluminium_ingot", new Item(new FabricItemSettings()));
    public static final Item PEBBLE_AXE = registerItem("pebble_axe", new AxeItem(ToolMaterials.WOOD, 6.0F, -3.2F, new Item.Settings()));
    public static final Item PEBBLE_PICKAXE = registerItem("pebble_pickaxe", new PickaxeItem(ToolMaterials.WOOD, 1, -2.8F, new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(StellarVoyage.MOD_ID, name), item);
    }

    public static void registerModItems() {
    }
}
