package com.rgerva.infinitytech.blockentity;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.baterry.BatteryBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.cables.CableBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.baterry.CreativeBatteryBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.chest.CopperChestBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.solar_panel.SolarPanelBlockEntity;
import com.rgerva.infinitytech.blockentity.custom.chest.IronChestBlockEntity;
import com.rgerva.infinitytech.util.ModUtils;
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

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_ENTITY_1 = BLOCK_ENTITIES.register("solar_panel_1",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new SolarPanelBlockEntity(blockPos, blockState,
                    ModUtils.eSolarPanelConfigs.solar_panel_1)), ModBlocks.SOLAR_PANEL_1.get()));

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_ENTITY_2 = BLOCK_ENTITIES.register("solar_panel_2",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new SolarPanelBlockEntity(blockPos, blockState,
                    ModUtils.eSolarPanelConfigs.solar_panel_2)), ModBlocks.SOLAR_PANEL_2.get()));

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_ENTITY_3 = BLOCK_ENTITIES.register("solar_panel_3",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new SolarPanelBlockEntity(blockPos, blockState,
                    ModUtils.eSolarPanelConfigs.solar_panel_3)), ModBlocks.SOLAR_PANEL_3.get()));

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_ENTITY_4 = BLOCK_ENTITIES.register("solar_panel_4",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new SolarPanelBlockEntity(blockPos, blockState,
                    ModUtils.eSolarPanelConfigs.solar_panel_4)), ModBlocks.SOLAR_PANEL_4.get()));

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_ENTITY_5 = BLOCK_ENTITIES.register("solar_panel_5",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new SolarPanelBlockEntity(blockPos, blockState,
                    ModUtils.eSolarPanelConfigs.solar_panel_5)), ModBlocks.SOLAR_PANEL_5.get()));

    public static final Supplier<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL_ENTITY_6 = BLOCK_ENTITIES.register("solar_panel_6",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new SolarPanelBlockEntity(blockPos, blockState,
                    ModUtils.eSolarPanelConfigs.solar_panel_6)), ModBlocks.SOLAR_PANEL_6.get()));

    public static final Supplier<BlockEntityType<CableBlockEntity>> TIN_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_tin",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new CableBlockEntity(blockPos, blockState,
                    ModUtils.eCablesConfigs.TIN)), ModBlocks.TIN_CABLE.get()));

    public static final Supplier<BlockEntityType<CableBlockEntity>> COPPER_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_copper",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new CableBlockEntity(blockPos, blockState,
                    ModUtils.eCablesConfigs.COPPER)), ModBlocks.COPPER_CABLE.get()));

    public static final Supplier<BlockEntityType<CableBlockEntity>> GOLD_CABLE_ENTITY = BLOCK_ENTITIES.register("cable_gold",
            () -> new BlockEntityType<>(((blockPos, blockState) -> new CableBlockEntity(blockPos, blockState,
                    ModUtils.eCablesConfigs.GOLD)), ModBlocks.GOLD_CABLE.get()));

    public static final Supplier<BlockEntityType<IronChestBlockEntity>> CHEST_IRON_ENTITY = BLOCK_ENTITIES.register("chest_iron",
            () -> new BlockEntityType<>(IronChestBlockEntity::new, ModBlocks.CHEST_IRON.get()));

    public static final Supplier<BlockEntityType<CopperChestBlockEntity>> CHEST_COPPER_ENTITY = BLOCK_ENTITIES.register("chest_copper",
            () -> new BlockEntityType<>(CopperChestBlockEntity::new, ModBlocks.CHEST_COPPER.get()));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
