package com.rgerva.infinitytech.datagen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.item.ModItems;
import com.rgerva.infinitytech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Item>> parentProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, parentProvider, blockTags, InfinityTech.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.TITANIUM_INGOT.get())
                .add(ModItems.TITANIUM_RAW.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.TITANIUM_HELMET.get())
                .add(ModItems.TITANIUM_CHESTPLATE.get())
                .add(ModItems.TITANIUM_LEGGINGS.get())
                .add(ModItems.TITANIUM_BOOTS.get())
                .add(ModItems.TITANIUM_HORSE_ARMOR.get());

        this.tag(ItemTags.SWORDS)
                .add(ModItems.TITANIUM_SWORD.get());

        this.tag(ItemTags.PICKAXES)
                .add(ModItems.TITANIUM_PICKAXE.get());

        this.tag(ItemTags.SHOVELS)
                .add(ModItems.TITANIUM_SHOVEL.get());

        this.tag(ItemTags.AXES)
                .add(ModItems.TITANIUM_AXE.get());

        this.tag(ItemTags.HOES)
                .add(ModItems.TITANIUM_HOE.get());

        this.tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.TITANIUM_INGOT.get())
                .add(ModItems.TITANIUM_RAW.get())
                .add(ModItems.TITANIUM_NUGGET.get())
                .add(ModItems.STEEL_INGOT.get())
                .add(ModItems.STEEL_NUGGET.get());

        this.tag(ModTags.Items.TITANIUM_REPAIR)
                .add(ModItems.TITANIUM_INGOT.get());
    }
}