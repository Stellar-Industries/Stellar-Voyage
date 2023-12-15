package lol.koblizek.stellarvoyage.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeatures;

public final class BiomeModifiers {
    public static void register(BiomeModificationContext ctx) {
        String[] stuff = {
                "ore_iron_middle",
                "ore_iron_upper",
                "ore_iron_small"
        };
        for (String s : stuff) {
            ctx.getGenerationSettings()
                    .removeFeature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatures.of("minecraft:" + s));
        }
    }
}
