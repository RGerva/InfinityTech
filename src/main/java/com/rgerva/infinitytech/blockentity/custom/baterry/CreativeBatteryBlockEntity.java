package com.rgerva.infinitytech.blockentity.custom.baterry;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.base.MenuEnergyStorageBlockEntity;
import com.rgerva.infinitytech.energy.InfinityEnergyStorage;
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

public class CreativeBatteryBlockEntity extends MenuEnergyStorageBlockEntity<InfinityEnergyStorage> {

    public CreativeBatteryBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CREATIVE_BATTERY_ENTITY.get(), blockPos, blockState, "creative_battery", Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    protected InfinityEnergyStorage initEnergyStorage() {
        return new InfinityEnergyStorage() {
            @Override
            public int extractEnergy(int i, boolean b) {
                return super.extractEnergy(i, b);
            }

            @Override
            public int receiveEnergy(int i, boolean b) {
                return super.receiveEnergy(i, b);
            }

            @Override
            protected void onChange() {
                setChanged();
            }
        };
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return null;
    }

    public @Nullable IEnergyStorage getEnergyStorageCapability(@Nullable Direction side) {
        return energyStorage;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, CreativeBatteryBlockEntity blockEntity) {
        if(level.isClientSide)
            return;

        transferInfiniteEnergy(level, blockPos, state, blockEntity);
    }

    private static void transferInfiniteEnergy(Level level, BlockPos blockPos, BlockState state, CreativeBatteryBlockEntity blockEntity) {
        if(level.isClientSide)
            return;

        for(Direction direction:Direction.values()) {
            BlockPos testPos = blockPos.relative(direction);

            BlockEntity testBlockEntity = level.getBlockEntity(testPos);

            IEnergyStorage energyStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, testPos,
                    level.getBlockState(testPos), testBlockEntity, direction.getOpposite());
            if(energyStorage == null || !energyStorage.canReceive())
                continue;

            int received = energyStorage.receiveEnergy(energyStorage.getMaxEnergyStored(), true);
            energyStorage.receiveEnergy(received, false);
        }
    }
}
