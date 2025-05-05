/**
 * Class: ModFurnace
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.block.custom.furnace;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rgerva.infinitytech.util.types.eFurnaceConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

public class ModFurnaceBlock extends BaseEntityBlock {
    public static final MapCodec<ModFurnaceBlock> CODEC = RecordCodecBuilder.mapCodec(modFurnaceInstance -> {
        return modFurnaceInstance.group(ExtraCodecs.NON_EMPTY_STRING.xmap(eFurnaceConfigs::valueOf, eFurnaceConfigs::toString)
                        .fieldOf("FurnaceConfigs")
                        .forGetter(ModFurnaceBlock::getFurnaceConfig), Properties.CODEC.fieldOf("properties")
                        .forGetter(Block::properties))
                .apply(modFurnaceInstance, ModFurnaceBlock::new);
    });

    private final eFurnaceConfigs furnaceConfig;

    public ModFurnaceBlock(eFurnaceConfigs eFurnaceConfigs, Properties properties) {
        super(properties);
        this.furnaceConfig = eFurnaceConfigs;
        this.registerDefaultState(this.defaultBlockState()
                .setValue(BlockStateProperties.LIT, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    public eFurnaceConfigs getFurnaceConfig() {
        return furnaceConfig;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT);
    }
}
