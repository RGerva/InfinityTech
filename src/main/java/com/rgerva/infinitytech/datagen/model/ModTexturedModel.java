/**
 * Class: ModTexturedModel
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.datagen.model;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

public class ModTexturedModel {

    public static final TextureSlot CABLE = TextureSlot.create("cable");

    public static final TexturedModel.Provider SOLAR_PANEL = TexturedModel.createDefault(ModTexturedModel::solarPanel,
            ModModelTemplates.SOLAR_PANEL_TEMPLATE);

    public static final TexturedModel.Provider CABLE_CORE = TexturedModel.createDefault(ModTexturedModel::cableCore,
            ModModelTemplates.CABLE_CORE_TEMPLATE);

    public static final TexturedModel.Provider CABLE_SIDE = TexturedModel.createDefault(ModTexturedModel::cableSide,
            ModModelTemplates.CABLE_SIDE_TEMPLATE);


    public static TextureMapping solarPanel(Block block) {
        return new TextureMapping().
                put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")).
                put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side")).
                copySlot(TextureSlot.TOP, TextureSlot.PARTICLE);
    }

    public static TextureMapping cableCore(Block block) {
        return new TextureMapping().
                put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block)).
                put(CABLE, TextureMapping.getBlockTexture(block));
    }

    public static TextureMapping cableSide(Block block) {
        return new TextureMapping().
                put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block)).
                put(CABLE, TextureMapping.getBlockTexture(block));
    }
}
