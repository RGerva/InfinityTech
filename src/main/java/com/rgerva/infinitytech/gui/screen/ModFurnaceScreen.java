/**
 * Class: ModFurnaceScreen
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.gui.screen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.gui.menu.ModFurnaceMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModFurnaceScreen extends AbstractContainerScreen<ModFurnaceMenu> {
    protected final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "textures/gui/container/generic_furnace.png");

    public ModFurnaceScreen(ModFurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(RenderType::guiTextured,
                TEXTURE,
                x,
                y,
                0,
                0,
                this.imageWidth,
                this.imageHeight,
                256,
                256);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        List<Component> components = new ArrayList<>(3);
        components.add(Component.translatable("tooltip.infinity_tech.furnace_input.info"));
        components.add(Component.translatable("tooltip.infinity_tech.furnace_fuel.info"));
        components.add(Component.translatable("tooltip.infinity_tech.furnace_output.info"));

        guiGraphics.renderTooltip(font, components, Optional.empty(), mouseX, mouseY);
    }
}
