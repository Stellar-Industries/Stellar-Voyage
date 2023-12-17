package lol.koblizek.stellarvoyage.block.entity.bloomery;

import lol.koblizek.stellarvoyage.util.References;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BloomeryBlockModel extends GeoModel<BloomeryBlockEntity> implements References {
    @Override
    public Identifier getModelResource(BloomeryBlockEntity animatable) {
        return new Identifier(MOD_ID, "geo/bloomery.geo.json");
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
