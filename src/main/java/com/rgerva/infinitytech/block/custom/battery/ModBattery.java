/**
 *  Class: ModBattery
 *  Created by: D56V1OK
 *  On: 2025/mar.
 *  GitHub: https://github.com/RGerva
 *  Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.block.custom.battery;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.RecordBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.battery.BatteryBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.battery.ModBatteryEntity;
import com.rgerva.infinitytech.energy.ModEnergyUtils;
import com.rgerva.infinitytech.util.types.eBatteryConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBattery extends BaseEntityBlock {
    public static final MapCodec<ModBattery> CODEC = RecordCodecBuilder.mapCodec(modBatteryInstance -> {
        return modBatteryInstance.group(ExtraCodecs.NON_EMPTY_STRING.xmap(eBatteryConfigs::valueOf, eBatteryConfigs::toString)
                        .fieldOf("BatteryConfigs")
                        .forGetter(ModBattery::getBatteryConfigs), Properties.CODEC.fieldOf("properties")
                        .forGetter(Block::properties))
                .apply(modBatteryInstance, ModBattery::new);
    });

    private final eBatteryConfigs batteryConfigs;

    public ModBattery(eBatteryConfigs batteryConfigs, Properties properties) {
        super(properties);
        this.batteryConfigs = batteryConfigs;
    }

    public eBatteryConfigs getBatteryConfigs(){
        return batteryConfigs;
    }

    public static Block getBlockFromBatteryConfigs(eBatteryConfigs batteryConfigs){
        return switch (batteryConfigs){
          case INFINITY -> ModBlocks.INFINITY_BATTERY.get();
            case DUMP -> ModBlocks.DUMP_BATTERY.get();
            case null, default -> null;
        };
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ModBatteryEntity(blockPos, blockState, batteryConfigs);
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
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBatteryEntity.getEntityType(batteryConfigs), ModBatteryEntity::tick);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.capacity",
                            ModEnergyUtils.getEnergyWithPrefix(batteryConfigs.getCapacity())).
                    withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.transfer_rate",
                            ModEnergyUtils.getEnergyWithPrefix(batteryConfigs.getMaxReceive())).
                    withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.efficiency",
                            ModEnergyUtils.getEnergyWithPrefix(batteryConfigs.getEnergyEfficiency())).
                    withStyle(ChatFormatting.GRAY));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }
}
