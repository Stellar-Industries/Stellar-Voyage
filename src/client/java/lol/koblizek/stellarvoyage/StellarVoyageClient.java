package lol.koblizek.stellarvoyage;

import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class StellarVoyageClient implements ClientModInitializer, References {
	@Override
	public void onInitializeClient() {
		ClientPlayConnectionEvents.JOIN.register(this::onPlayReady);
	}

	public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
		client.player.sendMessage(Text.literal("Loaded world with Stellar Voyage!").formatted(Formatting.YELLOW));
	}
}