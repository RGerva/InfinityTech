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
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.TITANIUM_INGOT.get()))
                    .title(Component.translatable("name.infinity_tech.name"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.TITANIUM_ORE.get());
                        output.accept(ModBlocks.TITANIUM_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.TITANIUM_END_ORE.get());
                        output.accept(ModItems.TITANIUM_RAW.get());
                        output.accept(ModBlocks.TITANIUM_RAW_BLOCK.get());
                        output.accept(ModBlocks.TITANIUM_BLOCK.get());
                        output.accept(ModItems.TITANIUM_INGOT.get());
                        output.accept(ModItems.TITANIUM_NUGGET.get());
                        output.accept(ModItems.STEEL_INGOT.get());

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

                        output.accept(ModBlocks.CREATIVE_BATTERY.get());
                        output.accept(ModBlocks.BATTERY_BLOCK.get());
                        output.accept(ModBlocks.SOLAR_PANEL.get());

                    }).build());

    public static void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.TITANIUM_INGOT.get());
            event.accept(ModItems.TITANIUM_RAW.get());
            event.accept(ModItems.TITANIUM_NUGGET.get());
            event.accept(ModItems.STEEL_INGOT.get());
        }

        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.TITANIUM_DEEPSLATE_ORE.get());
            event.accept(ModBlocks.TITANIUM_END_ORE.get());
            event.accept(ModBlocks.TITANIUM_ORE.get());
            event.accept(ModBlocks.TITANIUM_RAW_BLOCK.get());
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.TITANIUM_BLOCK.get());
        }

        if(event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.TITANIUM_SWORD.get());
            event.accept(ModItems.TITANIUM_HELMET.get());
            event.accept(ModItems.TITANIUM_CHESTPLATE.get());
            event.accept(ModItems.TITANIUM_LEGGINGS.get());
            event.accept(ModItems.TITANIUM_BOOTS.get());
            event.accept(ModItems.TITANIUM_HORSE_ARMOR.get());
        }

        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.TITANIUM_PICKAXE.get());
            event.accept(ModItems.TITANIUM_SHOVEL.get());
            event.accept(ModItems.TITANIUM_AXE.get());
            event.accept(ModItems.TITANIUM_HOE.get());
        }

    }

    public static void register(IEventBus eventBus){
        CREATIVE_TAB.register(eventBus);
    }
}
