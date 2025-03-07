package com.rgerva.infinitytech.gui.menu;

import com.rgerva.infinitytech.blockentity.custom.chest.IronChestBlockEntity;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class ModChestMenu extends AbstractContainerMenu {
    private final Container container;
    private final eChestConfigs eChestConf;

    public static ModChestMenu createIronContainer(int containerId, Inventory playerInventory) {
        return new ModChestMenu(ModGUI.IRON_CHEST_MENU.get(), containerId, playerInventory, new SimpleContainer(eChestConfigs.IRON.size), eChestConfigs.IRON);
    }

    public static ModChestMenu createIronContainer(int containerId, Inventory playerInventory, Container inventory) {
        return new ModChestMenu(ModGUI.IRON_CHEST_MENU.get(), containerId, playerInventory, inventory, eChestConfigs.IRON);
    }

    public ModChestMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory, Container container, eChestConfigs eChestConfigs) {
        super(menuType, containerId);
        this.container = container;
        this.eChestConf = eChestConfigs;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public Container getContainer() {
        return this.container;
    }

}
