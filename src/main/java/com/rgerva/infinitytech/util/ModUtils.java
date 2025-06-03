package com.rgerva.infinitytech.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rgerva.infinitytech.block.ModBlocks.*;

public class ModUtils {

    public static class ModProperties {
        public record ModBlockVeinProperties(int veinSize, int minY, int maxY, int count) {
        }

        private static final Map<Block, ModBlockVeinProperties> blockPropertiesMap = new HashMap<>();

        public static void addProperties(Block block, int veinSize, int minY, int maxY, int count) {
            ModBlockVeinProperties properties = new ModBlockVeinProperties(veinSize, minY, maxY, count);
            blockPropertiesMap.put(block, properties);
        }

        public static ModBlockVeinProperties getProperties(Block block) {
            return blockPropertiesMap.get(block);
        }

        public static boolean hasProperties(Block block) {
            return blockPropertiesMap.containsKey(block);
        }
    }

    public static void setOresProperties() {
        ModProperties.addProperties(ALUMINUM_ORE.get(), 24, -64, 320, 4);
        ModProperties.addProperties(LEAD_ORE.get(), 24, -64, 56, 3);
        ModProperties.addProperties(NICKEL_ORE.get(), 24, -64, 56, 3);
        ModProperties.addProperties(PLATINUM_ORE.get(), 24, -64, 36, 1);
        ModProperties.addProperties(SILVER_ORE.get(), 24, -64, 56, 3);
        ModProperties.addProperties(TIN_ORE.get(), 24, -64, 196, 4);
        ModProperties.addProperties(TITANIUM_ORE.get(), 24, -64, 56, 1);
        ModProperties.addProperties(URANIUM_ORE.get(), 24, -64, 16, 2);
        ModProperties.addProperties(ZINC_ORE.get(), 24, -64, 126, 3);
    }

    public static List<Block> getOres() {
        return List.of(
                ALUMINUM_ORE.get(),
                LEAD_ORE.get(),
                NICKEL_ORE.get(),
                PLATINUM_ORE.get(),
                SILVER_ORE.get(),
                TIN_ORE.get(),
                TITANIUM_ORE.get(),
                URANIUM_ORE.get(),
                ZINC_ORE.get());
    }

    public static List<Block> getDeepslateOre() {
        return List.of(
                ALUMINUM_DEEPSLATE_ORE.get(),
                LEAD_DEEPSLATE_ORE.get(),
                NICKEL_DEEPSLATE_ORE.get(),
                PLATINUM_DEEPSLATE_ORE.get(),
                SILVER_DEEPSLATE_ORE.get(),
                TIN_DEEPSLATE_ORE.get(),
                TITANIUM_DEEPSLATE_ORE.get(),
                URANIUM_DEEPSLATE_ORE.get(),
                ZINC_DEEPSLATE_ORE.get());
    }

    public static List<Block> getNetherOre() {
        return List.of(
                ALUMINUM_NETHER_ORE.get(),
                LEAD_NETHER_ORE.get(),
                NICKEL_NETHER_ORE.get(),
                PLATINUM_NETHER_ORE.get(),
                SILVER_NETHER_ORE.get(),
                TIN_NETHER_ORE.get(),
                TITANIUM_NETHER_ORE.get(),
                URANIUM_NETHER_ORE.get(),
                ZINC_NETHER_ORE.get());
    }

    public static List<Block> getEndOre() {
        return List.of(
                ALUMINUM_END_ORE.get(),
                LEAD_END_ORE.get(),
                NICKEL_END_ORE.get(),
                PLATINUM_END_ORE.get(),
                SILVER_END_ORE.get(),
                TIN_END_ORE.get(),
                TITANIUM_END_ORE.get(),
                URANIUM_END_ORE.get(),
                ZINC_END_ORE.get());
    }

    public static VoxelShape combine(VoxelShape... shapes){
        if (shapes.length <= 0) {
            return Shapes.empty();
        }

        VoxelShape combined = shapes[0];

        for (int i = 1; i < shapes.length; i++) {
            combined = Shapes.or(combined, shapes[i]);
        }

        return combined;
    }

    public record Pair<K, V>(K key, V value) {}

    public record Triple<V1, V2, V3>(V1 value1, V2 value2, V3 value3) {}
}
