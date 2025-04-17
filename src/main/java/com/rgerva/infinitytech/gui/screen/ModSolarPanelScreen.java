/**
 * Class: ModSolarPanelScreen
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.gui.screen;

import com.rgerva.infinitytech.gui.ModEnergyContainerScreen;
import com.rgerva.infinitytech.gui.menu.ModSolarPanelMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ModSolarPanelScreen extends ModEnergyContainerScreen<ModSolarPanelMenu> {
    public ModSolarPanelScreen(ModSolarPanelMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        energyMeterX = 80;
    }
}
