package com.rgerva.infinitytech.gui.screen;

import com.rgerva.infinitytech.gui.base.EnergyStorageContainerScreen;
import com.rgerva.infinitytech.gui.menu.SolarPanelMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SolarPanelScreen extends EnergyStorageContainerScreen<SolarPanelMenu> {
    public SolarPanelScreen(SolarPanelMenu menu, Inventory inventory, Component titleComponent) {
        super(menu, inventory, titleComponent);
        energyMeterX = 80;
    }
}
