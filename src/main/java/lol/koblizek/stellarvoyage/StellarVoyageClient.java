package lol.koblizek.stellarvoyage;


import lol.koblizek.stellarvoyage.block.ModBlockEntities;
import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.block.entity.bloomery.BloomeryBlockRenderer;
import lol.koblizek.stellarvoyage.item.ItemCallbacks;
import lol.koblizek.stellarvoyage.item.ModItems;
import lol.koblizek.stellarvoyage.screen.ModScreenHandlers;
import lol.koblizek.stellarvoyage.screen.bloomery.BloomeryScreen;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Random;

public class StellarVoyageClient implements ClientModInitializer, References {
	@Override
	public void onInitializeClient() {
		ItemCallbacks.register();
		ClientLifecycleEvents.CLIENT_STARTED.register(this::onClientStarted);
		ClientPlayConnectionEvents.JOIN.register(this::onPlayReady);
		BlockEntityRendererFactories.register(ModBlockEntities.BLOOMERY, BloomeryBlockRenderer::new);
		HandledScreens.register(ModScreenHandlers.BLOOMERY_HANDLED_SCREEN_SCREEN_HANDLER_TYPE, BloomeryScreen::new);
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