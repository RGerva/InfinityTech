package com.rgerva.infinitytech.blockentity.custom.chest;

import com.rgerva.infinitytech.block.custom.chest.ModChestBlock;
import com.rgerva.infinitytech.gui.menu.ModChestMenu;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ModChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private final eChestConfigs eChestConfigs;
    private NonNullList<ItemStack> itemStacks;
    private final ChestLidController chestLidController = new ChestLidController();
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {

        @Override
        protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
            ModChestBlockEntity.playSound(level, blockPos, blockState, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
            ModChestBlockEntity.playSound(level, blockPos, blockState, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int i1) {
            ModChestBlockEntity.this.signalOpenCount(level, blockPos, blockState, i, i1);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof ModChestMenu)) {
                return false;
            } else {
                Container container = ((ModChestMenu) player.containerMenu).getContainer();
                return container instanceof ModChestBlockEntity || container instanceof CompoundContainer && ((CompoundContainer) container).contains(ModChestBlockEntity.this);
            }
        }
    };


    protected ModChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, eChestConfigs eChestConfigs) {
        super(type, pos, blockState);
        this.itemStacks = NonNullList.withSize(eChestConfigs.size, ItemStack.EMPTY);
        this.eChestConfigs = eChestConfigs;
    }

    @Override
    public int getContainerSize() {
        return this.getItems().size();
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.itemStacks, registries);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.itemStacks, registries);
        }
    }

    public static void lidAnimateTick(Level level, BlockPos blockPos, BlockState blockState, ModChestBlockEntity chestBlockEntity) {
        chestBlockEntity.chestLidController.tickLid();
    }

    static void playSound(Level level, BlockPos blockPos, BlockState blockState, SoundEvent soundEvent) {
        double d0 = (double) blockPos.getX() + 0.5;
        double d1 = (double) blockPos.getY() + 0.5;
        double d2 = (double) blockPos.getZ() + 0.5;

        level.playSound(null, d0, d1, d2, soundEvent, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.chestLidController.shouldBeOpen(type > 0);
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.itemStacks = NonNullList.withSize(this.geteChestConfigs().size, ItemStack.EMPTY);

        for (int i = 0; i < nonNullList.size(); i++) {
            if (i < this.itemStacks.size()) {
                this.getItems().set(i, nonNullList.get(i));
            }
        }
    }

    @Override
    public float getOpenNess(float v) {
        return this.chestLidController.getOpenness(v);
    }

    public static int getOpenCount(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockstate = blockGetter.getBlockState(blockPos);
        if (blockstate.hasBlockEntity()) {
            BlockEntity blockentity = blockGetter.getBlockEntity(blockPos);
            if (blockentity instanceof ModChestBlockEntity) {
                return ((ModChestBlockEntity) blockentity).openersCounter.getOpenerCount();
            }
        }
        return 0;
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int previousCount, int newCount) {
        Block block = blockState.getBlock();
        level.blockEvent(blockPos, block, 1, newCount);
    }

    public void removeAdornments() {

    }

    public eChestConfigs geteChestConfigs() {
        eChestConfigs type = com.rgerva.infinitytech.util.types.eChestConfigs.IRON;

        if (this.hasLevel()) {
            eChestConfigs typeFromBlock = ModChestBlock.getTypeFromBlock(this.getBlockState().getBlock());
            if (typeFromBlock != null) {
                type = typeFromBlock;
            }
        }
        return type;
    }
}
