package com.rgerva.infinitytech.blockentity.custom.base;

import com.rgerva.infinitytech.energy.IEnergyStoragePacketUpdate;
import com.rgerva.infinitytech.energy.IModEnergyStorage;
import com.rgerva.infinitytech.network.ModMessages;
import com.rgerva.infinitytech.network.packet.EnergySyncS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class EnergyStorageBlockEntity<E extends IModEnergyStorage> extends BlockEntity implements IEnergyStoragePacketUpdate {
    protected final E energyStorage;
    protected final int baseEnergyCapacity;
    protected final int baseEnergyTransferRate;

    public EnergyStorageBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState, int baseEnergyCapacity, int baseEnergyTransferRate) {
        super(type, blockPos, blockState);

        this.baseEnergyCapacity = baseEnergyCapacity;
        this.baseEnergyTransferRate = baseEnergyTransferRate;
        energyStorage = initEnergyStorage();
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
        ModMessages.sendToPlayer(new EnergySyncS2CPacket(energyStorage.getEnergy(), energyStorage.getCapacity(),
                getBlockPos()), (ServerPlayer)player);
    }

    protected final void syncEnergyToPlayers(int distance) {
        if(level != null && !level.isClientSide())
            ModMessages.sendToPlayersWithinXBlocks(
                    new EnergySyncS2CPacket(energyStorage.getEnergy(), energyStorage.getCapacity(), getBlockPos()),
                    getBlockPos(), (ServerLevel)level, distance
            );
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
