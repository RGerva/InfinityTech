/**
 * Class: ModFurnaceMenu
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.gui.menu;

import com.rgerva.infinitytech.block.custom.furnace.ModFurnaceBlock;
import com.rgerva.infinitytech.blockentity.custom.furnace.ModFurnaceEntity;
import com.rgerva.infinitytech.gui.ModGUI;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModFurnaceMenu extends AbstractContainerMenu {
    private final ContainerData data;
    private final Level level;
    private final ModFurnaceEntity blockEntity;
    private final SimpleContainer container;

    public ModFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf buffer) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(buffer.readBlockPos()), new SimpleContainerData(5));
    }

    public ModFurnaceMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(ModGUI.FURNACE_MENU.get(), containerId);
        this.data = data;
        this.level = inventory.player.level();
        this.blockEntity = (ModFurnaceEntity) blockEntity;
        this.container = new SimpleContainer(this.blockEntity.itemHandler.getSlots());

        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 0, 56, 17));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 1, 56, 53));
        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 2, 116, 35));

        addFurnaceData();

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(inventory, playerInvCol + playerInvRow * 9 + 9, 8 + playerInvCol * 18, 84 + playerInvRow * 18));
            }
        }

        for (int hotHarSlot = 0; hotHarSlot < 9; hotHarSlot++) {
            this.addSlot(new Slot(inventory, hotHarSlot, 8 + hotHarSlot * 18, 142));
        }
    }

    public void addFurnaceData(){

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity.furnaceBurnTime;
            }

            @Override
            public void set(int value) {
                blockEntity.furnaceBurnTime = value;
            }
        });

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity.recipesUsed;
            }

            @Override
            public void set(int value) {
                int add = blockEntity.recipesUsed;
                blockEntity.recipesUsed = value;
            }
        });

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity.cookTime;
            }

            @Override
            public void set(int value) {
                blockEntity.cookTime = value;
            }
        });

        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity.totalCookTime;
            }

            @Override
            public void set(int value) {
                blockEntity.totalCookTime = value;
            }
        });
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int pIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);

        if (slot.hasItem() && !this.level.isClientSide) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();

            int inventorySize = this.container.getContainerSize();
            if (pIndex < inventorySize) {
                if (!this.moveItemStackTo(itemStack1, inventorySize, inventorySize + 36, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemStack1, itemStack);
                if(playerIn instanceof ServerPlayer player){
                    this.blockEntity.unlockRecipes(player);
                }

            } else if (pIndex != 1 && pIndex != 0) {
                if (this.blockEntity.hasRecipe(itemStack1)) {
                    if (!this.moveItemStackTo(itemStack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (ModFurnaceEntity.isItemFuel(itemStack1, RecipeType.SMELTING)) {
                    if (!this.moveItemStackTo(itemStack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemStack1, 0, inventorySize, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemStack1);

        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModFurnaceBlock.getBlockType(blockEntity.getFurnaceConfig()));
    }

    public int getCookScaled(int pixels) {
        int i = this.blockEntity.cookTime;
        int j = this.blockEntity.totalCookTime;
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    public int getBurnLeftScaled(int pixels) {
        int i = this.blockEntity.recipesUsed;
        if (i == 0) {
            i = 200;
        }

        return this.blockEntity.furnaceBurnTime * pixels / i;
    }

    public boolean isBurning() {
        return this.blockEntity.isBurning();
    }
}
