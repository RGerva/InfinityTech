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

public class NetheriteChestBlockEntity extends ModChestBlockEntity{
    public NetheriteChestBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CHEST_NETHERITE_ENTITY.get(), pos, blockState, eChestConfigs.NETHERITE);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ModChestMenu(ModGUI.CHEST_NETHERITE_MENU.get(), i, inventory, this, eChestConfigs.NETHERITE);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.infinity_tech.chest_netherite");
    }
}
