/**
 * Class: FileIntegrity
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.integrity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileIntegrity {
    public List<Integer> testTexture() {
        List<String> listPath = List.of("/assets/infinity_tech/textures/block/",
                "/assets/infinity_tech/textures/gui/container/",
                "/assets/infinity_tech/textures/item/",
                "/assets/infinity_tech/textures/particle/");

        int textureCount = 0;
        int textureTotal = 0;
        for (String stringList : listPath) {
            File textureDirectory = new File(getClass().getResource(stringList).getFile());
            assertTrue(textureDirectory.exists(), "Not Find Directory: " + stringList);
            File[] textureFiles = textureDirectory.listFiles();
            assert textureFiles != null;

            for (File textureFile : textureFiles) {
                String fileName = textureFile.getName().toLowerCase();
                if (fileName.endsWith(".png")) {
                    String resourcePath = stringList + textureFile.getName();
                    try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
                        assertNotNull(is, "Not possible to load: " + resourcePath);
                        textureCount++;
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage() + ": " + textureFile.getName(), e);
                    }
                }
            }
            textureTotal = textureCount;
        }
        System.out.printf("Total of Textures: %s\n", textureTotal);
        return List.of(textureTotal, textureCount);
    }
}
