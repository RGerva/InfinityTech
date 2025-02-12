package com.rgerva.infinitytech.block.custom;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.BatteryBlockEntity;
import com.rgerva.infinitytech.energy.ModEnergyUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BatteryBlock extends BaseEntityBlock {
    public static final MapCodec<BatteryBlock> CODEC = simpleCodec(BatteryBlock::new);

    public BatteryBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BatteryBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos blockPos) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(!(blockEntity instanceof BatteryBlockEntity batteryBoxBlockEntity))
            return super.getAnalogOutputSignal(state, level, blockPos);

        return batteryBoxBlockEntity.getRedstoneOutput();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos blockPos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide())
            return InteractionResult.SUCCESS;

        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(!(blockEntity instanceof BatteryBlockEntity))
            throw new IllegalStateException("Container is invalid");

        player.openMenu((BatteryBlockEntity)blockEntity, blockPos);

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.BATTERY_BOX_ENTITY.get(), BatteryBlockEntity::tick);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.capacity",
                            ModEnergyUtils.getEnergyWithPrefix(BatteryBlockEntity.CAPACITY)).
                    withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.transfer_rate",
                            ModEnergyUtils.getEnergyWithPrefix(BatteryBlockEntity.MAX_TRANSFER)).
                    withStyle(ChatFormatting.GRAY));
        }else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }
}
