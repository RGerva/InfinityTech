/**
 * Class: InfinityTechTest
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech;

import com.rgerva.infinitytech.energy.ModEnergyTests;
import com.rgerva.infinitytech.fluid.ModFluidTests;
import com.rgerva.infinitytech.integrity.FileIntegrity;
import net.jqwik.api.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Label("Unit Tests")
public class InfinityTechTest {

    FileIntegrity integrity = new FileIntegrity();
    ModEnergyTests energyTests = new ModEnergyTests();
    ModFluidTests fluidTests = new ModFluidTests();

    @Label("File Integrity")
    @DisplayName("File Integrity")
    @Test
    public void FileIntegrity() {
        List<Integer> list = integrity.testTexture();
        Assertions.assertEquals(list.get(0), list.get(1), "Textures Fail");
    }

    @Label("Fluid Units")
    @DisplayName("Fluid Units")
    @Test
    public void FluidUnit() {
        fluidTests.FluidUnit();
    }

    @Label("Energy Units")
    @DisplayName("Energy Units")
    @Test
    public void EnergyUnit() {
        energyTests.EnergyUnit();
    }

}
