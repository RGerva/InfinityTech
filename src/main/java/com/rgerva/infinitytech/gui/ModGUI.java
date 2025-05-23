package com.rgerva.infinitytech.gui;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.gui.menu.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModGUI {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, InfinityTech.MOD_ID);

    public static final Supplier<MenuType<ModBatteryMenu>> BATTERY_MENU =
            registerMenuType("battery_menu", ModBatteryMenu::new);

    public static final Supplier<MenuType<ModSolarPanelMenu>> SOLAR_MENU =
            registerMenuType("solar_panel_menu", ModSolarPanelMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_IRON_MENU =
            registerMenuTypeChest("chest_iron", ModChestMenu::createIronContainer);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_COPPER_MENU =
            registerMenuTypeChest("chest_copper", ModChestMenu::createCopperContainer);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_GOLD_MENU =
            registerMenuTypeChest("chest_gold", ModChestMenu::createGoldContainer);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_DIAMOND_MENU =
            registerMenuTypeChest("chest_diamond", ModChestMenu::createDiamondContainer);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_OBSIDIAN_MENU =
            registerMenuTypeChest("chest_obsidian", ModChestMenu::createObsidianContainer);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_NETHERITE_MENU =
            registerMenuTypeChest("chest_netherite", ModChestMenu::createNetheriteContainer);

    public static final Supplier<MenuType<ModCoalGeneratorMenu>> COAL_GENERATOR_MENU =
            registerMenuType("coal_generator", ModCoalGeneratorMenu::new);

    public static final Supplier<MenuType<ModFurnaceMenu>> FURNACE_MENU =
            registerMenuType("furnace_menu", ModFurnaceMenu::new);

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuTypeChest(String name, MenuType.MenuSupplier<T> constructor) {
        return MENUS.register(name, () -> new MenuType<>(constructor, FeatureFlags.REGISTRY.allFlags()));
    }

    public static void register(IEventBus modEventBus) {
        MENUS.register(modEventBus);
    }
}
