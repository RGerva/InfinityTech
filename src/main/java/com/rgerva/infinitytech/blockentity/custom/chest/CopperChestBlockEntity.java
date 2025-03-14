package com.rgerva.infinitytech.blockentity.custom.chest;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.menu.ModChestMenu;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class CopperChestBlockEntity extends ModChestBlockEntity {
    public CopperChestBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CHEST_COPPER_ENTITY.get(), pos, blockState, eChestConfigs.COPPER);
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new ModChestMenu(ModGUI.CHEST_COPPER_MENU.get(), containerId, inventory, this, eChestConfigs.COPPER);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.infinity_tech.chest_copper");
    }
}
