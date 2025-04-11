package com.rgerva.infinitytech;

import com.mojang.logging.LogUtils;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.capabilities.ModCapabilities;
import com.rgerva.infinitytech.config.ModConfiguration;
import com.rgerva.infinitytech.crative.ModCreativeTab;
import com.rgerva.infinitytech.gui.ModGUI;
import com.rgerva.infinitytech.item.ModItems;
import com.rgerva.infinitytech.network.ModMessages;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
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

}
