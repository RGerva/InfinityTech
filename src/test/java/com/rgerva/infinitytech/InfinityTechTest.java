/**
 * Class: InfinityTechTest
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech;

import com.rgerva.infinitytech.cable.ModCableTests;
import com.rgerva.infinitytech.energy.ModEnergyTests;
import com.rgerva.infinitytech.fluid.ModFluidTests;
import com.rgerva.infinitytech.integrity.FileIntegrity;
import net.jqwik.api.Label;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.rgerva.infinitytech.util.ModUtilsTests.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Label("Unit Tests")
public class InfinityTechTest {

    FileIntegrity integrity = new FileIntegrity();
    ModEnergyTests energyTests = new ModEnergyTests();
    ModFluidTests fluidTests = new ModFluidTests();
    ModCableTests cableTests = new ModCableTests();

    @Label("File Integrity")
    @DisplayName("File Integrity")
    @Test
    @Order(1)
    void FileIntegrity() {
        log.start("Starting File Integrity Tests...");
        List<Integer> list = integrity.testTexture();
        Assertions.assertEquals(list.get(0), list.get(1), "Textures Fail");
        log.done("File Integrity Test Done!");
    }

    @Label("Fluid Units")
    @DisplayName("Fluid Units")
    @Test
    @Order(2)
    void FluidUnit() {
        log.start("Starting Fluid Unit Tests...");
        fluidTests.FluidUnit();
        log.done("Fluid Unit Done!");
    }

    @Label("Energy Units")
    @DisplayName("Energy Units")
    @Test
    @Order(3)
    void EnergyUnit() {
        log.start("Starting Energy Unit Tests...");
        energyTests.EnergyUnit();
        log.done("Energy Unit Done!");
    }

    @Label("Cable Enum")
    @DisplayName("Cable Enum")
    @Test
    @Order(4)
    void CableEnum(){
        log.start("Starting Cable Enum Tests...");
        cableTests.testGetMaxTransferForEachStatus();
        cableTests.cableEnergyCapacities();
        cableTests.testOnlyItemCapacities();
        cableTests.testOnlyFluidCapacities();
        cableTests.testToStringOverride();
        cableTests.testActiveCapacitiesToString();
        log.done("Cable Enum Tests Done!");
    }
}
