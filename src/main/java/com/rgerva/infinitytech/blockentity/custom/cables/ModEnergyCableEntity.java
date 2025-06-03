package com.rgerva.infinitytech.blockentity.custom.cables;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ModEnergyCableEntity extends ModCableEntity{
    public ModEnergyCableEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TEST_CABLE.get(), pos, blockState);
    }
}
