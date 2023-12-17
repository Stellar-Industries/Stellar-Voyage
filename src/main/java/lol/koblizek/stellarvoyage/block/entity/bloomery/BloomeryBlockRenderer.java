package lol.koblizek.stellarvoyage.block.entity.bloomery;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.AxisTransformation;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Axis;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class BloomeryBlockRenderer extends GeoBlockRenderer<BloomeryBlockEntity> {
    public BloomeryBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new BloomeryBlockModel());
        addRenderLayer(new BlockAndItemGeoLayer<>(this) {
            @Override
            protected ItemStack getStackForBone(GeoBone bone, BloomeryBlockEntity animatable) {
                return switch (bone.getName()) {
                    case "ItemHolder" -> animatable.getStack(BloomeryBlockEntity.FUEL_SLOT);
                    default -> ItemStack.EMPTY;
                };
            }
            @Override
            protected ModelTransformationMode getTransformTypeForStack(GeoBone bone, ItemStack stack, BloomeryBlockEntity animatable) {
                return switch (bone.getName()) {
                    default -> ModelTransformationMode.THIRD_PERSON_RIGHT_HAND;
                };
            }

            @Override
            protected void renderStackForBone(MatrixStack poseStack, GeoBone bone, ItemStack stack, BloomeryBlockEntity animatable, VertexConsumerProvider bufferSource, float partialTick, int packedLight, int packedOverlay) {
                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }

}
