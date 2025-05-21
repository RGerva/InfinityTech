package com.rgerva.infinitytech.util.types;

public enum _eCablesConfigs {
    TIN(128),
    COPPER(1024),
    GOLD(16384);

    private final int maxTransfer;

    _eCablesConfigs(int maxTransfer) {
        this.maxTransfer = maxTransfer;
    }

    public int getMaxTransfer() {
        return maxTransfer;
    }

    public enum ExtractionMode {
        PUSH, PULL, BOTH;

        public boolean isPush() {
            return this == PUSH || this == BOTH;
        }

        public boolean isPull() {
            return this == PULL || this == BOTH;
        }

        public ExtractionMode setPush(){
            return PUSH;
        }
    }
}
