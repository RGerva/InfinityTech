/**
 * Class: ModChestUpgrade
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.item.custom.chest;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.block.custom.chest.ModChestBlock;
import com.rgerva.infinitytech.blockentity.custom.chest.ModChestEntity;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChestUpgradeItem extends Item {

    private final eChestConfigs.eChestUpgrade chestUpgrade;

    public ChestUpgradeItem(eChestConfigs.eChestUpgrade eChestUpgrade, Properties properties) {
        super(properties);
        this.chestUpgrade = eChestUpgrade;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.chest_upgrade.info").
                    withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }else{
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public @NotNull InteractionResult onItemUseFirst(@NotNull ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        Level level = context.getLevel();
        Block block = level.getBlockState(blockPos).getBlock();

        assert player != null;
        if(player.isShiftKeyDown()){
            if(!canUpgradeChest(block)){
                return InteractionResult.PASS;
            }

            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            NonNullList<ItemStack> chestContent = getChestContent(blockEntity);

            if(chestContent.isEmpty()){
                return InteractionResult.PASS;
            }

            ModChestEntity newChest = (ModChestEntity) getNewChest(level, blockPos);

            if(newChest == null){
                return InteractionResult.PASS;
            }

            level.removeBlockEntity(blockPos);
            level.removeBlock(blockPos, false);

            level.setBlock(blockPos, newChest.getBlockState(), 3);
            level.setBlockEntity(newChest);

            level.sendBlockUpdated(blockPos, newChest.getBlockState(), newChest.getBlockState(), 3);

            BlockEntity updatedChest = level.getBlockEntity(blockPos);
            if(updatedChest instanceof ModChestEntity){
                ((ModChestEntity) updatedChest).setItems(chestContent);
            }

            if(!player.getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private BlockEntity getNewChest(Level level, BlockPos blockPos){
        BlockState iState = null;
        switch (chestUpgrade.target){
            case WOOD -> {
                iState = Blocks.CHEST.defaultBlockState();
            }
            case IRON -> {
                iState = ModBlocks.CHEST_IRON.get().defaultBlockState();
            }
            case COPPER -> {
                iState = ModBlocks.CHEST_COPPER.get().defaultBlockState();
            }
            case GOLD -> {
                iState = ModBlocks.CHEST_GOLD.get().defaultBlockState();
            }
            case DIAMOND -> {
                iState = ModBlocks.CHEST_DIAMOND.get().defaultBlockState();
            }
            case OBSIDIAN -> {
                iState = ModBlocks.CHEST_OBSIDIAN.get().defaultBlockState();
            }
            case NETHERITE -> {
                iState = ModBlocks.CHEST_NETHERITE.get().defaultBlockState();
            }
        }

        Direction chestFacing = level.getBlockState(blockPos).getValue(ModChestBlock.FACING);
        iState = iState.setValue(ModChestBlock.FACING, chestFacing);

        return chestUpgrade.target.makeEntity(blockPos, iState);
    }

    private NonNullList<ItemStack> getChestContent(BlockEntity blockEntity){
        NonNullList<ItemStack> chestContent = NonNullList.withSize(0, ItemStack.EMPTY);
        if(blockEntity instanceof ModChestEntity entity){
            if(!entity.getItems().isEmpty()){
                chestContent = entity.getItems();
            }
        }else if(blockEntity instanceof ChestBlockEntity entity){

            for(int i = 0; i < entity.getContainerSize(); i++){
                if(!entity.getItem(i).isEmpty()){
                    chestContent.add(entity.getItem(i));
                }else{
                    chestContent = NonNullList.withSize(entity.getContainerSize(), ItemStack.EMPTY);
                }
            }
        }
        return chestContent;
    }

    private boolean canUpgradeChest(Block block){
        boolean canUpgrade = false;
        if(block instanceof ChestBlock){
            canUpgrade = chestUpgrade.canUpgrade(eChestConfigs.WOOD);
        } else if (block instanceof ModChestBlock chestBlock) {
            canUpgrade = chestUpgrade.canUpgrade(chestBlock.getChestConfigs());
        }
        return canUpgrade;
    }
}
