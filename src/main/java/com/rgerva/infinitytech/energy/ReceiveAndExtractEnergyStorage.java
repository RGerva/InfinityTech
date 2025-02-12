package com.rgerva.infinitytech.energy;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;

public class ReceiveAndExtractEnergyStorage implements IModEnergyStorage {
    protected int energy;
    protected int capacity;
    protected int maxTransfer;

    public ReceiveAndExtractEnergyStorage(int energy, int capacity, int maxTransfer) {
        this.energy = energy;
        this.capacity = capacity;
        this.maxTransfer = maxTransfer;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = energy;
        onChange();
    }

    @Override
    public void setEnergyWithoutUpdate(int energy) {
        this.energy = energy;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
        onChange();
    }

    @Override
    public void setCapacityWithoutUpdate(int capacity) {
        this.capacity = capacity;
    }

    public int getMaxTransfer() {
        return maxTransfer;
    }

    public void setMaxTransfer(int maxTransfer) {
        this.maxTransfer = maxTransfer;
        onChange();
    }

    public void setMaxTransferWithoutUpdate(int maxTransfer) {
        this.maxTransfer = maxTransfer;
    }

    protected void onChange() {}

    @Override
    public Tag saveNBT() {
        return IntTag.valueOf(energy);
    }

    @Override
    public void loadNBT(Tag tag) {
        if(!(tag instanceof IntTag)) {
            energy = 0;

            return;
        }

        energy = ((IntTag)tag).getAsInt();
    }

    @Override
    public int receiveEnergy(int i, boolean b) {
        if(!canReceive())
            return 0;

        int received = Math.min(getMaxEnergyStored() - energy, Math.min(getMaxTransfer(), i));

        if(!b) {
            energy += received;
            onChange();
        }

        return received;
    }

    @Override
    public int extractEnergy(int i, boolean b) {
        if(!canExtract())
            return 0;

        int extracted = Math.min(energy, Math.min(getMaxTransfer(), i));

        if(!b) {
            energy -= extracted;
            onChange();
        }

        return extracted;
    }

    @Override
    public int getEnergyStored() {
        return getEnergy();
    }

    @Override
    public int getMaxEnergyStored() {
        return getCapacity();
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
