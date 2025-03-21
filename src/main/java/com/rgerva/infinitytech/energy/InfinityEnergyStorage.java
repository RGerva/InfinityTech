package com.rgerva.infinitytech.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class InfinityEnergyStorage implements IModEnergyStorage {
    @Override
    public int getEnergy() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void setEnergy(int energy) {

    }

    @Override
    public void setEnergyWithoutUpdate(int energy) {

    }

    @Override
    public int getCapacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void setCapacity(int capacity) {

    }

    @Override
    public void setCapacityWithoutUpdate(int capacity) {

    }

    public int getMaxTransfer() {
        return Integer.MAX_VALUE;
    }

    protected void onChange() {
    }

    @Override
    public Tag saveNBT() {
        return new CompoundTag();
    }

    @Override
    public void loadNBT(Tag tag) {

    }

    @Override
    public int receiveEnergy(int i, boolean b) {
        return i;
    }

    @Override
    public int extractEnergy(int i, boolean b) {
        return i;
    }

    @Override
    public int getEnergyStored() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxEnergyStored() {
        return Integer.MAX_VALUE;
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
