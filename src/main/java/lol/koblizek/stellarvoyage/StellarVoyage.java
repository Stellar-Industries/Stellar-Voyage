package lol.koblizek.stellarvoyage;

import lol.koblizek.stellarvoyage.block.ModBlocks;
import lol.koblizek.stellarvoyage.item.ModItemGroups;
import lol.koblizek.stellarvoyage.item.ModItems;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class StellarVoyage implements ModInitializer, References {
	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlock();
		ModItems.registerModItems();
	}
}