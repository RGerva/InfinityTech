/**
 * Class: ModChestUpgrade
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.item.custom.chest;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.custom.chest.ModChestBlock;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;

public class ChestUpgradeItem extends Item {

    private final eChestConfigs.eChestUpgrade chestUpgrade;

    public ChestUpgradeItem(eChestConfigs.eChestUpgrade eChestUpgrade, Properties properties) {
        super(properties);
        this.chestUpgrade = eChestUpgrade;
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        Level level = context.getLevel();
        ItemStack itemStack = context.getItemInHand();
        Block block = level.getBlockState(blockPos).getBlock();
        boolean canUpgrade = false;

        if(block instanceof ChestBlock){ //vanilla
            canUpgrade = chestUpgrade.canUpgrade(eChestConfigs.WOOD);
        }

        if(block instanceof ModChestBlock chestBlock){
            canUpgrade = chestUpgrade.canUpgrade(chestBlock.getChestConfigs());
        }

        InfinityTech.LOGGER.info("canUpgrade? {}", canUpgrade);

        return InteractionResult.SUCCESS;
    }

}
