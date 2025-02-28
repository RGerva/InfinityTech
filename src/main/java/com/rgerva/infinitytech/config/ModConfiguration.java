package com.rgerva.infinitytech.config;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.custom.CableBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

@EventBusSubscriber(modid = InfinityTech.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModConfiguration {

    public static ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue MAGIC_NUM = BUILDER
            .defineInRange("magicNum", 50, 0, Integer.MAX_VALUE);

    public static final ModConfigSpec.EnumValue<CableBlock.EnergyExtractionMode> CABLES_ENERGY_EXTRACTION_MODE = BUILDER
            .defineEnum(List.of("PUSH, PULL, BOTH"), CableBlock.EnergyExtractionMode.BOTH, CableBlock.EnergyExtractionMode.values());

    public static ModConfigSpec CONFIG = BUILDER.build();

    @SubscribeEvent
    public static void onConfigLoad(final ModConfigEvent event){
        InfinityTech.LOGGER.debug("Magic Number: {}", MAGIC_NUM.get());
        InfinityTech.LOGGER.debug("CABLES_ENERGY_EXTRACTION_MODE: {}", CABLES_ENERGY_EXTRACTION_MODE.get());
    }

}
