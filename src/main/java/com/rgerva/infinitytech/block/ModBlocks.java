package com.rgerva.infinitytech.block;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.custom.BatteryBlock;
import com.rgerva.infinitytech.block.custom.CreativeBatteryBlock;
import com.rgerva.infinitytech.block.custom.SolarPanelBlock;
import com.rgerva.infinitytech.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(InfinityTech.MOD_ID);

    public static final DeferredBlock<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_ore")))
                            .strength(3f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TITANIUM_DEEPSLATE_ORE = registerBlock("titanium_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_deepslate_ore")))
                            .strength(3f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> TITANIUM_END_ORE = registerBlock("titanium_end_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of()
                            .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_end_ore")))
                            .strength(3.5f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.GILDED_BLACKSTONE)));

    public static final DeferredBlock<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_block")))
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> TITANIUM_RAW_BLOCK = registerBlock("titanium_raw_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_raw_block")))
                    .strength(1f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRAVEL)));

    public static final DeferredBlock<Block> CREATIVE_BATTERY = registerBlock("creative_battery_box",
            () -> new CreativeBatteryBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "creative_battery_box_side")))
                    .strength(-1f, 3600000.f)
                    .noLootTable()));

    public static final DeferredBlock<Block> BATTERY_BLOCK = registerBlock("battery_box",
            () -> new BatteryBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "battery_box")))
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> SOLAR_PANEL = registerBlock("solar_panel",
            () -> new SolarPanelBlock(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "solar_panel")))
                    .strength(4.0F, 5.0F)
                    .sound(SoundType.METAL)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().useBlockDescriptionPrefix()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, name)))));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
