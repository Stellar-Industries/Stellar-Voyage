package lol.koblizek.stellarvoyage.block.tileentity;

import lol.koblizek.stellarvoyage.block.tileentity.bloomery.BloomeryTileEntityGeoBlock;
import lol.koblizek.stellarvoyage.util.References;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.RenderLayer;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class BloomeryTileEntity extends GeoModel<BloomeryTileEntityGeoBlock> implements References {
    private static final Identifier MODEL = new Identifier(MOD_ID, "geo/bloomery_fired.geo.json");
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/block/bloomery_fired.png");
    private static final Identifier ANIMATION = new Identifier(MOD_ID, "animations/bloomery.animation.json");
    @Override
    public Identifier getModelResource(BloomeryTileEntityGeoBlock animatable) {
        return MODEL;
    }

    @Override
    public Identifier getTextureResource(BloomeryTileEntityGeoBlock animatable) {
        return TEXTURE;
    }

    @Override
    public Identifier getAnimationResource(BloomeryTileEntityGeoBlock animatable) {
        return ANIMATION;
    }

    @Override
    public RenderLayer getRenderType(BloomeryTileEntityGeoBlock animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
