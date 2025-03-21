package com.rgerva.infinitytech.crative;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InfinityTech.MOD_ID);

    public static final Supplier<CreativeModeTab> INFINITY_TECH_ITEM_TAB = CREATIVE_TAB.register("infinity_tech_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TITANIUM_INGOT.get()))
                    .title(Component.translatable("name.infinity_tech.name"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModBlocks.TITANIUM_ORE.get());
                        output.accept(ModBlocks.TITANIUM_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.TITANIUM_NETHER_ORE.get());
                        output.accept(ModBlocks.TITANIUM_END_ORE.get());
                        output.accept(ModItems.TITANIUM_RAW.get());
                        output.accept(ModBlocks.TITANIUM_RAW_BLOCK.get());
                        output.accept(ModBlocks.TITANIUM_BLOCK.get());
                        output.accept(ModItems.TITANIUM_INGOT.get());
                        output.accept(ModItems.TITANIUM_NUGGET.get());

                        output.accept(ModBlocks.LEAD_ORE.get());
                        output.accept(ModBlocks.LEAD_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.LEAD_NETHER_ORE.get());
                        output.accept(ModBlocks.LEAD_END_ORE.get());
                        output.accept(ModItems.LEAD_RAW.get());
                        output.accept(ModBlocks.LEAD_RAW_BLOCK.get());
                        output.accept(ModBlocks.LEAD_BLOCK.get());
                        output.accept(ModItems.LEAD_INGOT.get());
                        output.accept(ModItems.LEAD_NUGGET.get());

                        output.accept(ModBlocks.ALUMINUM_ORE.get());
                        output.accept(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.ALUMINUM_NETHER_ORE.get());
                        output.accept(ModBlocks.ALUMINUM_END_ORE.get());
                        output.accept(ModItems.ALUMINUM_RAW.get());
                        output.accept(ModBlocks.ALUMINUM_RAW_BLOCK.get());
                        output.accept(ModBlocks.ALUMINUM_BLOCK.get());
                        output.accept(ModItems.ALUMINUM_INGOT.get());
                        output.accept(ModItems.ALUMINUM_NUGGET.get());

                        output.accept(ModBlocks.NICKEL_ORE.get());
                        output.accept(ModBlocks.NICKEL_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.NICKEL_NETHER_ORE.get());
                        output.accept(ModBlocks.NICKEL_END_ORE.get());
                        output.accept(ModItems.NICKEL_RAW.get());
                        output.accept(ModBlocks.NICKEL_RAW_BLOCK.get());
                        output.accept(ModBlocks.NICKEL_BLOCK.get());
                        output.accept(ModItems.NICKEL_INGOT.get());
                        output.accept(ModItems.NICKEL_NUGGET.get());

                        output.accept(ModBlocks.PLATINUM_ORE.get());
                        output.accept(ModBlocks.PLATINUM_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.PLATINUM_NETHER_ORE.get());
                        output.accept(ModBlocks.PLATINUM_END_ORE.get());
                        output.accept(ModItems.PLATINUM_RAW.get());
                        output.accept(ModBlocks.PLATINUM_RAW_BLOCK.get());
                        output.accept(ModBlocks.PLATINUM_BLOCK.get());
                        output.accept(ModItems.PLATINUM_INGOT.get());
                        output.accept(ModItems.PLATINUM_NUGGET.get());

                        output.accept(ModBlocks.SILVER_ORE.get());
                        output.accept(ModBlocks.SILVER_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.SILVER_NETHER_ORE.get());
                        output.accept(ModBlocks.SILVER_END_ORE.get());
                        output.accept(ModItems.SILVER_RAW.get());
                        output.accept(ModBlocks.SILVER_RAW_BLOCK.get());
                        output.accept(ModBlocks.SILVER_BLOCK.get());
                        output.accept(ModItems.SILVER_INGOT.get());
                        output.accept(ModItems.SILVER_NUGGET.get());

                        output.accept(ModBlocks.TIN_ORE.get());
                        output.accept(ModBlocks.TIN_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.TIN_NETHER_ORE.get());
                        output.accept(ModBlocks.TIN_END_ORE.get());
                        output.accept(ModItems.TIN_RAW.get());
                        output.accept(ModBlocks.TIN_RAW_BLOCK.get());
                        output.accept(ModBlocks.TIN_BLOCK.get());
                        output.accept(ModItems.TIN_INGOT.get());
                        output.accept(ModItems.TIN_NUGGET.get());

                        output.accept(ModBlocks.URANIUM_ORE.get());
                        output.accept(ModBlocks.URANIUM_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.URANIUM_NETHER_ORE.get());
                        output.accept(ModBlocks.URANIUM_END_ORE.get());
                        output.accept(ModItems.URANIUM_RAW.get());
                        output.accept(ModBlocks.URANIUM_RAW_BLOCK.get());
                        output.accept(ModBlocks.URANIUM_BLOCK.get());
                        output.accept(ModItems.URANIUM_INGOT.get());
                        output.accept(ModItems.URANIUM_NUGGET.get());

                        output.accept(ModBlocks.ZINC_ORE.get());
                        output.accept(ModBlocks.ZINC_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.ZINC_NETHER_ORE.get());
                        output.accept(ModBlocks.ZINC_END_ORE.get());
                        output.accept(ModItems.ZINC_RAW.get());
                        output.accept(ModBlocks.ZINC_RAW_BLOCK.get());
                        output.accept(ModBlocks.ZINC_BLOCK.get());
                        output.accept(ModItems.ZINC_INGOT.get());
                        output.accept(ModItems.ZINC_NUGGET.get());

                        output.accept(ModItems.TITANIUM_SWORD.get());
                        output.accept(ModItems.TITANIUM_PICKAXE.get());
                        output.accept(ModItems.TITANIUM_SHOVEL.get());
                        output.accept(ModItems.TITANIUM_AXE.get());
                        output.accept(ModItems.TITANIUM_HOE.get());

                        output.accept(ModItems.TITANIUM_HELMET.get());
                        output.accept(ModItems.TITANIUM_CHESTPLATE.get());
                        output.accept(ModItems.TITANIUM_LEGGINGS.get());
                        output.accept(ModItems.TITANIUM_BOOTS.get());

                        output.accept(ModItems.TITANIUM_HORSE_ARMOR.get());

                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.STEEL_NUGGET.get());
                        output.accept(ModBlocks.STEEL_BLOCK.get());

                        output.accept(ModItems.WRENCH.get());

                        output.accept(ModBlocks.CREATIVE_BATTERY.get());
                        output.accept(ModBlocks.BATTERY_BLOCK.get());
                        output.accept(ModBlocks.INFINITY_BATTERY.get());

                        output.accept(ModBlocks.SOLAR_PANEL_1.get());
                        output.accept(ModBlocks.SOLAR_PANEL_2.get());
                        output.accept(ModBlocks.SOLAR_PANEL_3.get());
                        output.accept(ModBlocks.SOLAR_PANEL_4.get());
                        output.accept(ModBlocks.SOLAR_PANEL_5.get());
                        output.accept(ModBlocks.SOLAR_PANEL_6.get());

                        output.accept(ModBlocks.TIN_CABLE.get());
                        output.accept(ModBlocks.COPPER_CABLE.get());
                        output.accept(ModBlocks.GOLD_CABLE.get());

                        output.accept(ModBlocks.CHEST_IRON.get());
                        output.accept(ModBlocks.CHEST_COPPER.get());
                        output.accept(ModBlocks.CHEST_GOLD.get());
                        output.accept(ModBlocks.CHEST_DIAMOND.get());
                        output.accept(ModBlocks.CHEST_OBSIDIAN.get());
                        output.accept(ModBlocks.CHEST_NETHERITE.get());

                        output.accept(ModBlocks.COAL_GENERATOR.get());

                    }).build());

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.TITANIUM_INGOT.get());
            event.accept(ModItems.TITANIUM_RAW.get());
            event.accept(ModItems.TITANIUM_NUGGET.get());

            event.accept(ModItems.LEAD_INGOT.get());
            event.accept(ModItems.LEAD_RAW.get());
            event.accept(ModItems.LEAD_NUGGET.get());

            event.accept(ModItems.ALUMINUM_INGOT.get());
            event.accept(ModItems.ALUMINUM_RAW.get());
            event.accept(ModItems.ALUMINUM_NUGGET.get());

            event.accept(ModItems.NICKEL_INGOT.get());
            event.accept(ModItems.NICKEL_RAW.get());
            event.accept(ModItems.NICKEL_NUGGET.get());

            event.accept(ModItems.PLATINUM_INGOT.get());
            event.accept(ModItems.PLATINUM_RAW.get());
            event.accept(ModItems.PLATINUM_NUGGET.get());

            event.accept(ModItems.SILVER_INGOT.get());
            event.accept(ModItems.SILVER_RAW.get());
            event.accept(ModItems.SILVER_NUGGET.get());

            event.accept(ModItems.TIN_INGOT.get());
            event.accept(ModItems.TIN_RAW.get());
            event.accept(ModItems.TIN_NUGGET.get());

            event.accept(ModItems.URANIUM_INGOT.get());
            event.accept(ModItems.URANIUM_RAW.get());
            event.accept(ModItems.URANIUM_NUGGET.get());

            event.accept(ModItems.ZINC_INGOT.get());
            event.accept(ModItems.ZINC_RAW.get());
            event.accept(ModItems.ZINC_NUGGET.get());

            event.accept(ModItems.STEEL_INGOT.get());
            event.accept(ModItems.STEEL_NUGGET.get());
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.TITANIUM_NETHER_ORE.get());
            event.accept(ModBlocks.TITANIUM_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.TITANIUM_END_ORE.get());
            event.accept(ModBlocks.TITANIUM_ORE.get());
            event.accept(ModBlocks.TITANIUM_RAW_BLOCK.get());

            event.accept(ModBlocks.LEAD_NETHER_ORE.get());
            event.accept(ModBlocks.LEAD_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.LEAD_END_ORE.get());
            event.accept(ModBlocks.LEAD_ORE.get());
            event.accept(ModBlocks.LEAD_RAW_BLOCK.get());

            event.accept(ModBlocks.ALUMINUM_NETHER_ORE.get());
            event.accept(ModBlocks.ALUMINUM_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.ALUMINUM_END_ORE.get());
            event.accept(ModBlocks.ALUMINUM_ORE.get());
            event.accept(ModBlocks.ALUMINUM_RAW_BLOCK.get());

            event.accept(ModBlocks.NICKEL_NETHER_ORE.get());
            event.accept(ModBlocks.NICKEL_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.NICKEL_END_ORE.get());
            event.accept(ModBlocks.NICKEL_ORE.get());
            event.accept(ModBlocks.NICKEL_RAW_BLOCK.get());

            event.accept(ModBlocks.PLATINUM_NETHER_ORE.get());
            event.accept(ModBlocks.PLATINUM_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.PLATINUM_END_ORE.get());
            event.accept(ModBlocks.PLATINUM_ORE.get());
            event.accept(ModBlocks.PLATINUM_RAW_BLOCK.get());

            event.accept(ModBlocks.SILVER_NETHER_ORE.get());
            event.accept(ModBlocks.SILVER_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.SILVER_END_ORE.get());
            event.accept(ModBlocks.SILVER_ORE.get());
            event.accept(ModBlocks.SILVER_RAW_BLOCK.get());

            event.accept(ModBlocks.TIN_NETHER_ORE.get());
            event.accept(ModBlocks.TIN_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.TIN_END_ORE.get());
            event.accept(ModBlocks.TIN_ORE.get());
            event.accept(ModBlocks.TIN_RAW_BLOCK.get());

            event.accept(ModBlocks.URANIUM_NETHER_ORE.get());
            event.accept(ModBlocks.URANIUM_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.URANIUM_END_ORE.get());
            event.accept(ModBlocks.URANIUM_ORE.get());
            event.accept(ModBlocks.URANIUM_RAW_BLOCK.get());

            event.accept(ModBlocks.ZINC_NETHER_ORE.get());
            event.accept(ModBlocks.ZINC_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.ZINC_END_ORE.get());
            event.accept(ModBlocks.ZINC_ORE.get());
            event.accept(ModBlocks.ZINC_RAW_BLOCK.get());
        }

        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.TITANIUM_BLOCK.get());
            event.accept(ModBlocks.STEEL_BLOCK.get());
            event.accept(ModBlocks.LEAD_BLOCK.get());
            event.accept(ModBlocks.ALUMINUM_BLOCK.get());
            event.accept(ModBlocks.NICKEL_BLOCK.get());
            event.accept(ModBlocks.PLATINUM_BLOCK.get());
            event.accept(ModBlocks.SILVER_BLOCK.get());
            event.accept(ModBlocks.TIN_BLOCK.get());
            event.accept(ModBlocks.URANIUM_BLOCK.get());
            event.accept(ModBlocks.ZINC_BLOCK.get());
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.TITANIUM_SWORD.get());
            event.accept(ModItems.TITANIUM_HELMET.get());
            event.accept(ModItems.TITANIUM_CHESTPLATE.get());
            event.accept(ModItems.TITANIUM_LEGGINGS.get());
            event.accept(ModItems.TITANIUM_BOOTS.get());
            event.accept(ModItems.TITANIUM_HORSE_ARMOR.get());
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.TITANIUM_PICKAXE.get());
            event.accept(ModItems.TITANIUM_SHOVEL.get());
            event.accept(ModItems.TITANIUM_AXE.get());
            event.accept(ModItems.TITANIUM_HOE.get());
            event.accept(ModItems.WRENCH.get());
        }

        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(ModBlocks.SOLAR_PANEL_1.get());
            event.accept(ModBlocks.SOLAR_PANEL_2.get());
            event.accept(ModBlocks.SOLAR_PANEL_3.get());
            event.accept(ModBlocks.SOLAR_PANEL_4.get());
            event.accept(ModBlocks.SOLAR_PANEL_5.get());
            event.accept(ModBlocks.SOLAR_PANEL_6.get());
            event.accept(ModBlocks.BATTERY_BLOCK.get());
            event.accept(ModBlocks.TIN_CABLE.get());
            event.accept(ModBlocks.COPPER_CABLE.get());
            event.accept(ModBlocks.GOLD_CABLE.get());
            event.accept(ModBlocks.CHEST_IRON.get());
            event.accept(ModBlocks.CHEST_COPPER.get());
            event.accept(ModBlocks.CHEST_GOLD.get());
            event.accept(ModBlocks.CHEST_DIAMOND.get());
            event.accept(ModBlocks.CHEST_OBSIDIAN.get());
            event.accept(ModBlocks.CHEST_NETHERITE.get());
        }

        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.CHEST_IRON.get());
            event.accept(ModBlocks.CHEST_COPPER.get());
            event.accept(ModBlocks.CHEST_GOLD.get());
            event.accept(ModBlocks.CHEST_DIAMOND.get());
            event.accept(ModBlocks.CHEST_OBSIDIAN.get());
            event.accept(ModBlocks.CHEST_NETHERITE.get());
        }

        if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
            event.accept(ModBlocks.CREATIVE_BATTERY.get());
        }

    }

    public static void register(IEventBus eventBus) {
        CREATIVE_TAB.register(eventBus);
    }
}
