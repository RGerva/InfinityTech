package com.rgerva.infinitytech.blockentity.custom.cables;

import com.mojang.datafixers.util.Pair;
import com.rgerva.infinitytech.block.custom.cables.CableBlock;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.config.ModConfiguration;
import com.rgerva.infinitytech.energy.ReceiveOnlyEnergyStorage;
import com.rgerva.infinitytech.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CableBlockEntity extends BlockEntity {
    private static final CableBlock.EnergyExtractionMode ENERGY_EXTRACTION_MODE = ModConfiguration.CABLES_ENERGY_EXTRACTION_MODE.get();

    private final ModUtils.eCablesConfigs eCablesConfigs;
    private final ReceiveOnlyEnergyStorage energyStorage;
    private boolean loaded;

    private final Map<Pair<BlockPos, Direction>, IEnergyStorage> producers = new HashMap<>();
    private final Map<Pair<BlockPos, Direction>, IEnergyStorage> consumers = new HashMap<>();
    private final List<BlockPos> cableBlocks = new LinkedList<>();

    public CableBlockEntity(BlockPos pos, BlockState blockState, ModUtils.eCablesConfigs eCablesConfigs) {
        super(getEntityType(eCablesConfigs), pos, blockState);
        this.eCablesConfigs = eCablesConfigs;

        if(ENERGY_EXTRACTION_MODE.isPush()){
            energyStorage = new ReceiveOnlyEnergyStorage(0, eCablesConfigs.getMaxTransfer(), eCablesConfigs.getMaxTransfer()){
                @Override
                protected void onChange() {
                    setChanged();
                }
            };
        }else{
            energyStorage = new ReceiveOnlyEnergyStorage(){
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    return 0;
                }

                @Override
                public boolean canReceive() {
                    return false;
                }
            };
        }
    }

    public ModUtils.eCablesConfigs getCableConfigs(){
        return eCablesConfigs;
    }

    public static BlockEntityType<CableBlockEntity> getEntityType(ModUtils.eCablesConfigs eCablesConfigs) {
        return switch(eCablesConfigs) {
            case TIN -> ModBlockEntities.TIN_CABLE_ENTITY.get();
            case COPPER -> ModBlockEntities.COPPER_CABLE_ENTITY.get();
            case GOLD -> ModBlockEntities.GOLD_CABLE_ENTITY.get();
        };
    }

    public Map<Pair<BlockPos, Direction>, IEnergyStorage> getProducers() {
        return producers;
    }

    public Map<Pair<BlockPos, Direction>, IEnergyStorage> getConsumers() {
        return consumers;
    }

    public List<BlockPos> getCableBlocks() {
        return cableBlocks;
    }

    public @Nullable IEnergyStorage getEnergyStorageCapability(@Nullable Direction side) {
        return energyStorage;
    }

    public static void updateConnections(Level level, BlockPos blockPos, BlockState state, CableBlockEntity blockEntity) {
        if(level.isClientSide){
            return;
        }

        blockEntity.producers.clear();
        blockEntity.consumers.clear();
        blockEntity.cableBlocks.clear();

        for(Direction direction:Direction.values()){
            BlockPos testPos = blockPos.relative(direction);
            BlockEntity testBlockEntity = level.getBlockEntity(testPos);

            if(testBlockEntity instanceof CableBlockEntity cableBlockEntity){
//                if(cableBlockEntity.getCableBlocks() != blockEntity.getCableBlocks()){
//                    continue;
//                }
                blockEntity.cableBlocks.add(testPos);
                continue;
            }

            IEnergyStorage iEnergyStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, testPos, level.getBlockState(testPos), testBlockEntity, direction.getOpposite());

            if(iEnergyStorage == null){
                continue;
            }

            if(ENERGY_EXTRACTION_MODE.isPull() && iEnergyStorage.canExtract()){
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

        while (!cableBlocksLeft.isEmpty()){
            BlockPos checkPos = cableBlocksLeft.pop();

            BlockEntity blockEntity = level.getBlockEntity(checkPos);
            if(!(blockEntity instanceof CableBlockEntity)){
                continue;
            }

            CableBlockEntity cableBlockEntity = (CableBlockEntity) blockEntity;
            cableBlockEntity.getCableBlocks().forEach(pos -> {
                if(!checkedCables.contains(pos)){
                    checkedCables.add(pos);
                    cableBlocksLeft.add(pos);
                }
            });

            consumers.addAll(cableBlockEntity.getConsumers().values());
        }
        return consumers;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, CableBlockEntity blockEntity) {
        if(level.isClientSide){
            return;
        }

        if(!blockEntity.loaded){
            updateConnections(level, blockPos, state, blockEntity);
            blockEntity.loaded = true;
        }

        final int MAX_TRANSFER = blockEntity.eCablesConfigs.getMaxTransfer();
        List<IEnergyStorage> energyProduction = new LinkedList<>();
        List<Integer> energyProductionValues = new LinkedList<>();

        int productionSum = blockEntity.energyStorage.getEnergy();
        for(IEnergyStorage iEnergyStorage:blockEntity.producers.values()){
            int extracted = iEnergyStorage.extractEnergy(MAX_TRANSFER, true);
            if(extracted <= 0){
                continue;
            }

            energyProduction.add(iEnergyStorage);
            energyProductionValues.add(extracted);
            productionSum += extracted;
        }

        if(productionSum <= 0){
            return;
        }

        List<IEnergyStorage> energyConsumption = new LinkedList<>();
        List<Integer> energyConsumptionValues = new LinkedList<>();
        List<IEnergyStorage> consumers = getConnectedConsumers(level, blockPos, new LinkedList<>());

        int consumptionSum = 0;
        for(IEnergyStorage iEnergyStorage:consumers){
            int received = iEnergyStorage.receiveEnergy(MAX_TRANSFER, true);
            if(received <= 0){
                continue;
            }

            energyConsumption.add(iEnergyStorage);
            energyConsumptionValues.add(received);
            consumptionSum += received;
        }

        if(consumptionSum <= 0){
            return;
        }

        int transferLeft = Math.min(Math.min(MAX_TRANSFER, productionSum), consumptionSum);
        int extractInternally = 0;
        if(ENERGY_EXTRACTION_MODE.isPush()){
            extractInternally = Math.min(blockEntity.energyStorage.getEnergy(), transferLeft);
            blockEntity.energyStorage.setEnergy(blockEntity.energyStorage.getEnergy() - extractInternally);
        }

        List<Integer> energyProductionDistributed = new LinkedList<>();
        for(int i = 0; i < energyProduction.size(); i++){
            energyProductionDistributed.add(0);
        }

        //Set to 0 for PUSH only mode
        int productionLeft = ENERGY_EXTRACTION_MODE.isPull()?transferLeft - extractInternally:0;
        int divisor = energyProduction.size();
        outer:
        while (productionLeft > 0){
            int productionPerProducer = productionLeft / divisor;
            if(productionPerProducer == 0) {
                divisor = Math.max(1, divisor - 1);
                productionPerProducer = productionLeft / divisor;
            }

            for(int i = 0; i < energyConsumptionValues.size(); i++){
                int productionDistributed = energyProductionDistributed.get(i);
                int productionOfProducerLeft = energyProductionValues.get(i) - productionDistributed;

                int productionDistributedNew = Math.min(productionPerProducer, Math.min(productionOfProducerLeft, productionLeft));
                energyProductionDistributed.set(i, productionDistributed + productionDistributedNew);
                productionLeft -= productionDistributedNew;

                if(productionLeft == 0){
                    break outer;
                }
            }
        }

        for(int i = 0; i < energyProduction.size(); i++){
            int energy = energyProductionDistributed.get(i);
            if(energy > 0){
                energyProduction.get(i).extractEnergy(energy, false);
            }
        }

        List<Integer> energyConsumptionDistributed = new LinkedList<>();
        for(int i = 0;i < energyConsumption.size();i++){
            energyConsumptionDistributed.add(0);
        }

        int consumptionLeft = transferLeft;
        divisor = energyConsumption.size();
        outer:
        while (consumptionLeft > 0){
            int consumptionPerConsumer = consumptionLeft / divisor;
            if(consumptionPerConsumer == 0) {
                divisor = Math.max(1, divisor - 1);
                consumptionPerConsumer = consumptionLeft / divisor;
            }

            for(int i = 0;i < energyConsumptionValues.size();i++) {
                int consumptionDistributed = energyConsumptionDistributed.get(i);
                int consumptionOfConsumerLeft = energyConsumptionValues.get(i) - consumptionDistributed;

                int consumptionDistributedNew = Math.min(consumptionOfConsumerLeft, Math.min(consumptionPerConsumer, consumptionLeft));
                energyConsumptionDistributed.set(i, consumptionDistributed + consumptionDistributedNew);
                consumptionLeft -= consumptionDistributedNew;
                if(consumptionLeft == 0){
                    break outer;
                }
            }
        }

        for(int i = 0;i < energyConsumption.size();i++) {
            int energy = energyConsumptionDistributed.get(i);
            if(energy > 0){
                energyConsumption.get(i).receiveEnergy(energy, false);
            }
        }

    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        if(ENERGY_EXTRACTION_MODE.isPush()){
            tag.put("energy", energyStorage.saveNBT());
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if(ENERGY_EXTRACTION_MODE.isPush()){
            energyStorage.loadNBT(tag.get("energy"));
        }
    }
}
