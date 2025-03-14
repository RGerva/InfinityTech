package com.rgerva.infinitytech.block.custom.solar_panel;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.solar_panel.SolarPanelBlockEntity;
import com.rgerva.infinitytech.energy.ModEnergyUtils;
import com.rgerva.infinitytech.util.types.eSolarPanelConfigs;
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

import java.util.List;

public class SolarPanelBlock extends BaseEntityBlock {
    public static final MapCodec<SolarPanelBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(ExtraCodecs.NON_EMPTY_STRING.xmap(eSolarPanelConfigs::valueOf, eSolarPanelConfigs::toString).fieldOf("SolarPanelConfigs").
                                forGetter(SolarPanelBlock::geteSolarPanelConfigs),
                        Properties.CODEC.fieldOf("properties").forGetter(Block::properties)).
                apply(instance, SolarPanelBlock::new);
    });

    private static final VoxelShape SHAPE = Block.box(0.d, 0.d, 0.d, 16.d, 4.d, 16.d);
    private final eSolarPanelConfigs solarPanelConfigs;

    public SolarPanelBlock(eSolarPanelConfigs eSolarPanelConfigs, Properties properties) {
        super(properties);
        this.solarPanelConfigs = eSolarPanelConfigs;
    }

    public static Block getBlockFromPanelConfigs(eSolarPanelConfigs geteSolarPanelConfigs) {
        return switch (geteSolarPanelConfigs) {
            case solar_panel_1 -> ModBlocks.SOLAR_PANEL_1.get();
            case solar_panel_2 -> ModBlocks.SOLAR_PANEL_2.get();
            case solar_panel_3 -> ModBlocks.SOLAR_PANEL_3.get();
            case solar_panel_4 -> ModBlocks.SOLAR_PANEL_4.get();
            case solar_panel_5 -> ModBlocks.SOLAR_PANEL_5.get();
            case solar_panel_6 -> ModBlocks.SOLAR_PANEL_6.get();
        };
    }

    public eSolarPanelConfigs geteSolarPanelConfigs() {
        return solarPanelConfigs;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SolarPanelBlockEntity(blockPos, blockState, solarPanelConfigs);

    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos blockPos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide())
            return InteractionResult.SUCCESS;

        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (!(blockEntity instanceof SolarPanelBlockEntity))
            throw new IllegalStateException("Container is invalid");

        player.openMenu((SolarPanelBlockEntity) blockEntity, blockPos);

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, SolarPanelBlockEntity.getEntityType(solarPanelConfigs), SolarPanelBlockEntity::tick);
    }


    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.solar_panel.produces",
                    ModEnergyUtils.getEnergyWithPrefix(solarPanelConfigs.getPeakFePerTick())).withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.solar_panel.info").withStyle(ChatFormatting.GRAY));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }
}
