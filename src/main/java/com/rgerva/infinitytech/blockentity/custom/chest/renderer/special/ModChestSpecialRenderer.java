/**
 * Class: ModChestSpecialRenderer
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity.custom.chest.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rgerva.infinitytech.blockentity.custom.chest.model.ModChestModel;
import com.rgerva.infinitytech.events.ModBusEvents;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class ModChestSpecialRenderer implements NoDataSpecialModelRenderer {

    private final ModChestModel model;
    private final Material material;
    private final float openness;

    public ModChestSpecialRenderer(ModChestModel model, Material material, float openness) {
        this.model = model;
        this.material = material;
        this.openness = openness;
    }

    @Override
    public void render(ItemDisplayContext itemDisplayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay, boolean hasFoilType) {
        VertexConsumer vertexconsumer = this.material.buffer(multiBufferSource, RenderType::entityCutout);
        this.model.setupAnim(this.openness);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, packedOverlay);
    }

    @OnlyIn(Dist.CLIENT)
    public record Unbaked(ResourceLocation texture, float openness) implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<ModChestSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
                unbakedInstance -> unbakedInstance.group(
                                ResourceLocation.CODEC.fieldOf("texture").forGetter(ModChestSpecialRenderer.Unbaked::texture),
                                Codec.FLOAT.optionalFieldOf("openness", 0.0F).forGetter(ModChestSpecialRenderer.Unbaked::openness)
                        )
                        .apply(unbakedInstance, ModChestSpecialRenderer.Unbaked::new)
        );

        public Unbaked(ResourceLocation resourceLocation) {
            this(resourceLocation, 0.0F);
        }


        @Override
        public @Nullable SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            ModChestModel chestModel = new ModChestModel(entityModelSet.bakeLayer(ModBusEvents.CHEST_LAYER_LOC));
            Material material = new Material(Sheets.CHEST_SHEET, texture);
            return new ModChestSpecialRenderer(chestModel, material, this.openness);
        }

        @Override
        public MapCodec<ModChestSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
