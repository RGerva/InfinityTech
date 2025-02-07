package com.rgerva.infinitytech.gui.menu;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.SolarPanelBlockEntity;
import com.rgerva.infinitytech.energy.IModEnergyStorage;
import com.rgerva.infinitytech.energy.base.ModEnergyStorageBlockEntity;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.base.IModEnergyStorageMenu;
import com.rgerva.infinitytech.gui.base.ModEnergyStorageMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SolarPanelMenu extends ModEnergyStorageMenu<SolarPanelBlockEntity> {

    public SolarPanelMenu(int id, Inventory inv, FriendlyByteBuf buffer){
        this(id, inv, inv.player.level().getBlockEntity(buffer.readBlockPos()));

    }

    public SolarPanelMenu(int id, Inventory inv, BlockEntity blockEntity) {
        super(ModGUI.SOLAR_PANEL_MENU.get(), id, inv, blockEntity, ModBlocks.SOLAR_PANEL.get());

    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
