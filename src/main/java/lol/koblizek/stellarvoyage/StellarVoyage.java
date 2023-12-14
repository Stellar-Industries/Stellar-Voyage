package lol.koblizek.stellarvoyage;

import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.item.ModItemGroups;
import lol.koblizek.stellarvoyage.item.ModItems;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.api.ModInitializer;

public class StellarVoyage implements ModInitializer, References {
	@Override
	public void onInitialize() {
		ModBlocks.registerModBlock();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
	}
}