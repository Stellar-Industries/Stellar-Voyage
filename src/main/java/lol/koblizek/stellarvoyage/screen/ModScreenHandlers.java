package lol.koblizek.stellarvoyage.screen;

import lol.koblizek.stellarvoyage.screen.bloomery.BloomeryHandledScreen;
import lol.koblizek.stellarvoyage.util.References;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import javax.xml.crypto.dsig.Reference;

public class ModScreenHandlers implements References {
    public static final ScreenHandlerType<BloomeryHandledScreen> BLOOMERY_HANDLED_SCREEN_SCREEN_HANDLER_TYPE =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(MOD_ID, "bloomery_gui"),
                    new ExtendedScreenHandlerType<>(BloomeryHandledScreen::new));

    public static void registerScreenHandlers() {
    }
}
