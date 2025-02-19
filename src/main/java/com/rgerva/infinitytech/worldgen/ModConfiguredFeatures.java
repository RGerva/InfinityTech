package com.rgerva.infinitytech.worldgen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.util.ModUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class ModConfiguredFeatures {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        if(ModUtils.getOres().size() != ModUtils.getDeepslateOre().size()){
            InfinityTech.LOGGER.error("Ores and Deepslate Ores are different");
            return;
        }

        for(int x = 0; x < ModUtils.getOres().size(); x++){
            List<OreConfiguration.TargetBlockState> overworldOres = List.of(
                    OreConfiguration.target(stoneReplaceables, ModUtils.getOres().get(x).defaultBlockState()),
                    OreConfiguration.target(deepslateReplaceables, ModUtils.getDeepslateOre().get(x).defaultBlockState())
            );
            register(context, registerKey(BuiltInRegistries.ITEM.getKey(ModUtils.getOres().get(x).asItem()).getPath()), Feature.ORE, new OreConfiguration(overworldOres, 9));
        }

        for(int x = 0; x < ModUtils.getNetherOre().size(); x++){

            List<OreConfiguration.TargetBlockState> netherOres = List.of(
                    OreConfiguration.target(netherrackReplaceables, ModUtils.getNetherOre().get(x).defaultBlockState())
            );
            register(context, registerKey(BuiltInRegistries.ITEM.getKey(ModUtils.getNetherOre().get(x).asItem()).getPath()), Feature.ORE, new OreConfiguration(netherOres, 9));
        }

        for(int x = 0; x < ModUtils.getEndOre().size(); x++){

            List<OreConfiguration.TargetBlockState> endOres = List.of(
                    OreConfiguration.target(endReplaceables, ModUtils.getEndOre().get(x).defaultBlockState())
            );
            register(context, registerKey(BuiltInRegistries.ITEM.getKey(ModUtils.getEndOre().get(x).asItem()).getPath()), Feature.ORE, new OreConfiguration(endOres, 9));
        }


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
