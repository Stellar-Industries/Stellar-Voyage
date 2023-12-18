package lol.koblizek.stellarvoyage.block.entity.bloomery;

import lol.koblizek.stellarvoyage.block.entity.Multiblock;
import lol.koblizek.stellarvoyage.util.References;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockTypes;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import software.bernie.geckolib.model.GeoModel;

import java.util.Objects;

public class BloomeryBlockModel extends GeoModel<BloomeryBlockEntity> implements References {
    @Override
    public Identifier getModelResource(BloomeryBlockEntity animatable) {
        BlockState state = Objects.requireNonNull(animatable.getWorld()).getBlockState(animatable.getPos());
        if(animatable.getWorld().getBlockState(animatable.getPos()).getBlock() != Blocks.AIR) {
            if(state.get(Multiblock.ISMASTER)) {
                return new Identifier(MOD_ID, "geo/bloomery.geo.json");
            }
        }

        return new Identifier(MOD_ID, "geo/blank.geo.json");


    }

    @Override
    public Identifier getTextureResource(BloomeryBlockEntity animatable) {

        return new Identifier(MOD_ID, "textures/block/bloomery_fired.png");
    }

    @Override
    public Identifier getAnimationResource(BloomeryBlockEntity animatable) {
        return new Identifier(MOD_ID, "animations/bloomery.animation.json");
    }
}
