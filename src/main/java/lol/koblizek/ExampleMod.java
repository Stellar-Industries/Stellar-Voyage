package lol.koblizek;

import lol.koblizek.block.ModBlocks;
import lol.koblizek.item.ModItemGroups;
import lol.koblizek.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "stellarvoyage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlock();
		ModItems.registerModItems();
	}
}