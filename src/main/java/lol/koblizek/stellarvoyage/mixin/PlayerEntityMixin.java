package lol.koblizek.stellarvoyage.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import org.joml.Vector3f;
import org.lwjgl.system.MathUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.Arrays;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

	private static final Block[] BURNING_MATERIALS = {
			Blocks.COAL_ORE
	};

	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if (!player.isCreative()) {
			player.getWorld().getStatesInBox(new Box(player.getBlockPos().add(-2, -2, -2).toCenterPos(), player.getBlockPos().add(2, 2, 2).toCenterPos())).forEach((blockState) -> {
				Block block = blockState.getBlock();
				for (Block material : BURNING_MATERIALS) {
					if (material.equals(block)) {
						player.getWorld().addParticle(new DustParticleEffect(new Vector3f(0f, 0f, 0f), 1.5f), player.getX(), player.getY() + 1.6, player.getZ(), 0.0D, 0.0D, 0.0D);
						player.sendMessage(Text.of(Formatting.RED + "My lungs are burning!"), true);
						player.damage(player.getWorld().getDamageSources().generic(), 1);
					}
				}
			});
		}
	}
}