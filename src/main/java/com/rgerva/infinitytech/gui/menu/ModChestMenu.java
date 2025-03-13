package com.rgerva.infinitytech.gui.menu;

import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ModChestMenu extends AbstractContainerMenu {
    private final Container container;
    private static eChestConfigs eChestConf;

    public static ModChestMenu createNetheriteContainer(int containerId, Inventory plaInventory){
        return new ModChestMenu(ModGUI.CHEST_NETHERITE_MENU.get(), containerId, plaInventory, new SimpleContainer(eChestConfigs.NETHERITE.size), eChestConfigs.NETHERITE);
    }

    public static ModChestMenu createObsidianContainer(int containerId, Inventory plaInventory){
        return new ModChestMenu(ModGUI.CHEST_OBSIDIAN_MENU.get(), containerId, plaInventory, new SimpleContainer(eChestConfigs.OBSIDIAN.size), eChestConfigs.OBSIDIAN);
    }

    public static ModChestMenu createDiamondContainer(int containerId, Inventory plaInventory){
        return new ModChestMenu(ModGUI.CHEST_DIAMOND_MENU.get(), containerId, plaInventory, new SimpleContainer(eChestConfigs.DIAMOND.size), eChestConfigs.DIAMOND);
    }

    public static ModChestMenu createGoldContainer(int containerId, Inventory plaInventory){
        return new ModChestMenu(ModGUI.CHEST_GOLD_MENU.get(), containerId, plaInventory, new SimpleContainer(eChestConfigs.GOLD.size), eChestConfigs.GOLD);
    }

    public static ModChestMenu createCopperContainer(int containerId, Inventory plaInventory){
        return new ModChestMenu(ModGUI.CHEST_COPPER_MENU.get(), containerId, plaInventory, new SimpleContainer(eChestConfigs.COPPER.size), eChestConfigs.COPPER);
    }

    public static ModChestMenu createIronContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModGUI.CHEST_IRON_MENU.get(), containerId, playerInventory, new SimpleContainer(eChestConfigs.IRON.size), eChestConfigs.IRON);
    }

    
    public ModChestMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory, Container container, eChestConfigs eChestConfigs) {
        super(menuType, containerId);
        this.container = container;
        eChestConf = eChestConfigs;

        container.startOpen(playerInventory.player);
        for (int chestRow = 0; chestRow < eChestConfigs.getRowCount(); chestRow++) {
            for (int chestCol = 0; chestCol < eChestConfigs.rowLength; chestCol++) {
                this.addSlot(new Slot(container, chestCol + chestRow * eChestConfigs.rowLength, 12 + chestCol * 18, 18 + chestRow * 18));
            }
        }

        int leftCol = (eChestConfigs.xSize - 162) / 2 + 1;
        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, eChestConfigs.ySize - (4 - playerInvRow) * 18 - 10));
            }
        }

        for (int hotHarSlot = 0; hotHarSlot < 9; hotHarSlot++) {
            this.addSlot(new Slot(playerInventory, hotHarSlot, leftCol + hotHarSlot * 18, eChestConfigs.ySize - 24));
        }

    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < this.eChestConf.size) {
                if (!this.moveItemStackTo(itemstack1, this.eChestConf.size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.eChestConf.size, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            }else{
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public Container getContainer() {
        return this.container;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }

    public static eChestConfigs getChestConfig() {
        return eChestConf;
    }
}
