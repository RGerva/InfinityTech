package com.rgerva.infinitytech.datagen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.datagen.providers.ModDataMapProvider;
import com.rgerva.infinitytech.datagen.providers.ModDatapackProvider;
import com.rgerva.infinitytech.datagen.providers.ModRecipeProvider;
import com.rgerva.infinitytech.datagen.providers.ModSpriteProvider;
import com.rgerva.infinitytech.datagen.providers.block.ModBlockLootTableProvider;
import com.rgerva.infinitytech.datagen.providers.block.ModBlockStateProvider;
import com.rgerva.infinitytech.datagen.providers.block.ModBlockTagProvider;
import com.rgerva.infinitytech.datagen.providers.item.ModEquipmentProvider;
import com.rgerva.infinitytech.datagen.providers.item.ModItemModelProvider;
import com.rgerva.infinitytech.datagen.providers.item.ModItemTagProvider;
import net.minecraft.DetectedVersion;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.HolderLookup;
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
        PackOutput packOutput = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        event.createProvider(output -> new ModelProvider(packOutput, InfinityTech.MOD_ID){
            @Override
            protected void registerModels(@NotNull BlockModelGenerators blockModels, @NotNull ItemModelGenerators itemModels) {
                ModBlockStateProvider.run(blockModels);
                ModItemModelProvider.run(itemModels);
                InfinityTech.LOGGER.info("Done");
            }
        });

        event.addProvider(new ModEquipmentProvider(packOutput));

        event.addProvider(new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider);
        event.addProvider(blockTagsProvider);
        event.addProvider(new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter()));

        event.addProvider(new ModRecipeProvider.Runner(packOutput, lookupProvider));

        event.addProvider(new ModDataMapProvider(packOutput,lookupProvider));

        event.addProvider(new ModDatapackProvider(packOutput, lookupProvider));

        event.addProvider(new ModSpriteProvider(packOutput, lookupProvider));

        event.addProvider(new PackMetadataGenerator(packOutput)
                .add(PackMetadataSection.TYPE, new PackMetadataSection(Component.literal("Infinity Tech resources & data"),
                        DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES))));
    }

}
