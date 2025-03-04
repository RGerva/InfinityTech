package com.rgerva.infinitytech.blockentity.custom.chest;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class IronChestBlockEntity extends ChestBlockEntity{
    public IronChestBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.IRON_CHEST_ENTITY.get(), pos, blockState,eChestConfigs.IRON);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return super.createMenu(i, inventory);
    }
}
