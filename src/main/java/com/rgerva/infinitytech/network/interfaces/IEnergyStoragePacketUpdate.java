package com.rgerva.infinitytech.network.interfaces;

/**
 * Used for EnergySyncS2CPacket
 */

public interface IEnergyStoragePacketUpdate {
    void setEnergy(int energy);

    void setCapacity(int capacity);
}
