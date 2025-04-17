/**
 * Class: ModSync
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.network.interfaces;

import com.rgerva.infinitytech.network.ModMessages;
import com.rgerva.infinitytech.network.packet.EnergySyncS2CPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface ModSyncPackages {
    BlockEntity getInterfaceSyncBlockEntity();

    int getInterfaceSyncEnergy();

    int getInterfaceSyncCapacity();

    default void syncEnergyToPlayer(Player player) {
        ModMessages.sendToPlayer(new EnergySyncS2CPacket(getInterfaceSyncEnergy(), getInterfaceSyncCapacity(),
                getInterfaceSyncBlockEntity().getBlockPos()), (ServerPlayer) player);
    }

    default void syncEnergyToPlayers(int distance) {
        Level level = getInterfaceSyncBlockEntity().getLevel();
        if (level != null && !level.isClientSide())
            ModMessages.sendToPlayersWithinXBlocks(
                    new EnergySyncS2CPacket(getInterfaceSyncEnergy(), getInterfaceSyncCapacity(), getInterfaceSyncBlockEntity().getBlockPos()),
                    getInterfaceSyncBlockEntity().getBlockPos(), (ServerLevel) level, distance
            );
    }
}