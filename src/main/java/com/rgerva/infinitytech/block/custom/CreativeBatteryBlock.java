package com.rgerva.infinitytech.block.custom;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.CreativeBatteryBlockEntity;
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

public class CreativeBatteryBlock extends BaseEntityBlock {
    public static final MapCodec<CreativeBatteryBlock> CODEC = simpleCodec(CreativeBatteryBlock::new);

    public CreativeBatteryBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CreativeBatteryBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos blockPos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide())
            return InteractionResult.SUCCESS;

        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(!(blockEntity instanceof CreativeBatteryBlockEntity))
            throw new IllegalStateException("Container is invalid");

        player.openMenu((CreativeBatteryBlockEntity)blockEntity, blockPos);

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.CREATIVE_BATTERY_ENTITY.get(), CreativeBatteryBlockEntity::tick);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.capacity",
                            Component.translatable("tooltip.infinity_tech.infinite").
                                    withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.ITALIC)).
                    withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.transfer_rate",
                            Component.translatable("tooltip.infinity_tech.infinite").
                                    withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.ITALIC)).
                    withStyle(ChatFormatting.GRAY));
        }else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }
}
