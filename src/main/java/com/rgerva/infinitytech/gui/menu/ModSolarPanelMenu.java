/**
 * Class: ModSolarPanelMenu
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.gui.menu;

import com.rgerva.infinitytech.blockentity.custom.solar_panel.ModSolarPanelEntity;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.base.ModEnergyContainerScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ModSolarPanelMenu extends AbstractContainerMenu implements ModEnergyContainerScreen.IModMenu {
    public final ModSolarPanelEntity blockEntity;
    private final Level level;

    public ModSolarPanelMenu(int id, Inventory inv, FriendlyByteBuf buffer){
        this(id, inv, inv.player.level().getBlockEntity(buffer.readBlockPos()));
    }

    public ModSolarPanelMenu(int containerId, Inventory inventory, BlockEntity blockEntity) {
        super(ModGUI.SOLAR_MENU.get(), containerId);
        this.blockEntity = (ModSolarPanelEntity) blockEntity;
        this.level = inventory.player.level();

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(inventory, playerInvCol + playerInvRow * 9 + 9, 8 + playerInvCol * 18, 84 + playerInvRow * 18));
            }
        }

        for (int hotHarSlot = 0; hotHarSlot < 9; hotHarSlot++) {
            this.addSlot(new Slot(inventory, hotHarSlot, 8 + hotHarSlot * 18, 142));
        }
    }

    @Override
    public int getEnergy() {
        return blockEntity.getEnergy();
    }

    @Override
    public int getCapacity() {
        return blockEntity.getCapacity();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
