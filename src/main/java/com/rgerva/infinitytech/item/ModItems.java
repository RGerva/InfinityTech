package com.rgerva.infinitytech.item;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.item.custom.chest.ChestUpgradeItem;
import com.rgerva.infinitytech.item.custom.furnace.FurnaceUpgradeItem;
import com.rgerva.infinitytech.item.custom.wrench.WrenchItem;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import com.rgerva.infinitytech.util.types.eFurnaceConfigs;
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

    //region INGOT

    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_ingot")))));

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "steel_ingot")))));

    public static final DeferredItem<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_ingot")))));

    public static final DeferredItem<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_ingot")))));

    public static final DeferredItem<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_ingot")))));

    public static final DeferredItem<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_ingot")))));

    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_ingot")))));

    public static final DeferredItem<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_ingot")))));

    public static final DeferredItem<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_ingot")))));

    public static final DeferredItem<Item> ZINC_INGOT = ITEMS.register("zinc_ingot",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_ingot")))));

    //endregion

    //region NUGGET

    public static final DeferredItem<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_nugget")))));

    public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "steel_nugget")))));

    public static final DeferredItem<Item> LEAD_NUGGET = ITEMS.register("lead_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_nugget")))));

    public static final DeferredItem<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_nugget")))));

    public static final DeferredItem<Item> NICKEL_NUGGET = ITEMS.register("nickel_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_nugget")))));

    public static final DeferredItem<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_nugget")))));

    public static final DeferredItem<Item> SILVER_NUGGET = ITEMS.register("silver_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_nugget")))));

    public static final DeferredItem<Item> TIN_NUGGET = ITEMS.register("tin_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_nugget")))));

    public static final DeferredItem<Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_nugget")))));

    public static final DeferredItem<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_nugget")))));

    //endregion

    //region RAW

    public static final DeferredItem<Item> TITANIUM_RAW = ITEMS.register("titanium_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_raw")))));

    public static final DeferredItem<Item> LEAD_RAW = ITEMS.register("lead_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "lead_raw")))));

    public static final DeferredItem<Item> ALUMINUM_RAW = ITEMS.register("aluminum_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "aluminum_raw")))));

    public static final DeferredItem<Item> NICKEL_RAW = ITEMS.register("nickel_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "nickel_raw")))));

    public static final DeferredItem<Item> PLATINUM_RAW = ITEMS.register("platinum_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "platinum_raw")))));

    public static final DeferredItem<Item> SILVER_RAW = ITEMS.register("silver_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "silver_raw")))));

    public static final DeferredItem<Item> TIN_RAW = ITEMS.register("tin_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "tin_raw")))));

    public static final DeferredItem<Item> URANIUM_RAW = ITEMS.register("uranium_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "uranium_raw")))));

    public static final DeferredItem<Item> ZINC_RAW = ITEMS.register("zinc_raw",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "zinc_raw")))));

    //endregion

    //region TOOLS

    public static final DeferredItem<Item> WRENCH = ITEMS.register("wrench",
            () -> new WrenchItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "wrench")))
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)));

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
    //endregion

    //region ARMOR

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

    public static final DeferredItem<AnimalArmorItem> TITANIUM_HORSE_ARMOR = ITEMS.register("titanium_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.TITANIUM_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "titanium_horse_armor")))));

    //endregion

    //region CHEST UPGRADE

    public static final DeferredItem<ChestUpgradeItem> CHEST_COPPER_TO_IRON = ITEMS.register("chest_copper_to_iron",
            () -> new ChestUpgradeItem(eChestConfigs.eChestUpgrade.COPPER_TO_IRON, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_copper_to_iron")))));

    public static final DeferredItem<ChestUpgradeItem> CHEST_DIAMOND_TO_OBSIDIAN = ITEMS.register("chest_diamond_to_obsidian",
            () -> new ChestUpgradeItem(eChestConfigs.eChestUpgrade.DIAMOND_TO_OBSIDIAN, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_diamond_to_obsidian")))));

    public static final DeferredItem<ChestUpgradeItem> CHEST_GOLD_TO_DIAMOND = ITEMS.register("chest_gold_to_diamond",
            () -> new ChestUpgradeItem(eChestConfigs.eChestUpgrade.GOLD_TO_DIAMOND, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_gold_to_diamond")))));

    public static final DeferredItem<ChestUpgradeItem> CHEST_IRON_TO_GOLD = ITEMS.register("chest_iron_to_gold",
            () -> new ChestUpgradeItem(eChestConfigs.eChestUpgrade.IRON_TO_GOLD, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_iron_to_gold")))));

    public static final DeferredItem<ChestUpgradeItem> CHEST_OBSIDIAN_TO_NETHERITE = ITEMS.register("chest_obsidian_to_netherite",
            () -> new ChestUpgradeItem(eChestConfigs.eChestUpgrade.OBSIDIAN_TO_NETHERITE, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_obsidian_to_netherite")))));

    public static final DeferredItem<ChestUpgradeItem> CHEST_VANILLA_TO_COPPER = ITEMS.register("chest_vanilla_to_copper",
            () -> new ChestUpgradeItem(eChestConfigs.eChestUpgrade.VANILLA_TO_COPPER, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest_vanilla_to_copper")))));

    //endregion

    //region FURNACE UPGRADE

    public static final DeferredItem<FurnaceUpgradeItem> FURNACE_VANILLA_TO_COPPER = ITEMS.register("furnace_vanilla_to_copper",
            () -> new FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade.VANILLA_TO_COPPER, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_vanilla_to_copper")))));

    public static final DeferredItem<FurnaceUpgradeItem> FURNACE_COPPER_TO_IRON = ITEMS.register("furnace_copper_to_iron",
            () -> new FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade.COPPER_TO_IRON, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_copper_to_iron")))));

    public static final DeferredItem<FurnaceUpgradeItem> FURNACE_IRON_TO_SILVER = ITEMS.register("furnace_iron_to_silver",
            () -> new FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade.IRON_TO_SILVER, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_iron_to_silver")))));

    public static final DeferredItem<FurnaceUpgradeItem> FURNACE_SILVER_TO_GOLD = ITEMS.register("furnace_silver_to_gold",
            () -> new FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade.SILVER_TO_GOLD, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_silver_to_gold")))));

    public static final DeferredItem<FurnaceUpgradeItem> FURNACE_GOLD_TO_DIAMOND = ITEMS.register("furnace_gold_to_diamond",
            () -> new FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade.GOLD_TO_DIAMOND, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_gold_to_diamond")))));

    public static final DeferredItem<FurnaceUpgradeItem> FURNACE_DIAMOND_TO_EMERALD = ITEMS.register("furnace_diamond_to_emerald",
            () -> new FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade.DIAMOND_TO_EMERALD, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_diamond_to_emerald")))));

    public static final DeferredItem<FurnaceUpgradeItem> FURNACE_EMERALD_TO_OBSIDIAN = ITEMS.register("furnace_emerald_to_obsidian",
            () -> new FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade.EMERALD_TO_OBSIDIAN, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_emerald_to_obsidian")))));

    public static final DeferredItem<FurnaceUpgradeItem> FURNACE_OBSIDIAN_TO_NETHERITE = ITEMS.register("furnace_obsidian_to_netherite",
            () -> new FurnaceUpgradeItem(eFurnaceConfigs.eFurnaceUpgrade.OBSIDIAN_TO_NETHERITE, new Item.Properties()
                    .stacksTo(1)
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "furnace_obsidian_to_netherite")))));

    //endregion

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
