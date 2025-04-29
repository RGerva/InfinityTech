/**
 * Class: ModChestEntity
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity.custom.chest;

import com.rgerva.infinitytech.block.custom.chest.ModChestBlock;
import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.gui.ModGUI;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class ModChestEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private final eChestConfigs chestConfigs;
    private NonNullList<ItemStack> itemStacks;
    private final ChestLidController chestLidController = new ChestLidController();
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {

        @Override
        protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
            ModChestEntity.playSound(level, blockPos, blockState, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
            ModChestEntity.playSound(level, blockPos, blockState, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int i1) {
            ModChestEntity.this.signalOpenCount(level, blockPos, blockState, i, i1);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if(!(player.containerMenu instanceof ModChestMenu)){
                return false;
            }else{
                Container container = ((ModChestMenu) player.containerMenu).getContainer();
                return container instanceof ModChestEntity || container instanceof CompoundContainer && ((CompoundContainer) container).contains(ModChestEntity.this);
            }
        }
    };

    public ModChestEntity(BlockPos pos, BlockState blockState, eChestConfigs eChestConfigs) {
        super(Objects.requireNonNull(getEntityType(eChestConfigs)), pos, blockState);
        this.chestConfigs = eChestConfigs;
        this.itemStacks = NonNullList.withSize(eChestConfigs.size, ItemStack.EMPTY);
    }

    public static BlockEntityType<ModChestEntity> getEntityType(eChestConfigs eChestConfigs){
        return switch (eChestConfigs){
            case IRON -> ModBlockEntities.CHEST_IRON_ENTITY.get();
            case COPPER -> ModBlockEntities.CHEST_COPPER_ENTITY.get();
            case GOLD -> ModBlockEntities.CHEST_GOLD_ENTITY.get();
            case DIAMOND -> ModBlockEntities.CHEST_DIAMOND_ENTITY.get();
            case OBSIDIAN -> ModBlockEntities.CHEST_OBSIDIAN_ENTITY.get();
            case NETHERITE -> ModBlockEntities.CHEST_NETHERITE_ENTITY.get();
            case WOOD -> null;
        };
    }

    public BlockEntity getBlockEntity(Inventory inventory){
        BlockEntity blockEntity = inventory.player.level().getBlockEntity(this.getBlockPos());
        return blockEntity;
    }

    public eChestConfigs getChestConfigs(){
        eChestConfigs type = chestConfigs;
        if(this.hasLevel()){
            eChestConfigs typeFromBlock = ModChestBlock.getTypeFromBlock(this.getBlockState().getBlock());
            if(typeFromBlock != null){
                type = typeFromBlock;
            }
        }
        return type;
    }

    @Override
    public Component getDefaultName() {
        return Component.translatable("block.infinity_tech.chest_" + getChestConfigs().name().toLowerCase());
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return itemStacks;
    }

    @Override
    public void setItems(NonNullList<ItemStack> nonNullList) {
        itemStacks = NonNullList.withSize(getChestConfigs().size, ItemStack.EMPTY);

        for(int i = 0; i < nonNullList.size(); i++){
            if(i < itemStacks.size()){
                getItems().set(i, nonNullList.get(i));
            }
        }
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return switch (getChestConfigs()){
            case IRON -> new ModChestMenu(ModGUI.CHEST_IRON_MENU.get(), i, inventory, this, eChestConfigs.IRON);
            case COPPER -> new ModChestMenu(ModGUI.CHEST_COPPER_MENU.get(), i, inventory, this, eChestConfigs.COPPER);
            case GOLD -> new ModChestMenu(ModGUI.CHEST_GOLD_MENU.get(), i, inventory, this, eChestConfigs.GOLD);
            case DIAMOND -> new ModChestMenu(ModGUI.CHEST_DIAMOND_MENU.get(), i, inventory, this, eChestConfigs.DIAMOND);
            case OBSIDIAN -> new ModChestMenu(ModGUI.CHEST_OBSIDIAN_MENU.get(), i, inventory, this, eChestConfigs.OBSIDIAN);
            case NETHERITE -> new ModChestMenu(ModGUI.CHEST_NETHERITE_MENU.get(), i, inventory, this, eChestConfigs.NETHERITE);
            case WOOD -> null;
        };
    }

    @Override
    public int getContainerSize() {
        return getItems().size();
    }

    @Override
    public float getOpenNess(float v) {
        return chestLidController.getOpenness(v);
    }

    private static void playSound(Level level, BlockPos blockPos, BlockState blockState, SoundEvent soundEvent) {
        double d0 = (double) blockPos.getX() + 0.5;
        double d1 = (double) blockPos.getY() + 0.5;
        double d2 = (double) blockPos.getZ() + 0.5;

        level.playSound(null, d0, d1, d2, soundEvent, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
    }

    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int previousCount, int newCount) {
        Block block = blockState.getBlock();
        level.blockEvent(blockPos, block, 1, newCount);
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);

        itemStacks = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if(!tryLoadLootTable(compoundTag)){
            ContainerHelper.loadAllItems(compoundTag, itemStacks, provider);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);

        if(!trySaveLootTable(compoundTag)){
            ContainerHelper.saveAllItems(compoundTag, itemStacks, provider);
        }
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id  == 1){
            chestLidController.shouldBeOpen(type > 0);
            return true;
        }else{
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(Player player) {
        if(!this.remove && !player.isSpectator()){
            openersCounter.incrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if(!this.remove && !player.isSpectator()){
            openersCounter.decrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    public static int getOpenCount(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockState = blockGetter.getBlockState(blockPos);

        if(blockState.hasBlockEntity()){
            BlockEntity blockEntity = blockGetter.getBlockEntity(blockPos);
            if(blockEntity instanceof ModChestEntity){
                return ((ModChestEntity) blockEntity).openersCounter.getOpenerCount();
            }
        }
        return 0;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ModChestEntity chestEntity){
        chestEntity.chestLidController.tickLid();
    }
}
