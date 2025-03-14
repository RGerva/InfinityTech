package com.rgerva.infinitytech.network.packet;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.network.base.IChest;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record StacksSyncS2CPacket(BlockPos blockPos,
                                  NonNullList<ItemStack> topItemStacks) implements CustomPacketPayload {

    public static final Type<StacksSyncS2CPacket> ID =
            new Type<>(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "top_stacks"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StacksSyncS2CPacket> STREAM_CODEC =
            CustomPacketPayload.codec(StacksSyncS2CPacket::write, StacksSyncS2CPacket::new);


    public StacksSyncS2CPacket(RegistryFriendlyByteBuf buf) {
        this(buf.readBlockPos(), NonNullList.withSize(ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buf).size(), ItemStack.EMPTY));
    }

    public void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeBlockPos(blockPos);
        ItemStack.OPTIONAL_STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buffer, this.topItemStacks);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void handle(StacksSyncS2CPacket data, IPayloadContext context) {
        if (context.flow().isClientbound()) {
            context.enqueueWork(() -> {
                Level level = context.player().level();

                BlockEntity blockEntity = level.getBlockEntity(data.blockPos);
                if (blockEntity != null) {
                    if (blockEntity instanceof IChest) {
                        ((IChest) blockEntity).receiveMessageFromServer(data.topItemStacks);
                        Minecraft.getInstance().levelRenderer.blockChanged(null, data.blockPos, null, null, 0);
                    }
                }
            });
        }
    }
}
