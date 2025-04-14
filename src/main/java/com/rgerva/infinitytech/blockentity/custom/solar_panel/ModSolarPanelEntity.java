/**
 * Class: ModSolarPanelEntity
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity.custom.solar_panel;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.energy.ModEnergyStorage;
import com.rgerva.infinitytech.gui.menu.ModSolarPanelMenu;
import com.rgerva.infinitytech.network.base.ModSyncPackages;
import com.rgerva.infinitytech.util.types.eSolarPanelConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ModSolarPanelEntity extends BlockEntity implements MenuProvider, ModSyncPackages {
    public final eSolarPanelConfigs panelConfigs;
    private final ModEnergyStorage ENERGY_STORAGE;

    public ModSolarPanelEntity(BlockPos pos, BlockState blockState, eSolarPanelConfigs eSolarPanelConfigs) {
        super(Objects.requireNonNull(getEntityType(eSolarPanelConfigs)), pos, blockState);
        this.panelConfigs = eSolarPanelConfigs;
        ENERGY_STORAGE = createEnergyStorage(eSolarPanelConfigs);
    }

    private ModEnergyStorage createEnergyStorage(eSolarPanelConfigs eSolarPanelConfigs) {
        return new ModEnergyStorage(eSolarPanelConfigs.getCapacity(), 0, eSolarPanelConfigs.getMaxTransfer(), 0) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                syncEnergyToPlayers(32);
                Objects.requireNonNull(getLevel()).sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }

            @Override
            public int getCapacity() {
                return Math.max(1, capacity);
            }

            @Override
            public int getMaxExtract() {
                return Math.max(1, maxExtract);
            }

            @Override
            public boolean canExtract() {
                return eSolarPanelConfigs.getMaxTransfer() != 0;
            }

            @Override
            public boolean canReceive() {
                return false;
            }
        };
    }

    public static BlockEntityType<ModSolarPanelEntity> getEntityType(eSolarPanelConfigs eSolarPanelConfigs){
        return switch (eSolarPanelConfigs){
            case solar_panel_1 -> ModBlockEntities.SOLAR_PANEL_ENTITY_1.get();
            case solar_panel_2 -> ModBlockEntities.SOLAR_PANEL_ENTITY_2.get();
            case solar_panel_3 -> ModBlockEntities.SOLAR_PANEL_ENTITY_3.get();
            case solar_panel_4 -> ModBlockEntities.SOLAR_PANEL_ENTITY_4.get();
            case solar_panel_5 -> ModBlockEntities.SOLAR_PANEL_ENTITY_5.get();
            case solar_panel_6 -> ModBlockEntities.SOLAR_PANEL_ENTITY_6.get();
        };
    }

    public eSolarPanelConfigs getPanelConfigs(){
        return panelConfigs;
    }

    public IEnergyStorage getEnergyStorageCapability(Direction direction) {
        if (direction == null || direction == Direction.DOWN){
            return this.ENERGY_STORAGE;
        }
        return null;
    }

    public int getEnergy(){
        return ENERGY_STORAGE.getEnergy();
    }

    public int getCapacity(){
        return ENERGY_STORAGE.getCapacity();
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
        return Component.translatable("machine.infinity_tech." + getPanelConfigs().name().toLowerCase());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        syncEnergyToPlayer(player);
        return new ModSolarPanelMenu(i, inventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("solar.energy", ENERGY_STORAGE.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        ENERGY_STORAGE.setEnergy(tag.getInt("solar.energy"));
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, ModSolarPanelEntity blockEntity){
        if (level.isClientSide){
            return;
        }

        int brightness = 4 * (level.getBrightness(LightLayer.SKY, blockPos) - level.getSkyDarken());
        float angle = level.getSunAngle(1.0F);
        if (brightness > 0) {

            float f1 = angle < (float) Math.PI ? 0.0F : ((float) Math.PI * 2F);

            angle += (f1 - angle) * 0.2F;

            brightness = Math.round((float) brightness * Mth.cos(angle));
        }

        brightness = Mth.clamp(brightness, 0, 60);

        int energyProduction = (int) (brightness / 60.f * blockEntity.getPanelConfigs().getPeakFePerTick());

        blockEntity.ENERGY_STORAGE.setEnergy(Math.min(blockEntity.ENERGY_STORAGE.getCapacity(), blockEntity.ENERGY_STORAGE.getEnergy() + energyProduction));

        transferEnergy(level, blockPos, state, blockEntity);
    }

    private static void transferEnergy(Level level, BlockPos blockPos, BlockState state, ModSolarPanelEntity blockEntity) {

        if(level.isClientSide){
            return;
        }

        BlockPos testPos = blockPos.relative(Direction.DOWN);
        BlockEntity testBlockEntity = level.getBlockEntity(testPos);

        IEnergyStorage energyStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, testPos,
                level.getBlockState(testPos), testBlockEntity, Direction.DOWN.getOpposite());
        if(energyStorage == null || !energyStorage.canReceive()){
            return;
        }

        int amount = energyStorage.receiveEnergy(Math.min(blockEntity.ENERGY_STORAGE.getEnergy(), blockEntity.ENERGY_STORAGE.getMaxExtract()), false);
        if(amount > 0){
            blockEntity.ENERGY_STORAGE.extractEnergy(amount, false);
        }
    }
}
