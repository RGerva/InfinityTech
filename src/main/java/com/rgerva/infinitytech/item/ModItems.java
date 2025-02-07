package com.rgerva.infinitytech.item;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.item.custom.ModArmorMaterials;
import com.rgerva.infinitytech.item.custom.ModToolTiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(InfinityTech.MOD_ID);

    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_ingot")))));

    public static final DeferredItem<Item> TITANIUM_RAW = ITEMS.register("titanium_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_raw")))));

    public static final DeferredItem<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_nugget")))));

    public static final DeferredItem<SwordItem> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(ModToolTiers.TITANIUM, 3.0F, -2.4F, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_sword")))));

    public static final DeferredItem<PickaxeItem> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TITANIUM, 1.0F, -2.8F, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_pickaxe")))));

    public static final DeferredItem<ShovelItem> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModToolTiers.TITANIUM, 1.5F, -3.0F, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_shovel")))));

    public static final DeferredItem<AxeItem> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModToolTiers.TITANIUM, 5.0F, -3.2F, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_axe")))));

    public static final DeferredItem<HoeItem> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModToolTiers.TITANIUM, -3.5F, -0.5F, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_hoe")))));

    public static final DeferredItem<ArmorItem> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ArmorItem(ModArmorMaterials.TITANIUM_ARMOR_MATERIAL, ArmorType.HELMET, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_helmet")))));

    public static final DeferredItem<ArmorItem> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.TITANIUM_ARMOR_MATERIAL, ArmorType.CHESTPLATE, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_chestplate")))));

    public static final DeferredItem<ArmorItem> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ArmorItem(ModArmorMaterials.TITANIUM_ARMOR_MATERIAL, ArmorType.LEGGINGS, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_leggings")))));

    public static final DeferredItem<ArmorItem> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ArmorItem(ModArmorMaterials.TITANIUM_ARMOR_MATERIAL, ArmorType.BOOTS, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_boots")))));

    public static final DeferredItem<Item> TITANIUM_HORSE_ARMOR = ITEMS.register("titanium_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.TITANIUM_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_horse_armor")))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
