/**
 * Class: ModBatteryScreen
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.gui.screen;

import com.rgerva.infinitytech.gui.ModEnergyContainerScreen;
import com.rgerva.infinitytech.gui.menu.ModBatteryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ModBatteryScreen extends ModEnergyContainerScreen<ModBatteryMenu> {

    public ModBatteryScreen(ModBatteryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        energyMeterX = 80;
    }
}
