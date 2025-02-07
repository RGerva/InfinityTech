package com.rgerva.infinitytech;

import com.mojang.logging.LogUtils;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.ModBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.SolarPanelBlockEntity;
import com.rgerva.infinitytech.crative.ModCreativeTab;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.gui.screen.SolarPanelScreen;
import com.rgerva.infinitytech.item.ModItems;
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

@Mod(InfinityTech.MOD_ID)
public class InfinityTech {
    public static final String MOD_ID = "infinity_tech";
    private static final Logger LOGGER = LogUtils.getLogger();

    public InfinityTech(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);

        ModCreativeTab.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntity.register(modEventBus);
        ModGUI.register(modEventBus);

        modEventBus.addListener(ModCreativeTab::addCreative);
        modEventBus.addListener(this::registerCapabilities);
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
            event.register(ModGUI.SOLAR_PANEL_MENU.get(), SolarPanelScreen::new);
        }
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event){
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntity.SOLAR_PANEL_ENTITY.get(), SolarPanelBlockEntity::getEnergyStorageCapability);
    }


}
