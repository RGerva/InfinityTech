/**
 * Class: eCablesConfigs
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util.types;

import com.rgerva.infinitytech.energy.ModEnergyUtils;
import com.rgerva.infinitytech.fluid.ModFluidUtils;

public enum eCablesConfigs {
    TIN(iStatus.ENERGY, 128),
    COPPER(iStatus.ENERGY, 1024),
    GOLD(iStatus.ENERGY,16384);

    private final iStatus status;
    private final int maxTransfer;

    eCablesConfigs(iStatus iStatus, int maxTransfer) {
        this.status = iStatus;
        this.maxTransfer = maxTransfer;
    }

    public iStatus getStatus() {
        return status;
    }

    public int getMaxTransfer() {
        return maxTransfer;
    }

    @Override
    public String toString() {
        String unit = null;
        switch (status){
            case ITEM -> unit = "Stack per tick";
            case FLUID -> unit = ModFluidUtils.getFluidWithPrefix(getMaxTransfer()) + "/s";
            case ENERGY -> unit = ModEnergyUtils.getEnergyWithPrefix(getMaxTransfer()) + "/s";
        }
        return name() + " [Type: " + status + ", Ratio: " + maxTransfer + " " + unit + "]";
    }

    public enum iStatus {
        ITEM, FLUID, ENERGY;
    }
}
