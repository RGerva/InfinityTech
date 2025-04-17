/**
 * Class: CoalGeneratorBlock
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.block.custom.generator;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.generator.ModCoalGeneratorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ModCoalGeneratorBlock extends BaseEntityBlock {
    public static final MapCodec<ModCoalGeneratorBlock> CODEC = simpleCodec(ModCoalGeneratorBlock::new);

    public ModCoalGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ModCoalGeneratorEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level plevel, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.COAL_GENERATOR_ENTITY.get(), ModCoalGeneratorEntity::tick);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ModCoalGeneratorEntity modCoalGeneratorEntity) {
                modCoalGeneratorEntity.drops();
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof ModCoalGeneratorEntity)) {
                throw new IllegalStateException("Container is invalid");
            }
            player.openMenu((ModCoalGeneratorEntity) blockEntity, pos);
        }
        return InteractionResult.SUCCESS;
    }
}
