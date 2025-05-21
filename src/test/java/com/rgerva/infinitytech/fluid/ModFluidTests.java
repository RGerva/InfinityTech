/**
 * Class: ModFluidTests
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.fluid;

import static com.rgerva.infinitytech.util.ModUtilsTests.assertEquals;

public class ModFluidTests {

    public void FluidUnit(){
        assertEquals("1.00m Buckets", ModFluidUtils.getFluidWithPrefix(1));
        assertEquals("999.00m Buckets", ModFluidUtils.getFluidWithPrefix(999));
        assertEquals("1.00 Buckets", ModFluidUtils.getFluidWithPrefix(1000));
        assertEquals("1.50L Buckets", ModFluidUtils.getFluidWithPrefix(1_500_000));
        assertEquals("1.00L Buckets", ModFluidUtils.getFluidWithPrefix(1_000_000));
        assertEquals("1.50L Buckets", ModFluidUtils.getFluidWithPrefix(1_500_000));
    }
}
