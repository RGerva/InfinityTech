package com.rgerva.infinitytech.network.base;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface IChest {
    NonNullList<ItemStack> getTopItems();
    void receiveMessageFromServer(NonNullList<ItemStack> topStacks);
}
