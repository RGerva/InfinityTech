package com.rgerva.infinitytech.worldgen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.util.ModUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.ArrayList;
import java.util.List;

public class ModConfiguredFeatures {

    public static List<ResourceKey<ConfiguredFeature<?,?>>> ORE_KEY = new ArrayList<>();

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        ModUtils.setOresProperties();
        registerAllKey();

        RuleTest stoneReplacebles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        for(int x = 0; x < ModUtils.getOres().size(); x++){
            List<OreConfiguration.TargetBlockState> Ores = List.of(
                    OreConfiguration.target(stoneReplacebles, ModUtils.getOres().get(x).defaultBlockState()),
                    OreConfiguration.target(deepslateReplaceables, ModUtils.getDeepslateOre().get(x).defaultBlockState()),
                    OreConfiguration.target(netherrackReplaceables, ModUtils.getNetherOre().get(x).defaultBlockState()),
                    OreConfiguration.target(endReplaceables, ModUtils.getEndOre().get(x).defaultBlockState()));

            register(context, ORE_KEY.get(x), Feature.ORE, new OreConfiguration(Ores,
                    ModUtils.ModProperties.getProperties(ModUtils.getOres().get(x)).veinSize()));

        }

    }

    private static void registerAllKey(){
        for(int x = 0; x < ModUtils.getOres().size(); x++){
            ORE_KEY.add(registerKey(BuiltInRegistries.ITEM.getKey(ModUtils.getOres().get(x).asItem()).getPath()));
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
