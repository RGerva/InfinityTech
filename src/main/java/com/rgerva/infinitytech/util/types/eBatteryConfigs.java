/**
 * Class: eBatteryConfigs
 * Created by: D56V1OK
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util.types;

public enum eBatteryConfigs {
    INFINITY(Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE),
    DUMP(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);

    private final int capacity;
    private final int maxReceive;
    private final int maxExtract;
    private final int energyEfficiency;

    eBatteryConfigs(int capacity, int maxReceive, int maxExtract, int energyEfficiency) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energyEfficiency = energyEfficiency;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getMaxExtract() {
        return maxExtract;
    }

    public int getMaxReceive() {
        return maxReceive;
    }

    public int getEnergyEfficiency() {
        return energyEfficiency;
    }
}
