package com.rgerva.infinitytech.datagen.providers.block;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        LootTableOre(ModBlocks.TITANIUM_ORE.get(), ModItems.TITANIUM_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.TITANIUM_DEEPSLATE_ORE.get(), ModItems.TITANIUM_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.TITANIUM_END_ORE.get(), ModItems.TITANIUM_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.TITANIUM_NETHER_ORE.get(), ModItems.TITANIUM_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        dropSelf(ModBlocks.TITANIUM_RAW_BLOCK.get());

        dropSelf(ModBlocks.STEEL_BLOCK.get());

        LootTableOre(ModBlocks.LEAD_ORE.get(), ModItems.LEAD_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.LEAD_DEEPSLATE_ORE.get(), ModItems.LEAD_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.LEAD_NETHER_ORE.get(), ModItems.LEAD_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.LEAD_END_ORE.get(), ModItems.LEAD_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.LEAD_BLOCK.get());
        dropSelf(ModBlocks.LEAD_RAW_BLOCK.get());

        LootTableOre(ModBlocks.ALUMINUM_ORE.get(), ModItems.ALUMINUM_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get(), ModItems.ALUMINUM_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.ALUMINUM_NETHER_ORE.get(), ModItems.ALUMINUM_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.ALUMINUM_END_ORE.get(), ModItems.ALUMINUM_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.ALUMINUM_BLOCK.get());
        dropSelf(ModBlocks.ALUMINUM_RAW_BLOCK.get());

        LootTableOre(ModBlocks.NICKEL_ORE.get(), ModItems.NICKEL_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.NICKEL_DEEPSLATE_ORE.get(), ModItems.NICKEL_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.NICKEL_NETHER_ORE.get(), ModItems.NICKEL_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.NICKEL_END_ORE.get(), ModItems.NICKEL_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.NICKEL_BLOCK.get());
        dropSelf(ModBlocks.NICKEL_RAW_BLOCK.get());

        LootTableOre(ModBlocks.PLATINUM_ORE.get(), ModItems.PLATINUM_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.PLATINUM_DEEPSLATE_ORE.get(), ModItems.PLATINUM_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.PLATINUM_NETHER_ORE.get(), ModItems.PLATINUM_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.PLATINUM_END_ORE.get(), ModItems.PLATINUM_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.PLATINUM_BLOCK.get());
        dropSelf(ModBlocks.PLATINUM_RAW_BLOCK.get());

        LootTableOre(ModBlocks.SILVER_ORE.get(), ModItems.SILVER_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.SILVER_DEEPSLATE_ORE.get(), ModItems.SILVER_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.SILVER_NETHER_ORE.get(), ModItems.SILVER_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.SILVER_END_ORE.get(), ModItems.SILVER_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.SILVER_BLOCK.get());
        dropSelf(ModBlocks.SILVER_RAW_BLOCK.get());

        LootTableOre(ModBlocks.TIN_ORE.get(), ModItems.TIN_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.TIN_DEEPSLATE_ORE.get(), ModItems.TIN_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.TIN_NETHER_ORE.get(), ModItems.TIN_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.TIN_END_ORE.get(), ModItems.TIN_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.TIN_BLOCK.get());
        dropSelf(ModBlocks.TIN_RAW_BLOCK.get());

        LootTableOre(ModBlocks.URANIUM_ORE.get(), ModItems.URANIUM_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.URANIUM_DEEPSLATE_ORE.get(), ModItems.URANIUM_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.URANIUM_NETHER_ORE.get(), ModItems.URANIUM_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.URANIUM_END_ORE.get(), ModItems.URANIUM_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.URANIUM_BLOCK.get());
        dropSelf(ModBlocks.URANIUM_RAW_BLOCK.get());

        LootTableOre(ModBlocks.ZINC_ORE.get(), ModItems.ZINC_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.ZINC_DEEPSLATE_ORE.get(), ModItems.ZINC_RAW.get(), 0, 0);
        LootTableOre(ModBlocks.ZINC_NETHER_ORE.get(), ModItems.ZINC_RAW.get(), 2.0F, 3.0F);
        LootTableOre(ModBlocks.ZINC_END_ORE.get(), ModItems.ZINC_RAW.get(), 2.0F, 3.0F);

        dropSelf(ModBlocks.ZINC_BLOCK.get());
        dropSelf(ModBlocks.ZINC_RAW_BLOCK.get());

        dropSelf(ModBlocks.BATTERY_LVL_1.get());

        dropSelf(ModBlocks.SOLAR_PANEL_1.get());
        dropSelf(ModBlocks.SOLAR_PANEL_2.get());
        dropSelf(ModBlocks.SOLAR_PANEL_3.get());
        dropSelf(ModBlocks.SOLAR_PANEL_4.get());
        dropSelf(ModBlocks.SOLAR_PANEL_5.get());
        dropSelf(ModBlocks.SOLAR_PANEL_6.get());

        dropSelf(ModBlocks.TIN_CABLE.get());
        dropSelf(ModBlocks.COPPER_CABLE.get());
        dropSelf(ModBlocks.GOLD_CABLE.get());

        dropSelf(ModBlocks.CHEST_IRON.get());
        dropSelf(ModBlocks.CHEST_COPPER.get());
        dropSelf(ModBlocks.CHEST_GOLD.get());
        dropSelf(ModBlocks.CHEST_DIAMOND.get());
        dropSelf(ModBlocks.CHEST_OBSIDIAN.get());
        dropSelf(ModBlocks.CHEST_NETHERITE.get());

        dropSelf(ModBlocks.COAL_GENERATOR.get());

        dropSelf(ModBlocks.COPPER_FURNACE.get());
        dropSelf(ModBlocks.IRON_FURNACE.get());
        dropSelf(ModBlocks.SILVER_FURNACE.get());
        dropSelf(ModBlocks.GOLD_FURNACE.get());
        dropSelf(ModBlocks.DIAMOND_FURNACE.get());
        dropSelf(ModBlocks.EMERALD_FURNACE.get());
        dropSelf(ModBlocks.OBSIDIAN_FURNACE.get());
        dropSelf(ModBlocks.NETHERITE_FURNACE.get());

    }

    protected void LootTableOre(Block pInput, Item pOutput, float minDrops, float maxDrops) {
        if (minDrops == 0 && maxDrops == 0) {
            add(pInput,
                    block -> createOreDrop(pInput, pOutput));
        } else {
            add(pInput,
                    block -> createMultipleOreDrops(pInput, pOutput, minDrops, maxDrops));

        }
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
