package lol.koblizek.stellarvoyage;

import lol.koblizek.stellarvoyage.blocks.ModBlocks;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StellarVoyageMod implements ModInitializer {

	@Override
	public void onInitialize() {
		new ModBlocks().registerAll();
	}
}