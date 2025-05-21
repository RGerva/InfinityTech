/**
 * Class: ModCableBlock
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.block.custom.cables;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.blockentity.custom.cables._ModCableEntity;
import com.rgerva.infinitytech.energy.ModEnergyUtils;
import com.rgerva.infinitytech.util.WrenchConfigurable;
import com.rgerva.infinitytech.util.types._eCablesConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class _ModCableBlock extends BaseEntityBlock implements SimpleWaterloggedBlock, WrenchConfigurable {
    public static final MapCodec<_ModCableBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(ExtraCodecs.NON_EMPTY_STRING.xmap(_eCablesConfigs::valueOf, _eCablesConfigs::toString).fieldOf("eCablesConfigs").
                                forGetter(_ModCableBlock::geteCablesConfigs),
                        Properties.CODEC.fieldOf("properties").forGetter(Block::properties)).
                apply(instance, _ModCableBlock::new);
    });

    private final _eCablesConfigs cablesConfigs;

    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_CORE = Block.box(6.d, 6.d, 6.d, 10.d, 10.d, 10.d);
    private static final VoxelShape SHAPE_UP = Block.box(6.d, 10.d, 6.d, 10.d, 16.d, 10.d);
    private static final VoxelShape SHAPE_DOWN = Block.box(6.d, 0.d, 6.d, 10.d, 6.d, 10.d);
    private static final VoxelShape SHAPE_NORTH = Block.box(6.d, 6.d, 0.d, 10.d, 10.d, 6.d);
    private static final VoxelShape SHAPE_SOUTH = Block.box(6.d, 6.d, 10.d, 10.d, 10.d, 16.d);
    private static final VoxelShape SHAPE_EAST = Block.box(10.d, 6.d, 6.d, 16.d, 10.d, 10.d);
    private static final VoxelShape SHAPE_WEST = Block.box(0.d, 6.d, 6.d, 6.d, 10.d, 10.d);

    public _ModCableBlock(_eCablesConfigs cablesConfigs, Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(EAST, false)
                .setValue(WEST, false)
                .setValue(WATERLOGGED, false));

        this.cablesConfigs = cablesConfigs;
    }

    public _eCablesConfigs geteCablesConfigs() {
        return cablesConfigs;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new _ModCableEntity(blockPos, blockState, cablesConfigs);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos selfPos = context.getClickedPos();
        FluidState fluidState = level.getFluidState(selfPos);

        return defaultBlockState().
                setValue(UP, shouldConnectTo(level, selfPos, Direction.UP)).
                setValue(DOWN, shouldConnectTo(level, selfPos, Direction.DOWN)).
                setValue(NORTH, shouldConnectTo(level, selfPos, Direction.NORTH)).
                setValue(SOUTH, shouldConnectTo(level, selfPos, Direction.SOUTH)).
                setValue(EAST, shouldConnectTo(level, selfPos, Direction.EAST)).
                setValue(WEST, shouldConnectTo(level, selfPos, Direction.WEST)).
                setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    private boolean shouldConnectTo(Level level, BlockPos selfPos, Direction direction) {
        BlockPos toPos = selfPos.relative(direction);
        BlockEntity blockEntity = level.getBlockEntity(toPos);

        if (blockEntity instanceof _ModCableEntity cableBlockEntity && cableBlockEntity.getCablesConfigs() != this.geteCablesConfigs()){
            return false;
        }

        IEnergyStorage energyStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK,
                toPos, level.getBlockState(toPos), blockEntity, direction.getOpposite());
        return energyStorage != null;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return switch (rotation) {
            case CLOCKWISE_90 -> state.
                    setValue(NORTH, state.getValue(WEST)).
                    setValue(SOUTH, state.getValue(EAST)).
                    setValue(EAST, state.getValue(NORTH)).
                    setValue(WEST, state.getValue(SOUTH));
            case CLOCKWISE_180 -> state.
                    setValue(NORTH, state.getValue(SOUTH)).
                    setValue(SOUTH, state.getValue(NORTH)).
                    setValue(EAST, state.getValue(WEST)).
                    setValue(WEST, state.getValue(EAST));
            case COUNTERCLOCKWISE_90 -> state.
                    setValue(NORTH, state.getValue(EAST)).
                    setValue(SOUTH, state.getValue(WEST)).
                    setValue(EAST, state.getValue(SOUTH)).
                    setValue(WEST, state.getValue(NORTH));
            default -> state;
        };
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case LEFT_RIGHT -> state.
                    setValue(NORTH, state.getValue(SOUTH)).
                    setValue(SOUTH, state.getValue(NORTH));
            case FRONT_BACK -> state.
                    setValue(EAST, state.getValue(WEST)).
                    setValue(WEST, state.getValue(EAST));
            default -> state;
        };
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = SHAPE_CORE;

        if (state.getValue(UP)) {
            shape = Shapes.or(shape, SHAPE_UP);
        }

        if (state.getValue(DOWN)) {
            shape = Shapes.or(shape, SHAPE_DOWN);
        }

        if (state.getValue(NORTH)) {
            shape = Shapes.or(shape, SHAPE_NORTH);
        }

        if (state.getValue(SOUTH)) {
            shape = Shapes.or(shape, SHAPE_SOUTH);
        }

        if (state.getValue(EAST)) {
            shape = Shapes.or(shape, SHAPE_EAST);
        }

        if (state.getValue(WEST)) {
            shape = Shapes.or(shape, SHAPE_WEST);
        }

        return shape;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)){
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP).add(DOWN).add(NORTH).add(SOUTH).add(EAST).add(WEST).add(WATERLOGGED);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);

        if(level.isClientSide()){
            return;
        }

        FluidState fluidState = level.getFluidState(pos);

        BlockState newState = defaultBlockState().
                setValue(UP, shouldConnectTo(level, pos, Direction.UP)).
                setValue(DOWN, shouldConnectTo(level, pos, Direction.DOWN)).
                setValue(NORTH, shouldConnectTo(level, pos, Direction.NORTH)).
                setValue(SOUTH, shouldConnectTo(level, pos, Direction.SOUTH)).
                setValue(EAST, shouldConnectTo(level, pos, Direction.EAST)).
                setValue(WEST, shouldConnectTo(level, pos, Direction.WEST)).
                setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);

        level.setBlockAndUpdate(pos, newState);

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if(!(blockEntity instanceof _ModCableEntity)){
            return;
        }

        _ModCableEntity.updateConnections(level, pos, newState, (_ModCableEntity) blockEntity);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, _ModCableEntity.getEntityType(cablesConfigs), _ModCableEntity::tick);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.cable.transfer_rate",
                    ModEnergyUtils.getEnergyWithPrefix(cablesConfigs.getMaxTransfer())).withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.cable.info").
                    withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public @NotNull InteractionResult onUseWrench(UseOnContext context, Direction selectedFace, boolean nextPreviousValue) {

        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        if(blockEntity instanceof _ModCableEntity){
            String extractionMode = ((_ModCableEntity) blockEntity).getExtractionMode().name();
            InfinityTech.LOGGER.info("ExtractionMode {}", extractionMode);
            ((_ModCableEntity) blockEntity).setExtractionMode(_eCablesConfigs.ExtractionMode.PUSH);
            String extractionMode1 = ((_ModCableEntity) blockEntity).getExtractionMode().name();
            InfinityTech.LOGGER.info("ExtractionMode1 {}", extractionMode1);
        }
        return InteractionResult.SUCCESS;
    }
}
