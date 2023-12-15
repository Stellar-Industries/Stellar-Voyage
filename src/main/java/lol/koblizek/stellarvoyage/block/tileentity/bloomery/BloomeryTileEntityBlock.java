package lol.koblizek.stellarvoyage.block.tileentity.bloomery;

import com.mojang.serialization.MapCodec;
import lol.koblizek.stellarvoyage.block.tileentity.ModTileEntities;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class BloomeryTileEntityBlock extends BlockWithEntity implements BlockEntityProvider {

    public BloomeryTileEntityBlock() {
        super(Settings.create().nonOpaque());
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return ModTileEntities.BLOOMERY_ENTITY_BLOCK.instantiate(blockPos, blockState);
    }
}
