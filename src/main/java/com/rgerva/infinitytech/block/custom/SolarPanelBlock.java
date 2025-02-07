package com.rgerva.infinitytech.block.custom;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.blockentity.ModBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.SolarPanelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SolarPanelBlock extends BaseEntityBlock {

    private static final VoxelShape SHAPE = Block.box(0.d, 0.d, 0.d, 16.d, 4.d, 16.d);
    private final int peakFePerTick;
    private final int maxTransfer;
    private final int capacity;

    public SolarPanelBlock(int peakFePerTick, int maxTransfer, int capacity, Properties properties) {
        super(properties);
        this.peakFePerTick = peakFePerTick;
        this.maxTransfer = maxTransfer;
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getPeakFePerTick(){
        return peakFePerTick;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SolarPanelBlockEntity(ModBlockEntity.SOLAR_PANEL_ENTITY.get(), blockPos, blockState, getCapacity(), getPeakFePerTick());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() == newState.getBlock())
            return;

        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(!(blockEntity instanceof SolarPanelBlockEntity))
            return;

        super.onRemove(state, level, blockPos, newState, movedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos blockPos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide())
            return InteractionResult.SUCCESS;

        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(!(blockEntity instanceof SolarPanelBlockEntity))
            throw new IllegalStateException("Container is invalid");

        player.openMenu((SolarPanelBlockEntity)blockEntity, blockPos);

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntity.SOLAR_PANEL_ENTITY.get(), SolarPanelBlockEntity::tick);
    }

}
