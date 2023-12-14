package lol.koblizek.stellarvoyage.item;

import lol.koblizek.stellarvoyage.StellarVoyage;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModItems implements References {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final Item ALUMINIUM_PLATE = registerItem("aluminium_plate", new Item(new FabricItemSettings()));
    public static final Item ALUMINIUM_INGOT = registerItem("aluminium_ingot", new Item(new FabricItemSettings()));
    public static final Item PEBBLE_AXE = registerItem("pebble_axe", new AxeItem(ToolMaterials.WOOD, 6.0F, -3.2F, new Item.Settings()));
    public static final Item PEBBLE_PICKAXE = registerItem("pebble_pickaxe", new PickaxeItem(ToolMaterials.WOOD, 1, -2.8F, new Item.Settings()));
    public static final Item WOODEN_BAT = registerItem("wooden_bat", new SwordItem(ToolMaterials.WOOD, 3, -2.4F, new Item.Settings()));
    public static final Item FLINT_KNIFE = registerItem("flint_knife", new SwordItem(ToolMaterials.STONE, 4, -2F, new Item.Settings()));
    public static final Item GRASS_FIBER = registerItem("grass_fiber", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        ITEMS.add(item);
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }

    public static void registerModItems() {
    }
}
