package com.rgerva.infinitytech.energy;

import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.energy.IEnergyStorage;

public interface IModEnergyStorage extends IEnergyStorage {
    int getEnergy();

    void setEnergy(int energy);

    void setEnergyWithoutUpdate(int energy);

    int getCapacity();

    void setCapacity(int capacity);

    void setCapacityWithoutUpdate(int capacity);

    Tag saveNBT();

    void loadNBT(Tag tag);
}
