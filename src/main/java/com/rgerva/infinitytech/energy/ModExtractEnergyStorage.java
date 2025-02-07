package com.rgerva.infinitytech.energy;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;

public class ModExtractEnergyStorage implements IModEnergyStorage {
    protected int energy;
    protected int capacity;
    protected int maxExtract;

    protected void onChange(){}

    public ModExtractEnergyStorage() {}

    public ModExtractEnergyStorage(int energy, int capacity, int maxExtract) {
        this.energy = energy;
        this.capacity = capacity;
        this.maxExtract = maxExtract;
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

    public int getMaxExtract(){
        return maxExtract;
    }

    public void setMaxExtract(int maxExtract){
        this.maxExtract = maxExtract;
        onChange();
    }

    @Override
    public Tag saveNBT() {
        return IntTag.valueOf(energy);
    }

    @Override
    public void loadNBT(Tag tag) {
        if(!(tag instanceof IntTag)){
            energy = 0;
            return;
        }

        energy = ((IntTag)tag).getAsInt();
    }

    @Override
    public int receiveEnergy(int i, boolean b) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if(!canExtract()){
            return 0;
        }

        int extracted = Math.min(energy, Math.min(getMaxExtract(), maxExtract));
        if(!simulate){
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
        return false;
    }
}
