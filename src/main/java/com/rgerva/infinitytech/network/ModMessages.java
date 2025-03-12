package com.rgerva.infinitytech.network;

import com.rgerva.infinitytech.network.packet.EnergySyncS2CPacket;
import com.rgerva.infinitytech.network.packet.StacksSyncS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModMessages {
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1.0");

        registrar.playToClient(EnergySyncS2CPacket.ID, EnergySyncS2CPacket.STREAM_CODEC, EnergySyncS2CPacket::handle);
        registrar.playBidirectional(StacksSyncS2CPacket.ID, StacksSyncS2CPacket.STREAM_CODEC, StacksSyncS2CPacket::handle);
    }

    public static void sendToServer(CustomPacketPayload message) {
        PacketDistributor.sendToServer(message);
    }

    public static void sendToPlayer(CustomPacketPayload message, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, message);
    }

    public static void sendToPlayersWithinXBlocks(CustomPacketPayload message, BlockPos pos, ServerLevel level, int distance) {
        PacketDistributor.sendToPlayersNear(level, null, pos.getX(), pos.getY(), pos.getZ(), distance, message);
    }
}
