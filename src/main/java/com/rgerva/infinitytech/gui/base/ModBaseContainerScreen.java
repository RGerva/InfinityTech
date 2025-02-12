package com.rgerva.infinitytech.gui.base;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ModBaseContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    public ModBaseContainerScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }
}
