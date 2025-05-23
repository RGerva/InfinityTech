package com.rgerva.infinitytech.datagen.providers;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModSpriteProvider extends SpriteSourceProvider {


    public ModSpriteProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, InfinityTech.MOD_ID);
    }

    @Override
    protected void gather() {
        chestSprite(ModBlocks.CHEST_IRON);
        chestSprite(ModBlocks.CHEST_COPPER);
        chestSprite(ModBlocks.CHEST_GOLD);
        chestSprite(ModBlocks.CHEST_DIAMOND);
        chestSprite(ModBlocks.CHEST_OBSIDIAN);
        chestSprite(ModBlocks.CHEST_NETHERITE);
    }

    private void chestSprite(Holder<? extends Block> block) {
        atlas(CHESTS_ATLAS).addSource(new SingleFile(getBlockTexture(block), Optional.empty()));
        atlas(BLOCKS_ATLAS).addSource(new SingleFile(getBlockParticleTexture(block), Optional.empty()));
    }

    private ResourceLocation getBlockParticleTexture(Holder<? extends Block> block) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "particle/" + blockId.getPath());
    }

    private ResourceLocation getBlockTexture(Holder<? extends Block> block) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "block/" + blockId.getPath());
    }

}
