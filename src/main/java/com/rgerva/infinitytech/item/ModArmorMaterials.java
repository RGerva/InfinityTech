package com.rgerva.infinitytech.item;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.util.ModTags;
import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;

public class ModArmorMaterials {

    public static final ResourceKey<EquipmentAsset> TITANIUM =
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium"));

    public static final ArmorMaterial TITANIUM_ARMOR_MATERIAL = new ArmorMaterial(
            1796, Util.make(new EnumMap<>(ArmorType.class),
            attribute -> {
                    attribute.put(ArmorType.BOOTS, 4);
                    attribute.put(ArmorType.LEGGINGS, 6);
                    attribute.put(ArmorType.CHESTPLATE, 8);
                    attribute.put(ArmorType.HELMET, 3);
                    attribute.put(ArmorType.BODY, 10);
                }),
            16, SoundEvents.ARMOR_EQUIP_DIAMOND, 1.5f,0.05f,
            ModTags.Items.TITANIUM_REPAIR, TITANIUM);
}