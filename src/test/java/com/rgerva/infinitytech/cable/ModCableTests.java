/**
 * Class: ModCableTests
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.cable;

import com.rgerva.infinitytech.util.types.eCablesConfigs;

import static com.rgerva.infinitytech.util.ModUtilsTests.*;

public class ModCableTests {

    public void testGetMaxTransferForEachStatus(){
        eCablesConfigs tin = eCablesConfigs.TIN;

        assertEquals(128, tin.getMaxTransfer(eCablesConfigs.iStatus.ENERGY));
        assertEquals(128, tin.getMaxTransfer(eCablesConfigs.iStatus.FLUID));
        assertEquals(128, tin.getMaxTransfer(eCablesConfigs.iStatus.ITEM));
    }

    public void cableEnergyCapacities(){
        eCablesConfigs.ActiveCableCapacities cap = eCablesConfigs.TIN.onlyEnergy();

        assertTrue(cap.isEnergyEnabled());
        assertEquals(128, cap.getEnergy());

        assertFalse(cap.isFluidEnabled());
        assertFalse(cap.isItemEnabled());

        assertThrows(IllegalStateException.class, cap::getFluid);
        assertThrows(IllegalStateException.class, cap::getItem);
    }

    public void testOnlyItemCapacities() {
        eCablesConfigs.ActiveCableCapacities cap = eCablesConfigs.COPPER.onlyItem();

        assertTrue(cap.isItemEnabled());
        assertEquals(256, cap.getItem());

        assertFalse(cap.isFluidEnabled());
        assertFalse(cap.isEnergyEnabled());

        assertThrows(IllegalStateException.class, cap::getFluid);
        assertThrows(IllegalStateException.class, cap::getEnergy);
    }

    public void testOnlyFluidCapacities() {
        eCablesConfigs.ActiveCableCapacities cap = eCablesConfigs.GOLD.onlyFluid();

        assertTrue(cap.isFluidEnabled());
        assertEquals(16384, cap.getFluid());

        assertFalse(cap.isItemEnabled());
        assertFalse(cap.isEnergyEnabled());

        assertThrows(IllegalStateException.class, cap::getItem);
        assertThrows(IllegalStateException.class, cap::getEnergy);
    }

    public void testToStringOverride() {
        String result = eCablesConfigs.TIN.toString();
        assertTrue(result.contains("Type: ENERGY"));
        assertTrue(result.contains("Type: FLUID"));
        assertTrue(result.contains("Type: ITEM"));
    }

    public void testActiveCapacitiesToString() {
        eCablesConfigs.ActiveCableCapacities cap = eCablesConfigs.GOLD.onlyEnergy();
        String output = cap.toString();
        assertTrue(output.contains("Energy"));
        assertFalse(output.contains("Item"));
        assertFalse(output.contains("Fluid"));
    }
}
