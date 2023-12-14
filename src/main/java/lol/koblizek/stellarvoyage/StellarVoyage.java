package lol.koblizek.stellarvoyage;

import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.item.ModItemGroups;
import lol.koblizek.stellarvoyage.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StellarVoyage implements ModInitializer {
	public static final String MOD_ID = "stellarvoyage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlock();
		ModItems.registerModItems();
	}
}