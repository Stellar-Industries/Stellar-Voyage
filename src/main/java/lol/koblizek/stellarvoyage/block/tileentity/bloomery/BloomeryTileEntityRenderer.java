package lol.koblizek.stellarvoyage.block.tileentity.bloomery;

import lol.koblizek.stellarvoyage.block.tileentity.BloomeryTileEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;


public class BloomeryTileEntityRenderer extends GeoBlockRenderer<BloomeryTileEntityGeoBlock> {
    public BloomeryTileEntityRenderer() {
        super(new BloomeryTileEntity());
    }
}
