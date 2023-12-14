package lol.koblizek.stellarvoyage.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if (!player.isCreative()) {
			player.getWorld().getStatesInBox(new Box(player.getBlockPos().add(-2, -2, -2).toCenterPos(), player.getBlockPos().add(2, 2, 2).toCenterPos())).forEach((blockState) -> {
				Block block = blockState.getBlock();
				if (block == Blocks.COAL_ORE) {
					player.sendMessage(Text.literal(Formatting.RED + "My lungs are burning!"), true);
					player.damage(player.getWorld().getDamageSources().generic(), 1);
				}
			});
		}
	}
}