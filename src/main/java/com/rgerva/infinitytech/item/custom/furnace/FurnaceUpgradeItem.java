/**
 * Class: FurnaceUpgradeItem
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.item.custom.furnace;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.block.custom.furnace.ModFurnaceBlock;
import com.rgerva.infinitytech.blockentity.custom.furnace.ModFurnaceEntity;
import com.rgerva.infinitytech.util.types.eFurnaceConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FurnaceUpgradeItem extends Item {

    private final eFurnaceConfigs.eFurnaceUpgrade furnaceUpgrade;

    public FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade eFurnaceUpgrade, Properties properties) {
        super(properties);
        this.furnaceUpgrade = eFurnaceUpgrade;
    }

    public eFurnaceConfigs.eFurnaceUpgrade getFurnaceUpgrade() {
        return furnaceUpgrade;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.furnace_upgrade.info").
                    withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }else{
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        Level level = context.getLevel();
        Block block = level.getBlockState(blockPos).getBlock();

        if(Objects.requireNonNull(player).isShiftKeyDown()){
            if(!canUpgradeFurnace(block)){
                return InteractionResult.PASS;
            }

            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            ArrayList<ItemStack> furnaceContent = getFurnaceContent(blockEntity);

            ModFurnaceEntity newFurnace = (ModFurnaceEntity) getNewFurnace(level, blockPos);

            if(newFurnace == null){
                return InteractionResult.PASS;
            }

            level.removeBlockEntity(blockPos);
            level.removeBlock(blockPos, false);

            level.setBlock(blockPos, newFurnace.getBlockState(), 3);
            level.setBlockEntity(newFurnace);

            BlockEntity updatedFurnace = level.getBlockEntity(blockPos);
            if(updatedFurnace instanceof ModFurnaceEntity && !furnaceContent.isEmpty()){
                ((ModFurnaceEntity) updatedFurnace).itemHandler.insertItem(0, furnaceContent.get(0), false);
                ((ModFurnaceEntity) updatedFurnace).itemHandler.insertItem(1, furnaceContent.get(1), false);
                ((ModFurnaceEntity) updatedFurnace).itemHandler.insertItem(2, furnaceContent.get(2), false);
            }

            if(!player.getAbilities().instabuild){
                context.getItemInHand().shrink(1);
            }

            return InteractionResult.PASS;
        }

        return InteractionResult.SUCCESS;
    }

    private BlockEntity getNewFurnace(Level level, BlockPos blockPos){
        BlockState iState = null;
        switch (furnaceUpgrade.target){
            case VANILLA -> {
                iState = Blocks.FURNACE.defaultBlockState();
            }
            case COPPER -> {
                iState = ModBlocks.COPPER_FURNACE.get().defaultBlockState();
            }
            case IRON -> {
                iState = ModBlocks.IRON_FURNACE.get().defaultBlockState();
            }
            case SILVER -> {
                iState = ModBlocks.SILVER_FURNACE.get().defaultBlockState();
            }
            case GOLD -> {
                iState = ModBlocks.GOLD_FURNACE.get().defaultBlockState();
            }
            case DIAMOND -> {
                iState = ModBlocks.DIAMOND_FURNACE.get().defaultBlockState();
            }
            case EMERALD -> {
                iState = ModBlocks.EMERALD_FURNACE.get().defaultBlockState();
            }
            case OBSIDIAN -> {
                iState = ModBlocks.OBSIDIAN_FURNACE.get().defaultBlockState();
            }
            case NETHERITE -> {
                iState = ModBlocks.NETHERITE_FURNACE.get().defaultBlockState();
            }
        }

        Direction direction = level.getBlockState(blockPos).getValue(BlockStateProperties.HORIZONTAL_FACING);
        iState = iState.setValue(BlockStateProperties.HORIZONTAL_FACING, direction);

        return furnaceUpgrade.target.makeEntity(blockPos, iState);
    }

    private ArrayList<ItemStack> getFurnaceContent(BlockEntity blockEntity){
        ArrayList<ItemStack> furnaceContent = new ArrayList<>();

        if(blockEntity instanceof ModFurnaceEntity entity){

            ItemStack inputItem = entity.itemHandler.getStackInSlot(0);
            ItemStack fuelItem = entity.itemHandler.getStackInSlot(1);
            ItemStack outputItem = entity.itemHandler.getStackInSlot(2);

            furnaceContent.add(inputItem);
            furnaceContent.add(fuelItem);
            furnaceContent.add(outputItem);

        } else if (blockEntity instanceof FurnaceBlockEntity entity) {
            for(int i = 1; i < entity.getContainerSize(); i++){
                ItemStack stack = entity.getItem(i);
                Containers.dropItemStack(Objects.requireNonNull(entity.getLevel()), entity.getBlockPos().getX(), entity.getBlockPos().getY(), entity.getBlockPos().getZ(), stack);
            }
        }
        return furnaceContent;
    }

    private boolean canUpgradeFurnace(Block block){
        boolean canUpgrade = false;
        if(block instanceof FurnaceBlock){
            canUpgrade = furnaceUpgrade.canUpgrade(eFurnaceConfigs.VANILLA);
        }else if (block instanceof ModFurnaceBlock furnaceBlock){
            canUpgrade = furnaceUpgrade.canUpgrade(furnaceBlock.getFurnaceConfig());
        }
        return canUpgrade;
    }
}
