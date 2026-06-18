package com.cells.integration.projectex;

import net.minecraftforge.fml.common.Loader;

import com.cells.Cells;


/**
 * Optional ProjectEX integration gate.
 */
public final class ProjectEXIntegration {

    private static final String MOD_ID = "projectex";
    private static Boolean modLoaded;

    private ProjectEXIntegration() {}

    public static boolean isModLoaded() {
        if (modLoaded == null) {
            modLoaded = Loader.isModLoaded(MOD_ID);

            if (modLoaded) Cells.LOGGER.info("ProjectEX detected, enabling EMC cell support");
        }

        return modLoaded;
    }
}