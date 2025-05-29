/**
 * Class: ModCableEntity
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity.custom.cables;

import com.rgerva.infinitytech.network.interfaces.ModSyncPackages;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ModCableEntity extends BlockEntity implements ModSyncPackages {
    public ModCableEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public BlockEntity getInterfaceSyncBlockEntity() {
        return null;
    }

    @Override
    public int getInterfaceSyncEnergy() {
        return 0;
    }

    @Override
    public int getInterfaceSyncCapacity() {
        return 0;
    }
}
