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
import com.rgerva.infinitytech.blockentity.custom.cables.ModCableEntity;
import com.rgerva.infinitytech.blockentity.custom.chest.*;
import com.rgerva.infinitytech.blockentity.custom.furnace.ModFurnaceEntity;
import com.rgerva.infinitytech.blockentity.custom.generator.ModCoalGeneratorEntity;
import com.rgerva.infinitytech.blockentity.custom.solar_panel.ModSolarPanelEntity;
import com.rgerva.infinitytech.util.types.*;
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

    public static final Supplier<BlockEntityType<ModCableEntity>> TIN_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_tin",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModCableEntity(blockPos, blockState,
                    eCablesConfigs.TIN)), ModBlocks.TIN_CABLE.get()));

    public static final Supplier<BlockEntityType<ModCableEntity>> COPPER_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_copper",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModCableEntity(blockPos, blockState,
                    eCablesConfigs.COPPER)), ModBlocks.COPPER_CABLE.get()));

    public static final Supplier<BlockEntityType<ModCableEntity>> GOLD_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_gold",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModCableEntity(blockPos, blockState,
                    eCablesConfigs.GOLD)), ModBlocks.GOLD_CABLE.get()));
    //endregion

    //region CHEST

    public static final Supplier<BlockEntityType<ModChestEntity>> CHEST_IRON_ENTITY = BLOCK_ENTITIES.register("chest_iron",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModChestEntity(blockPos, blockState,
                    eChestConfigs.IRON)), ModBlocks.CHEST_IRON.get()));

    public static final Supplier<BlockEntityType<ModChestEntity>> CHEST_COPPER_ENTITY = BLOCK_ENTITIES.register("chest_copper",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModChestEntity(blockPos, blockState,
                    eChestConfigs.COPPER)), ModBlocks.CHEST_COPPER.get()));

    public static final Supplier<BlockEntityType<ModChestEntity>> CHEST_GOLD_ENTITY = BLOCK_ENTITIES.register("chest_gold",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModChestEntity(blockPos, blockState,
                    eChestConfigs.GOLD)), ModBlocks.CHEST_GOLD.get()));

    public static final Supplier<BlockEntityType<ModChestEntity>> CHEST_DIAMOND_ENTITY = BLOCK_ENTITIES.register("chest_diamond",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModChestEntity(blockPos, blockState,
                    eChestConfigs.DIAMOND)), ModBlocks.CHEST_DIAMOND.get()));

    public static final Supplier<BlockEntityType<ModChestEntity>> CHEST_OBSIDIAN_ENTITY = BLOCK_ENTITIES.register("chest_obsidian",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModChestEntity(blockPos, blockState,
                    eChestConfigs.OBSIDIAN)), ModBlocks.CHEST_OBSIDIAN.get()));

    public static final Supplier<BlockEntityType<ModChestEntity>> CHEST_NETHERITE_ENTITY = BLOCK_ENTITIES.register("chest_netherite",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModChestEntity(blockPos, blockState,
                    eChestConfigs.NETHERITE)), ModBlocks.CHEST_NETHERITE.get()));

    //endregion

    //region GENERATOR
    public static final Supplier<BlockEntityType<ModCoalGeneratorEntity>> COAL_GENERATOR_ENTITY = BLOCK_ENTITIES.register("generic_generator",
            () -> new BlockEntityType<>(ModCoalGeneratorEntity::new, ModBlocks.COAL_GENERATOR.get()));

    //endregion

    //region FURNACE

    public static final Supplier<BlockEntityType<ModFurnaceEntity>> COPPER_FURNACE_ENTITY = BLOCK_ENTITIES.register("copper_furnace",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModFurnaceEntity(blockPos, blockState,
                    eFurnaceConfigs.COPPER)), ModBlocks.COPPER_FURNACE.get()));

    public static final Supplier<BlockEntityType<ModFurnaceEntity>> IRON_FURNACE_ENTITY = BLOCK_ENTITIES.register("iron_furnace",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModFurnaceEntity(blockPos, blockState,
                    eFurnaceConfigs.IRON)), ModBlocks.IRON_FURNACE.get()));

    public static final Supplier<BlockEntityType<ModFurnaceEntity>> SILVER_FURNACE_ENTITY = BLOCK_ENTITIES.register("silver_furnace",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModFurnaceEntity(blockPos, blockState,
                    eFurnaceConfigs.SILVER)), ModBlocks.SILVER_FURNACE.get()));

    public static final Supplier<BlockEntityType<ModFurnaceEntity>> GOLD_FURNACE_ENTITY = BLOCK_ENTITIES.register("gold_furnace",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModFurnaceEntity(blockPos, blockState,
                    eFurnaceConfigs.GOLD)), ModBlocks.GOLD_FURNACE.get()));

    public static final Supplier<BlockEntityType<ModFurnaceEntity>> DIAMOND_FURNACE_ENTITY = BLOCK_ENTITIES.register("diamond_furnace",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModFurnaceEntity(blockPos, blockState,
                    eFurnaceConfigs.DIAMOND)), ModBlocks.DIAMOND_FURNACE.get()));

    public static final Supplier<BlockEntityType<ModFurnaceEntity>> EMERALD_FURNACE_ENTITY = BLOCK_ENTITIES.register("emerald_furnace",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModFurnaceEntity(blockPos, blockState,
                    eFurnaceConfigs.EMERALD)), ModBlocks.EMERALD_FURNACE.get()));

    public static final Supplier<BlockEntityType<ModFurnaceEntity>> OBSIDIAN_FURNACE_ENTITY = BLOCK_ENTITIES.register("obsidian_furnace",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModFurnaceEntity(blockPos, blockState,
                    eFurnaceConfigs.OBSIDIAN)), ModBlocks.OBSIDIAN_FURNACE.get()));

    public static final Supplier<BlockEntityType<ModFurnaceEntity>> NETHERITE_FURNACE_ENTITY = BLOCK_ENTITIES.register("netherite_furnace",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new ModFurnaceEntity(blockPos, blockState,
                    eFurnaceConfigs.NETHERITE)), ModBlocks.NETHERITE_FURNACE.get()));

    //endregion

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
