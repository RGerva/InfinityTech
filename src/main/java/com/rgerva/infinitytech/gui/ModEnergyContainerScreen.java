/**
 * Class: ModEnergyContainerScreen
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.energy.ModEnergyUtils;
import com.rgerva.infinitytech.util.types.eBarSideConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModEnergyContainerScreen<T extends AbstractContainerMenu & ModEnergyContainerScreen.IModMenu> extends AbstractContainerScreen<T> {
    protected final ResourceLocation TEXTURE;

    protected int energyMeterX;
    protected int energyMeterY;
    protected int energyMeterWidth = 16;
    protected int energyMeterHeight = 52;

    protected int energyMeterU = 176;
    protected int energyMeterV = 0;

    protected final String energyIndicatorBarTooltipComponentID;

    /**
     * @comment Texture is generic constant
     */
    public ModEnergyContainerScreen(T menu, Inventory playerInventory, Component title) {
        this(menu, playerInventory, title, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "textures/gui/container/generic_energy.png"), null, eBarSideConfigs.CENTER);
    }

    /**
     * @param texture needs texture ResourceLocation
     * @comment energyIndicatorBarTooltipComponentID is null
     */
    public ModEnergyContainerScreen(T menu, Inventory playerInventory, Component title, ResourceLocation texture, eBarSideConfigs configs) {
        this(menu, playerInventory, title, texture, null, configs);
    }

    /**
     * @comment MainContainerScreen
     */
    public ModEnergyContainerScreen(T menu, Inventory playerInventory, Component title, ResourceLocation texture, String energyIndicatorBarTooltipComponentID, eBarSideConfigs configs) {
        super(menu, playerInventory, title);
        this.TEXTURE = texture;
        this.energyIndicatorBarTooltipComponentID = energyIndicatorBarTooltipComponentID;
        this.energyMeterX = configs.getEnergyMeterX();
        this.energyMeterY = configs.getEnergyMeterY();
    }

    protected void renderEnergyBar(GuiGraphics guiGraphics, int x, int y) {
        int pos = menu.getScaledEnergyMeterPos(energyMeterHeight);
        guiGraphics.blit(RenderType::guiTextured, TEXTURE, x + energyMeterX, y + energyMeterY + energyMeterHeight - pos, energyMeterU,
                energyMeterV + energyMeterHeight - pos, energyMeterWidth, pos, 256, 256);
    }

    protected void renderEnergyIndicatorBar(GuiGraphics guiGraphics, int x, int y) {
        int pos = menu.getScaledEnergyIndicatorBarPos(energyMeterHeight);
        if (pos > 0)
            guiGraphics.blit(RenderType::guiTextured, TEXTURE, x + energyMeterX, y + energyMeterY + energyMeterHeight - pos, energyMeterU,
                    energyMeterV + energyMeterHeight, energyMeterWidth, 1, 256, 256);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(CoreShaders.POSITION_TEX);
        RenderSystem.setShaderColor(1.f, 1.f, 1.f, 1.f);

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

        renderEnergyBar(guiGraphics, x, y);
        renderEnergyIndicatorBar(guiGraphics, x, y);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        if (isHovering(energyMeterX, energyMeterY, energyMeterWidth, energyMeterHeight, mouseX, mouseY)) {
            List<Component> components = new ArrayList<>(2);
            components.add(Component.translatable("tooltip.infinity_tech.energy_meter",
                    ModEnergyUtils.getEnergyWithPrefix(menu.getEnergy()), ModEnergyUtils.getEnergyWithPrefix(menu.getCapacity())));
            if (menu.getEnergyIndicatorBarValue() > 0 && energyIndicatorBarTooltipComponentID != null) {
                components.add(Component.translatable(energyIndicatorBarTooltipComponentID,
                        ModEnergyUtils.getEnergyWithPrefix(menu.getEnergyIndicatorBarValue())).withStyle(ChatFormatting.YELLOW));
            }

            guiGraphics.renderTooltip(font, components, Optional.empty(), mouseX, mouseY);
        }
    }

    public interface IModMenu {
        int getEnergy();

        int getCapacity();

        default int getScaledEnergyMeterPos(int energyMeterHeight) {
            int energy = getEnergy();
            int capacity = getCapacity();

            return (energy == 0 || capacity == 0) ? 0 : Math.max(1, energy * energyMeterHeight / capacity);
        }

        default int getEnergyIndicatorBarValue() {
            return 0;
        }

        default int getScaledEnergyIndicatorBarPos(int energyMeterHeight) {
            return 0;
        }
    }

}
