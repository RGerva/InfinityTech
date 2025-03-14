package com.rgerva.infinitytech.worldgen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.util.ModUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.ArrayList;
import java.util.List;

public class ModPlacedFeatures {

    public static List<ResourceKey<PlacedFeature>> ORE_PLACED_KEY = new ArrayList<>();

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {

        registerAllKey();

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        for (int x = 0; x < ModUtils.getOres().size(); x++) {
            register(context, ORE_PLACED_KEY.get(x), configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_KEY.get(x)),
                    ModOrePlacement.commonOrePlacement(ModUtils.ModProperties.getProperties(ModUtils.getOres().get(x)).count(),
                            HeightRangePlacement.triangle(VerticalAnchor.absolute(ModUtils.ModProperties.getProperties(ModUtils.getOres().get(x)).minY()),
                                    VerticalAnchor.absolute(ModUtils.ModProperties.getProperties(ModUtils.getOres().get(x)).maxY()))));
        }

    }

    private static void registerAllKey() {
        for (int x = 0; x < ModUtils.getOres().size(); x++) {
            ORE_PLACED_KEY.add(registerKey(BuiltInRegistries.ITEM.getKey(ModUtils.getOres().get(x).asItem()).getPath().concat("_placed")));
        }
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
