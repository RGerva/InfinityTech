package com.rgerva.infinitytech.datagen.providers.block;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, InfinityTech.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Blocks.ORES)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TITANIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.TITANIUM_NETHER_ORE.get())
                .add(ModBlocks.TITANIUM_END_ORE.get())

                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.LEAD_DEEPSLATE_ORE.get())
                .add(ModBlocks.LEAD_NETHER_ORE.get())
                .add(ModBlocks.LEAD_END_ORE.get())

                .add(ModBlocks.ALUMINUM_ORE.get())
                .add(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.ALUMINUM_NETHER_ORE.get())
                .add(ModBlocks.ALUMINUM_END_ORE.get())

                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.NICKEL_DEEPSLATE_ORE.get())
                .add(ModBlocks.NICKEL_NETHER_ORE.get())
                .add(ModBlocks.NICKEL_END_ORE.get())

                .add(ModBlocks.PLATINUM_ORE.get())
                .add(ModBlocks.PLATINUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.PLATINUM_NETHER_ORE.get())
                .add(ModBlocks.PLATINUM_END_ORE.get())

                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.SILVER_DEEPSLATE_ORE.get())
                .add(ModBlocks.SILVER_NETHER_ORE.get())
                .add(ModBlocks.SILVER_END_ORE.get())

                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.TIN_DEEPSLATE_ORE.get())
                .add(ModBlocks.TIN_NETHER_ORE.get())
                .add(ModBlocks.TIN_END_ORE.get())

                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.URANIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.URANIUM_NETHER_ORE.get())
                .add(ModBlocks.URANIUM_END_ORE.get())

                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.ZINC_DEEPSLATE_ORE.get())
                .add(ModBlocks.ZINC_NETHER_ORE.get())
                .add(ModBlocks.ZINC_END_ORE.get());


        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TITANIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.TITANIUM_NETHER_ORE.get())
                .add(ModBlocks.TITANIUM_END_ORE.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.TITANIUM_RAW_BLOCK.get())

                .add(ModBlocks.STEEL_BLOCK.get())

                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.LEAD_DEEPSLATE_ORE.get())
                .add(ModBlocks.LEAD_NETHER_ORE.get())
                .add(ModBlocks.LEAD_END_ORE.get())
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.LEAD_RAW_BLOCK.get())

                .add(ModBlocks.ALUMINUM_ORE.get())
                .add(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.ALUMINUM_NETHER_ORE.get())
                .add(ModBlocks.ALUMINUM_END_ORE.get())
                .add(ModBlocks.ALUMINUM_BLOCK.get())
                .add(ModBlocks.ALUMINUM_RAW_BLOCK.get())

                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.NICKEL_DEEPSLATE_ORE.get())
                .add(ModBlocks.NICKEL_NETHER_ORE.get())
                .add(ModBlocks.NICKEL_END_ORE.get())
                .add(ModBlocks.NICKEL_BLOCK.get())
                .add(ModBlocks.NICKEL_RAW_BLOCK.get())

                .add(ModBlocks.PLATINUM_ORE.get())
                .add(ModBlocks.PLATINUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.PLATINUM_NETHER_ORE.get())
                .add(ModBlocks.PLATINUM_END_ORE.get())
                .add(ModBlocks.PLATINUM_BLOCK.get())
                .add(ModBlocks.PLATINUM_RAW_BLOCK.get())

                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.SILVER_DEEPSLATE_ORE.get())
                .add(ModBlocks.SILVER_NETHER_ORE.get())
                .add(ModBlocks.SILVER_END_ORE.get())
                .add(ModBlocks.SILVER_BLOCK.get())
                .add(ModBlocks.SILVER_RAW_BLOCK.get())

                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.TIN_DEEPSLATE_ORE.get())
                .add(ModBlocks.TIN_NETHER_ORE.get())
                .add(ModBlocks.TIN_END_ORE.get())
                .add(ModBlocks.TIN_BLOCK.get())
                .add(ModBlocks.TIN_RAW_BLOCK.get())

                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.URANIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.URANIUM_NETHER_ORE.get())
                .add(ModBlocks.URANIUM_END_ORE.get())
                .add(ModBlocks.URANIUM_BLOCK.get())
                .add(ModBlocks.URANIUM_RAW_BLOCK.get())

                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.ZINC_DEEPSLATE_ORE.get())
                .add(ModBlocks.ZINC_NETHER_ORE.get())
                .add(ModBlocks.ZINC_END_ORE.get())
                .add(ModBlocks.ZINC_BLOCK.get())
                .add(ModBlocks.ZINC_RAW_BLOCK.get())

                .add(ModBlocks.CREATIVE_BATTERY.get())
                .add(ModBlocks.BATTERY_BLOCK.get())
                .add(ModBlocks.SOLAR_PANEL_1.get())
                .add(ModBlocks.SOLAR_PANEL_2.get())
                .add(ModBlocks.SOLAR_PANEL_3.get())
                .add(ModBlocks.SOLAR_PANEL_4.get())
                .add(ModBlocks.SOLAR_PANEL_5.get())
                .add(ModBlocks.SOLAR_PANEL_6.get())

                .add(ModBlocks.TIN_CABLE.get())
                .add(ModBlocks.COPPER_CABLE.get())
                .add(ModBlocks.GOLD_CABLE.get())

                .add(ModBlocks.CHEST_IRON.get())
                .add(ModBlocks.CHEST_COPPER.get())
                .add(ModBlocks.CHEST_GOLD.get())
                .add(ModBlocks.CHEST_DIAMOND.get())
                .add(ModBlocks.CHEST_OBSIDIAN.get())
                .add(ModBlocks.CHEST_NETHERITE.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.TITANIUM_RAW_BLOCK.get())
                .add(ModBlocks.LEAD_RAW_BLOCK.get())
                .add(ModBlocks.ALUMINUM_RAW_BLOCK.get())
                .add(ModBlocks.NICKEL_RAW_BLOCK.get())
                .add(ModBlocks.PLATINUM_RAW_BLOCK.get())
                .add(ModBlocks.SILVER_RAW_BLOCK.get())
                .add(ModBlocks.TIN_RAW_BLOCK.get())
                .add(ModBlocks.URANIUM_RAW_BLOCK.get())
                .add(ModBlocks.ZINC_RAW_BLOCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TITANIUM_RAW_BLOCK.get())
                .add(ModBlocks.LEAD_RAW_BLOCK.get())
                .add(ModBlocks.ALUMINUM_RAW_BLOCK.get())
                .add(ModBlocks.NICKEL_RAW_BLOCK.get())
                .add(ModBlocks.PLATINUM_RAW_BLOCK.get())
                .add(ModBlocks.SILVER_RAW_BLOCK.get())
                .add(ModBlocks.TIN_RAW_BLOCK.get())
                .add(ModBlocks.URANIUM_RAW_BLOCK.get())
                .add(ModBlocks.ZINC_RAW_BLOCK.get())

                .add(ModBlocks.CREATIVE_BATTERY.get())
                .add(ModBlocks.BATTERY_BLOCK.get())
                .add(ModBlocks.SOLAR_PANEL_1.get())
                .add(ModBlocks.SOLAR_PANEL_2.get())
                .add(ModBlocks.SOLAR_PANEL_3.get())
                .add(ModBlocks.SOLAR_PANEL_4.get())
                .add(ModBlocks.SOLAR_PANEL_5.get())
                .add(ModBlocks.SOLAR_PANEL_6.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TITANIUM_ORE.get())
                .add(ModBlocks.TITANIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.TITANIUM_BLOCK.get())
                .add(ModBlocks.TITANIUM_RAW_BLOCK.get())

                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.LEAD_DEEPSLATE_ORE.get())
                .add(ModBlocks.LEAD_BLOCK.get())

                .add(ModBlocks.ALUMINUM_ORE.get())
                .add(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.ALUMINUM_BLOCK.get())

                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.NICKEL_DEEPSLATE_ORE.get())
                .add(ModBlocks.NICKEL_BLOCK.get())

                .add(ModBlocks.PLATINUM_ORE.get())
                .add(ModBlocks.PLATINUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.PLATINUM_BLOCK.get())

                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.SILVER_DEEPSLATE_ORE.get())
                .add(ModBlocks.SILVER_BLOCK.get())

                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.TIN_DEEPSLATE_ORE.get())
                .add(ModBlocks.TIN_BLOCK.get())

                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.URANIUM_DEEPSLATE_ORE.get())
                .add(ModBlocks.URANIUM_BLOCK.get())

                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.ZINC_DEEPSLATE_ORE.get())
                .add(ModBlocks.ZINC_BLOCK.get())

                .add(ModBlocks.TIN_CABLE.get())
                .add(ModBlocks.COPPER_CABLE.get())
                .add(ModBlocks.GOLD_CABLE.get())

                .add(ModBlocks.CHEST_IRON.get())
                .add(ModBlocks.CHEST_COPPER.get())
                .add(ModBlocks.CHEST_GOLD.get())
                .add(ModBlocks.CHEST_DIAMOND.get())
                .add(ModBlocks.CHEST_OBSIDIAN.get())
                .add(ModBlocks.CHEST_NETHERITE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.LEAD_NETHER_ORE.get())
                .add(ModBlocks.LEAD_END_ORE.get())

                .add(ModBlocks.ALUMINUM_NETHER_ORE.get())
                .add(ModBlocks.ALUMINUM_END_ORE.get())

                .add(ModBlocks.NICKEL_NETHER_ORE.get())
                .add(ModBlocks.NICKEL_END_ORE.get())

                .add(ModBlocks.PLATINUM_NETHER_ORE.get())
                .add(ModBlocks.PLATINUM_END_ORE.get())

                .add(ModBlocks.TITANIUM_NETHER_ORE.get())
                .add(ModBlocks.TITANIUM_END_ORE.get())

                .add(ModBlocks.SILVER_NETHER_ORE.get())
                .add(ModBlocks.SILVER_END_ORE.get())

                .add(ModBlocks.TIN_NETHER_ORE.get())
                .add(ModBlocks.TIN_END_ORE.get())

                .add(ModBlocks.URANIUM_NETHER_ORE.get())
                .add(ModBlocks.URANIUM_END_ORE.get())

                .add(ModBlocks.ZINC_NETHER_ORE.get())
                .add(ModBlocks.ZINC_END_ORE.get());

        tag(ModTags.Blocks.NEEDS_TITANIUM_TOOL)
                .add(ModBlocks.STEEL_BLOCK.get());

        tag(ModTags.Blocks.MOD_CHESTS)
                .add(ModBlocks.CHEST_IRON.get())
                .add(ModBlocks.CHEST_COPPER.get())
                .add(ModBlocks.CHEST_GOLD.get())
                .add(ModBlocks.CHEST_DIAMOND.get())
                .add(ModBlocks.CHEST_OBSIDIAN.get())
                .add(ModBlocks.CHEST_NETHERITE.get());

        tag(ModTags.Blocks.MOD_ENERGY)
                .add(ModBlocks.CREATIVE_BATTERY.get())
                .add(ModBlocks.BATTERY_BLOCK.get())
                .add(ModBlocks.SOLAR_PANEL_1.get())
                .add(ModBlocks.SOLAR_PANEL_2.get())
                .add(ModBlocks.SOLAR_PANEL_3.get())
                .add(ModBlocks.SOLAR_PANEL_4.get())
                .add(ModBlocks.SOLAR_PANEL_5.get())
                .add(ModBlocks.TIN_CABLE.get())
                .add(ModBlocks.COPPER_CABLE.get())
                .add(ModBlocks.GOLD_CABLE.get());

        tag(ModTags.Blocks.MOD_ENERGY_CABLES)
                .add(ModBlocks.TIN_CABLE.get())
                .add(ModBlocks.COPPER_CABLE.get())
                .add(ModBlocks.GOLD_CABLE.get());
    }
}
