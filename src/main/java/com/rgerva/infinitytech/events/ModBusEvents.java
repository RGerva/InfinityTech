/**
 * Class: ModBusEvents
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.events;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.chest.model.ModChestModel;
import com.rgerva.infinitytech.blockentity.custom.chest.renderer.ModChestRenderer;
import com.rgerva.infinitytech.blockentity.custom.chest.renderer.special.ModChestSpecialRenderer;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.screen.ChestScreen;
import com.rgerva.infinitytech.gui.screen.CoalGeneratorScreen;
import com.rgerva.infinitytech.gui.screen.ModBatteryScreen;
import com.rgerva.infinitytech.gui.screen.SolarPanelScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

@EventBusSubscriber(modid = InfinityTech.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup(FMLClientSetupEvent event) {
        InfinityTech.LOGGER.info("HELLO FROM CLIENT SETUP");
        InfinityTech.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModGUI.BATTERY_MENU.get(), ModBatteryScreen::new);

        event.register(ModGUI.SOLAR_PANEL_MENU.get(), SolarPanelScreen::new);
        event.register(ModGUI.CHEST_IRON_MENU.get(), ChestScreen::new);
        event.register(ModGUI.CHEST_COPPER_MENU.get(), ChestScreen::new);
        event.register(ModGUI.CHEST_GOLD_MENU.get(), ChestScreen::new);
        event.register(ModGUI.CHEST_DIAMOND_MENU.get(), ChestScreen::new);
        event.register(ModGUI.CHEST_OBSIDIAN_MENU.get(), ChestScreen::new);
        event.register(ModGUI.CHEST_NETHERITE_MENU.get(), ChestScreen::new);
        event.register(ModGUI.COAL_GENERATOR_MENU.get(), CoalGeneratorScreen::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.CHEST_IRON_ENTITY.get(), ModChestRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHEST_COPPER_ENTITY.get(), ModChestRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHEST_GOLD_ENTITY.get(), ModChestRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHEST_DIAMOND_ENTITY.get(), ModChestRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHEST_OBSIDIAN_ENTITY.get(), ModChestRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHEST_NETHERITE_ENTITY.get(), ModChestRenderer::new);
    }

    public static final ModelLayerLocation CHEST_LAYER_LOC = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest"), "main");

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CHEST_LAYER_LOC, ModChestModel::createLayerDefinition);
    }

    @SubscribeEvent
    public static void registerSpecialRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest"), ModChestSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
