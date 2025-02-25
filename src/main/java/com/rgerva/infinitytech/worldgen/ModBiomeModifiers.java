package com.rgerva.infinitytech.worldgen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.util.ModUtils;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModBiomeModifiers {

    public static List<ResourceKey<BiomeModifier>> OVERWORLD_ADD_ORE = new ArrayList<>();
    public static List<ResourceKey<BiomeModifier>> NETHER_ADD_ORE = new ArrayList<>();
    public static List<ResourceKey<BiomeModifier>> END_ADD_ORE = new ArrayList<>();

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {

        registerAllKey();

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        for(int x = 0; x < ModUtils.getOres().size(); x++){
            context.register(OVERWORLD_ADD_ORE.get(x), new BiomeModifiers.AddFeaturesBiomeModifier(
               biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
               HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_PLACED_KEY.get(x))),
               GenerationStep.Decoration.UNDERGROUND_ORES
            ));
        }

        for(int x = 0; x < ModUtils.getOres().size(); x++){
            context.register(NETHER_ADD_ORE.get(x), new BiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(BiomeTags.IS_NETHER),
                    HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_PLACED_KEY.get(x))),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ));
        }

        for(int x = 0; x < ModUtils.getOres().size(); x++){
            context.register(END_ADD_ORE.get(x), new BiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(BiomeTags.IS_END),
                    HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_PLACED_KEY.get(x))),
                    GenerationStep.Decoration.UNDERGROUND_ORES
            ));
        }

    }

    private static void registerAllKey(){
        for(int x = 0; x < ModUtils.getOres().size(); x++){
            OVERWORLD_ADD_ORE.add(registerKey("add_".concat(BuiltInRegistries.ITEM.getKey(ModUtils.getOres().get(x).asItem()).getPath())));
        }

        for(int x = 0; x < ModUtils.getOres().size(); x++){
            NETHER_ADD_ORE.add(registerKey("add_".concat(BuiltInRegistries.ITEM.getKey(ModUtils.getNetherOre().get(x).asItem()).getPath())));
        }

        for(int x = 0; x < ModUtils.getOres().size(); x++){
            END_ADD_ORE.add(registerKey("add_".concat(BuiltInRegistries.ITEM.getKey(ModUtils.getEndOre().get(x).asItem()).getPath())));
        }
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name));
    }
}
