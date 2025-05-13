/**
 * Class: eFurnaceConfigs
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util.types;

public enum eFurnaceConfigs {
    COPPER(180, 40),
    IRON(160, 40),
    GOLD(120, 160);

    private final int burnSpeed;
    private final int energyCapacity;

    eFurnaceConfigs(int burnSpeed, int energyCapacity) {
        this.burnSpeed = burnSpeed;
        this.energyCapacity = energyCapacity;
    }

    public int getBurnSpeed() {
        return burnSpeed;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }
}
