package com.rgerva.infinitytech.blockentity.custom.cables;

import com.rgerva.infinitytech.block.custom.cables.ModCableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ModCableEntity extends BlockEntity {

    protected boolean[] extractingSides;
    protected boolean[] disconnectedSides;

    public ModCableEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        extractingSides = new boolean[Direction.values().length];
        disconnectedSides = new boolean[Direction.values().length];
    }

    public static void markCablesDirty(Level level, BlockPos pos) {
        List<BlockPos> travelPositions = new ArrayList<>();
        LinkedList<BlockPos> queue = new LinkedList<>();
        Block block = level.getBlockState(pos).getBlock();
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(block instanceof ModCableBlock) || (!(blockEntity instanceof ModCableEntity))) {
            return;
        }

        for (Direction side : Direction.values()) {
            if (((ModCableEntity) blockEntity).isExtracting(side)) {
                if (((ModCableBlock) block).isConnected(level, pos, side)) {
                    ((ModCableEntity) blockEntity).setExtracting(side, false);
                    if (!((ModCableEntity) blockEntity).hasReasonToStay()) {
                        ((ModCableBlock) block).setHasData(level, pos, false);
                    }
                    ((ModCableEntity) blockEntity).syncData();
                }
            }
        }

        travelPositions.add(pos);
        addToDirtyList(level, pos, (ModCableBlock) block, travelPositions, queue);
        while (queue.size() > 0) {
            BlockPos blockPos = queue.removeFirst();
            block = level.getBlockState(blockPos).getBlock();
            if (block instanceof ModCableBlock) {
                addToDirtyList(level, blockPos, (ModCableBlock) block, travelPositions, queue);
            }
        }

        for (BlockPos p : travelPositions) {
            BlockEntity te = level.getBlockEntity(p);
            if (!(te instanceof ModCableEntity)) {
                continue;
            }
            ModCableEntity cable = (ModCableEntity) te;
            //TODO cable.connectionCache = null;
        }
    }

    private static void addToDirtyList(Level level, BlockPos pos, ModCableBlock block, List<BlockPos> travelPositions, LinkedList<BlockPos> queue) {
        for (Direction direction : Direction.values()) {
            if (block.isConnected(level, pos, direction)) {
                BlockPos p = pos.relative(direction);
                if (!travelPositions.contains(p) && !queue.contains(p)) {
                    travelPositions.add(p);
                    queue.add(p);
                }
            }
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        extractingSides = new boolean[Direction.values().length];
        ListTag extractingList = tag.getList("cable.extracting_sides", Tag.TAG_BYTE);
        if (extractingList.size() >= extractingSides.length) {
            for (int i = 0; i < extractingSides.length; i++) {
                ByteTag b = (ByteTag) extractingList.get(i);
                extractingSides[i] = b.getAsByte() != 0;
            }
        }

        disconnectedSides = new boolean[Direction.values().length];
        ListTag disconnectedList = tag.getList("cable.disconnected_sides", Tag.TAG_BYTE);
        if (disconnectedList.size() >= disconnectedSides.length) {
            for (int i = 0; i < disconnectedSides.length; i++) {
                ByteTag b = (ByteTag) disconnectedList.get(i);
                disconnectedSides[i] = b.getAsByte() != 0;
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        ListTag extractingList = new ListTag();
        for (boolean extractingSide : extractingSides) {
            extractingList.add(ByteTag.valueOf(extractingSide));
        }
        tag.put("cable.extracting_sides", extractingList);

        ListTag disconnectedList = new ListTag();
        for (boolean disconnected : disconnectedSides) {
            disconnectedList.add(ByteTag.valueOf(disconnected));
        }
        tag.put("cable.disconnected_sides", disconnectedList);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public boolean isDisconnected(Direction facing) {
        return disconnectedSides[facing.get3DDataValue()];
    }

    public boolean isExtracting(Direction side) {
        return extractingSides[side.get3DDataValue()];
    }

    public boolean isExtracting() {
        for (boolean extract : extractingSides) {
            if (extract) {
                return true;
            }
        }
        return false;
    }

    public void setExtracting(Direction side, boolean extracting) {
        extractingSides[side.get3DDataValue()] = extracting;
        //extractingConnectionCache = null;
        setChanged();
    }

    private boolean hasReasonToStay() {
        if (isExtracting()) {
            return true;
        }
        for (boolean disconnected : disconnectedSides) {
            if (disconnected) {
                return true;
            }
        }
        return false;
    }

    private void syncData() {
        if (level == null || level.isClientSide) {
            return;
        }
        LevelChunk chunk = level.getChunkAt(getBlockPos());
        ((ServerChunkCache) level.getChunkSource()).chunkMap.getPlayers(chunk.getPos(), false).forEach(e -> e.connection.send(getUpdatePacket()));
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, ModCableEntity cableEntity){

    }
}
