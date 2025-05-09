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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ModFurnaceMenu extends AbstractContainerMenu {
    private final ContainerData data;
    private final Level level;
    private final ModFurnaceEntity blockEntity;

    public ModFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf buffer) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(buffer.readBlockPos()), new SimpleContainerData(5));
    }

    public ModFurnaceMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(ModGUI.FURNACE_MENU.get(), containerId);
        this.data = data;
        this.level = inventory.player.level();
        this.blockEntity = (ModFurnaceEntity) blockEntity;

//        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 0, 56, 17));
//        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 1, 56, 53));
        //this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 2, 116, 35));
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

        return itemStack;
//        if(slot.hasItem()){
//           ItemStack itemStack1 = slot.getItem();
//           itemStack = itemStack1.copy();
//
//           if(pIndex == 2){
//               if(!this.moveItemStackTo(itemStack1, 19,55,true)){
//                   return ItemStack.EMPTY;
//               }
//               slot.onQuickCraft(itemStack1, itemStack);
//           } else if (pIndex != 1 && pIndex != 0 && pIndex != 3 && pIndex != 4 && pIndex != 5) {
//               if(this.blockEntity.hasRecipe(itemStack1)){
//                   if(!this.moveItemStackTo(itemStack1, 0,1, false)){
//                       return ItemStack.EMPTY;
//                   }
//               } else if (pIndex >= 19 && pIndex <= 45) {
//                   if(!this.moveItemStackTo(itemStack1, 46,55, false)){
//                       return ItemStack.EMPTY;
//                   }
//               } else if (pIndex >= 45 && pIndex < 55 && !this.moveItemStackTo(itemStack1, 19, 46, false)) {
//                   return ItemStack.EMPTY;
//               }
//           } else if (!this.moveItemStackTo(itemStack1, 19,55, false)) {
//               return ItemStack.EMPTY;
//           }
//
//           if(itemStack1.isEmpty()){
//               slot.set(ItemStack.EMPTY);
//           }else {
//               slot.setChanged();
//           }
//
//           if(itemStack1.getCount() == itemStack.getCount()){
//               return ItemStack.EMPTY;
//           }
//
//           slot.onTake(playerIn, itemStack1);
//        }
//
//        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModFurnaceBlock.getBlockType(blockEntity.getFurnaceConfig()));
    }

    public boolean isBurning() {
        return data.get(0) < 160;
    }

    public float getFuelProgress() {
        int i = this.data.get(1);
        if (i == 0) {
            i = 160;
        }
        return Mth.clamp((float) this.data.get(0) / (float) i, 0.0F, 1.0F);
    }
}
