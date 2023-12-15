package lol.koblizek.stellarvoyage.block.tileentity.bloomery;

import software.bernie.geckolib.renderer.GeoBlockRenderer;


public class BloomeryTileEntityRenderer extends GeoBlockRenderer<BloomeryTileEntityGeoBlock> {
    public BloomeryTileEntityRenderer() {
        super(new BloomeryTileEntity());
    }
}
