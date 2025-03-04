package com.rgerva.infinitytech;

import com.mojang.logging.LogUtils;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.*;
import com.rgerva.infinitytech.config.ModConfiguration;
import com.rgerva.infinitytech.crative.ModCreativeTab;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.screen.BatteryScreen;
import com.rgerva.infinitytech.gui.screen.SolarPanelScreen;
import com.rgerva.infinitytech.item.ModItems;
import com.rgerva.infinitytech.network.ModMessages;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
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
        modEventBus.addListener(this::registerCapabilities);
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
        public static void onRegisterMenuScreens(RegisterMenuScreensEvent event){
            event.register(ModGUI.BATTERY_BOX_MENU.get(), BatteryScreen::new);
            event.register(ModGUI.SOLAR_PENEL_MENU.get(), SolarPanelScreen::new);
        }
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.CREATIVE_BATTERY_ENTITY.get(), CreativeBatteryBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.BATTERY_BOX_ENTITY.get(), BatteryBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_1.get(), SolarPanelBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_2.get(), SolarPanelBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_3.get(), SolarPanelBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_4.get(), SolarPanelBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_5.get(), SolarPanelBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_6.get(), SolarPanelBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.TIN_CABLE_ENTITY.get(), CableBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.COPPER_CABLE_ENTITY.get(), CableBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.GOLD_CABLE_ENTITY.get(), CableBlockEntity::getEnergyStorageCapability);

    }
}
