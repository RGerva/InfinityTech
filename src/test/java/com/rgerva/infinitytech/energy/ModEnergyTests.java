/**
 *  Class: ModEnergyTests
 *  Created by: D56V1OK
 *  On: 2025/mai.
 *  GitHub: https://github.com/RGerva
 *  Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.energy;

import static com.rgerva.infinitytech.util.ModUtilsTests.assertEquals;

public class ModEnergyTests {

    public void EnergyUnit(){
        assertEquals("1 FE", ModEnergyUtils.getEnergyWithPrefix(1));
        assertEquals("999 FE", ModEnergyUtils.getEnergyWithPrefix(999));
        assertEquals("1.00k FE", ModEnergyUtils.getEnergyWithPrefix(1000));
        assertEquals("1.50k FE", ModEnergyUtils.getEnergyWithPrefix(1500));
        assertEquals("999.99k FE", ModEnergyUtils.getEnergyWithPrefix(999_999));
        assertEquals("1.00M FE", ModEnergyUtils.getEnergyWithPrefix(1_000_000));
        assertEquals("1.50M FE", ModEnergyUtils.getEnergyWithPrefix(1_500_000));
        assertEquals("1.00G FE", ModEnergyUtils.getEnergyWithPrefix(1_000_000_000));
        assertEquals("2.50G FE", ModEnergyUtils.getEnergyWithPrefix(2_500_000_000L));
        assertEquals("1.00T FE", ModEnergyUtils.getEnergyWithPrefix(1_000_000_000_000L));
        assertEquals("1.00P FE", ModEnergyUtils.getEnergyWithPrefix(1_000_000_000_000_000L));
        assertEquals("1000.00P FE", ModEnergyUtils.getEnergyWithPrefix(1_000_000_000_000_000_000L));
    }


}
