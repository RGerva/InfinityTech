package com.rgerva.infinitytech.gui.screen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.gui.menu.ModChestMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ModChestScreen extends AbstractContainerScreen<ModChestMenu> implements MenuAccess<ModChestMenu> {

    private final int textureXSize;
    private final int textureYSize;

    public ModChestScreen(ModChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        this.imageWidth = ModChestMenu.getChestConfig().xSize;
        this.imageHeight = ModChestMenu.getChestConfig().ySize;
        this.textureXSize = ModChestMenu.getChestConfig().textureXSize;
        this.textureYSize = ModChestMenu.getChestConfig().textureYSize;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, 8, 6, 4210752, false);

        guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 4210752, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(RenderType::guiTextured,
                ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "textures/gui/container/chest_" + ModChestMenu.getChestConfig().getEnumName().toLowerCase() + ".png"),
                x,
                y,
                0,
                0,
                this.imageWidth,
                this.imageHeight,
                this.textureXSize,
                this.textureYSize);
    }

}
