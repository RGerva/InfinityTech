/**
 * Class: eFurnaceConfigs
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util.types;

import com.rgerva.infinitytech.blockentity.custom.furnace.ModFurnaceEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public enum eFurnaceConfigs {
    VANILLA(0,0),
    COPPER(180, 40),
    IRON(160, 40),
    SILVER(140,100),
    GOLD(120, 160),
    DIAMOND(80,240),
    EMERALD(40,320),
    OBSIDIAN(20, 300),
    NETHERITE(5,1000);

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

    public ModFurnaceEntity makeEntity(BlockPos blockPos, BlockState blockState){
        return switch (this){
            case VANILLA -> null;
            case COPPER -> new ModFurnaceEntity(blockPos, blockState, COPPER);
            case IRON -> new ModFurnaceEntity(blockPos, blockState, IRON);
            case SILVER -> new ModFurnaceEntity(blockPos, blockState, SILVER);
            case GOLD -> new ModFurnaceEntity(blockPos, blockState, GOLD);
            case DIAMOND -> new ModFurnaceEntity(blockPos, blockState, DIAMOND);
            case EMERALD -> new ModFurnaceEntity(blockPos, blockState, EMERALD);
            case OBSIDIAN -> new ModFurnaceEntity(blockPos, blockState, OBSIDIAN);
            case NETHERITE -> new ModFurnaceEntity(blockPos, blockState, NETHERITE);
        };
    }

    public enum eFurnaceUpgrade {
        VANILLA_TO_COPPER(VANILLA, COPPER),
        COPPER_TO_IRON(COPPER, IRON),
        IRON_TO_SILVER(IRON, SILVER),
        SILVER_TO_GOLD(SILVER, GOLD),
        GOLD_TO_DIAMOND(GOLD, DIAMOND),
        DIAMOND_TO_EMERALD(DIAMOND, EMERALD),
        EMERALD_TO_OBSIDIAN(EMERALD, OBSIDIAN),
        OBSIDIAN_TO_NETHERITE(OBSIDIAN, NETHERITE);

        public final eFurnaceConfigs source;
        public final eFurnaceConfigs target;

        eFurnaceUpgrade(eFurnaceConfigs source, eFurnaceConfigs target) {
            this.source = source;
            this.target = target;
        }

        public boolean canUpgrade(eFurnaceConfigs from){
            return from.name().equals(this.source.name());
        }
    }
}
