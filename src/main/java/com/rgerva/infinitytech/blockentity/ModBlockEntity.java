package com.rgerva.infinitytech.blockentity;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.block.custom.SolarPanelBlock;
import com.rgerva.infinitytech.blockentity.custom.SolarPanelBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>>BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, InfinityTech.MOD_ID);

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_ENTITY =
            createSolarPanelBlockEntity("solar_panel", ModBlocks.SOLAR_PANEL);

    private static Supplier<BlockEntityType<SolarPanelBlockEntity>> createSolarPanelBlockEntity(String name, Supplier<SolarPanelBlock> blockSupplier) {
        return BLOCK_ENTITIES.register(name, () -> new BlockEntityType<>((blockPos, state) -> new SolarPanelBlockEntity(ModBlockEntity.SOLAR_PANEL_ENTITY.get() ,blockPos, state,
                blockSupplier.get().getCapacity(), blockSupplier.get().getPeakFePerTick()), blockSupplier.get()));
    }


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
