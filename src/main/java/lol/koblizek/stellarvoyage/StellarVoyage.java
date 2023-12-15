package lol.koblizek.stellarvoyage;

import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.item.ModItemGroups;
import lol.koblizek.stellarvoyage.item.ModItems;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;

public class StellarVoyage implements ModInitializer, References {
	@Override
	public void onInitialize() {
		BiomeModifications.create(new Identifier(MC_ID, "ore_iron"))
				.add(ModificationPhase.REMOVALS, ctx -> true, ctx -> {
					
				});
		ModBlocks.registerModBlock();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
	}
}