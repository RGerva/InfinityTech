package com.rgerva.infinitytech;

import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Unit Tests")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ModTest {

    public static final String MOD_ID = "mod_test";

    @Nested
    @Label("File Integrity")
    @Order(0)
    public class FileIntegrity {

        @Test
        @Label("Texture Verify")
        public void testTexture() {
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
                            System.out.printf("OK: %s\n", resourcePath);
                            textureCount++;
                        } catch (IOException e) {
                            throw new RuntimeException(e.getMessage() + ": " + textureFile.getName(), e);
                        }
                    }
                }
                textureTotal += textureCount;
                System.out.printf("Find in %s: %d\n", stringList, textureCount);
            }
            System.out.printf("Total of Textures: %d\n", textureTotal);
        }

    }


}
