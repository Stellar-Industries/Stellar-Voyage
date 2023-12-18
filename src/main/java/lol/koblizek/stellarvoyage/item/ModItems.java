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
    public static final Item PEBBLE_SHOVEL = registerItem("pebble_shovel", new ShovelItem(ToolMaterials.WOOD, 1, -2F, new Item.Settings()));
    public static final Item PEBBLE_HOE = registerItem("pebble_hoe", new HoeItem(ToolMaterials.WOOD, 1, -2F, new Item.Settings()));
    public static final Item FLINT_KNIFE = registerItem("flint_knife", new SwordItem(ToolMaterials.WOOD, 4, -2F, new Item.Settings()));
    public static final Item GRASS_FIBER = registerItem("grass_fiber", new Item(new FabricItemSettings()));
    public static final Item BRONZE_GEAR = registerItem("bronze_gear", new Item(new FabricItemSettings()));
    public static final Item BRONZE_SWORD = registerItem("bronze_sword", new Item(new FabricItemSettings()));
    public static final Item COPPER_COIL = registerItem("copper_coil", new Item(new FabricItemSettings()));
    public static final Item COPPER_GEAR = registerItem("copper_gear", new Item(new FabricItemSettings()));
    public static final Item DIAMOND_GEAR = registerItem("diamond_gear", new Item(new FabricItemSettings()));
    public static final Item DRILL = registerItem("drill", new Item(new FabricItemSettings()));
    public static final Item DOUGH = registerItem("dough", new Item(new FabricItemSettings()));
    public static final Item GLASS_OF_BEER = registerItem("glass_of_beer", new Item(new FabricItemSettings()));
    public static final Item GOLD_SCYTHE = registerItem("gold_scythe", new Item(new FabricItemSettings()));
    public static final Item IRON_GEAR = registerItem("iron_gear", new Item(new FabricItemSettings()));
    public static final Item IRON_SCYTHE = registerItem("iron_scythe", new Item(new FabricItemSettings()));
    public static final Item SCIMITAR = registerItem("scimitar", new Item(new FabricItemSettings()));
    public static final Item WEIRD_DOUGH = registerItem("weird_dough", new Item(new FabricItemSettings()));



    private static Item registerItem(String name, Item item) {
        ITEMS.add(item);
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }

    public static void registerModItems() {
    }
}
