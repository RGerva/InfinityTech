/**
 * Class: ModCableBlock
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.block.custom.cables;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.blockentity.custom.cables.ModCableEntity;
import com.rgerva.infinitytech.item.custom.wrench.WrenchItem;
import com.rgerva.infinitytech.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public abstract class ModCableBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty HAS_DATA = BooleanProperty.create("has_data");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE_CORE = Block.box(6.d, 6.d, 6.d, 10.d, 10.d, 10.d);
    private static final VoxelShape SHAPE_UP = Block.box(6.d, 10.d, 6.d, 10.d, 16.d, 10.d);
    private static final VoxelShape SHAPE_DOWN = Block.box(6.d, 0.d, 6.d, 10.d, 6.d, 10.d);
    private static final VoxelShape SHAPE_NORTH = Block.box(6.d, 6.d, 0.d, 10.d, 10.d, 6.d);
    private static final VoxelShape SHAPE_SOUTH = Block.box(6.d, 6.d, 10.d, 10.d, 10.d, 16.d);
    private static final VoxelShape SHAPE_EAST = Block.box(10.d, 6.d, 6.d, 16.d, 10.d, 10.d);
    private static final VoxelShape SHAPE_WEST = Block.box(0.d, 6.d, 6.d, 6.d, 10.d, 10.d);

    public static final VoxelShape SHAPE_EXTRACT_NORTH = ModUtils.combine(SHAPE_NORTH, Block.box(4D, 4D, 0D, 12D, 12D, 1D));
    public static final VoxelShape SHAPE_EXTRACT_SOUTH = ModUtils.combine(SHAPE_SOUTH, Block.box(4D, 4D, 15D, 12D, 12D, 16D));
    public static final VoxelShape SHAPE_EXTRACT_EAST = ModUtils.combine(SHAPE_EAST, Block.box(15D, 4D, 4D, 16D, 12D, 12D));
    public static final VoxelShape SHAPE_EXTRACT_WEST = ModUtils.combine(SHAPE_WEST, Block.box(0D, 4D, 4D, 1D, 12D, 12D));
    public static final VoxelShape SHAPE_EXTRACT_UP = ModUtils.combine(SHAPE_UP, Block.box(4D, 15D, 4D, 12D, 16D, 12D));
    public static final VoxelShape SHAPE_EXTRACT_DOWN = ModUtils.combine(SHAPE_DOWN, Block.box(4D, 0D, 4D, 12D, 1D, 12D));


    public ModCableBlock(Properties properties) {
        super(properties.pushReaction(PushReaction.BLOCK));

        registerDefaultState(this.stateDefinition.any()
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(NORTH, false)
                .setValue(SOUTH, false)
                .setValue(EAST, false)
                .setValue(WEST, false)
                .setValue(HAS_DATA, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP).add(DOWN).add(NORTH).add(SOUTH).add(EAST).add(WEST).add(HAS_DATA).add(WATERLOGGED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    private static final List<ModUtils.Triple<VoxelShape, BooleanProperty, Direction>> SHAPES = Arrays.asList(
            new ModUtils.Triple<>(SHAPE_NORTH, NORTH, Direction.NORTH),
            new ModUtils.Triple<>(SHAPE_SOUTH, SOUTH, Direction.SOUTH),
            new ModUtils.Triple<>(SHAPE_WEST, WEST, Direction.WEST),
            new ModUtils.Triple<>(SHAPE_EAST, EAST, Direction.EAST),
            new ModUtils.Triple<>(SHAPE_UP, UP, Direction.UP),
            new ModUtils.Triple<>(SHAPE_DOWN, DOWN, Direction.DOWN)
    );

    private static final List<ModUtils.Pair<VoxelShape, Direction>> EXTRACT_SHAPES = Arrays.asList(
            new ModUtils.Pair<>(SHAPE_EXTRACT_NORTH, Direction.NORTH),
            new ModUtils.Pair<>(SHAPE_EXTRACT_SOUTH, Direction.SOUTH),
            new ModUtils.Pair<>(SHAPE_EXTRACT_WEST, Direction.WEST),
            new ModUtils.Pair<>(SHAPE_EXTRACT_EAST, Direction.EAST),
            new ModUtils.Pair<>(SHAPE_EXTRACT_UP, Direction.UP),
            new ModUtils.Pair<>(SHAPE_EXTRACT_DOWN, Direction.DOWN)
    );

    public ModUtils.Pair<Direction, VoxelShape> getSelection(BlockState state, BlockGetter blockReader, BlockPos pos, Player player) {
        Vec3 start = player.getEyePosition(1F);
        Vec3 end = start.add(player.getLookAngle().normalize().scale(getBlockReachDistance(player)));

        Direction direction = null;
        VoxelShape selection = null;
        double shortest = Double.MAX_VALUE;

        double d = checkShape(state, blockReader, pos, start, end, SHAPE_CORE, null);
        if (d < shortest) {
            shortest = d;
        }

        if (!(blockReader instanceof LevelAccessor)) {
            return new ModUtils.Pair<>(direction, selection);
        }

        ModCableEntity entity = (ModCableEntity) blockReader.getBlockEntity(pos);
        if(!(entity instanceof ModCableEntity)){
            return null;
        }

        for (int i = 0; i < Direction.values().length; i++) {
            ModUtils.Pair<VoxelShape, Direction> extract = EXTRACT_SHAPES.get(i);
            ModUtils.Triple<VoxelShape, BooleanProperty, Direction> shape = SHAPES.get(i);
            if (entity.isExtracting(extract.value())) {
                d = checkShape(state, blockReader, pos, start, end, extract.key(), entity, extract.value());
                if (d < shortest) {
                    shortest = d;
                    direction = extract.value();
                    selection = extract.key();
                }
            } else {
                d = checkShape(state, blockReader, pos, start, end, shape.value1(), shape.value2());
                if (d < shortest) {
                    shortest = d;
                    direction = shape.value3();
                    selection = shape.value1();
                }
            }
        }

        return new ModUtils.Pair<>(direction, selection);
    }

    private double checkShape(BlockState state, BlockGetter world, BlockPos pos, Vec3 start, Vec3 end, VoxelShape shape, BooleanProperty direction) {
        if (direction != null && !state.getValue(direction)) {
            return Double.MAX_VALUE;
        }
        BlockHitResult blockRayTraceResult = world.clipWithInteractionOverride(start, end, pos, shape, state);
        if (blockRayTraceResult == null) {
            return Double.MAX_VALUE;
        }
        return blockRayTraceResult.getLocation().distanceTo(start);
    }

    private double checkShape(BlockState state, BlockGetter world, BlockPos pos, Vec3 start, Vec3 end, VoxelShape shape, ModCableEntity cableEntity, Direction side) {
        if (cableEntity != null && !cableEntity.isExtracting(side)) {
            return Double.MAX_VALUE;
        }
        BlockHitResult blockRayTraceResult = world.clipWithInteractionOverride(start, end, pos, shape, state);
        if (blockRayTraceResult == null) {
            return Double.MAX_VALUE;
        }
        return blockRayTraceResult.getLocation().distanceTo(start);
    }

    private double getBlockReachDistance(Player player) {
        AttributeInstance attribute = player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
        if (attribute == null) {
            return (float) Attributes.BLOCK_INTERACTION_RANGE.value().getDefaultValue();
        }
        return (float) attribute.getValue();
    }

    public InteractionResult onCableSideActivated(ItemStack itemStack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, Direction direction) {
        return super.useItemOn(itemStack, state, worldIn, pos, player, handIn, hit);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        Direction side = getSelection(state, level, pos, player).key();
        if (side != null) {
            return onCableSideActivated(stack, state, level, pos, player, hand, hitResult, side);
        } else {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }
    }

    private BlockState getState(Level world, BlockPos pos, @javax.annotation.Nullable BlockState oldState) {
        FluidState fluidState = world.getFluidState(pos);
        boolean hasData = false;
        if (oldState != null && oldState.getBlock() == this) {
            hasData = oldState.getValue(HAS_DATA);
        }
        return defaultBlockState()
                .setValue(UP, isConnected(world, pos, Direction.UP))
                .setValue(DOWN, isConnected(world, pos, Direction.DOWN))
                .setValue(NORTH, isConnected(world, pos, Direction.NORTH))
                .setValue(SOUTH, isConnected(world, pos, Direction.SOUTH))
                .setValue(EAST, isConnected(world, pos, Direction.EAST))
                .setValue(WEST, isConnected(world, pos, Direction.WEST))
                .setValue(HAS_DATA, hasData)
                .setValue(WATERLOGGED, fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8);
    }

    public boolean isConnected(Level world, BlockPos pos, Direction facing) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        BlockEntity blockEntity1 = world.getBlockEntity(pos.relative(facing));

        if(blockEntity instanceof ModCableEntity cableEntity && blockEntity1 instanceof ModCableEntity cableEntity1){

            if(!isAbleToConnect(world, pos, facing)){
                return false;
            }

            boolean canSelfConnect = !cableEntity.isDisconnected(facing);
            if (!canSelfConnect) {
                return false;
            }
            return !cableEntity1.isDisconnected(facing.getOpposite());

        }else{
            return false;
        }
    }

    private boolean isAbleToConnect(Level world, BlockPos pos, Direction facing) {
        return isCable(world, pos, facing) || canConnectTo(world, pos, facing);
    }

    abstract boolean canConnectTo(Level world, BlockPos pos, Direction facing);

    abstract boolean isCable(LevelAccessor world, BlockPos pos, Direction facing);

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return getState(context.getLevel(), context.getClickedPos(), null);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, level, scheduledTickAccess, pos, direction, pos, neighborState, random);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);
        BlockState newState = getState(level, pos, state);
        if (!state.getProperties().stream().allMatch(property -> state.getValue(property).equals(newState.getValue(property)))) {
            level.setBlockAndUpdate(pos, newState);
            ModCableEntity.markCablesDirty(level, pos);
        }
    }

    public VoxelShape getShape(BlockGetter blockReader, BlockPos pos, BlockState state, boolean advanced) {

        VoxelShape shape = SHAPE_CORE;

        return shape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext) {
            EntityCollisionContext ctx = (EntityCollisionContext) context;
            if (ctx.getEntity() instanceof Player player) {
                if (player.level().isClientSide) {
                    return getSelectionShape(state, level, pos, player);
                }
            }
        }
        return getShape(level, pos, state, true);
    }

    private VoxelShape getSelectionShape(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        ModUtils.Pair<Direction, VoxelShape> selection = getSelection(state, level, pos, player);
        if (selection.key() == null) {
            return getShape(level, pos, state, true);
        }

        if (level.getBlockState(pos.relative(selection.key())).getBlock() == this) {
            if (!WrenchItem.isHoldingWrench(player)) {
                return getShape(level, pos, state, true);
            }
        }

        return selection.value();
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.is(newState.getBlock())) {
            if (!newState.getValue(HAS_DATA)) {
                level.removeBlockEntity(pos);
            }
        }else {
            BlockEntity blockentity = level.getBlockEntity(pos);
            //TODO
            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    @Override
    protected VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(level, pos, state, false);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(level, pos, state, false);
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return getShape(level, pos, state, false);
    }

    @Override
    protected VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return getShape(level, pos, state, false);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public void setHasData(Level level, BlockPos pos, boolean hasData) {
        BlockState blockState = level.getBlockState(pos);
        level.setBlockAndUpdate(pos, blockState.setValue(HAS_DATA, hasData));
    }
}
