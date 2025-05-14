/**
 * Class: ModBlocks
 * Created by: DRIB934
 * On: 2024/dec.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.block;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.custom.battery.ModBatteryBlock;
import com.rgerva.infinitytech.block.custom.cables.ModCableBlock;
import com.rgerva.infinitytech.block.custom.chest.ModChestBlock;
import com.rgerva.infinitytech.block.custom.furnace.ModFurnaceBlock;
import com.rgerva.infinitytech.block.custom.generator.ModCoalGeneratorBlock;
import com.rgerva.infinitytech.block.custom.solar_panel.ModSolarPanelBlock;
import com.rgerva.infinitytech.item.ModItems;
import com.rgerva.infinitytech.util.types.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(InfinityTech.MOD_ID);


    //region ORES

    //TITANIUM
    public static final DeferredBlock<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_ore")))
                            .strength(3f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TITANIUM_DEEPSLATE_ORE = registerBlock("titanium_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_deepslate_ore")))
                            .strength(3f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> TITANIUM_END_ORE = registerBlock("titanium_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_end_ore")))
                            .strength(3.5f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.GILDED_BLACKSTONE)));

    public static final DeferredBlock<Block> TITANIUM_NETHER_ORE = registerBlock("titanium_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    //LEAD
    public static final DeferredBlock<Block> LEAD_ORE = registerBlock("lead_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LEAD_DEEPSLATE_ORE = registerBlock("lead_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_deepslate_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> LEAD_NETHER_ORE = registerBlock("lead_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    public static final DeferredBlock<Block> LEAD_END_ORE = registerBlock("lead_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_end_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)));

    //ALUMINIUM
    public static final DeferredBlock<Block> ALUMINUM_ORE = registerBlock("aluminum_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ALUMINUM_DEEPSLATE_ORE = registerBlock("aluminum_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_deepslate_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> ALUMINUM_NETHER_ORE = registerBlock("aluminum_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    public static final DeferredBlock<Block> ALUMINUM_END_ORE = registerBlock("aluminum_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_end_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)));

    //NICKEL
    public static final DeferredBlock<Block> NICKEL_ORE = registerBlock("nickel_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> NICKEL_DEEPSLATE_ORE = registerBlock("nickel_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_deepslate_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> NICKEL_NETHER_ORE = registerBlock("nickel_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    public static final DeferredBlock<Block> NICKEL_END_ORE = registerBlock("nickel_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_end_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)));
    //PLATINUM
    public static final DeferredBlock<Block> PLATINUM_ORE = registerBlock("platinum_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> PLATINUM_DEEPSLATE_ORE = registerBlock("platinum_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_deepslate_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> PLATINUM_NETHER_ORE = registerBlock("platinum_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    public static final DeferredBlock<Block> PLATINUM_END_ORE = registerBlock("platinum_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_end_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)));

    //SILVER
    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> SILVER_DEEPSLATE_ORE = registerBlock("silver_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_deepslate_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> SILVER_NETHER_ORE = registerBlock("silver_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    public static final DeferredBlock<Block> SILVER_END_ORE = registerBlock("silver_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_end_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)));

    //TIN
    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TIN_DEEPSLATE_ORE = registerBlock("tin_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_deepslate_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> TIN_NETHER_ORE = registerBlock("tin_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    public static final DeferredBlock<Block> TIN_END_ORE = registerBlock("tin_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_end_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)));

    //URANIUM
    public static final DeferredBlock<Block> URANIUM_ORE = registerBlock("uranium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> URANIUM_DEEPSLATE_ORE = registerBlock("uranium_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_deepslate_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> URANIUM_NETHER_ORE = registerBlock("uranium_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    public static final DeferredBlock<Block> URANIUM_END_ORE = registerBlock("uranium_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_end_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)));

    //ZINC
    public static final DeferredBlock<Block> ZINC_ORE = registerBlock("zinc_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> ZINC_DEEPSLATE_ORE = registerBlock("zinc_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_deepslate_ore")))
                            .strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> ZINC_NETHER_ORE = registerBlock("zinc_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_nether_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_ORE)));

    public static final DeferredBlock<Block> ZINC_END_ORE = registerBlock("zinc_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_end_ore")))
                            .strength(3.5F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DRIPSTONE_BLOCK)));
    //endregion

    //region BLOCKS

    public static final DeferredBlock<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_block")))
                    .strength(7.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> TITANIUM_RAW_BLOCK = registerBlock("titanium_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "steel_block")))
                    .strength(6.5F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> LEAD_BLOCK = registerBlock("lead_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_block")))
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> LEAD_RAW_BLOCK = registerBlock("lead_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> ALUMINUM_BLOCK = registerBlock("aluminum_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_block")))
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> ALUMINUM_RAW_BLOCK = registerBlock("aluminum_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> NICKEL_BLOCK = registerBlock("nickel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_block")))
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> NICKEL_RAW_BLOCK = registerBlock("nickel_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> PLATINUM_BLOCK = registerBlock("platinum_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_block")))
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> PLATINUM_RAW_BLOCK = registerBlock("platinum_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> SILVER_BLOCK = registerBlock("silver_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_block")))
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> SILVER_RAW_BLOCK = registerBlock("silver_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("tin_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_block")))
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> TIN_RAW_BLOCK = registerBlock("tin_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> URANIUM_BLOCK = registerBlock("uranium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_block")))
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> URANIUM_RAW_BLOCK = registerBlock("uranium_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> ZINC_BLOCK = registerBlock("zinc_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_block")))
                    .strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> ZINC_RAW_BLOCK = registerBlock("zinc_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));
    //endregion

    //region BATTERY

    public static final DeferredBlock<Block> INFINITY_BATTERY = registerBlock("battery_infinity",
            () -> new ModBatteryBlock(eBatteryConfigs.INFINITY, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "battery_infinity")))
                    .strength(-1F, Integer.MAX_VALUE)
                    .noLootTable()));

    public static final DeferredBlock<Block> DUMP_BATTERY = registerBlock("battery_dump",
            () -> new ModBatteryBlock(eBatteryConfigs.DUMP, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "battery_dump")))
                    .strength(-1F, Integer.MAX_VALUE)
                    .noLootTable()));

    public static final DeferredBlock<Block> BATTERY_LVL_1 = registerBlock("battery_lvl_1",
            () -> new ModBatteryBlock(eBatteryConfigs.BATTERY_LVL_1, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "battery_lvl_1")))
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)));

    //endregion

    //region SOLAR PANEL

    public static final DeferredBlock<Block> SOLAR_PANEL_1 = registerBlock("solar_panel_1",
            () -> new ModSolarPanelBlock(eSolarPanelConfigs.solar_panel_1, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "solar_panel_1")))
                    .strength(4.0F, 5.0F)
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> SOLAR_PANEL_2 = registerBlock("solar_panel_2",
            () -> new ModSolarPanelBlock(eSolarPanelConfigs.solar_panel_2, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "solar_panel_2")))
                    .strength(4.0F, 5.0F)
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> SOLAR_PANEL_3 = registerBlock("solar_panel_3",
            () -> new ModSolarPanelBlock(eSolarPanelConfigs.solar_panel_3, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "solar_panel_3")))
                    .strength(4.0F, 5.0F)
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> SOLAR_PANEL_4 = registerBlock("solar_panel_4",
            () -> new ModSolarPanelBlock(eSolarPanelConfigs.solar_panel_4, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "solar_panel_4")))
                    .strength(4.0F, 5.0F)
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> SOLAR_PANEL_5 = registerBlock("solar_panel_5",
            () -> new ModSolarPanelBlock(eSolarPanelConfigs.solar_panel_5, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "solar_panel_5")))
                    .strength(4.0F, 5.0F)
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> SOLAR_PANEL_6 = registerBlock("solar_panel_6",
            () -> new ModSolarPanelBlock(eSolarPanelConfigs.solar_panel_6, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "solar_panel_6")))
                    .strength(4.0F, 5.0F)
                    .sound(SoundType.METAL)));

    //endregion

    //region CABLES

    public static final DeferredBlock<Block> TIN_CABLE = registerBlock("cable_tin",
            () -> new ModCableBlock(eCablesConfigs.TIN, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "cable_tin")))
                    .strength(5.0F)
                    .sound(SoundType.WOOL)
                    .mapColor(MapColor.COLOR_GRAY)));

    public static final DeferredBlock<Block> COPPER_CABLE = registerBlock("cable_copper",
            () -> new ModCableBlock(eCablesConfigs.COPPER, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "cable_copper")))
                    .strength(5.0F)
                    .sound(SoundType.WOOL)
                    .mapColor(MapColor.COLOR_GRAY)));

    public static final DeferredBlock<Block> GOLD_CABLE = registerBlock("cable_gold",
            () -> new ModCableBlock(eCablesConfigs.GOLD, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "cable_gold")))
                    .strength(5.0F)
                    .sound(SoundType.WOOL)
                    .mapColor(MapColor.COLOR_GRAY)));
    //endregion

    //region CHEST

    public static final DeferredBlock<Block> CHEST_COPPER = registerBlock("chest_copper",
            () -> new ModChestBlock(eChestConfigs.COPPER, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_copper")))
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.METAL)));

    public static final DeferredBlock<Block> CHEST_IRON = registerBlock("chest_iron",
            () -> new ModChestBlock(eChestConfigs.IRON, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_iron")))
                    .strength(5.0F, 6.0F)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.METAL)));

    public static final DeferredBlock<Block> CHEST_GOLD = registerBlock("chest_gold",
            () -> new ModChestBlock(eChestConfigs.GOLD, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_gold")))
                    .strength(3.0F, 6.0F)
                    .instrument(NoteBlockInstrument.BELL)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.GOLD)));

    public static final DeferredBlock<Block> CHEST_DIAMOND = registerBlock("chest_diamond",
            () -> new ModChestBlock(eChestConfigs.DIAMOND, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_diamond")))
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.DIAMOND)));

    public static final DeferredBlock<Block> CHEST_OBSIDIAN = registerBlock("chest_obsidian",
            () -> new ModChestBlock(eChestConfigs.OBSIDIAN, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_obsidian")))
                    .strength(22.5F, 1200.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.COLOR_BLACK)));

    public static final DeferredBlock<Block> CHEST_NETHERITE = registerBlock("chest_netherite",
            () -> new ModChestBlock(eChestConfigs.NETHERITE, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_netherite")))
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .strength(22.5F, 1200.0F)
                    .mapColor(MapColor.COLOR_BLACK)));
    //endregion

    //region GENERATOR

    public static final DeferredBlock<Block> COAL_GENERATOR = registerBlock("coal_generator",
            () -> new ModCoalGeneratorBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "coal_generator")))
                    .strength(3F)
                    .requiresCorrectToolForDrops()));
    //endregion

    //region FURNACES

    public static final DeferredBlock<Block> COPPER_FURNACE = registerBlock("furnace_copper",
            () -> new ModFurnaceBlock(eFurnaceConfigs.COPPER, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_copper")))
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> IRON_FURNACE = registerBlock("furnace_iron",
            () -> new ModFurnaceBlock(eFurnaceConfigs.IRON, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_iron")))
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SILVER_FURNACE = registerBlock("furnace_silver",
            () -> new ModFurnaceBlock(eFurnaceConfigs.SILVER, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_silver")))
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GOLD_FURNACE = registerBlock("furnace_gold",
            () -> new ModFurnaceBlock(eFurnaceConfigs.GOLD, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_gold")))
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.GOLD)
                    .instrument(NoteBlockInstrument.BELL)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> DIAMOND_FURNACE = registerBlock("furnace_diamond",
            () -> new ModFurnaceBlock(eFurnaceConfigs.DIAMOND, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_diamond")))
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.DIAMOND)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> EMERALD_FURNACE = registerBlock("furnace_emerald",
            () -> new ModFurnaceBlock(eFurnaceConfigs.EMERALD, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_emerald")))
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.EMERALD)
                    .instrument(NoteBlockInstrument.BIT)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> OBSIDIAN_FURNACE = registerBlock("furnace_obsidian",
            () -> new ModFurnaceBlock(eFurnaceConfigs.OBSIDIAN, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_obsidian")))
                    .strength(50.0F, 1200.0F)
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NETHERITE_FURNACE = registerBlock("furnace_netherite",
            () -> new ModFurnaceBlock(eFurnaceConfigs.NETHERITE, BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_netherite")))
                    .strength(50.0F, 1200.0F)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .mapColor(MapColor.COLOR_BLACK)
                    .requiresCorrectToolForDrops()));

    //endregion

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().useBlockDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name)))));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
