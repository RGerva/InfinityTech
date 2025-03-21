/**
 * Class: eBatteryConfigs
 * Created by: D56V1OK
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util.types;

public enum eBatteryConfigs {
    INFINITY(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE),
    DUMP(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

    private final int capacity;
    private final int maxTransfer;
    private final int energyEfficiency;

    eBatteryConfigs(int capacity, int maxTransfer, int energyEfficiency) {
        this.capacity = capacity;
        this.maxTransfer = maxTransfer;
        this.energyEfficiency = energyEfficiency;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getMaxTransfer(){
        return maxTransfer;
    }

    public int getEnergyEfficiency() {
        return energyEfficiency;
    }
}
