package com.rgerva.infinitytech.blockentity.custom;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.base.MenuEnergyStorageBlockEntity;
import com.rgerva.infinitytech.energy.ModEnergyUtils;
import com.rgerva.infinitytech.energy.ReceiveAndExtractEnergyStorage;
import com.rgerva.infinitytech.gui.menu.BatteryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class BatteryBlockEntity extends MenuEnergyStorageBlockEntity<ReceiveAndExtractEnergyStorage> {
    public static final int CAPACITY = 65536;
    public static final int MAX_TRANSFER = 2048;

    public BatteryBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.BATTERY_BOX_ENTITY.get(), blockPos, blockState, "battery_block", CAPACITY, MAX_TRANSFER);
    }

    @Override
    protected ReceiveAndExtractEnergyStorage initEnergyStorage() {
        return new ReceiveAndExtractEnergyStorage(0, baseEnergyCapacity, baseEnergyTransferRate){
            @Override
            protected void onChange() {
                setChanged();
                syncEnergyToPlayers(32);
            }
        };
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        syncEnergyToPlayer(player);
        return new BatteryMenu(i, inventory, this);
    }

    public int getRedstoneOutput() {
        return ModEnergyUtils.getRedstoneSignalFromEnergyStorage(energyStorage);
    }

    public @Nullable IEnergyStorage getEnergyStorageCapability(@Nullable Direction side) {
        return energyStorage;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, BatteryBlockEntity blockEntity) {
        if(level.isClientSide)
            return;

        transferEnergy(level, blockPos, state, blockEntity);
    }

    private static void transferEnergy(Level level, BlockPos blockPos, BlockState state, BatteryBlockEntity blockEntity) {
        if(level.isClientSide)
            return;

        List<IEnergyStorage> consumerItems = new LinkedList<>();
        List<Integer> consumerEnergyValues = new LinkedList<>();
        int consumptionSum = 0;
        for(Direction direction:Direction.values()) {
            BlockPos testPos = blockPos.relative(direction);

            BlockEntity testBlockEntity = level.getBlockEntity(testPos);

            IEnergyStorage energyStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, testPos,
                    level.getBlockState(testPos), testBlockEntity, direction.getOpposite());
            if(energyStorage == null || !energyStorage.canReceive())
                continue;

            int received = energyStorage.receiveEnergy(Math.min(blockEntity.energyStorage.getMaxTransfer(),
                    blockEntity.energyStorage.getEnergy()), true);
            if(received <= 0)
                continue;

            consumptionSum += received;
            consumerItems.add(energyStorage);
            consumerEnergyValues.add(received);
        }

        List<Integer> consumerEnergyDistributed = new LinkedList<>();
        for(int i = 0;i < consumerItems.size();i++)
            consumerEnergyDistributed.add(0);

        int consumptionLeft = Math.min(blockEntity.energyStorage.getMaxTransfer(),
                Math.min(blockEntity.energyStorage.getEnergy(), consumptionSum));
        blockEntity.energyStorage.extractEnergy(consumptionLeft, false);

        int divisor = consumerItems.size();
        outer:
        while(consumptionLeft > 0) {
            int consumptionPerConsumer = consumptionLeft / divisor;
            if(consumptionPerConsumer == 0) {
                divisor = Math.max(1, divisor - 1);
                consumptionPerConsumer = consumptionLeft / divisor;
            }

            for(int i = 0;i < consumerEnergyValues.size();i++) {
                int consumptionDistributed = consumerEnergyDistributed.get(i);
                int consumptionOfConsumerLeft = consumerEnergyValues.get(i) - consumptionDistributed;

                int consumptionDistributedNew = Math.min(consumptionOfConsumerLeft, Math.min(consumptionPerConsumer, consumptionLeft));
                consumerEnergyDistributed.set(i, consumptionDistributed + consumptionDistributedNew);
                consumptionLeft -= consumptionDistributedNew;
                if(consumptionLeft == 0)
                    break outer;
            }
        }

        for(int i = 0;i < consumerItems.size();i++) {
            int energy = consumerEnergyDistributed.get(i);
            if(energy > 0)
                consumerItems.get(i).receiveEnergy(energy, false);
        }
    }
}
