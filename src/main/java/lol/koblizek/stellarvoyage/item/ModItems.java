package lol.koblizek.stellarvoyage.item;

import lol.koblizek.stellarvoyage.StellarVoyage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ALUMINIUM_PLATE = registerItem("aluminium_plate", new Item(new FabricItemSettings()));
    public static final Item ALUMINIUM_INGOT = registerItem("aluminium_ingot", new Item(new FabricItemSettings()));
    public static final Item PEBBLE_AXE = registerItem("pebble_axe", new Item(new FabricItemSettings().maxDamage(3)));
    public static final Item PEBBLE_PICKAXE = registerItem("pebble_pickaxe", new Item(new FabricItemSettings().maxDamage(2)));


    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, new Identifier(StellarVoyage.MOD_ID, name), item);
    }
    public static void registerModItems() {
    }
}
