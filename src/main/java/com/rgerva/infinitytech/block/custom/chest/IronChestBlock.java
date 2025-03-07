package com.rgerva.infinitytech.block.custom.chest;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.blockentity.custom.chest.IronChestBlockEntity;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class IronChestBlock extends ModChestBlock {

    public static final MapCodec<IronChestBlock> CODEC = simpleCodec(IronChestBlock::new);

    public IronChestBlock(Properties properties) {
        super(properties, eChestConfigs.IRON);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new IronChestBlockEntity(blockPos, blockState);
    }
}
