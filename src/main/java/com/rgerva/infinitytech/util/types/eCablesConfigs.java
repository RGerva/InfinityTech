/**
 * Class: eCablesConfigs
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util.types;

import com.rgerva.infinitytech.energy.ModEnergyUtils;
import com.rgerva.infinitytech.fluid.ModFluidUtils;

public enum eCablesConfigs {
    TIN(new CableCapacities(128, 128, 128)),
    COPPER(new CableCapacities(256, 1024, 1024)),
    GOLD(new CableCapacities(512, 16384, 16384));

    public record CableCapacities(int item, int fluid, int energy) {}

    private CableCapacities capacities;

    eCablesConfigs(CableCapacities capacities) {
        this.capacities = capacities;
    }

    public CableCapacities getCapacities() {
        return capacities;
    }

    public int getMaxTransfer(iStatus status) {
        return switch (status){
            case ITEM -> capacities.item();
            case FLUID -> capacities.fluid();
            case ENERGY -> capacities.energy();
        };
    };

    public void setCapacities(int itemCapacity, int fluidCapacity, int energyCapacity) {
        this.capacities = new CableCapacities(itemCapacity, fluidCapacity, energyCapacity);
    }

    private ActiveCableCapacities setCapacities(boolean energy, boolean fluid, boolean item) {
        return new ActiveCableCapacities(
                item, capacities.item(),
                fluid, capacities.fluid(),
                energy, capacities.energy()
        );
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (iStatus status : iStatus.values()) {
            int transferRate = getMaxTransfer(status);
            String unit = switch (status) {
                case ITEM -> "Stack per tick";
                case FLUID -> ModFluidUtils.getFluidWithPrefix(transferRate) + "/s";
                case ENERGY -> ModEnergyUtils.getEnergyWithPrefix(transferRate) + "/s";
            };
            result.append(name()).append(" [Type: ").append(status)
                    .append(", Ratio: ").append(transferRate).append(" ")
                    .append(unit).append("]\n");
        }
        return result.toString();
    }

    public ActiveCableCapacities onlyEnergy() {
        return setCapacities(true, false, false);
    }

    public ActiveCableCapacities onlyFluid() {
        return setCapacities(false, true, false);
    }

    public ActiveCableCapacities onlyItem() {
        return setCapacities(false, false, true);
    }

    public static class ActiveCableCapacities {

        private final boolean hasItem;
        private final boolean hasFluid;
        private final boolean hasEnergy;

        private final int item;
        private final int fluid;
        private final int energy;

        public ActiveCableCapacities(boolean hasItem, int item, boolean hasFluid, int fluid, boolean hasEnergy, int energy) {
            this.hasItem = hasItem;
            this.item = hasItem ? item : 0;

            this.hasFluid = hasFluid;
            this.fluid = hasFluid ? fluid : 0;

            this.hasEnergy = hasEnergy;
            this.energy = hasEnergy ? energy : 0;
        }

        public boolean isItemEnabled() {
            return hasItem;
        }

        public boolean isFluidEnabled() {
            return hasFluid;
        }

        public boolean isEnergyEnabled() {
            return hasEnergy;
        }

        public int getItem() {
            if (!hasItem) throw new IllegalStateException("Item capacity not enabled.");
            return item;
        }

        public int getFluid() {
            if (!hasFluid) throw new IllegalStateException("Fluid capacity not enabled.");
            return fluid;
        }

        public int getEnergy() {
            if (!hasEnergy) throw new IllegalStateException("Energy capacity not enabled.");
            return energy;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("ActiveCableCapacities [");

            if (hasItem) {
                sb.append("Item: ").append(item).append(" Stack/tick, ");
            }
            if (hasFluid) {
                sb.append("Fluid: ").append(ModFluidUtils.getFluidWithPrefix(fluid)).append("/s, ");
            }
            if (hasEnergy) {
                sb.append("Energy: ").append(ModEnergyUtils.getEnergyWithPrefix(energy)).append("/s, ");
            }

            if (sb.lastIndexOf(", ") == sb.length() - 2) {
                sb.delete(sb.length() - 2, sb.length());
            }

            sb.append("]");
            return sb.toString();
        }
    }

    public enum iStatus {
        ITEM, FLUID, ENERGY;
    }
}
