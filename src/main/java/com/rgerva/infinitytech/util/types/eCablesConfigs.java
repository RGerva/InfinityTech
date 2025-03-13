package com.rgerva.infinitytech.util.types;

public enum eCablesConfigs {
    TIN(128),
    COPPER(1024),
    GOLD(16384);

    private final int maxTransfer;

    eCablesConfigs(int maxTransfer) {
        this.maxTransfer = maxTransfer;
    }

    public int getMaxTransfer() {
        return maxTransfer;
    }
}
