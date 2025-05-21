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

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static class log {

        public static void done(String message) {
            System.out.println(ANSI_GREEN + "[DONE] " + message + ANSI_RESET);
        }

        public static void done(String format, Object... args) {
            String formattedMessage = String.format(format, args);
            done(formattedMessage);
        }

        public static void info(String message) {
            System.out.println(ANSI_PURPLE + "[INFO] " + message + ANSI_RESET);
        }

        public static void info(String format, Object... args) {
            String formattedMessage = String.format(format, args);
            info(formattedMessage);
        }

        public static void start(String message) {
            System.out.println(ANSI_YELLOW + "[PROG]" + message + ANSI_RESET);
        }

        public static void start(String format, Object... args) {
            String formattedMessage = String.format(format, args);
            start(formattedMessage);
        }
    }

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

    public static void assertTrue(boolean condition, String message) {
        System.out.println(ANSI_YELLOW + "[assertTrue]" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Condition: " + condition + ANSI_RESET);
        Assertions.assertTrue(condition, message);
    }

    public static void assertTrue(boolean condition) {
        System.out.println(ANSI_YELLOW + "[assertTrue]" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Condition: " + condition + ANSI_RESET);
        Assertions.assertTrue(condition);
    }

    public static void assertFalse(boolean condition, String message) {
        System.out.println(ANSI_YELLOW + "[assertFalse]" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Condition: " + condition + ANSI_RESET);
        Assertions.assertFalse(condition, message);
    }

    public static void assertFalse(boolean condition) {
        System.out.println(ANSI_YELLOW + "[assertFalse]" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Condition: " + condition + ANSI_RESET);
        Assertions.assertFalse(condition);
    }

    public static void assertThrows(Class<? extends Throwable> expectedType, Runnable executable, String message) {
        System.out.println(ANSI_YELLOW + "[assertThrows]" + ANSI_RESET);
        try {
            executable.run();
            System.out.println(ANSI_RED + "Expected exception: " + expectedType.getSimpleName() + " but none thrown" + ANSI_RESET);
            Assertions.fail(message);
        } catch (Throwable thrown) {
            if (!expectedType.isInstance(thrown)) {
                System.out.println(ANSI_RED + "Wrong exception thrown: " + thrown + ANSI_RESET);
                Assertions.fail(message);
            } else {
                System.out.println(ANSI_GREEN + "Correctly threw: " + thrown.getClass().getSimpleName() + ANSI_RESET);
            }
        }
    }

    public static void assertThrows(Class<? extends Throwable> expectedType, Runnable executable) {
        System.out.println(ANSI_YELLOW + "[assertThrows]" + ANSI_RESET);
        try {
            executable.run();
            System.out.println(ANSI_RED + "Expected exception: " + expectedType.getSimpleName() + " but none thrown" + ANSI_RESET);
        } catch (Throwable thrown) {
            if (!expectedType.isInstance(thrown)) {
                System.out.println(ANSI_RED + "Wrong exception thrown: " + thrown + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + "Correctly threw: " + thrown.getClass().getSimpleName() + ANSI_RESET);
            }
        }
    }
}
