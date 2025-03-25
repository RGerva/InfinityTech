/**
 * Class: ModBatteryEntity
 * Created by: D56V1OK
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity.custom.battery;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.solar_panel.SolarPanelBlockEntity;
import com.rgerva.infinitytech.energy.ModEnergyStorage;
import com.rgerva.infinitytech.network.base.ModSyncPackages;
import com.rgerva.infinitytech.util.types.eBatteryConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ModBatteryEntity extends BlockEntity implements MenuProvider, ModSyncPackages {

    private final eBatteryConfigs batteryConfigs;
    private final ModEnergyStorage ENERGY_STORAGE;

    private ModEnergyStorage createEnergyStorage(eBatteryConfigs batteryConfigs) {
        return new ModEnergyStorage(batteryConfigs.getCapacity(), batteryConfigs.getMaxReceive(), batteryConfigs.getMaxExtract(), 0) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                syncEnergyToPlayers(32);
            }

            @Override
            public boolean canReceive() {
                return batteryConfigs.getMaxReceive() != 0;
            }

            @Override
            public boolean canExtract() {
                return batteryConfigs.getMaxExtract() != 0;
            }
        };
    }

    public ModBatteryEntity(BlockPos pos, BlockState blockState, eBatteryConfigs batteryConfigs) {
        super(Objects.requireNonNull(getEntityType(batteryConfigs)), pos, blockState);
        this.batteryConfigs = batteryConfigs;
        ENERGY_STORAGE = createEnergyStorage(batteryConfigs);
    }

    public IEnergyStorage getEnergyStorageCapability(Direction direction) {
        return this.ENERGY_STORAGE;
    }

    public int getEnergy() {
        return ENERGY_STORAGE.getEnergy();
    }

    public int getCapacity() {
        return ENERGY_STORAGE.getCapacity();
    }

    public eBatteryConfigs getBatteryConfigs(){
        return batteryConfigs;
    }

    public static BlockEntityType<ModBatteryEntity> getEntityType(eBatteryConfigs batteryConfigs) {
        return switch (batteryConfigs){
            case INFINITY -> ModBlockEntities.INFINITY_BATTERY_ENTITY.get();
            case DUMP -> ModBlockEntities.DUMP_BATTERY_ENTITY.get();
            case null, default -> null;
        };
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, ModBatteryEntity blockEntity) {
        if(blockEntity.getBatteryConfigs() == eBatteryConfigs.INFINITY){
            blockEntity.ENERGY_STORAGE.setEnergy(blockEntity.ENERGY_STORAGE.getCapacity());
        }


    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("infinity_battery.energy", ENERGY_STORAGE.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        ENERGY_STORAGE.setEnergy(tag.getInt("infinity_battery.energy"));
    }

    @Override
    public BlockEntity getInterfaceSyncBlockEntity() {
        return this;
    }

    @Override
    public int getInterfaceSyncEnergy() {
        return getEnergy();
    }

    @Override
    public int getInterfaceSyncCapacity() {
        return getCapacity();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("machine.infinity_tech.infinity_battery");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }


    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

}
