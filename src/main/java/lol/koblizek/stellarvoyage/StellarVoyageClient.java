package lol.koblizek.stellarvoyage;


import lol.koblizek.stellarvoyage.block.ModBlockEntities;
import lol.koblizek.stellarvoyage.block.entity.bloomery.BloomeryBlockRenderer;
import lol.koblizek.stellarvoyage.item.ItemCallbacks;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Random;

public class StellarVoyageClient implements ClientModInitializer, References {
	@Override
	public void onInitializeClient() {
		ItemCallbacks.register();
		ClientLifecycleEvents.CLIENT_STARTED.register(this::onClientStarted);
		ClientPlayConnectionEvents.JOIN.register(this::onPlayReady);
		BlockEntityRendererFactories.register(ModBlockEntities.BLOOMERY, BloomeryBlockRenderer::new);
	}

	public void onClientStarted(MinecraftClient client) {
		if (new Random().nextBoolean()) {
			MinecraftClient.getInstance().getWindow().setTitle("Tisice lidi sukaji denne v teto hre...");
		}
	}

	public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
		client.player.sendMessage(Text.empty().append(Text.literal("K").formatted(Formatting.DARK_PURPLE, Formatting.OBFUSCATED))
				.append(Text.literal("Welcome long lost adventurer, the world welcomes you").formatted(Formatting.GOLD))
				.append(Text.literal("K").formatted(Formatting.OBFUSCATED, Formatting.DARK_PURPLE)));
		client.player.sendMessage(Text.literal("Get started by obtaining flint!").formatted(Formatting.GREEN));
	}
}