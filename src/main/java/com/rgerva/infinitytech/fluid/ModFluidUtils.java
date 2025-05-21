/**
 * Class: ModFluidUtils
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.fluid;

import java.util.Locale;

public class ModFluidUtils {
    private static final String[] FLUID_PREFIXES = new String[]{
            "m", "", "L",
    };

    public static String getFluidWithPrefix(int fluid){
        double fluidWithPrefix = fluid;
        int prefixIndex = 0;

        while (fluidWithPrefix >= 1000 && prefixIndex + 1 < FLUID_PREFIXES.length) {
            fluidWithPrefix /= 1000;
            prefixIndex++;
        }

        return String.format(Locale.ENGLISH, "%.2f%s Buckets", fluidWithPrefix, FLUID_PREFIXES[prefixIndex]);
    }
}
