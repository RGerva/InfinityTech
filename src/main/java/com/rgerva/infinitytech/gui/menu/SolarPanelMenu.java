package com.rgerva.infinitytech.gui.menu;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.block.custom.SolarPanelBlock;
import com.rgerva.infinitytech.blockentity.custom.SolarPanelBlockEntity;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.base.EnergyStorageMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SolarPanelMenu extends EnergyStorageMenu<SolarPanelBlockEntity> {

    public SolarPanelMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
        this(id, inv, inv.player.level().getBlockEntity(buffer.readBlockPos()));
    }

    public SolarPanelMenu(int id, Inventory inv, BlockEntity blockEntity) {
        super(ModGUI.SOLAR_PENEL_MENU.get(), id, inv, blockEntity, SolarPanelBlock.getBlockFromPanelConfigs(((SolarPanelBlockEntity)blockEntity).geteSolarPanelConfigs()));
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if(sourceSlot == null || !sourceSlot.hasItem())
            return ItemStack.EMPTY;

        ItemStack sourceItem = sourceSlot.getItem();
        ItemStack sourceItemCopy = sourceItem.copy();

        if(index < 4 * 9) {
            //Player inventory slot -> Merge into upgrade module inventory
            if(!moveItemStackTo(sourceItem, 4 * 9, 4 * 9 + 2, false)) {
                return ItemStack.EMPTY;
            }
        }else if(index < 4 * 9 + 2) {
            //Tile inventory and upgrade module slot -> Merge into player inventory
            if(!moveItemStackTo(sourceItem, 0, 4 * 9, false)) {
                return ItemStack.EMPTY;
            }
        }else {
            throw new IllegalArgumentException("Invalid slot index");
        }

        if(sourceItem.getCount() == 0)
            sourceSlot.set(ItemStack.EMPTY);
        else
            sourceSlot.setChanged();

        sourceSlot.onTake(player, sourceItem);

        return sourceItemCopy;
    }
}
