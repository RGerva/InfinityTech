package com.rgerva.infinitytech.util;

import com.rgerva.infinitytech.InfinityTech;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_TITANIUM_TOOL = createTag("needs_titanium_tool");
        public static final TagKey<Block> INCORRECT_FOR_TITANIUM_TOOL = createTag("incorrect_for_titanium_tool");
        public static final TagKey<Block> MOD_CHESTS = createTag(InfinityTech.MOD_ID + "_chest");
        public static final TagKey<Block> MOD_ENERGY = createTag(InfinityTech.MOD_ID + "_energy");
        public static final TagKey<Block> MOD_ENERGY_CABLES = createTag(InfinityTech.MOD_ID + "_energy_cables");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> TITANIUM_REPAIR = createTag("titanium_repair");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name));
        }
    }
}
