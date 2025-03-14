package com.rgerva.infinitytech;

import com.mojang.logging.LogUtils;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.chest.model.ModChestModel;
import com.rgerva.infinitytech.blockentity.custom.chest.renderer.ModChestRenderer;
import com.rgerva.infinitytech.capabilities.ModCapabilities;
import com.rgerva.infinitytech.config.ModConfiguration;
import com.rgerva.infinitytech.crative.ModCreativeTab;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.screen.BatteryScreen;
import com.rgerva.infinitytech.gui.screen.ChestScreen;
import com.rgerva.infinitytech.gui.screen.SolarPanelScreen;
import com.rgerva.infinitytech.item.ModItems;
import com.rgerva.infinitytech.network.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(InfinityTech.MOD_ID)
public class InfinityTech {
    public static final String MOD_ID = "infinity_tech";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InfinityTech(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.debug("START REGISTERING >> {}", InfinityTech.MOD_ID);

        NeoForge.EVENT_BUS.register(this);

        ModCreativeTab.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModGUI.register(modEventBus);

        modEventBus.addListener(ModCreativeTab::addCreative);
        modEventBus.addListener(ModCapabilities::registerCapabilities);
        modEventBus.addListener(ModMessages::register);
        modContainer.registerConfig(ModConfig.Type.COMMON, ModConfiguration.CONFIG);

        LOGGER.debug("END REGISTERING >> {}", InfinityTech.MOD_ID);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
            event.register(ModGUI.BATTERY_BOX_MENU.get(), BatteryScreen::new);
            event.register(ModGUI.SOLAR_PENEL_MENU.get(), SolarPanelScreen::new);
            event.register(ModGUI.CHEST_IRON_MENU.get(), ChestScreen::new);
            event.register(ModGUI.CHEST_COPPER_MENU.get(), ChestScreen::new);
            event.register(ModGUI.CHEST_GOLD_MENU.get(), ChestScreen::new);
            event.register(ModGUI.CHEST_DIAMOND_MENU.get(), ChestScreen::new);
            event.register(ModGUI.CHEST_OBSIDIAN_MENU.get(), ChestScreen::new);
            event.register(ModGUI.CHEST_NETHERITE_MENU.get(), ChestScreen::new);
        }

        @SubscribeEvent
        public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.CHEST_IRON_ENTITY.get(), ModChestRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.CHEST_COPPER_ENTITY.get(), ModChestRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.CHEST_GOLD_ENTITY.get(), ModChestRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.CHEST_DIAMOND_ENTITY.get(), ModChestRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.CHEST_OBSIDIAN_ENTITY.get(), ModChestRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.CHEST_NETHERITE_ENTITY.get(), ModChestRenderer::new);
        }

        @SubscribeEvent
        public static void onRegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "iron_chest"), "main"), ModChestModel::createLayerDefinition);
        }
    }
}
