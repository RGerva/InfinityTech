package com.rgerva.infinitytech.block.custom.chest;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.chest.GoldChestBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.chest._ModChestBlockEntity;
import com.rgerva.infinitytech.util.types._eChestConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GoldChestBlock extends _ModChestBlock {
    public static final MapCodec<GoldChestBlock> CODEC = simpleCodec(GoldChestBlock::new);

    public GoldChestBlock(Properties properties) {
        super(properties, _eChestConfigs.GOLD);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GoldChestBlockEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? createTickerHelper(blockEntityType, ModBlockEntities.CHEST_GOLD_ENTITY.get(), _ModChestBlockEntity::lidAnimateTick) : null;
    }
}
