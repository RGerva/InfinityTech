package com.rgerva.infinitytech.network.base;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

/**
 * Used for StacksSyncS2CPacket
 */

public interface IChestPackageUpdate {
    NonNullList<ItemStack> getTopItems();

    void receiveMessageFromServer(NonNullList<ItemStack> topStacks);
}
