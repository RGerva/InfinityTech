package com.rgerva.infinitytech.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

import java.util.Locale;

public class ModEnergyUtils {
    private static final String[] ENERGY_PREFIXES = new String[]{
            "", "k", "M", "G", "T", "P"
    };

    public static String getEnergyWithPrefix(long energy) {
        if (energy < 1000)
            return String.format(Locale.ENGLISH, "%d FE", energy);

        double energyWithPrefix = energy;

        int prefixIndex = 0;
        while (((int) energyWithPrefix >= 1000) && prefixIndex + 1 < ENERGY_PREFIXES.length) {
            energyWithPrefix /= 1000;

            prefixIndex++;
        }

        return String.format(Locale.ENGLISH, "%s%s FE",
                String.format(Locale.ENGLISH, "%.2f", Math.floor(energyWithPrefix * 100) / 100),
                ENERGY_PREFIXES[prefixIndex]);
    }

    public static int getRedstoneSignalFromEnergyStorage(IEnergyStorage energyStorage) {
        boolean isEmptyFlag = energyStorage.getEnergyStored() == 0;

        return Math.min(Mth.floor((float) energyStorage.getEnergyStored() / energyStorage.getMaxEnergyStored() * 14.f) + (isEmptyFlag ? 0 : 1), 15);
    }

    public static boolean move(BlockPos from, BlockPos to, int amount, Level level) {
        IEnergyStorage fromStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, from, null);
        IEnergyStorage toStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, to, null);
        assert fromStorage != null;
        if (canEnergyStorageExtractThisAmount(fromStorage, amount)) {
            return false;
        }

        assert toStorage != null;
        if (canEnergyStorageStillReceiveEnergy(toStorage)) {
            return false;
        }

        int maxAmountToReceive = toStorage.receiveEnergy(amount, true);

        int extractedEnergy = fromStorage.extractEnergy(maxAmountToReceive, false);
        toStorage.receiveEnergy(extractedEnergy, false);

        return true;
    }

    private static boolean canEnergyStorageStillReceiveEnergy(IEnergyStorage toStorage) {
        return toStorage.getEnergyStored() >= toStorage.getMaxEnergyStored() || !toStorage.canReceive();
    }

    private static boolean canEnergyStorageExtractThisAmount(IEnergyStorage fromStorage, int amount) {
        return fromStorage.getEnergyStored() <= 0 || fromStorage.getEnergyStored() < amount || !fromStorage.canExtract();
    }

    public static boolean doesBlockHaveEnergyStorage(BlockPos positionToCheck, Level level) {
        return level.getBlockEntity(positionToCheck) != null
                && level.getCapability(Capabilities.EnergyStorage.BLOCK, positionToCheck, null) != null;
    }
}
