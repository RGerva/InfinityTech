package com.rgerva.infinitytech.energy.base;

import com.rgerva.infinitytech.energy.IModEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ModEnergyStorageBlockEntity<E extends IModEnergyStorage> extends BlockEntity implements IModEnergyStoragePacketUpdate {

    protected final E energyStorage;
    protected final int baseEnergyCapacity;
    protected final int baseEnergyTransferRate;

    public ModEnergyStorageBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, int baseEnergyCapacity, int baseEnergyTransferRate) {
        super(type, pos, blockState);
        energyStorage = initEnergyStorage();
        this.baseEnergyCapacity = baseEnergyCapacity;
        this.baseEnergyTransferRate = baseEnergyTransferRate;
    }

    protected abstract E initEnergyStorage();

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("energy", energyStorage.saveNBT());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        energyStorage.loadNBT(tag.get("energy"));
    }

    protected final void syncEnergyToPlayer(Player player) {

    }

    protected final void syncEnergyToPlayers(int distance) {

    }

    public int getEnergy() {
        return energyStorage.getEnergy();
    }

    public int getCapacity() {
        return energyStorage.getCapacity();
    }

    @Override
    public void setEnergy(int energy) {
        energyStorage.setEnergyWithoutUpdate(energy);
    }

    @Override
    public void setCapacity(int capacity) {
        energyStorage.setCapacityWithoutUpdate(capacity);
    }
}
