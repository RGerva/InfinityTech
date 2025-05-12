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
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModFurnaceMenu extends AbstractContainerMenu {
    private final ContainerData data;
    private final Level level;
    private final ModFurnaceEntity blockEntity;
    private final SimpleContainer container;
    private final ResourceKey<RecipePropertySet> resourceKey;

    public ModFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf buffer) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(buffer.readBlockPos()), new SimpleContainerData(5));
    }

    public ModFurnaceMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(ModGUI.FURNACE_MENU.get(), containerId);
        this.data = data;
        this.level = inventory.player.level();
        this.blockEntity = (ModFurnaceEntity) blockEntity;
        this.container = new SimpleContainer(this.blockEntity.itemHandler.getSlots());
        this.resourceKey = RecipePropertySet.SMITHING_BASE;

        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 0, 56, 17));
        //this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 1, 56, 53));
        this.addSlot(new FurnaceFuelSlot(new AbstractFurnaceMenu(ModGUI.FURNACE_MENU.get(), this.blockEntity.recipeType, resourceKey, RecipeBookType.FURNACE, containerId, inventory) {
            @Override
            public void fillCraftSlotsStackedContents(@NotNull StackedItemContents stackedItemContents) {
                SimpleContainer simpleContainer = new SimpleContainer(((ModFurnaceEntity) blockEntity).itemHandler.getSlots());
                for(int i = 0; i < ((ModFurnaceEntity) blockEntity).itemHandler.getSlots(); i++){
                    stackedItemContents.accountStack(simpleContainer.getItem(i));
                }
            }
        }, this.container, 1, 56, 53));

        this.addSlot(new FurnaceResultSlot(inventory.player, container, 2, 116, 35));

        addDataSlots(data);

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++) {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++) {
                this.addSlot(new Slot(inventory, playerInvCol + playerInvRow * 9 + 9, 8 + playerInvCol * 18, 84 + playerInvRow * 18));
            }
        }

        for (int hotHarSlot = 0; hotHarSlot < 9; hotHarSlot++) {
            this.addSlot(new Slot(inventory, hotHarSlot, 8 + hotHarSlot * 18, 142));
        }
    }



    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = slots.get(pIndex);

        if(slot.hasItem()){
            ItemStack stack = slot.getItem();
            itemStack = stack.copy();
            int inventorySize = this.container.getContainerSize();
            if(pIndex < inventorySize){
                if (!this.moveItemStackTo(stack, inventorySize, inventorySize + 36, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemStack);
                if(this.blockEntity instanceof ModFurnaceEntity entity && !this.level.isClientSide){
                    entity.unlockRecipes((ServerPlayer) playerIn);
                }

            } else if (!this.moveItemStackTo(stack, 0, inventorySize, false)) {
                if (pIndex < inventorySize + 27) {
                    if (!this.moveItemStackTo(stack, inventorySize + 27, inventorySize + 36, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (pIndex < inventorySize + 36) {
                    if (!this.moveItemStackTo(stack, inventorySize, inventorySize + 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                return ItemStack.EMPTY;
            }

            if(stack.isEmpty()){
                slot.set(ItemStack.EMPTY);
            }else {
                slot.setChanged();
            }

            if(stack.getCount() == itemStack.getCount()){
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }
        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModFurnaceBlock.getBlockType(blockEntity.getFurnaceConfig()));
    }

}
