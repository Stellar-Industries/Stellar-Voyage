package lol.koblizek.stellarvoyage.blocks;

import lol.koblizek.stellarvoyage.util.IDataLookupRegistration;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks extends IDataLookupRegistration<Block> {
    @Override
    public Registry<Block> getRegistry() {
        return Registries.BLOCK;
    }

    @Override
    public String getPackage() {
        return "lol.koblizek.stellarvoyage.blocks";
    }
}
