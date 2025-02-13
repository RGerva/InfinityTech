package com.rgerva.infinitytech;

import com.mojang.logging.LogUtils;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.BatteryBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.CreativeBatteryBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.SolarPanelBlockEntity;
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
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import static com.rgerva.infinitytech.blockentity.ModBlockEntities.*;

@Mod(InfinityTech.MOD_ID)
public class InfinityTech {
    public static final String MOD_ID = "infinity_tech";
    private static final Logger LOGGER = LogUtils.getLogger();

    public InfinityTech(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);

        ModCreativeTab.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModGUI.register(modEventBus);

        modEventBus.addListener(ModCreativeTab::addCreative);
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(ModMessages::register);
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
                CREATIVE_BATTERY_ENTITY.get(), CreativeBatteryBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                BATTERY_BOX_ENTITY.get(), BatteryBlockEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                SOLAR_PANEL.get(), SolarPanelBlockEntity::getEnergyStorageCapability);
    }
}
