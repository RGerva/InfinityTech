/**
 * Class: ModModelTemplates
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.datagen.model;

import com.rgerva.infinitytech.InfinityTech;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class ModModelTemplates {

    public static final ModelTemplate SOLAR_PANEL_TEMPLATE = block("solar_panel_template",
            TextureSlot.PARTICLE, TextureSlot.TOP, TextureSlot.SIDE);

    public static final ModelTemplate CABLE_CORE_TEMPLATE = block("cable_core_template",
            TextureSlot.PARTICLE, ModTexturedModel.CABLE);

    public static final ModelTemplate CABLE_SIDE_TEMPLATE = block("cable_side_template",
            TextureSlot.PARTICLE, ModTexturedModel.CABLE);

    private static ModelTemplate block(String parent, TextureSlot... requiredTextureSlots) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "block/" + parent)),
                Optional.empty(), requiredTextureSlots);
    }

    private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureSlots) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "block/" + parent)),
                Optional.of(variant), requiredTextureSlots);
    }
}
