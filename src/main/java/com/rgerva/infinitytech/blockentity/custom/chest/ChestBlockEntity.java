package com.rgerva.infinitytech.blockentity.custom.chest;

import com.rgerva.infinitytech.block.custom.chest.ChestBlock;
import com.rgerva.infinitytech.block.custom.chest.IronChestBlock;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public abstract class ChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private final eChestConfigs eChestConfigs;
    private NonNullList<ItemStack> itemStacks;
    private final ChestLidController chestLidController = new ChestLidController();

    protected ChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, eChestConfigs eChestConfigs) {
        super(type, pos, blockState);
        this.eChestConfigs = eChestConfigs;
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("TESTE");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.itemStacks = NonNullList.withSize(this.geteChestConfigs().size, ItemStack.EMPTY);

        for(int i = 0; i < nonNullList.size(); i++){
            if(i < this.itemStacks.size()){
                this.getItems().set(i, nonNullList.get(i));
            }
        }
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return this.getItems().size();
    }

    @Override
    public float getOpenNess(float v) {
        return this.chestLidController.getOpenness(v);
    }

    public eChestConfigs geteChestConfigs(){
        eChestConfigs type = eChestConfigs.IRON;

        if(this.hasLevel()){
            eChestConfigs typeFromBlock = ChestBlock.getTypeFromBlock(this.getBlockState().getBlock());
            if(typeFromBlock != null){
                type = typeFromBlock;
            }
        }
        return type;
    }

}
