package com.rgerva.infinitytech.blockentity.custom;

import com.rgerva.infinitytech.block.custom.SolarPanelBlock;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.base.MenuEnergyStorageBlockEntity;
import com.rgerva.infinitytech.energy.ExtractOnlyEnergyStorage;
import com.rgerva.infinitytech.gui.menu.SolarPanelMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

public class SolarPanelBlockEntity extends MenuEnergyStorageBlockEntity<ExtractOnlyEnergyStorage> {
    public SolarPanelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.SOLAR_PANEL.get(), blockPos, blockState, "solar_panel", 128 * 20 * 2, 128 * 4);
    }

    @Override
    protected ExtractOnlyEnergyStorage initEnergyStorage() {
        return new ExtractOnlyEnergyStorage(0, baseEnergyCapacity, baseEnergyTransferRate) {
            @Override
            public int getCapacity() {
                return Math.max(1, capacity);
            }

            @Override
            public int getMaxExtract() {
                return Math.max(1, maxExtract);
            }

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
        return new SolarPanelMenu(i, inventory, this);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, SolarPanelBlockEntity blockEntity) {
        if(level.isClientSide)
            return;

        int i = 4 * (level.getBrightness(LightLayer.SKY, blockPos) - level.getSkyDarken()); //(0 - 15) * 4 => (0 - 60)
        float f = level.getSunAngle(1.0F);
        if(i > 0) {
            float f1 = f < (float)Math.PI ? 0.0F : ((float)Math.PI * 2F);

            f += (f1 - f) * 0.2F;

            i = Math.round((float)i * Mth.cos(f));
        }

        i = Mth.clamp(i, 0, 60);

        int energyProduction = (int)(i/60.f * SolarPanelBlock.getPeakFePerTick());

        blockEntity.energyStorage.setEnergy(Math.min(blockEntity.energyStorage.getCapacity(),
                blockEntity.energyStorage.getEnergy() + energyProduction));

        transferEnergy(level, blockPos, state, blockEntity);
    }

    private static void transferEnergy(Level level, BlockPos blockPos, BlockState state, SolarPanelBlockEntity blockEntity) {
        if(level.isClientSide)
            return;

        BlockPos testPos = blockPos.relative(Direction.DOWN);

        BlockEntity testBlockEntity = level.getBlockEntity(testPos);

        IEnergyStorage energyStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, testPos,
                level.getBlockState(testPos), testBlockEntity, Direction.DOWN.getOpposite());
        if(energyStorage == null || !energyStorage.canReceive())
            return;

        int amount = energyStorage.receiveEnergy(Math.min(blockEntity.energyStorage.getEnergy(), blockEntity.energyStorage.getMaxExtract()), false);
        if(amount > 0)
            blockEntity.energyStorage.extractEnergy(amount, false);
    }

    public @Nullable IEnergyStorage getEnergyStorageCapability(@Nullable Direction side) {
        if(side == null || side == Direction.DOWN)
            return energyStorage;

        return null;
    }
}
