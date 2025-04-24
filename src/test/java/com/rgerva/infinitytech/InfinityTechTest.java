/**
 * Class: InfinityTechTest
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech;

import com.rgerva.infinitytech.integrity.FileIntegrity;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Label("Unit Tests")
public class InfinityTechTest {

    // Teste usando o JQWik, que irá gerar propriedades aleatórias para validar.
    @Property
    public void testCommutativeProperty(@ForAll int a, @ForAll int b) {
        // Verifica se a + b é igual a b + a (Propriedade Comutativa da Soma)
        assertEquals(a + b, b + a, "A soma não é comutativa!");
    }

    // Teste simples utilizando JUnit para verificar a soma
    @Test
    public void simpleAdditionTest() {
        int result = 2 + 2;
        assertEquals(4, result, "A soma simples falhou!");
    }

    @Label("File Integrity")
    @Test
    public void FileIntegrity() {
        FileIntegrity integrity = new FileIntegrity();
        List<Integer> list = integrity.testTexture();
        assertEquals(list.get(0), list.get(1), "Textures Fail");
    }
}
