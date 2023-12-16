package lol.koblizek.stellarvoyage.block.entity.bloomery;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BloomeryBlockRenderer extends GeoBlockRenderer<BloomeryBlockEntity> {
    public BloomeryBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new BloomeryBlockModel());
    }
}
