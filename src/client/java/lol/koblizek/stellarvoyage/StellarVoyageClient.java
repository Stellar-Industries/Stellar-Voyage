package lol.koblizek.stellarvoyage;

import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.Normalizer;

public class StellarVoyageClient implements ClientModInitializer, References {
	@Override
	public void onInitializeClient() {
		ClientPlayConnectionEvents.JOIN.register(this::onPlayReady);
	}

	public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
		client.player.sendMessage(Text.literal("K").formatted(Formatting.DARK_PURPLE, Formatting.OBFUSCATED)
				.append(Text.literal("Welcome long lost adventurer, the world welcomes you").formatted(Formatting.GOLD))
				.append(Text.literal("K").formatted(Formatting.OBFUSCATED, Formatting.DARK_PURPLE)));
	}
}