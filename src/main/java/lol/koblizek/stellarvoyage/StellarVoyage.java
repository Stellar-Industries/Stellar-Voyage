package lol.koblizek.stellarvoyage;

import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.item.ModItemGroups;
import lol.koblizek.stellarvoyage.item.ModItems;
import lol.koblizek.stellarvoyage.util.References;
import lol.koblizek.stellarvoyage.worldgen.BiomeModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.PlacedFeatures;

import java.util.function.BiPredicate;

public class StellarVoyage implements ModInitializer, References {
	@Override
	public void onInitialize() {
		BiomeModifications.create(new Identifier(MOD_ID, "biome_mods")).add(ModificationPhase.REMOVALS, ctx -> true, BiomeModifiers::register);
		ModBlocks.registerModBlock();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
	}
}