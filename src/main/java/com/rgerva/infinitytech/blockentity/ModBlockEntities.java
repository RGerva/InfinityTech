package com.rgerva.infinitytech.blockentity;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.BatteryBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.CreativeBatteryBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.SolarPanelBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>>BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, InfinityTech.MOD_ID);

    public static final Supplier<BlockEntityType<CreativeBatteryBlockEntity>> CREATIVE_BATTERY_ENTITY = BLOCK_ENTITIES.register("creative_battery_box",
            () -> new BlockEntityType<>(CreativeBatteryBlockEntity::new, ModBlocks.CREATIVE_BATTERY.get()));

    public static final Supplier<BlockEntityType<BatteryBlockEntity>> BATTERY_BOX_ENTITY = BLOCK_ENTITIES.register("battery_box",
            () -> new BlockEntityType<>(BatteryBlockEntity::new, ModBlocks.BATTERY_BLOCK.get()));

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL = BLOCK_ENTITIES.register("solar_penal",
            () -> new BlockEntityType<>(SolarPanelBlockEntity::new, ModBlocks.SOLAR_PANEL.get()));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
