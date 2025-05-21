/**
 * Class: ModUtilsTests
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util;

import org.junit.jupiter.api.Assertions;

public class ModUtilsTests {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void assertEquals(String expected, String actual) {
        System.out.println(ANSI_RED + "Expected : " + expected + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Actual   : " + actual + ANSI_RESET);
        Assertions.assertEquals(expected, actual);
    }

    public static void assertEquals(int expected, int actual) {
        System.out.println(ANSI_RED + "Expected : " + String.valueOf(expected) + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Actual   : " + String.valueOf(actual) + ANSI_RESET);
        Assertions.assertEquals(expected, actual);
    }
}
