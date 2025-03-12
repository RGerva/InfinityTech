package com.rgerva.infinitytech.gui.menu;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.baterry.BatteryBlockEntity;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.base.EnergyStorageMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BatteryMenu extends EnergyStorageMenu<BatteryBlockEntity> {
    public BatteryMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
        this(id, inv, inv.player.level().getBlockEntity(buffer.readBlockPos()));
    }

    public BatteryMenu(int id, Inventory inv, BlockEntity blockEntity) {
        super(ModGUI.BATTERY_BOX_MENU.get(), id, inv, blockEntity, ModBlocks.BATTERY_BLOCK.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
