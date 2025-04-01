package com.rgerva.infinitytech.datagen;

import com.rgerva.infinitytech.InfinityTech;
import net.minecraft.DetectedVersion;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = InfinityTech.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators{
    @SubscribeEvent
    public static void gatherDataServer(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //region ModelProvider

        event.createProvider(output -> new ModelProvider(packOutput, InfinityTech.MOD_ID){
            @Override
            protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
                ModBlockStateProvider.run(blockModels);
                ModItemModelProvider.run(itemModels);
                InfinityTech.LOGGER.info("Done");
            }
        });
        //endregion

        //region LootTable

        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        //endregion

        //region TagsProvider

        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider);
        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true, new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter()));

        //endregion

        //region Recipe

        generator.addProvider(true, new ModRecipeProvider.Runner(packOutput, lookupProvider));
        //endregion

        //region DataMap

        generator.addProvider(true, new ModDataMapProvider(packOutput, lookupProvider));
        //endregion

        //region DataPack

        generator.addProvider(true, new ModDatapackProvider(packOutput, lookupProvider));
        //endregion

        //region Sprite

        generator.addProvider(true, new ModSpriteProvider(packOutput, lookupProvider));
        //endregion

        generator.addProvider(true, new PackMetadataGenerator(packOutput)
                .add(PackMetadataSection.TYPE, new PackMetadataSection(Component.literal("Infinity Tech resources & data"),
                        DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES))));
    }

}
