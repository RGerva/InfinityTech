package com.rgerva.infinitytech.block.custom.chest;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.chest.ModChestBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.chest.NetheriteChestBlockEntity;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class NetheriteChestBlock extends ModChestBlock {
    public static final MapCodec<NetheriteChestBlock> CODEC = simpleCodec(NetheriteChestBlock::new);

    public NetheriteChestBlock(Properties properties) {
        super(properties, eChestConfigs.NETHERITE);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new NetheriteChestBlockEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? createTickerHelper(blockEntityType, ModBlockEntities.CHEST_NETHERITE_ENTITY.get(), ModChestBlockEntity::lidAnimateTick) : null;
    }
}
