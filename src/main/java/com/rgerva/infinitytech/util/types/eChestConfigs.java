/**
 * Class: eChestConfigs
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util.types;

public enum eChestConfigs {
    IRON(54, 9, 184, 222, 256, 256),
    COPPER(45, 9, 184, 204, 256, 256),
    GOLD(81, 9, 184, 276, 256, 276),
    DIAMOND(108, 12, 238, 276, 256, 276),
    OBSIDIAN(108, 12, 238, 276, 256, 276),
    NETHERITE(108, 12, 238, 276, 256, 276);

    public final int size;
    public final int rowLength;
    public final int xSize;
    public final int ySize;
    public final int textureXSize;
    public final int textureYSize;

    eChestConfigs(int size, int rowLength, int xSize, int ySize, int textureXSize, int textureYSize) {
        this.size = size;
        this.rowLength = rowLength;
        this.xSize = xSize;
        this.ySize = ySize;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    public int getRowCount() {
        return this.size / this.rowLength;
    }

    public enum eChestUpgrade {
        COPPER_TO_IRON(COPPER, IRON),
        IRON_TO_GOLD(IRON, GOLD),
        GOLD_TO_DIAMOND(GOLD, DIAMOND),
        DIAMOND_TO_OBSIDIAN(DIAMOND, OBSIDIAN),
        DIAMOND_TO_NETHERITE(DIAMOND, NETHERITE);

        public final eChestConfigs source;
        public final eChestConfigs target;

        eChestUpgrade(eChestConfigs source, eChestConfigs target) {
            this.source = source;
            this.target = target;
        }

        public boolean canUpgrade(eCablesConfigs from){
            return from.name().equals(this.source.name());
        }
    }
}
