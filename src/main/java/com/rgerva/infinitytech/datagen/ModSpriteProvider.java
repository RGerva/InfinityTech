package com.rgerva.infinitytech.datagen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModSpriteProvider extends SpriteSourceProvider {
    public ModSpriteProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, InfinityTech.MOD_ID, existingFileHelper);
    }

    @Override
    protected void gather() {
        chestSprite(ModBlocks.IRON_CHEST);
    }

    private void chestSprite(Holder<? extends Block> block){
        atlas(CHESTS_ATLAS).addSource(new SingleFile(getBlockTexture(block), Optional.empty()));
        atlas(BLOCKS_ATLAS).addSource(new SingleFile(getBlockParticleTexture(block), Optional.empty()));
    }

    private ResourceLocation getBlockParticleTexture(Holder<? extends Block> block){
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "particle/" + blockId.getPath());
    }

    private ResourceLocation getBlockTexture(Holder<? extends Block> block) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + blockId.getPath());
    }

}
