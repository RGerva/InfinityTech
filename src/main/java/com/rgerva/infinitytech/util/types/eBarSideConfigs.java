/**
 * Class: eBarSideConfigs
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util.types;

public enum eBarSideConfigs {
    RIGHT(152, 17),
    CENTER(80, 17);

    private final int energyMeterX;
    private final int energyMeterY;

    eBarSideConfigs(int energyMeterX, int energyMeterY) {
        this.energyMeterX = energyMeterX;
        this.energyMeterY = energyMeterY;
    }

    public int getEnergyMeterX() {
        return energyMeterX;
    }

    public int getEnergyMeterY() {
        return energyMeterY;
    }
}
