package lol.koblizek.stellarvoyage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface References {
    Logger LOGGER = LoggerFactory.getLogger("StellarVoyage");
    String MOD_ID = "stellarvoyage";

    default Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }
}
