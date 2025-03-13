package com.rgerva.infinitytech.util.types;

public enum eSolarPanelConfigs {
    solar_panel_1(8 * 20 * 2, 8 * 4, 8),
    solar_panel_2(16 * 20 * 2, 16 * 4, 16),
    solar_panel_3(128 * 20 * 2, 128 * 4, 128),
    solar_panel_4(2048 * 20 * 2, 2048 * 4, 2048),
    solar_panel_5(16384 * 20, 16384 * 8, 16384),
    solar_panel_6(131072 * 8, 131072 * 4, 131072);

    private final int capacity;
    private final int maxTransfer;
    private final int peakFePerTick;

    eSolarPanelConfigs(int capacity, int maxTransfer, int peakFePerTick) {
        this.capacity = capacity;
        this.maxTransfer = maxTransfer;
        this.peakFePerTick = peakFePerTick;
    }

    public int getPeakFePerTick() {
        return peakFePerTick;
    }

    public int getMaxTransfer() {
        return maxTransfer;
    }

    public int getCapacity() {
        return capacity;
    }
}
