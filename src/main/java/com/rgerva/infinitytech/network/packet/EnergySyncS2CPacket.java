package com.rgerva.infinitytech.network.packet;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.energy.IEnergyStoragePacketUpdate;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record EnergySyncS2CPacket(int energy, int capacity, BlockPos pos) implements CustomPacketPayload {
    public static final Type<EnergySyncS2CPacket> ID =
            new Type<>(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID,"energy_sync"));
    public static final StreamCodec<RegistryFriendlyByteBuf, EnergySyncS2CPacket> STREAM_CODEC =
            StreamCodec.ofMember(EnergySyncS2CPacket::write, EnergySyncS2CPacket::new);

    public EnergySyncS2CPacket(RegistryFriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readInt(), buffer.readBlockPos());
    }

    public void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeInt(energy);
        buffer.writeInt(capacity);
        buffer.writeBlockPos(pos);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void handle(EnergySyncS2CPacket data, IPayloadContext context) {
        context.enqueueWork(() -> {
            BlockEntity blockEntity = context.player().level().getBlockEntity(data.pos);

            //BlockEntity
            if(blockEntity instanceof IEnergyStoragePacketUpdate) {
                IEnergyStoragePacketUpdate energyStorage = (IEnergyStoragePacketUpdate)blockEntity;
                energyStorage.setCapacity(data.capacity);
                energyStorage.setEnergy(data.energy);
            }
        });
    }
}
