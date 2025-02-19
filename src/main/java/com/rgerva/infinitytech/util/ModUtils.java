package com.rgerva.infinitytech.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.List;

import static com.rgerva.infinitytech.block.ModBlocks.*;
import static com.rgerva.infinitytech.block.ModBlocks.TITANIUM_END_ORE;

public class ModUtils {
    public static List<Block> getOres(){
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

    public static List<Block> getDeepslateOre(){
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

    public static List<Block> getNetherOre(){
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

    public static List<Block> getEndOre(){
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
}
