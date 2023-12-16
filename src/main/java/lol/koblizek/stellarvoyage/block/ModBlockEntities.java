package lol.koblizek.stellarvoyage.block;

import lol.koblizek.stellarvoyage.block.entity.bloomery.BloomeryBlock;
import lol.koblizek.stellarvoyage.block.entity.bloomery.BloomeryBlockEntity;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import software.bernie.example.block.entity.GeckoHabitatBlockEntity;
import software.bernie.example.registry.BlockRegistry;
import software.bernie.geckolib.GeckoLib;

public class ModBlockEntities implements References {
    public static BlockEntityType<BloomeryBlockEntity> BLOOMERY;


    public static void register() {
        BLOOMERY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "bloomery"), FabricBlockEntityTypeBuilder.create(BloomeryBlockEntity::new, ModBlocks.BLOOMERY).build());
    }
}
