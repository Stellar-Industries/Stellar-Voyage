package lol.koblizek.stellarvoyage.block;

import lol.koblizek.stellarvoyage.block.entity.bloomery.BloomeryBlock;
import lol.koblizek.stellarvoyage.item.ModItems;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks implements References {

    public static final Block BAUXITE_ORE = registerBlock("bauxite",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));
    public static final Block CASSITERITE_ORE = registerBlock("cassiterite",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));
    public static final Block CHALCOPYRITE_ORE = registerBlock("chalcopyrite",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));

    public static final Block SPHALERITE_ORE = registerBlock("sphalerite",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));
    public static final Block LIMESTONE = registerBlock("limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));
    public static final Block BLOOMERY = registerBlock("bloomery_fired",
        new BloomeryBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block BLOOMERY_UNFIRED = registerBlock("bloomery_unfired",
            new BloomeryBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block MUD_MIXTURE = registerBlock("mud_mixture",
            new MudMixtureBlock());

    public static <B extends Block> B registerBlock(String name, B block) {
        ModItems.ITEMS.add(registerBlockItem(name, block));
        return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
    }


    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name),
            new BlockItem(block,new FabricItemSettings()));
    }
    public static void registerModBlock() {

    }
}
