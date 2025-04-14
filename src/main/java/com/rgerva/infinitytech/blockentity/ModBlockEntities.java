/**
 * Class: ModBlockEntities
 * Created by: DRIB934
 * On: 2024/dec.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.battery.ModBatteryEntity;
import com.rgerva.infinitytech.blockentity.custom.cables.CableBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.chest.*;
import com.rgerva.infinitytech.blockentity.custom.generator.CoalGeneratorBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.solar_panel.ModSolarPanelEntity;
import com.rgerva.infinitytech.util.types.eBatteryConfigs;
import com.rgerva.infinitytech.util.types.eCablesConfigs;
import com.rgerva.infinitytech.util.types.eSolarPanelConfigs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, InfinityTech.MOD_ID);

    //region BATTERY

    public static final Supplier<BlockEntityType<ModBatteryEntity>> INFINITY_BATTERY_ENTITY = BLOCK_ENTITIES.register("battery_infinity",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModBatteryEntity(blockPos, blockState,
                    eBatteryConfigs.INFINITY)), ModBlocks.INFINITY_BATTERY.get()));

    public static final Supplier<BlockEntityType<ModBatteryEntity>> DUMP_BATTERY_ENTITY = BLOCK_ENTITIES.register("battery_dump",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModBatteryEntity(blockPos, blockState,
                    eBatteryConfigs.DUMP)), ModBlocks.DUMP_BATTERY.get()));

    public static final Supplier<BlockEntityType<ModBatteryEntity>> BATTERY_LVL_1_ENTITY = BLOCK_ENTITIES.register("battery_lvl_1",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModBatteryEntity(blockPos, blockState,
                    eBatteryConfigs.BATTERY_LVL_1)), ModBlocks.BATTERY_LVL_1.get()));

    //endregion

    //region SOLAR PANEL

    public static final Supplier<BlockEntityType<ModSolarPanelEntity>> SOLAR_PANEL_ENTITY_1 = BLOCK_ENTITIES.register("solar_panel_1",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModSolarPanelEntity(blockPos, blockState,
                    eSolarPanelConfigs.solar_panel_1)), ModBlocks.SOLAR_PANEL_1.get()));

    public static final Supplier<BlockEntityType<ModSolarPanelEntity>> SOLAR_PANEL_ENTITY_2 = BLOCK_ENTITIES.register("solar_panel_2",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModSolarPanelEntity(blockPos, blockState,
                    eSolarPanelConfigs.solar_panel_2)), ModBlocks.SOLAR_PANEL_2.get()));

    public static final Supplier<BlockEntityType<ModSolarPanelEntity>> SOLAR_PANEL_ENTITY_3 = BLOCK_ENTITIES.register("solar_panel_3",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModSolarPanelEntity(blockPos, blockState,
                    eSolarPanelConfigs.solar_panel_3)), ModBlocks.SOLAR_PANEL_3.get()));

    public static final Supplier<BlockEntityType<ModSolarPanelEntity>> SOLAR_PANEL_ENTITY_4 = BLOCK_ENTITIES.register("solar_panel_4",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModSolarPanelEntity(blockPos, blockState,
                    eSolarPanelConfigs.solar_panel_4)), ModBlocks.SOLAR_PANEL_4.get()));

    public static final Supplier<BlockEntityType<ModSolarPanelEntity>> SOLAR_PANEL_ENTITY_5 = BLOCK_ENTITIES.register("solar_panel_5",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModSolarPanelEntity(blockPos, blockState,
                    eSolarPanelConfigs.solar_panel_5)), ModBlocks.SOLAR_PANEL_5.get()));

    public static final Supplier<BlockEntityType<ModSolarPanelEntity>> SOLAR_PANEL_ENTITY_6 = BLOCK_ENTITIES.register("solar_panel_6",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModSolarPanelEntity(blockPos, blockState,
                    eSolarPanelConfigs.solar_panel_6)), ModBlocks.SOLAR_PANEL_6.get()));
    //endregion

    //region CABLE

    public static final Supplier<BlockEntityType<CableBlockEntity>> TIN_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_tin",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new CableBlockEntity(blockPos, blockState,
                    eCablesConfigs.TIN)), ModBlocks.TIN_CABLE.get()));

    public static final Supplier<BlockEntityType<CableBlockEntity>> COPPER_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_copper",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new CableBlockEntity(blockPos, blockState,
                    eCablesConfigs.COPPER)), ModBlocks.COPPER_CABLE.get()));

    public static final Supplier<BlockEntityType<CableBlockEntity>> GOLD_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_gold",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new CableBlockEntity(blockPos, blockState,
                    eCablesConfigs.GOLD)), ModBlocks.GOLD_CABLE.get()));
    //endregion

    //region CHEST

    public static final Supplier<BlockEntityType<IronChestBlockEntity>> CHEST_IRON_ENTITY = BLOCK_ENTITIES.register("chest_iron",
            () -> new BlockEntityType<>(IronChestBlockEntity::new, ModBlocks.CHEST_IRON.get()));

    public static final Supplier<BlockEntityType<CopperChestBlockEntity>> CHEST_COPPER_ENTITY = BLOCK_ENTITIES.register("chest_copper",
            () -> new BlockEntityType<>(CopperChestBlockEntity::new, ModBlocks.CHEST_COPPER.get()));

    public static final Supplier<BlockEntityType<GoldChestBlockEntity>> CHEST_GOLD_ENTITY = BLOCK_ENTITIES.register("chest_gold",
            () -> new BlockEntityType<>(GoldChestBlockEntity::new, ModBlocks.CHEST_GOLD.get()));

    public static final Supplier<BlockEntityType<DiamondChestBlockEntity>> CHEST_DIAMOND_ENTITY = BLOCK_ENTITIES.register("chest_diamond",
            () -> new BlockEntityType<>(DiamondChestBlockEntity::new, ModBlocks.CHEST_DIAMOND.get()));

    public static final Supplier<BlockEntityType<ObsidianChestBlockEntity>> CHEST_OBSIDIAN_ENTITY = BLOCK_ENTITIES.register("chest_obsidian",
            () -> new BlockEntityType<>(ObsidianChestBlockEntity::new, ModBlocks.CHEST_OBSIDIAN.get()));

    public static final Supplier<BlockEntityType<NetheriteChestBlockEntity>> CHEST_NETHERITE_ENTITY = BLOCK_ENTITIES.register("chest_netherite",
            () -> new BlockEntityType<>(NetheriteChestBlockEntity::new, ModBlocks.CHEST_NETHERITE.get()));

    public static final Supplier<BlockEntityType<CoalGeneratorBlockEntity>> COAL_GENERATOR_ENTITY = BLOCK_ENTITIES.register("generic_generator",
            () -> new BlockEntityType<>(CoalGeneratorBlockEntity::new, ModBlocks.COAL_GENERATOR.get()));
    //endregion

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
