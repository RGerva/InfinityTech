package com.rgerva.infinitytech.block.custom;

import com.mojang.serialization.MapCodec;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.SolarPanelBlockEntity;
import com.rgerva.infinitytech.energy.ModEnergyUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
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
    public static final MapCodec<SolarPanelBlock> CODEC = simpleCodec(SolarPanelBlock::new);
    private static final VoxelShape SHAPE = Block.box(0.d, 0.d, 0.d, 16.d, 4.d, 16.d);

    private static int CAPACITY;
    private static int TRANSFER_RATE;
    private static int PEAK_PRODUCTION;
    private static BlockEntityType<?> type;
    private static String machineName;

    public SolarPanelBlock(Properties properties, int capacity, int transferRate, int peakProduction) {
        this(properties);
        CAPACITY = capacity;
        TRANSFER_RATE = transferRate;
        PEAK_PRODUCTION = peakProduction;
    }

    public SolarPanelBlock(Properties properties) {
        super(properties);
    }

    public static int getPeakFePerTick(){
        return PEAK_PRODUCTION;
    }

    public static int getCapacity(){
        return CAPACITY;
    }

    public static int getTransferRate(){
        return TRANSFER_RATE;
    }

    public static BlockEntityType<?> getType(){
        return type;
    }

    public static String getMachineName(){
        return machineName;
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
        if(blockState.getBlock() == ModBlocks.SOLAR_PANEL_1.get()){
            type = ModBlockEntities.SOLAR_PANEL_ENTITY_1.get();
            machineName = BuiltInRegistries.BLOCK.getKey(ModBlocks.SOLAR_PANEL_1.get()).getPath();

        } else if (blockState.getBlock() == ModBlocks.SOLAR_PANEL_2.get()) {
            type = ModBlockEntities.SOLAR_PANEL_ENTITY_2.get();
            machineName = BuiltInRegistries.BLOCK.getKey(ModBlocks.SOLAR_PANEL_2.get()).getPath();

        }else if (blockState.getBlock() == ModBlocks.SOLAR_PANEL_3.get()) {
            type = ModBlockEntities.SOLAR_PANEL_ENTITY_3.get();
            machineName = BuiltInRegistries.BLOCK.getKey(ModBlocks.SOLAR_PANEL_3.get()).getPath();

        }else if (blockState.getBlock() == ModBlocks.SOLAR_PANEL_4.get()) {
            type = ModBlockEntities.SOLAR_PANEL_ENTITY_4.get();
            machineName = BuiltInRegistries.BLOCK.getKey(ModBlocks.SOLAR_PANEL_4.get()).getPath();

        }else if (blockState.getBlock() == ModBlocks.SOLAR_PANEL_5.get()) {
            type = ModBlockEntities.SOLAR_PANEL_ENTITY_5.get();
            machineName = BuiltInRegistries.BLOCK.getKey(ModBlocks.SOLAR_PANEL_5.get()).getPath();

        }else if (blockState.getBlock() == ModBlocks.SOLAR_PANEL_6.get()) {
            type = ModBlockEntities.SOLAR_PANEL_ENTITY_6.get();
            machineName = BuiltInRegistries.BLOCK.getKey(ModBlocks.SOLAR_PANEL_6.get()).getPath();

        }else{
            return null;
        }

        return new SolarPanelBlockEntity(blockPos, blockState);

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
        if(!(blockEntity instanceof SolarPanelBlockEntity))
            throw new IllegalStateException("Container is invalid");

        player.openMenu((SolarPanelBlockEntity)blockEntity, blockPos);

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.SOLAR_PANEL_ENTITY_1.get(), SolarPanelBlockEntity::tick);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.solar_panel.produces",
                    ModEnergyUtils.getEnergyWithPrefix(getPeakFePerTick())).withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.solar_panel.info").withStyle(ChatFormatting.GRAY));
        }else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }
}
