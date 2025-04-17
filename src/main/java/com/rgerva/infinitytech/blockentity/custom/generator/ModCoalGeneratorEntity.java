/**
 * Class: CoalGeneratorBlockEntity
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity.custom.generator;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.energy.ModEnergyStorage;
import com.rgerva.infinitytech.energy.ModEnergyUtils;
import com.rgerva.infinitytech.gui.menu.ModCoalGeneratorMenu;
import com.rgerva.infinitytech.network.interfaces.ModSyncPackages;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ModCoalGeneratorEntity extends BlockEntity implements MenuProvider, ModSyncPackages {

    private static final int INPUT_SLOT = 0;
    private boolean isBurning = false;
    private int burnProgress = 160;
    private int maxBurnProgress = 160;
    protected final ContainerData data;

    public final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();

    private ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(64000, 320, 320, 0) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                syncEnergyToPlayers(32);
                Objects.requireNonNull(getLevel()).sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    public ModCoalGeneratorEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COAL_GENERATOR_ENTITY.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> ModCoalGeneratorEntity.this.burnProgress;
                    case 1 -> ModCoalGeneratorEntity.this.maxBurnProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0 -> ModCoalGeneratorEntity.this.burnProgress = i1;
                    case 1 -> ModCoalGeneratorEntity.this.maxBurnProgress = i1;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public IEnergyStorage getEnergyStorageCapability(Direction direction) {
        return this.ENERGY_STORAGE;
    }

    public int getEnergy() {
        return ENERGY_STORAGE.getEnergy();
    }

    public int getCapacity() {
        return ENERGY_STORAGE.getCapacity();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.infinity_tech.coal_generator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        syncEnergyToPlayer(player);
        return new ModCoalGeneratorMenu(i, inventory, this, this.data);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ModCoalGeneratorEntity modCoalGeneratorEntity) {
        if (modCoalGeneratorEntity.itemHandler.getStackInSlot(INPUT_SLOT).is(ItemTags.COALS)) {
            if (!modCoalGeneratorEntity.isBurning && modCoalGeneratorEntity.ENERGY_STORAGE.getEnergy() != modCoalGeneratorEntity.getCapacity()) {
                modCoalGeneratorEntity.itemHandler.extractItem(INPUT_SLOT, 1, false);
                modCoalGeneratorEntity.isBurning = true;
            }
        }

        if (modCoalGeneratorEntity.isBurning) {
            modCoalGeneratorEntity.burnProgress--;
            if (modCoalGeneratorEntity.burnProgress < 0) {
                modCoalGeneratorEntity.isBurning = false;
                modCoalGeneratorEntity.burnProgress = modCoalGeneratorEntity.maxBurnProgress;
            }
            modCoalGeneratorEntity.ENERGY_STORAGE.receiveEnergy(320, false);
        }

        if (ModEnergyUtils.doesBlockHaveEnergyStorage(modCoalGeneratorEntity.worldPosition.above(), level)) {
            ModEnergyUtils.move(modCoalGeneratorEntity.worldPosition, modCoalGeneratorEntity.worldPosition.above(), 320, level);
        }
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("coal_generator.inventory", itemHandler.serializeNBT(registries));
        tag.putInt("coal_generator.burn_progress", burnProgress);
        tag.putInt("coal_generator.max_burn_progress", maxBurnProgress);

        tag.putInt("coal_generator.energy", ENERGY_STORAGE.getEnergyStored());
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        itemHandler.deserializeNBT(registries, tag.getCompound("coal_generator.inventory"));
        ENERGY_STORAGE.setEnergy(tag.getInt("coal_generator.energy"));

        burnProgress = tag.getInt("coal_generator.burn_progress");
        maxBurnProgress = tag.getInt("coal_generator.max_burn_progress");
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
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
}
