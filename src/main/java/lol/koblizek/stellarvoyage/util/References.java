package lol.koblizek.stellarvoyage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface References {
    Logger LOGGER = LoggerFactory.getLogger("StellarVoyage");
    String MOD_ID = "stellarvoyage";
    String MOD_VERSION = "1.0.0";
    String MC_ID = "minecraft";

    default Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }
}
