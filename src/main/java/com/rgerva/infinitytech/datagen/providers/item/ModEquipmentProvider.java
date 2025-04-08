/**
 * Class: ModEquipmentProvider
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.datagen.providers.item;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.item.ModArmorMaterials;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentProvider extends EquipmentAssetProvider {
    protected final PackOutput.PathProvider pathProvider;

    public ModEquipmentProvider(PackOutput output) {
        super(output);
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    @Override
    public @NotNull CompletableFuture<?> run(CachedOutput output) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> map = new HashMap<>();
        bootstrap((key, model) -> {
            if (map.putIfAbsent(key, model) != null)
            {
                throw new IllegalStateException("Duplicate equipment asset for id: " + key.location().toString());
            }
        });
        return DataProvider.saveAll(output, EquipmentClientInfo.CODEC, this.pathProvider::json, map);
    }

    public static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer)
    {
        consumer.accept(ModArmorMaterials.TITANIUM, humanoidAndHorse("titanium"));
    }

    public static EquipmentClientInfo humanoidAndHorse(String name) {
        return EquipmentClientInfo.builder().addHumanoidLayers(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name)).addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, new EquipmentClientInfo.Layer[]{EquipmentClientInfo.Layer.leatherDyeable(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name), false)}).build();
    }

    public static EquipmentClientInfo onlyHumanoid(String name)
    {
        return EquipmentClientInfo.builder().addHumanoidLayers(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name)).build();
    }
}
