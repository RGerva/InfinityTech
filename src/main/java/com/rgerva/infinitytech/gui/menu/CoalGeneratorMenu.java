/**
 * Class: CoalGeneratorMenu
 * Created by: DRIB934
 * On: 2025/mar.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.gui.menu;

import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.blockentity.custom.generator.CoalGeneratorBlockEntity;
import com.rgerva.infinitytech.gui.ModGUI;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class CoalGeneratorMenu extends AbstractContainerMenu {
    private final ContainerData data;
    private final Level level;
    public final CoalGeneratorBlockEntity blockEntity;

    public CoalGeneratorMenu(int pContainerId, Inventory inv, FriendlyByteBuf buffer) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(buffer.readBlockPos()), new SimpleContainerData(2));
    }

    public CoalGeneratorMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(ModGUI.COAL_GENERATOR_MENU.get(), containerId);
        this.data = data;
        this.level = inventory.player.level();
        this.blockEntity = ((CoalGeneratorBlockEntity) blockEntity);

        this.addSlot(new SlotItemHandler(this.blockEntity.itemHandler, 0, 80, 35));
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

    private static final int TE_INVENTORY_SLOT_COUNT = 1;
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()){
            return ItemStack.EMPTY;
        }
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (pIndex < 36) {
            if (!moveItemStackTo(sourceStack, 36, 36 + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        }else if (pIndex < 36 + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, 0, 36, false)) {
                return ItemStack.EMPTY;
            }
        }else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.COAL_GENERATOR.get());
    }

    public boolean isBurning() {
        return data.get(0) < 160;
    }

    public float getFuelProgress() {
        int i = this.data.get(1);
        if(i == 0){
            i = 160;
        }
        return Mth.clamp((float) this.data.get(0) / (float) i, 0.0F, 1.0F);
    }
}
