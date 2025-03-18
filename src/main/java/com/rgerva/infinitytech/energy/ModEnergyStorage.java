/**
 * Class: ModEnergyStorage
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.energy;

import com.rgerva.infinitytech.gui.base.ModEnergyContainerScreen;
import com.rgerva.infinitytech.network.ModMessages;
import com.rgerva.infinitytech.network.base.IEnergyStoragePacketUpdate;
import com.rgerva.infinitytech.network.packet.EnergySyncS2CPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.energy.EnergyStorage;
import net.neoforged.neoforge.energy.IEnergyStorage;

public abstract class ModEnergyStorage extends EnergyStorage implements IEnergyStoragePacketUpdate {

    /**
     *
     * @param capacity capacity of entity
     * @param maxReceive max receive transfer (For Generators = 0)
     * @param maxExtract max extract transfer (For Machines = 0)
     * @param energy energy already inside
     */
    public ModEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractedEnergy = super.extractEnergy(maxExtract, simulate);
        if(extractedEnergy != 0) {
            onEnergyChanged();
        }
        return extractedEnergy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receiveEnergy = super.receiveEnergy(maxReceive, simulate);
        if(receiveEnergy != 0) {
            onEnergyChanged();
        }
        return receiveEnergy;
    }

    public int getEnergy(){
        return energy;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public abstract void onEnergyChanged();

}
