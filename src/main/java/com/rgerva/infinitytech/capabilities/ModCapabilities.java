package com.rgerva.infinitytech.capabilities;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.battery.ModBatteryEntity;
import com.rgerva.infinitytech.blockentity.custom.cables.ModCableEntity;
import com.rgerva.infinitytech.blockentity.custom.chest.ModChestEntity;
import com.rgerva.infinitytech.blockentity.custom.generator.ModCoalGeneratorEntity;
import com.rgerva.infinitytech.blockentity.custom.solar_panel.ModSolarPanelEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

public class ModCapabilities {

    public static void registerCapabilities(final RegisterCapabilitiesEvent event) {

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.INFINITY_BATTERY_ENTITY.get(), ModBatteryEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.DUMP_BATTERY_ENTITY.get(), ModBatteryEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.BATTERY_LVL_1_ENTITY.get(), ModBatteryEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_1.get(), ModSolarPanelEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_2.get(), ModSolarPanelEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_3.get(), ModSolarPanelEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_4.get(), ModSolarPanelEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_5.get(), ModSolarPanelEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.SOLAR_PANEL_ENTITY_6.get(), ModSolarPanelEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.TIN_CABLE_ENTITY.get(), ModCableEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.COPPER_CABLE_ENTITY.get(), ModCableEntity::getEnergyStorageCapability);

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.GOLD_CABLE_ENTITY.get(), ModCableEntity::getEnergyStorageCapability);

        event.registerBlock(Capabilities.ItemHandler.BLOCK, (level, blockPos, blockState, blockEntity, direction) ->
                        level.getBlockEntity(blockPos) instanceof ModChestEntity chestBlockEntity ? new InvWrapper(chestBlockEntity) : null,
                ModBlocks.CHEST_IRON.get(), ModBlocks.CHEST_COPPER.get(),
                ModBlocks.CHEST_GOLD.get(), ModBlocks.CHEST_DIAMOND.get(),
                ModBlocks.CHEST_OBSIDIAN.get(), ModBlocks.CHEST_NETHERITE.get());

        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK,
                ModBlockEntities.COAL_GENERATOR_ENTITY.get(), ModCoalGeneratorEntity::getEnergyStorageCapability);

    }
}
