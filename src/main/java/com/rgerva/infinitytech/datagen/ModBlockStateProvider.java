/**
 * Class: ModBlockModelAndStateGeneratorProvider
 * Created by: D56V1OK
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.datagen;

import com.ibm.icu.text.MessagePatternUtil;
import com.rgerva.infinitytech.block.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModBlockStateProvider {

    static BlockModelGenerators blockModelGenerator;
    static Consumer<BlockStateGenerator> blockStateOutput;
    static BiConsumer<ResourceLocation, ModelInstance> modelOutput;

    protected static void run(BlockModelGenerators blockModelGenerators) {

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

        //OK

        blockWithItem(ModBlocks.CREATIVE_BATTERY);
        blockWithItem(ModBlocks.BATTERY_BLOCK);
        blockWithItem(ModBlocks.INFINITY_BATTERY);
        blockWithItem(ModBlocks.DUMP_BATTERY);

        blockWithItem(ModBlocks.SOLAR_PANEL_1);
        blockWithItem(ModBlocks.SOLAR_PANEL_2);
        blockWithItem(ModBlocks.SOLAR_PANEL_3);
        blockWithItem(ModBlocks.SOLAR_PANEL_4);
        blockWithItem(ModBlocks.SOLAR_PANEL_5);
        blockWithItem(ModBlocks.SOLAR_PANEL_6);

        blockWithItem(ModBlocks.TIN_CABLE);
        blockWithItem(ModBlocks.COPPER_CABLE);
        blockWithItem(ModBlocks.GOLD_CABLE);

        blockWithItem(ModBlocks.CHEST_IRON);
        blockWithItem(ModBlocks.CHEST_COPPER);
        blockWithItem(ModBlocks.CHEST_GOLD);
        blockWithItem(ModBlocks.CHEST_DIAMOND);
        blockWithItem(ModBlocks.CHEST_OBSIDIAN);
        blockWithItem(ModBlocks.CHEST_NETHERITE);

        blockWithItem(ModBlocks.COAL_GENERATOR);

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

    private static void horizontalBlockWithItem(Block block, boolean uniqueBottomTexture) {
        ResourceLocation modelLocation = TexturedModel.ORIENTABLE_ONLY_TOP.get((Block) block)
                .updateTextures(textureMapping -> {
                    textureMapping.put(TextureSlot.TOP, TextureMapping.getBlockTexture((Block)block, "_top"));
                    textureMapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture((Block)block, "_side"));
                    textureMapping.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture((Block)block, "_botton"));
                })
                .create((Block) block, modelOutput);

        blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block, Variant.variant().with(VariantProperties.MODEL, modelLocation))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    private ResourceLocation getBlockParticleTexture(Holder<? extends Block> block) {
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
