package lol.koblizek.stellarvoyage.blocks;

import lol.koblizek.stellarvoyage.util.Autoregister;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

@Autoregister("testblock")
public class TestBlock extends Block {
    public TestBlock() {
        super(FabricBlockSettings.copyOf(Blocks.TERRACOTTA));
    }
}
