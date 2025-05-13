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
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.furnace.ModFurnaceEntity;
import com.rgerva.infinitytech.util.types.eFurnaceConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        super(properties.destroyTime(3F));
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
        return new ModFurnaceEntity(blockPos, blockState, getFurnaceConfig());
    }

    public eFurnaceConfigs getFurnaceConfig() {
        return furnaceConfig;
    }
    
    public static Block getBlockType(eFurnaceConfigs eFurnaceConfigs){
        return switch (eFurnaceConfigs){
            case COPPER -> ModBlocks.COPPER_FURNACE.get();
            case IRON -> ModBlocks.IRON_FURNACE.get();
            case GOLD -> ModBlocks.GOLD_FURNACE.get();
        };
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModFurnaceEntity.getEntityType(getFurnaceConfig()), ModFurnaceEntity::tick);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(BlockStateProperties.HORIZONTAL_FACING, rotation.rotate(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(BlockStateProperties.LIT) ? 14 : 0;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(placer != null){
            ModFurnaceEntity entity = (ModFurnaceEntity) level.getBlockEntity(pos);
            Objects.requireNonNull(entity).totalCookTime = entity.getFurnaceConfig().getBurnSpeed();
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof ModFurnaceEntity)) {
                throw new IllegalStateException("Container is invalid");
            }
            player.openMenu((ModFurnaceEntity) blockEntity, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(state.getValue(BlockStateProperties.LIT)){
            if(level.getBlockEntity(pos) == null || (!(level.getBlockEntity(pos) instanceof ModFurnaceEntity))){
                return;
            }

            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY();
            double d2 = (double) pos.getZ() + 0.5D;

            if(random.nextDouble() < 0.1D){
                level.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            Direction.Axis axisDirection = direction.getAxis();
            double d3 = 0.52D;
            double d4 = random.nextDouble() * 0.6D - 0.3D;
            double d5 = axisDirection == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
            double d6 = random.nextDouble() * 6.0D / 16.0D;
            double d7 = axisDirection == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : d4;
            level.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity entity = level.getBlockEntity(pos);
            if(entity instanceof ModFurnaceEntity){
                ((ModFurnaceEntity) entity).drops();
                ((ModFurnaceEntity) entity).grantStoredRecipeExperience((ServerLevel) level, new Vec3(pos.getX(), pos.getY(), pos.getZ()));
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return getDirectSignal(state, level, pos, direction);
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        ModFurnaceEntity entity = (ModFurnaceEntity) level.getBlockEntity(pos);
        if(entity != null){
            return getComparatorInputOverride(state, Objects.requireNonNull(entity.getLevel()), pos);
        }
        return 0;
    }

    public int getComparatorInputOverride(BlockState state, Level level, BlockPos pos) {
        List<Integer> slots = new ArrayList<>();
        if(level.getBlockEntity(pos) instanceof ModFurnaceEntity){
            slots.add(0);
            slots.add(1);
            slots.add(2);
            return getRedStoneSignalFromContainer((WorldlyContainer) level.getBlockEntity(pos), slots);
        }
        return 0;
    }

    public static int getRedStoneSignalFromContainer(@Nullable Container container, List<Integer> slots) {
        if(container != null){
            int i = 0;
            float f = 0.0F;

            for(int slot : slots){
                ItemStack itemstack = container.getItem(slot);
                if (!itemstack.isEmpty()) {
                    f += (float)itemstack.getCount() / (float)Math.min(container.getMaxStackSize(), itemstack.getMaxStackSize());
                    i++;
                }
            }
            f /= (float)slots.size();
            return Mth.floor(f * 14.0F) + (i > 0 ? 1 : 0);
        }
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.infinity_tech.furnace_time").append(String.valueOf(getFurnaceConfig().getBurnSpeed())));

        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.furnace_time")
                    .append(String.valueOf(getFurnaceConfig().getBurnSpeed())).withStyle(ChatFormatting.GRAY));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }
}
