/**
 * Class: CoalGeneratorScreen
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.gui.screen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.gui.ModEnergyContainerScreen;
import com.rgerva.infinitytech.gui.menu.ModCoalGeneratorMenu;
import com.rgerva.infinitytech.util.types.eBarSideConfigs;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class ModCoalGeneratorScreen extends ModEnergyContainerScreen<ModCoalGeneratorMenu> {
    public ModCoalGeneratorScreen(ModCoalGeneratorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "textures/gui/container/generic_generator.png"), eBarSideConfigs.RIGHT);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTick, mouseX, mouseY);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        if (this.menu.isBurning()) {
            int l = Mth.ceil(this.menu.getFuelProgress() * 13.0F) + 1;
            guiGraphics.blitSprite(RenderType::guiTextured,
                    ResourceLocation.fromNamespaceAndPath("minecraft", "container/furnace/lit_progress"),
                    14,
                    14,
                    0,
                    14 - l,
                    x + 80,
                    y + 18 + 14 - l,
                    14,
                    l);
        }
    }
}
