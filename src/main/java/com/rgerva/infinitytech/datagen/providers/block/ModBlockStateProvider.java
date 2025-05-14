/**
 * Class: ModBlockModelAndStateGeneratorProvider
 * Created by: D56V1OK
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.datagen.providers.block;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.block.custom.cables.ModCableBlock;
import com.rgerva.infinitytech.blockentity.custom.chest.renderer.special.ModChestSpecialRenderer;
import com.rgerva.infinitytech.datagen.model.ModTexturedModel;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.minecraft.client.data.models.BlockModelGenerators.createBooleanModelDispatch;
import static net.minecraft.client.data.models.BlockModelGenerators.createHorizontalFacingDispatch;

public class ModBlockStateProvider {

    static BlockModelGenerators blockModelGenerator;
    static Consumer<BlockStateGenerator> blockStateOutput;
    static BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    public static void run(BlockModelGenerators blockModelGenerators) {

        blockModelGenerator = blockModelGenerators;
        blockStateOutput = blockModelGenerators.blockStateOutput;
        modelOutput = blockModelGenerators.modelOutput;

        blockWithItem(ModBlocks.TITANIUM_ORE);
        blockWithItem(ModBlocks.TITANIUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.TITANIUM_NETHER_ORE);
        blockWithItem(ModBlocks.TITANIUM_END_ORE);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_RAW_BLOCK);

        blockWithItem(ModBlocks.STEEL_BLOCK);

        blockWithItem(ModBlocks.LEAD_ORE);
        blockWithItem(ModBlocks.LEAD_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.LEAD_NETHER_ORE);
        blockWithItem(ModBlocks.LEAD_END_ORE);
        blockWithItem(ModBlocks.LEAD_BLOCK);
        blockWithItem(ModBlocks.LEAD_RAW_BLOCK);

        blockWithItem(ModBlocks.ALUMINUM_ORE);
        blockWithItem(ModBlocks.ALUMINUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.ALUMINUM_NETHER_ORE);
        blockWithItem(ModBlocks.ALUMINUM_END_ORE);
        blockWithItem(ModBlocks.ALUMINUM_BLOCK);
        blockWithItem(ModBlocks.ALUMINUM_RAW_BLOCK);

        blockWithItem(ModBlocks.NICKEL_ORE);
        blockWithItem(ModBlocks.NICKEL_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.NICKEL_NETHER_ORE);
        blockWithItem(ModBlocks.NICKEL_END_ORE);
        blockWithItem(ModBlocks.NICKEL_BLOCK);
        blockWithItem(ModBlocks.NICKEL_RAW_BLOCK);

        blockWithItem(ModBlocks.PLATINUM_ORE);
        blockWithItem(ModBlocks.PLATINUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.PLATINUM_NETHER_ORE);
        blockWithItem(ModBlocks.PLATINUM_END_ORE);
        blockWithItem(ModBlocks.PLATINUM_BLOCK);
        blockWithItem(ModBlocks.PLATINUM_RAW_BLOCK);

        blockWithItem(ModBlocks.SILVER_ORE);
        blockWithItem(ModBlocks.SILVER_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.SILVER_NETHER_ORE);
        blockWithItem(ModBlocks.SILVER_END_ORE);
        blockWithItem(ModBlocks.SILVER_BLOCK);
        blockWithItem(ModBlocks.SILVER_RAW_BLOCK);

        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.TIN_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.TIN_NETHER_ORE);
        blockWithItem(ModBlocks.TIN_END_ORE);
        blockWithItem(ModBlocks.TIN_BLOCK);
        blockWithItem(ModBlocks.TIN_RAW_BLOCK);

        blockWithItem(ModBlocks.URANIUM_ORE);
        blockWithItem(ModBlocks.URANIUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.URANIUM_NETHER_ORE);
        blockWithItem(ModBlocks.URANIUM_END_ORE);
        blockWithItem(ModBlocks.URANIUM_BLOCK);
        blockWithItem(ModBlocks.URANIUM_RAW_BLOCK);

        blockWithItem(ModBlocks.ZINC_ORE);
        blockWithItem(ModBlocks.ZINC_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.ZINC_NETHER_ORE);
        blockWithItem(ModBlocks.ZINC_END_ORE);
        blockWithItem(ModBlocks.ZINC_BLOCK);
        blockWithItem(ModBlocks.ZINC_RAW_BLOCK);

        blockWithItem(ModBlocks.INFINITY_BATTERY);
        blockWithItem(ModBlocks.DUMP_BATTERY);
        horizontalBlockWithItem(ModBlocks.BATTERY_LVL_1);

        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_1);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_2);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_3);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_4);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_5);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_6);

        cableBlockWithItem(ModBlocks.TIN_CABLE);
        cableBlockWithItem(ModBlocks.COPPER_CABLE);
        cableBlockWithItem(ModBlocks.GOLD_CABLE);

        chestBlockWithItem(ModBlocks.CHEST_IRON);
        chestBlockWithItem(ModBlocks.CHEST_COPPER);
        chestBlockWithItem(ModBlocks.CHEST_GOLD);
        chestBlockWithItem(ModBlocks.CHEST_DIAMOND);
        chestBlockWithItem(ModBlocks.CHEST_OBSIDIAN);
        chestBlockWithItem(ModBlocks.CHEST_NETHERITE);

        blockWithItem(ModBlocks.COAL_GENERATOR);

        furnaceBlockWithItem(ModBlocks.COPPER_FURNACE);
        furnaceBlockWithItem(ModBlocks.IRON_FURNACE);
        furnaceBlockWithItem(ModBlocks.SILVER_FURNACE);
        furnaceBlockWithItem(ModBlocks.GOLD_FURNACE);
        furnaceBlockWithItem(ModBlocks.DIAMOND_FURNACE);
        furnaceBlockWithItem(ModBlocks.EMERALD_FURNACE);
        furnaceBlockWithItem(ModBlocks.OBSIDIAN_FURNACE);
        furnaceBlockWithItem(ModBlocks.NETHERITE_FURNACE);
    }


    /**
     * Create simple block with item
     * @param deferredBlock is block to generate blockState and Model
     */
    private static void blockWithItem(DeferredBlock<?> deferredBlock){
        blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(deferredBlock.get(), getBlockTexture(deferredBlock)));
        simpleBlockModel(deferredBlock);
    }

    /**
     * Create simple Model Block use with function 'blockWithItem'
     * @param deferredBlock is block to generate Model
     */
    protected static void simpleBlockModel(DeferredBlock<?> deferredBlock){
        ResourceLocation resourcelocation =
                ModelTemplates.CUBE_ALL.create(
                        deferredBlock.get(),
                        TextureMapping.cube(getBlockTexture(deferredBlock)),
                        modelOutput);

        BlockModelGenerators.createRotatedVariant(deferredBlock.get(), getBlockTexture(deferredBlock), resourcelocation);
    }

    private static void furnaceBlockWithItem(DeferredBlock<?> deferredBlock){
        Block block = deferredBlock.get();

        ResourceLocation modelLocation = TexturedModel.ORIENTABLE.get(block)
                .updateTextures(textureMapping -> {
                    textureMapping.put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_front"));
                    textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
                    textureMapping.put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_side"));
                    textureMapping.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_side"));
                })
                .create(block, modelOutput);

        ResourceLocation modelLocationOn = TexturedModel.ORIENTABLE.get(block)
                .updateTextures(textureMapping -> {
                    textureMapping.put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_front_on"));
                    textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
                    textureMapping.put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_side"));
                    textureMapping.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_side"));
                })
                .createWithSuffix(block, "_on", modelOutput);

        blockStateOutput.accept(
                        MultiVariantGenerator.multiVariant(block)
                                .with(createBooleanModelDispatch(BlockStateProperties.LIT, modelLocationOn, modelLocation))
                                .with(createHorizontalFacingDispatch())
                );

        BlockModelGenerators.createSimpleBlock(block, modelLocation);
    }

    /**
     * Create horizontal block with item
     * @param deferredBlock is block to generate Model
     */
    private static void horizontalBlockWithItem(DeferredBlock<?> deferredBlock) {
        Block block = deferredBlock.get();

        ResourceLocation modelLocation = TexturedModel.ORIENTABLE.get(block)
                .updateTextures(textureMapping -> {
                    textureMapping.put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"));
                    textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
                    textureMapping.put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_side"));
                    textureMapping.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom"));
                })
                .create(block, modelOutput);

        blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, modelLocation));
        BlockModelGenerators.createSimpleBlock(block, modelLocation);
    }

    /**
     * Create Solar Panel block with item
     * @param block is block to generate Model
     */
    private static void solarPanelBlockWithItem(Holder<Block> block) {

        ResourceLocation solarPanel = ModTexturedModel.SOLAR_PANEL.get(block.value()).create(block.value(), modelOutput);

        blockStateOutput.accept(MultiVariantGenerator.multiVariant(block.value(),
                Variant.variant().with(VariantProperties.MODEL, solarPanel)));

        BlockModelGenerators.createSimpleBlock(block.value(), solarPanel);
    }

    /**
     * Create Cable block with item
     * @param block is block to generate Model
     */
    private static void cableBlockWithItem(Holder<Block> block) {
        ResourceLocation cableCore = ModTexturedModel.CABLE_CORE.get(block.value()).create(block.value(), modelOutput);
        ResourceLocation cableSide = ModTexturedModel.CABLE_SIDE.get(block.value()).createWithSuffix(block.value(), "_side", modelOutput);

        blockStateOutput.accept(
                MultiPartGenerator.multiPart(block.value()).
                        with(Variant.variant().
                                with(VariantProperties.MODEL, cableCore)).
                        with(Condition.condition().term(ModCableBlock.UP, true), Variant.variant().
                                with(VariantProperties.MODEL, cableSide).
                                with(VariantProperties.X_ROT, VariantProperties.Rotation.R270)).
                        with(Condition.condition().term(ModCableBlock.DOWN, true), Variant.variant().
                                with(VariantProperties.MODEL, cableSide).
                                with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)).
                        with(Condition.condition().term(ModCableBlock.NORTH, true), Variant.variant().
                                with(VariantProperties.MODEL, cableSide)).
                        with(Condition.condition().term(ModCableBlock.SOUTH, true), Variant.variant().
                                with(VariantProperties.MODEL, cableSide).
                                with(VariantProperties.X_ROT, VariantProperties.Rotation.R180)).
                        with(Condition.condition().term(ModCableBlock.EAST, true), Variant.variant().
                                with(VariantProperties.MODEL, cableSide).
                                with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).
                        with(Condition.condition().term(ModCableBlock.WEST, true), Variant.variant().
                                with(VariantProperties.MODEL, cableSide).
                                with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
        );

        BlockModelGenerators.createSimpleBlock(block.value(), cableCore);
    }

    /**
     * Create Chest block with item
     * @param deferredBlock is block to generate Model
     */
    public static void chestBlockWithItem(DeferredBlock<?> deferredBlock) {
        ResourceLocation particleTexture = getBlockParticleTexture(deferredBlock);
        blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(
                        deferredBlock.get(),Variant.variant()
                                .with(VariantProperties.MODEL,
                                        ModelTemplates.PARTICLE_ONLY.create(deferredBlock.get(),
                                                TextureMapping.particle(particleTexture),
                                                modelOutput))));

        Item chestItem = deferredBlock.asItem();
        ResourceLocation resourceLocation = ModelTemplates.CHEST_INVENTORY.create(chestItem, TextureMapping.particle(particleTexture), modelOutput);
        ItemModel.Unbaked unbaked = ItemModelUtils.specialModel(resourceLocation, new ModChestSpecialRenderer.Unbaked(getBlockTexture(deferredBlock)));
        blockModelGenerator.itemModelOutput.accept(chestItem, unbaked);
    }

    private static ResourceLocation getBlockParticleTexture(Holder<? extends Block> block) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "particle/" + blockId.getPath());
    }

    private ResourceLocation getBlockParticleTexture(Holder<? extends Block> block, String pathSuffix) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "particle/" + blockId.getPath() + pathSuffix);
    }

    private static ResourceLocation getBlockTexture(Holder<? extends Block> block) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "block/" + blockId.getPath());
    }

    private ResourceLocation getBlockTexture(Holder<? extends Block> block, String pathSuffix) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "block/" + blockId.getPath() + pathSuffix);
    }
}
