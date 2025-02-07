package com.rgerva.infinitytech.gui.screen;

import com.rgerva.infinitytech.gui.base.ModEnergyStorageContainerScreen;
import com.rgerva.infinitytech.gui.menu.SolarPanelMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SolarPanelScreen extends ModEnergyStorageContainerScreen<SolarPanelMenu> {
    public SolarPanelScreen(SolarPanelMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }
}
