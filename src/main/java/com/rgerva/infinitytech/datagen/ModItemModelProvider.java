/**
 * Class: ModItemModelGeneratorProvider
 * Created by: D56V1OK
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.datagen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.item.ModItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class ModItemModelProvider {

    private static ItemModelGenerators itemModel;
    private static BiConsumer<ResourceLocation, ModelInstance> modelOutput;
    private static ItemModelOutput itemInfo;

    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    protected static void run(ItemModelGenerators itemModels) {


        itemModel = itemModels;
        modelOutput = itemModels.modelOutput;
        itemInfo = itemModels.itemModelOutput;

        basicItem(ModItems.TITANIUM_INGOT.get());
        basicItem(ModItems.TITANIUM_RAW.get());
        basicItem(ModItems.TITANIUM_NUGGET.get());
        basicItem(ModItems.STEEL_INGOT.get());
        basicItem(ModItems.STEEL_NUGGET.get());

        handheldItem(ModItems.TITANIUM_SWORD.get());
        handheldItem(ModItems.TITANIUM_PICKAXE.get());
        handheldItem(ModItems.TITANIUM_SHOVEL.get());
        handheldItem(ModItems.TITANIUM_AXE.get());
        handheldItem(ModItems.TITANIUM_HOE.get());

        trimmedArmorItem(ModItems.TITANIUM_HELMET);
        trimmedArmorItem(ModItems.TITANIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.TITANIUM_LEGGINGS);
        trimmedArmorItem(ModItems.TITANIUM_BOOTS);

        basicItem(ModItems.TITANIUM_HORSE_ARMOR.get());

        basicItem(ModItems.LEAD_INGOT.get());
        basicItem(ModItems.LEAD_NUGGET.get());
        basicItem(ModItems.LEAD_RAW.get());

        basicItem(ModItems.ALUMINUM_INGOT.get());
        basicItem(ModItems.ALUMINUM_NUGGET.get());
        basicItem(ModItems.ALUMINUM_RAW.get());

        basicItem(ModItems.NICKEL_INGOT.get());
        basicItem(ModItems.NICKEL_NUGGET.get());
        basicItem(ModItems.NICKEL_RAW.get());

        basicItem(ModItems.PLATINUM_INGOT.get());
        basicItem(ModItems.PLATINUM_NUGGET.get());
        basicItem(ModItems.PLATINUM_RAW.get());

        basicItem(ModItems.SILVER_INGOT.get());
        basicItem(ModItems.SILVER_NUGGET.get());
        basicItem(ModItems.SILVER_RAW.get());

        basicItem(ModItems.TIN_INGOT.get());
        basicItem(ModItems.TIN_NUGGET.get());
        basicItem(ModItems.TIN_RAW.get());

        basicItem(ModItems.URANIUM_INGOT.get());
        basicItem(ModItems.URANIUM_NUGGET.get());
        basicItem(ModItems.URANIUM_RAW.get());

        basicItem(ModItems.ZINC_INGOT.get());
        basicItem(ModItems.ZINC_NUGGET.get());
        basicItem(ModItems.ZINC_RAW.get());

        basicItem(ModItems.WRENCH.get());


    }

    private static void basicItem(Item item){
        itemModel.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    private static void handheldItem(Item item){
        itemModel.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    private static void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem){

        if (itemDeferredItem.get() instanceof ArmorItem armorItem) {
            String armorType = "";
            if (armorItem.toString().contains("helmet")) {
                armorType = ItemModelGenerators.SLOT_HELMET;
            } else if (armorItem.toString().contains("chestplate")) {
                armorType = ItemModelGenerators.SLOT_CHESTPLATE;
            } else if (armorItem.toString().contains("leggings")) {
                armorType = ItemModelGenerators.SLOT_LEGGINS;
            } else if (armorItem.toString().contains("boots")) {
                armorType = ItemModelGenerators.SLOT_BOOTS;
            }
            itemModel.generateTrimmableItem(itemDeferredItem.get(), EquipmentAssets.NETHERITE, armorType, false);
        }

    }
}
