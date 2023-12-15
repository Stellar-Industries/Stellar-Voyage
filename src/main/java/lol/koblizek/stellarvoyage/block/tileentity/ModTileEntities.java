package lol.koblizek.stellarvoyage.block.tileentity;

import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.block.tileentity.bloomery.BloomeryTileEntityGeoBlock;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModTileEntities implements References {
    public static final BlockEntityType<BloomeryTileEntityGeoBlock> BLOOMERY_ENTITY_BLOCK = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            MOD_ID + ":bloomery",
            FabricBlockEntityTypeBuilder.create(BloomeryTileEntityGeoBlock::new, ModBlocks.BLOOMERY).build(null));
}
