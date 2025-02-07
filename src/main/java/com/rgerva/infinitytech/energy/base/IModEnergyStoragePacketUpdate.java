package com.rgerva.infinitytech.energy.base;

/**
 * Used for EnergySyncS2CPacket
 */
public interface IModEnergyStoragePacketUpdate {
    void setEnergy(int energy);
    void setCapacity(int capacity);
}
