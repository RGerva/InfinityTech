/**
 * Class: ModCableEntity
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity.custom.cables;

import com.mojang.datafixers.util.Pair;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.energy.ModEnergyStorage;
import com.rgerva.infinitytech.network.interfaces.ModSyncPackages;
import com.rgerva.infinitytech.util.types.eCablesConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ModCableEntity extends BlockEntity implements ModSyncPackages {
    private static eCablesConfigs.ExtractionMode EXTRACTION_MODE = eCablesConfigs.ExtractionMode.BOTH;

    private final eCablesConfigs cablesConfigs;
    private final ModEnergyStorage ENERGY_STORAGE;

    private final Map<Pair<BlockPos, Direction>, IEnergyStorage> producers = new HashMap<>();
    private final Map<Pair<BlockPos, Direction>, IEnergyStorage> consumers = new HashMap<>();
    private final List<BlockPos> cableBlocks = new LinkedList<>();
    private boolean loaded;

    public ModCableEntity(BlockPos pos, BlockState blockState, eCablesConfigs cablesConfigs) {
        super(Objects.requireNonNull(getEntityType(cablesConfigs)), pos, blockState);
        this.cablesConfigs = cablesConfigs;
        ENERGY_STORAGE = createEnergyStorage(cablesConfigs);
    }

    private ModEnergyStorage createEnergyStorage(eCablesConfigs cablesConfigs) {
        if(EXTRACTION_MODE.isPush()){
            return new ModEnergyStorage(0, cablesConfigs.getMaxTransfer(), 0, 0) {
                @Override
                public void onEnergyChanged() {
                    setChanged();
                    syncEnergyToPlayers(32);
                    Objects.requireNonNull(getLevel()).sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            };
        }else{
            return new ModEnergyStorage(0, 0, cablesConfigs.getMaxTransfer(), 0) {
                @Override
                public void onEnergyChanged() {
                    setChanged();
                    syncEnergyToPlayers(32);
                    Objects.requireNonNull(getLevel()).sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            };
        }
    }

    public eCablesConfigs getCablesConfigs(){
        return cablesConfigs;
    }

    public List<BlockPos> getCableBlocks() {
        return cableBlocks;
    }

    public Map<Pair<BlockPos, Direction>, IEnergyStorage> getConsumers() {
        return consumers;
    }

    public eCablesConfigs.ExtractionMode getExtractionMode(){
        return EXTRACTION_MODE;
    }

    public void setExtractionMode(eCablesConfigs.ExtractionMode extractionMode){
        EXTRACTION_MODE = extractionMode.setPush();
    }

    public static BlockEntityType<ModCableEntity> getEntityType(eCablesConfigs eCablesConfigs) {
        return switch (eCablesConfigs) {
            case TIN -> ModBlockEntities.TIN_CABLE_ENTITY.get();
            case COPPER -> ModBlockEntities.COPPER_CABLE_ENTITY.get();
            case GOLD -> ModBlockEntities.GOLD_CABLE_ENTITY.get();
        };
    }

    public @Nullable IEnergyStorage getEnergyStorageCapability(@Nullable Direction side) {
        return ENERGY_STORAGE;
    }

    public int getEnergy(){
        return ENERGY_STORAGE.getEnergy();
    }

    public int getCapacity(){
        return ENERGY_STORAGE.getCapacity();
    }

    @Override
    public BlockEntity getInterfaceSyncBlockEntity() {
        return this;
    }

    @Override
    public int getInterfaceSyncEnergy() {
        return getEnergy();
    }

    @Override
    public int getInterfaceSyncCapacity() {
        return getCapacity();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if(EXTRACTION_MODE.isPush()){
            tag.putInt("cable.energy", ENERGY_STORAGE.getEnergyStored());
        }

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if(EXTRACTION_MODE.isPush()){
            ENERGY_STORAGE.setEnergy(tag.getInt("cable.energy"));
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    public static void updateConnections(Level level, BlockPos blockPos, BlockState state, ModCableEntity blockEntity) {
        if(level.isClientSide){
            return;
        }

        blockEntity.producers.clear();
        blockEntity.consumers.clear();
        blockEntity.cableBlocks.clear();

        for(Direction direction : Direction.values()){
            BlockPos testPos = blockPos.relative(direction);
            BlockEntity testBlockEntity = level.getBlockEntity(testPos);

            if(testBlockEntity instanceof ModCableEntity cableEntity){
                blockEntity.cableBlocks.add(testPos);
                continue;
            }

            IEnergyStorage iEnergyStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, testPos, level.getBlockState(testPos), testBlockEntity, direction.getOpposite());

            if(iEnergyStorage == null){
                continue;
            }

            if(EXTRACTION_MODE.isPull() && iEnergyStorage.canExtract()){
                blockEntity.producers.put(Pair.of(testPos, direction.getOpposite()), iEnergyStorage);
            }

            if(iEnergyStorage.canReceive()){
                blockEntity.consumers.put(Pair.of(testPos, direction.getOpposite()), iEnergyStorage);
            }
        }
    }

    public static List<IEnergyStorage> getConnectedConsumers(Level level, BlockPos blockPos, List<BlockPos> checkedCables) {
        List<IEnergyStorage> consumers = new LinkedList<>();
        LinkedList<BlockPos> cableBlocksLeft = new LinkedList<>();

        cableBlocksLeft.add(blockPos);
        checkedCables.add(blockPos);

        while(!cableBlocksLeft.isEmpty()){
            BlockPos checkPos = cableBlocksLeft.pop();

            BlockEntity blockEntity = level.getBlockEntity(checkPos);
            if(!(blockEntity instanceof ModCableEntity cableEntity)){
                continue;
            }

            cableEntity.getCableBlocks().forEach(pos -> {
               if(!checkedCables.contains(pos)) {
                   checkedCables.add(pos);
                   cableBlocksLeft.add(pos);
               }
            });

            consumers.addAll(cableEntity.getConsumers().values());
        }
        return consumers;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, ModCableEntity blockEntity) {
        if (level.isClientSide) {
            return;
        }

        if (!blockEntity.loaded) {
            updateConnections(level, blockPos, state, blockEntity);
            blockEntity.loaded = true;
        }

        final int MAX_TRANSFER = blockEntity.cablesConfigs.getMaxTransfer();
        List<IEnergyStorage> energyProduction = new LinkedList<>();
        List<Integer> energyProductionValues = new LinkedList<>();

        int productionSum = blockEntity.ENERGY_STORAGE.getEnergy();
        for (IEnergyStorage iEnergyStorage : blockEntity.producers.values()) {
            int extracted = iEnergyStorage.extractEnergy(MAX_TRANSFER, true);
            if (extracted <= 0) {
                continue;
            }

            energyProduction.add(iEnergyStorage);
            energyProductionValues.add(extracted);
            productionSum += extracted;
        }

        if (productionSum <= 0) {
            return;
        }

        List<IEnergyStorage> energyConsumption = new LinkedList<>();
        List<Integer> energyConsumptionValues = new LinkedList<>();
        List<IEnergyStorage> consumers = getConnectedConsumers(level, blockPos, new LinkedList<>());


        int consumptionSum = 0;
        for (IEnergyStorage iEnergyStorage : consumers) {
            int received = iEnergyStorage.receiveEnergy(MAX_TRANSFER, true);
            if (received <= 0) {
                continue;
            }

            energyConsumption.add(iEnergyStorage);
            energyConsumptionValues.add(received);
            consumptionSum += received;
        }

        if (consumptionSum <= 0) {
            return;
        }

        int transferLeft = Math.min(Math.min(MAX_TRANSFER, productionSum), consumptionSum);
        int extractInternally = 0;
        if (EXTRACTION_MODE.isPush()) {
            extractInternally = Math.min(blockEntity.ENERGY_STORAGE.getEnergy(), transferLeft);
            blockEntity.ENERGY_STORAGE.setEnergy(blockEntity.ENERGY_STORAGE.getEnergy() - extractInternally);
        }

        List<Integer> energyProductionDistributed = new LinkedList<>();
        for (int i = 0; i < energyProduction.size(); i++) {
            energyProductionDistributed.add(0);
        }

        //Set to 0 for PUSH only mode
        int productionLeft = EXTRACTION_MODE.isPull() ? transferLeft - extractInternally : 0;
        int divisor = energyProduction.size();
        outer:
        while (productionLeft > 0) {
            int productionPerProducer = productionLeft / divisor;
            if (productionPerProducer == 0) {
                divisor = Math.max(1, divisor - 1);
                productionPerProducer = productionLeft / divisor;
            }

            for (int i = 0; i < energyConsumptionValues.size(); i++) {
                int productionDistributed = energyProductionDistributed.get(i);
                int productionOfProducerLeft = energyProductionValues.get(i) - productionDistributed;

                int productionDistributedNew = Math.min(productionPerProducer, Math.min(productionOfProducerLeft, productionLeft));
                energyProductionDistributed.set(i, productionDistributed + productionDistributedNew);
                productionLeft -= productionDistributedNew;

                if (productionLeft == 0) {
                    break outer;
                }
            }
        }

        for (int i = 0; i < energyProduction.size(); i++) {
            int energy = energyProductionDistributed.get(i);
            if (energy > 0) {
                energyProduction.get(i).extractEnergy(energy, false);
            }
        }

        List<Integer> energyConsumptionDistributed = new LinkedList<>();
        for (int i = 0; i < energyConsumption.size(); i++) {
            energyConsumptionDistributed.add(0);
        }

        int consumptionLeft = transferLeft;
        divisor = energyConsumption.size();
        outer:
        while (consumptionLeft > 0) {
            int consumptionPerConsumer = consumptionLeft / divisor;
            if (consumptionPerConsumer == 0) {
                divisor = Math.max(1, divisor - 1);
                consumptionPerConsumer = consumptionLeft / divisor;
            }

            for (int i = 0; i < energyConsumptionValues.size(); i++) {
                int consumptionDistributed = energyConsumptionDistributed.get(i);
                int consumptionOfConsumerLeft = energyConsumptionValues.get(i) - consumptionDistributed;

                int consumptionDistributedNew = Math.min(consumptionOfConsumerLeft, Math.min(consumptionPerConsumer, consumptionLeft));
                energyConsumptionDistributed.set(i, consumptionDistributed + consumptionDistributedNew);
                consumptionLeft -= consumptionDistributedNew;
                if (consumptionLeft == 0) {
                    break outer;
                }
            }
        }

        for (int i = 0; i < energyConsumption.size(); i++) {
            int energy = energyConsumptionDistributed.get(i);
            if (energy > 0) {
                energyConsumption.get(i).receiveEnergy(energy, false);
            }
        }

    }
}
