package com.rgerva.infinitytech.gui.screen;

import com.rgerva.infinitytech.gui.base.EnergyStorageContainerScreen;
import com.rgerva.infinitytech.gui.menu.BatteryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BatteryScreen extends EnergyStorageContainerScreen<BatteryMenu> {

    public BatteryScreen(BatteryMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        energyMeterX = 80;
    }
}
