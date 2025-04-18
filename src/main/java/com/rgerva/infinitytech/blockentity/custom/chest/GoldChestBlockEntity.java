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

public class GoldChestBlockEntity extends ModChestBlockEntity {
    public GoldChestBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CHEST_GOLD_ENTITY.get(), pos, blockState, eChestConfigs.GOLD);
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new ModChestMenu(ModGUI.CHEST_GOLD_MENU.get(), i, inventory, this, eChestConfigs.GOLD);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.infinity_tech.chest_gold");
    }
}
