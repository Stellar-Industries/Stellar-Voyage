package lol.koblizek.stellarvoyage.mixin;

import lol.koblizek.stellarvoyage.util.Randomizer;
import lol.koblizek.stellarvoyage.util.Utils;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.apache.commons.lang3.RandomUtils;
import org.joml.Vector3f;
import org.lwjgl.system.MathUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.Arrays;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

	@Unique
	private static final Block[] BURNING_MATERIALS = {
			Blocks.COAL_ORE
	};

	@Unique
	private static final String[] COMMENTS = {
			"comment.stellarvoyage.dying",
			"comment.stellarvoyage.dying2",
			"comment.stellarvoyage.dying3",
			"comment.stellarvoyage.dying4"
	};

	@Unique
	private int timer = 0;
	@Unique
	private int diseaseTimer = 0;
	@Unique
	private Randomizer rand;

	/**
	 * Hurts the player simulating coal dust disease
	 * @param player The player to hurt
	 */
	@Unique
	private void dustHurtPlayer(PlayerEntity player) {
		if (rand == null)
			rand = new Randomizer(COMMENTS);
		var text = Text.translatable(rand.get()).formatted(Formatting.RED);
		player.getWorld().addParticle(new DustParticleEffect(Utils.colorToVec(Color.gray), 2f), player.getX(), player.getY() + 1.6, player.getZ(), 0, 0.0D, 0.0D);
		player.sendMessage(text, true);
		player.damage(player.getWorld().getDamageSources().generic(), 2);
	}

	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if (!player.isCreative()) {
			var box = player.getWorld().getStatesInBox(new Box(player.getBlockPos().add(-2, -2, -2).toCenterPos(), player.getBlockPos().add(2, 2, 2).toCenterPos())).toList();

			if (!box.isEmpty() || player.getInventory().contains(Items.COAL.getDefaultStack())) {
				if (diseaseTimer != 400) {
					diseaseTimer++;
				} else {
					timer++;
				}
			}

			if (diseaseTimer == 400 && timer == 20) {
				timer = 0;
				if (player.getInventory().contains(Items.COAL.getDefaultStack())) {
					dustHurtPlayer(player);
				}
				box.forEach((blockState) -> {
					Block block = blockState.getBlock();
					for (Block material : BURNING_MATERIALS) {
						if (material.equals(block)) {
							dustHurtPlayer(player);
						}
					}
				});
			}
		}
	}
}