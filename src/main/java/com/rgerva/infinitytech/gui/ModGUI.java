package com.rgerva.infinitytech.gui;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.gui.menu.BatteryMenu;
import com.rgerva.infinitytech.gui.menu.ModChestMenu;
import com.rgerva.infinitytech.gui.menu.SolarPanelMenu;
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

    public static final Supplier<MenuType<BatteryMenu>> BATTERY_BOX_MENU =
            registerMenuType("battery_box", BatteryMenu::new);

    public static final Supplier<MenuType<SolarPanelMenu>> SOLAR_PENEL_MENU =
            registerMenuType("solar_penal", SolarPanelMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_IRON_MENU =
            registerMenuTypeChest("chest_iron", ModChestMenu::createIronContainer);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_COPPER_MENU =
            registerMenuTypeChest("chest_copper", ModChestMenu::createCopperContainer);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_GOLD_MENU =
            registerMenuTypeChest("chest_gold", ModChestMenu::createGoldContainer);

    public static final DeferredHolder<MenuType<?>, MenuType<ModChestMenu>> CHEST_DIAMOND_MENU =
            registerMenuTypeChest("chest_diamond", ModChestMenu::createDiamondContainer);


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
